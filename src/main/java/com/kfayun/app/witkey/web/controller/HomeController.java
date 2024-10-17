/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.math.BigDecimal;

import com.kfayun.app.witkey.Constants;
import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.model.*;
import com.kfayun.app.witkey.service.*;
import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.aop.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HomeController
 * 
 * @author Billy zhang (billy_zh@126.com)
 */
@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private Producer captchaProducer;
	@Autowired
	private UserService userService;
	@Autowired
	private VerifyService verifyService;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 健康状态
	 */
	@GetMapping("/health")
	@ResponseBody
	public String health() {
		return "UP";
	}

	/**
	 * 搜索
	 * 
	 * @param params 表单数据
	 */
	@RequestMapping("/search")
	public void search(@RequestParam Map<String, String> params,
			HttpServletResponse response) throws IOException {
		String cate = params.get("cate");
		String keywords = params.get("keywords");

		if ("找设计师".equals(cate)) {
			response.sendRedirect("/niuren/designer?wd=" + URLEncoder.encode(keywords, "utf-8"));
		} else if ("找设计团队".equals(cate)) {
			response.sendRedirect("/niuren/team?wd=" + URLEncoder.encode(keywords, "utf-8"));
		} else if ("抢市场标".equals(cate)) {
			response.sendRedirect("/task/index2?wd=" + URLEncoder.encode(keywords, "utf-8"));
		} else if ("抢管家标".equals(cate)) {
			response.sendRedirect("/task/index?wd=" + URLEncoder.encode(keywords, "utf-8"));
		} else {
			response.sendRedirect("/");
		}
	}

	/**
	 * 用户登录页
	 */
	@GetMapping("login")
    public ModelAndView login(){

		ModelAndView mv = new ModelAndView();

		mv.setViewName("login");
		return mv;
    }

	/**
	 * 用户登录处理
	 * 
	 * @param params 表单数据
	 */
	@UserOperate(UserAction.USER_LOGIN)
	@PostMapping("login")
	@ResponseBody
    public JsonResult<String> loginPost(
			@RequestParam Map<String, String> params,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			String mobile = params.get("mobile");
			User user = userService.getUserByMobile(mobile);
			if (user == null) {
				return JsonResult.fail(-1, "用户不存在.");
			}

			String passwd = params.get("password");
			String encPasswd = CryptoUtil.MD5(CryptoUtil.MD5(passwd) + user.getSalt());
			if (!user.getPasswd().equalsIgnoreCase(encPasswd)) {
				return JsonResult.fail(-2, "密码错误.");
			}

			String remember = params.get("remember");
			int remDay = 0;
			if (!StrUtil.isEmpty(remember)) {
				remDay = Integer.parseInt(remember);
			}

			Settings settings = (Settings)request.getAttribute("settings");
			WebUtil.writeCookie(response, 
				getCookieDomain(settings.getCookieDomain()), 
				"",
				settings.getCookieName(), 
				CryptoUtil.encryptDES(settings.getCookieSecret(), ""+user.getId()),
				remDay);
			
			user.setLastLogin(new Date());
			userService.updateUser(user);
				
			String url = "/";
			if (user.getClazz() == 1)
				url = "/user/designer/";
			else if (user.getClazz() == 2)
				url = "/user/employer/";
				
			return JsonResult.ok(url);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
    }

	/**
	 * 用户退出登录处理
	 */
	@RequestMapping("logout")
    public void logout(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Settings settings = (Settings)request.getAttribute("settings");
		WebUtil.deleteCookie(
			response, 
			getCookieDomain(settings.getCookieDomain()), 
			"", 
			settings.getCookieName());
		response.sendRedirect(config.getContextPath() + "/index");
    }

	private String getCookieDomain(String domain) {
		if (config.getProfile().equalsIgnoreCase("prod")) {
			return domain;
		}
		return ""; // use localhost or ip.
	}

	/**
	 * 用户注册页
	 */
	@GetMapping("reg")
    public ModelAndView reg(){

		ModelAndView mv = new ModelAndView();

		mv.setViewName("reg");
		return mv;
    }

	/**
	 * 用户注册页
	 */
	@GetMapping("reg2")
    public ModelAndView reg2(
			@RequestParam(value="clazz",required = false, defaultValue = "0")int clazz){

		ModelAndView mv = new ModelAndView();
		if (clazz == 0) {
			mv.setViewName("redirect:reg");
			return mv;
		}

		mv.addObject("clazz", clazz);
		mv.setViewName("reg2");
		return mv;
    }

	/**
	 * 验证码获取
	 * 
	 * @param params 表单数据
	 * @return
	 */
	@UserOperate(UserAction.USER_SENDVALIDCODE)
	@PostMapping("getvalidcode")
	@ResponseBody
	public JsonResult<String> getValidCode(
		@RequestParam Map<String, String> params,
		HttpServletRequest request) {
		try {
			User user = getCurrentUser(request);
			if (user == null) {
				return JsonResult.fail(-1, "无效的请求。");
			}

			String kind = params.get("kind");
				
			if ("withdraw".equalsIgnoreCase(kind)) {
						
				int bankId = getInt(params.get("bankid"), 0);
				BankAccount ba = userService.getBankAccount(bankId, user.getId());
				if (ba == null) {
					return JsonResult.fail(-1, "找不到用户银行帐户。");
				}

				VerifyCode verifyCode = new VerifyCode();
				verifyCode.setSendTo(user.getMobile());
				verifyCode.setType("sms");
				verifyCode.setKind("withdraw");
				verifyCode.setClientIp(WebUtil.getRealIP(request));
				int state = verifyService.sendVerifyCode(verifyCode);
				if (state == 1) {
					String msg = "手机验证码已发送。";
					if (config.getProfile().equals("dev")) {
						msg += "【验证码：666888】";
					}
					return JsonResult.ok(msg);
				} else {
					return JsonResult.fail(-90, "手机验证码发送失败，请检查手机号或联系客服。");
				}

			} else if ("bankadd".equalsIgnoreCase(kind)) {
				
				VerifyCode verifyCode = new VerifyCode();
				verifyCode.setSendTo(user.getMobile());
				verifyCode.setType("sms");
				verifyCode.setKind("bankadd");
				verifyCode.setClientIp(WebUtil.getRealIP(request));
				int state = verifyService.sendVerifyCode(verifyCode);
				if (state == 1) {
					String msg = "手机验证码已发送。";
					if (config.getProfile().equals("dev")) {
						msg += "【验证码：666888】";
					}
					return JsonResult.ok(msg);
				} else {
					return JsonResult.fail(-90, "手机验证码发送失败，请检查手机号或联系客服。");
				}
			
			} else if ("changepwd".equalsIgnoreCase(kind)) {

			}

			return JsonResult.fail(-90, "Unknown kind: " + kind);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}
	
	/**
	 * 验证码获取
	 * 
	 * @param params 表单数据
	 * @return
	 */
	@UserOperate(UserAction.USER_SENDVALIDCODE)
	@PostMapping("getvalidcode2")
	@ResponseBody
	public JsonResult<String> getValidCode2(
		@RequestParam Map<String, String> params,
		HttpServletRequest request) {
		try {
			String kind = params.get("kind");
					
			String mobile = params.get("mobile");
			if (StrUtil.isEmpty(mobile)) {
				return JsonResult.fail(-1, "手机号不能为空。");
			}

			String vcode = params.get("vcode");
			if (vcode.equalsIgnoreCase((String)request.getSession().getAttribute("vcode"))) {
				return JsonResult.fail(-2, "验证码不正确。");
			}

			if ("reg".equalsIgnoreCase(kind)) {
						
				int n = userService.checkUserByMobile(mobile);
				if (n != 0) {
					return JsonResult.fail(-1, "手机号已注册。");
				}

				VerifyCode verifyCode = new VerifyCode();
				verifyCode.setSendTo(mobile);
				verifyCode.setType("sms");
				verifyCode.setKind("reg");
				verifyCode.setClientIp(WebUtil.getRealIP(request));
				int state = verifyService.sendVerifyCode(verifyCode);
				if (state == 1) {
					String msg = "手机验证码已发送。";
					if (config.getProfile().equals("dev")) {
						msg += "【验证码：666888】";
					}
					return JsonResult.ok(msg);
				} else {
					return JsonResult.fail(-90, "手机验证码发送失败，请检查手机号或联系客服。");
				}
							
			} else if ("forget".equalsIgnoreCase(kind)) {
				
				int n = userService.checkUserByMobile(mobile);
				if (n == 0) {
					return JsonResult.fail(-3, "手机号未注册。");
				}

				VerifyCode verifyCode = new VerifyCode();
				verifyCode.setSendTo(mobile);
				verifyCode.setType("sms");
				verifyCode.setKind("forget");
				int state = verifyService.sendVerifyCode(verifyCode);
				if (state == 1) {
					return JsonResult.ok("验证码已发送。");
				} else {
					return JsonResult.fail(-9, "验证码发送失败，请检查手机号或联系客服。");
				}
			}

			return JsonResult.fail(-90, "Unknown kind: " + kind);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 用户注册处理
	 * 
	 * @param params 表单数据
	 * @return
	 */
	@UserOperate(UserAction.USER_REGISTER)
	@PostMapping("reg2")
	@ResponseBody
	public JsonResult<String> reg2Post(
			@RequestParam Map<String, String> params,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String mobile = params.get("mobile");

			int n = userService.checkUserByMobile(mobile);
			if (n != 0) {
				return JsonResult.fail(-1, "手机号已注册。");
			}

			String vcode = params.get("vcode");
			VerifyCode info = verifyService.getVerifyCodeByData(mobile, vcode, "reg");
			if (info == null) {
				return JsonResult.fail(-2, "验证码不正确。");
			}

			String passwd = params.get("password");
			
			User user = new User();
			user.setMobile(mobile);
			user.setSalt(UUID.randomUUID().toString().replace("-", "").substring(8, 16).toLowerCase() );
			user.setPasswd( CryptoUtil.MD5(CryptoUtil.MD5(passwd) + user.getSalt()) );
			user.setClazz( Integer.parseInt(params.get("clazz")) );
			user.setBalance( BigDecimal.ZERO );
			user.setStartingPrice( BigDecimal.ZERO );
			user.setGoodCount( 0 );
			user.setDealCount( 0 );
			user.setRegTime( new Date() );
			user.setLastLogin( new Date() );

			userService.saveUser(user);

			user.setNickname("usr" + user.getId());
			userService.updateUser(user);

			Settings settings = (Settings)request.getAttribute("settings");
			WebUtil.writeCookie(response, 
				getCookieDomain(settings.getCookieDomain()), 
				"",
				settings.getCookieName(), 
				CryptoUtil.encryptDES(settings.getCookieSecret(), ""+user.getId()),
				0);

			String url = "/user/designer/shezhi";
			if (user.getClazz() == 2) {
				url = "/user/employer/shezhi";
			}

			return JsonResult.ok(url);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}
	
	/**
	 * 用户找回密码页
	 */
	@GetMapping("forgetpwd")
    public ModelAndView forgetPwd(){

		ModelAndView mv = new ModelAndView();

		mv.setViewName("forgetpwd");
		return mv;
    }

	/**
	 * 用户找回密码处理
	 * 
	 * @param params 表单数据
	 */
	@UserOperate(UserAction.USER_FORGETPWD)
	@PostMapping("forgetpwd")
	@ResponseBody
	public JsonResult<Integer> forgetPwdPost(
			@RequestParam Map<String, String> params,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String mobile = params.get("mobile");
			User user = userService.getUserByMobile(mobile);
			if (user == null) {
				return JsonResult.fail(-1, "手机号未注册。");
			}

			String data = params.get("vcode");
			VerifyCode code = verifyService.getVerifyCodeByData(mobile, data, "forgetpwd");
			if (code == null) {
				return JsonResult.fail(-2, "验证码不正确。");
			}

			String newPasswd = params.get("password");
			String encPasswd = CryptoUtil.MD5(CryptoUtil.MD5(newPasswd) + user.getSalt());

			int n = userService.updateUserPwd(user.getId(), encPasswd);

			return JsonResult.ok(n);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 加入我们页
	 * 
	 * @return
	 */
	@GetMapping("join")
    public ModelAndView join(){

		ModelAndView mv = new ModelAndView();

		List<Category> cateList = commonService.getCategoryList(0);
		mv.addObject("cateList", cateList);

		// ltQuick1.Text = "" + commonService.GetRequireTotalByDate();
		// ltQuick2.Text = "" + commonService.GetRequireTotalByMonth();

		mv.setViewName("join");
		return mv;
    }

	/**
	 * 需求提交页
	 * @return
	 */
	@GetMapping("requires")
    public ModelAndView requires(){

		ModelAndView mv = new ModelAndView();

        List<Category> cateList = commonService.getCategoryList(0);
		mv.addObject("cateList", cateList);
		
		mv.setViewName("requires");
		return mv;
    }

	/**
	 * 城市数据获取
	 * 
	 * @param cid 省份ID
	 */
	@GetMapping("getcities")
	@ResponseBody
	public JsonResult<List<CityInfo>> getCities(
			@RequestParam("cid")int cid,
			HttpServletRequest request) {
		try {
			CityInfo city = commonService.getCity(cid);
			if (city == null) {
				return JsonResult.fail(-1, "city info not exists.");
			}
			List<CityInfo> list = commonService.getCityListByProvince(city.getProvince());
			return JsonResult.ok(list);
		} catch (Exception ex) {
			handleError(request, ex);
            return JsonResult.fail(Constants.GLOBAL_ERROR_CODE, Constants.GLOBAL_ERROR_MESSAGE);
		}
	}

	/**
	 * 二唯码生成
	 * 
	 * @param url URL
	 * @param width 图片宽度
	 */
	@RequestMapping("/qrcode.png")
	@ResponseBody
	public void makeImage(@RequestParam("url")String url,
			@RequestParam(value="width", required=false, defaultValue="280")int width,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		try {
			//转换字符，避免中文乱码问题
			String content = new String(url.getBytes("UTF-8"),"ISO-8859-1");
			 
	        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
	        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  // 矫错级别
	        hintMap.put(EncodeHintType.MARGIN, 1);
	        
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        //创建比特矩阵(位矩阵)的QR码编码的字符串
	        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, width, hintMap);
	        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
	        int matrixWidth = byteMatrix.getWidth();
	        BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
	        image.createGraphics();
	        Graphics2D graphics = (Graphics2D) image.getGraphics();
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
	        // 使用比特矩阵画并保存图像
	        graphics.setColor(Color.BLACK);
	        for (int i = 0; i < matrixWidth; i++){
	            for (int j = 0; j < matrixWidth; j++){
	                if (byteMatrix.get(i, j)){
	                    graphics.fillRect(i, j, 1, 1);
	                }
	            }
	        }
	        
			ImageIO.write(image,"png", response.getOutputStream());
		} catch (Exception ex) {
			handleError(request, ex);
			response.sendError(HttpServletResponse.SC_NOT_FOUND); 
		}
	}

	/**
	 * 图形验证码生成
	 * 
	 */
	@RequestMapping("captcha") 
	@ResponseBody
    public void captcha(HttpServletRequest request,
    		HttpServletResponse response) throws Exception{ 
      	byte[] captchaChallengeAsJpeg = null; 
       	ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream(); 
       	try { 
			Settings settings = (Settings)request.getAttribute("settings");
	       //生产验证码字符串并保存到session中 
	       String createText = captchaProducer.createText(); 
	       WebUtil.writeCookie(response,
					getCookieDomain(settings.getCookieDomain()),
				   "",
				   Constants.CAPTCHA, 
				   CryptoUtil.MD5(createText), 
				   1200);
	       //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中 
	       BufferedImage challenge = captchaProducer.createImage(createText); 
	       ImageIO.write(challenge, "jpg", jpegOutputStream); 
       } catch (IllegalArgumentException ex) { 
			handleError(request, ex);
	        response.sendError(HttpServletResponse.SC_NOT_FOUND); 
	        return; 
       } 
       //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组 
       captchaChallengeAsJpeg = jpegOutputStream.toByteArray(); 
       response.setHeader("Cache-Control", "no-store"); 
       response.setHeader("Pragma", "no-cache"); 
       response.setDateHeader("Expires", 0); 
       response.setContentType("image/jpeg"); 
       
       try (ServletOutputStream responseOutputStream = response.getOutputStream()) { 
	       responseOutputStream.write(captchaChallengeAsJpeg); 
	       responseOutputStream.flush(); 
       }
    }
}

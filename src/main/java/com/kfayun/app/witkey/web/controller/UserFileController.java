/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.web.controller;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.service.UserService;

import com.kfayun.app.witkey.util.*;
import com.kfayun.app.witkey.web.ZuopinUploadContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 用户文件操作Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/user")
public class UserFileController extends BaseController {
    
	@Autowired
	private UserService userService;

	/**
	 * 头像上传处理
	 * 
	 * @param upfile 上传文件
	 * @return
	 */
	@RequestMapping(value="uploadavatar")
    @ResponseBody
    public String uploadAvatar(
    		@RequestParam("file")MultipartFile upfile,
            HttpServletRequest request) {

		User user = getCurrentUser(request);
		try {
			String fname = upfile.getOriginalFilename().toLowerCase();
			
			String suffix = "";
			if (fname.lastIndexOf(".") > 0) {
				suffix = fname.substring(fname.lastIndexOf(".") + 1);
			}
			
			if ("jpg".equals( suffix ) ||
					"png".equals( suffix ) ||
					"jpeg".equals( suffix )
					) {
				
		        String fileName = user.getId() + "-" + CryptoUtil.MD5(System.currentTimeMillis()+"_"+fname).substring(7) + "." + suffix;
	
		        String objectPath = "image/" + DateUtil.toMonthStringNoSep(new Date());
		        Path objPath = Paths.get(config.getUploadPath(), objectPath);
		        if (!objPath.toFile().exists()) {
					Files.createDirectories(objPath);
				}

		        String objectName = objectPath + "/" + fileName;   

		        Path path = Paths.get(config.getUploadPath(), objectName);
		        if (!Files.exists(path.getParent())) {
		        		Files.createDirectory(path.getParent());
		        }
				Files.copy(upfile.getInputStream(), path); 
		      
				user.setAvatar("/upload/" + objectName);
				userService.updateUser(user);

		        String resp = "{\"msg\":\"success\", \"fileUrl\":\"/upload/" + objectName + "\", \"fileName\":\"" + upfile.getOriginalFilename() + "\", \"fileSize\":" + upfile.getSize() + " }";
		        System.out.println(resp);
		        return resp;
			} else {

				return "{\"msg\":\"文件格式不支持。\"}";
			}
		} catch (IOException e) {
			handleError(request, e);
			return "{\"msg\":\"Unknown error\"}";
		}
    }

	/**
	 * 认证文件输出
	 * 根据业务设计，此处可加鉴权处理，防止未授权访问。
	 * 
	 * @param month 月份文件夹
	 * @param filename 文件名
	 */
	@GetMapping("authent/{month:\\d+}/{filename}")
    @ResponseBody
    public void authentOutput(
    		@PathVariable("month")String month,
			@PathVariable("filename")String filename,
            HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		User user = getCurrentUser(request);
		
		Path path = Paths.get(config.getUploadPath(), "authent", month, filename);
		if (!Files.exists(path)) {
			// 404
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return;
		}
		
		try (OutputStream outStream = response.getOutputStream()) {
			StreamUtils.copy(new FileInputStream(path.toFile()), outStream);
		}
    }

	/**
	 * 认证文件上传
	 * 文件数据采用BASE64编码。
     * 
     * @param params 上传参数 
	 */
    @PostMapping("authent/upload")
	@ResponseBody
    public void authentUpload(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
			int uid = getCurrentUser(request).getId();

			String name = params.get("name");
			String[] strs = params.get("img").split(",");
			byte[] fileData = Base64.getDecoder().decode(strs[1]);

			String suffix = "";
			if (name.lastIndexOf(".") > 0) {
				suffix = name.substring(name.lastIndexOf(".") + 1);
			}
			String fileName = uid + "-" + CryptoUtil.MD5(System.currentTimeMillis()+"_"+name).substring(7) + "." + suffix;
		
			String objectPath = "authent/" + DateUtil.toMonthStringNoSep(new Date());
			String objectName = objectPath + "/" + fileName;   

			Path path = Paths.get(config.getUploadPath(), objectName);
			if (!Files.exists(path.getParent())) {
				Files.createDirectories(path.getParent());
			}
			ByteArrayInputStream inStream = new ByteArrayInputStream(fileData);
			Files.copy(inStream, path); 

			response.setContentType( "text/json" );
			response.getWriter().write("{\"img\":\"" + objectName + "\"}");
		} catch (Exception ex) {
			handleError(request, ex);
			response.setContentType( "text/json" );
			response.getWriter().write("{\"err\":\"Unknown error.\"}");
		}
	}

	/**
	 * 作品文件输出
	 * 
	 * @param month 月份文件夹
	 * @param filename 文件名
	 */
	@GetMapping("zuopin/{month:\\d+}/{filename}")
    @ResponseBody
    public void zuopinOutput(
    		@PathVariable("month")String month,
			@PathVariable("filename")String filename,
            HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		User user = getCurrentUser(request);
		
		Path path = Paths.get(config.getUploadPath(), "zuopin", month, filename);
		if (!Files.exists(path)) {
			// 404
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return;
		}
		
		try (OutputStream outStream = response.getOutputStream()) {
			StreamUtils.copy(new FileInputStream(path.toFile()), outStream);
		}
    }

	/**
	 * 作品文件上传
	 * 文件数据采用BASE64编码。
     * 
     * @param params 上传参数 
	 */
    @PostMapping("zuopin/upload")
	@ResponseBody
    public void zuopinUpload(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
			int uid = getCurrentUser(request).getId();

			String name = params.get("name");
			String[] strs = params.get("img").split(",");
			byte[] fileData = Base64.getDecoder().decode(strs[1]);

			String suffix = "";
			if (name.lastIndexOf(".") > 0) {
				suffix = name.substring(name.lastIndexOf(".") + 1);
			}
			String fileName = uid + "-" + CryptoUtil.MD5(System.currentTimeMillis()+"_"+name).substring(7) + "." + suffix;
		
			String objectPath = "zuopin/" + DateUtil.toMonthStringNoSep(new Date());
			String objectName = objectPath + "/" + fileName;   

			Path path = Paths.get(config.getUploadPath(), objectName);
			if (!Files.exists(path.getParent())) {
				Files.createDirectories(path.getParent());
			}
			ByteArrayInputStream inStream = new ByteArrayInputStream(fileData);
			Files.copy(inStream, path); 

			String key = UUID.randomUUID().toString();
			ZuopinUploadContext.getInstance(uid, request).addImage(key, objectName);
			getLog().info("用户{} 作品上传-添加图片{}。", uid, objectName);

			response.setContentType( "text/json" );
			response.getWriter().write("{\"key\":\"" + key + "\", \"img\":\"/user/" + objectName + "\"}");
		} catch (Exception ex) {
			handleError(request, ex);
			response.setContentType( "text/json" );
			response.getWriter().write("{\"err\":\"Unknown error.\"}");
		}
	}

	/**
	 * 认证封面文件上传
	 * 文件数据采用BASE64编码。
     * 
     * @param params 上传参数 
	 */
	@PostMapping("zuopin/uploadcover")
	@ResponseBody
    public void zuopinUploadCover(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
			int uid = getCurrentUser(request).getId();

			String[] strs = params.get("img").split(",");
			byte[] fileData = Base64.getDecoder().decode(strs[1]);

			String fileName = uid + "-" + CryptoUtil.MD5(System.currentTimeMillis()+"_cover").substring(7) + ".jpg";
		
			String objectPath = "zuopin/" + DateUtil.toMonthStringNoSep(new Date());
			String objectName = objectPath + "/" + fileName;   

			Path path = Paths.get(config.getUploadPath(), objectName);
			if (!Files.exists(path.getParent())) {
				Files.createDirectories(path.getParent());
			}

			byte[] thumbData = ImageUtil.getThumbtail(fileData, 311, 244);
			ByteArrayInputStream inStream = new ByteArrayInputStream(thumbData);
			Files.copy(inStream, path); 

			ZuopinUploadContext.getInstance(uid, request).setCover(objectName);
			getLog().info("用户{} 作品上传-添加封面{}。", uid, objectName);

			response.setContentType( "text/json" );
			response.getWriter().write("{\"cover\":\"/user/" + objectName + "\"}");
		} catch (Exception ex) {
			handleError(request, ex);
			response.setContentType( "text/json" );
			response.getWriter().write("{\"err\":\"Unknown error.\"}");
		}
	}
}

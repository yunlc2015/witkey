/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kfayun.app.witkey.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 管理端文件上传操作Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/manage/")
public class ManageFileController extends ManageBaseController {
    
	/**
	 * 图片上传处理
	 * 
	 * @param upfile 上传文件
	 * @return
	 */
	@RequestMapping(value="uploadimage")
    @ResponseBody
    public String uploadAvatar(
    		@RequestParam("file")MultipartFile upfile,
            HttpServletRequest request) {

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
				
		        String fileName = CryptoUtil.MD5(System.currentTimeMillis()+"_"+fname).substring(7) + "." + suffix;
	
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
	 * 管理员可查看用户上传的认证附件。
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
	 * KindEditor 上传处理
	 * 
	 * @param cate 上传类别， image / file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="kkupload")
	@ResponseBody
	public Map<String, Object> kkUploadImage(
			@RequestParam("cate")String cate,
			HttpServletRequest request) {

		try {
			if (!ServletFileUpload.isMultipartContent(request)) {
				return getError("请选择文件。");
			}

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> item = multipartRequest.getFileNames();
			if (item.hasNext()) {

				String name = (String)item.next();
				MultipartFile file = multipartRequest.getFile(name);

				String fname = file.getOriginalFilename().toLowerCase();
				
				String suffix = "";
				if (fname.lastIndexOf(".") > 0) {
					suffix = fname.substring(fname.lastIndexOf(".") + 1);
				}
				
				if ("image".equalsIgnoreCase(cate)) {
					if (!("jpg".equals( suffix ) || 
							"jpeg".equals( suffix ) || 
							"png".equals( suffix ) ||
							"gif".equals( suffix ))) {
						return getError("文件格式不支持，仅支持JPG、PNG、GIF文件。");
					}
				} else if ("file".equalsIgnoreCase(cate)) {
					if (!("zip".equals( suffix ) ||
							"rar".equals( suffix ) ||
							"pdf".equals( suffix ))) {
						return getError("文件格式不支持，仅支持ZIP、RAR、PDF文件。");
					}
				} else {
					return getError("文件类别不支持，仅支持IMAGE，FILE类别。");
				}
					
				String fileName = CryptoUtil.MD5(System.currentTimeMillis()+"_"+fname) + "." + suffix;

				String objectPath = "image/" + DateUtil.getYear();
				Path objPath = Paths.get(config.getUploadPath(), objectPath);
				if (!objPath.toFile().exists()) {
					Files.createDirectories(objPath);
				}

				String objectName = objectPath + "/" + fileName;

				Path path = Paths.get(config.getUploadPath(), objectName);
				Files.copy(file.getInputStream(), path);

				String fileUrl = "/upload/" + objectName;

				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("title", name);
				msg.put("url", fileUrl);

				return msg;
			}

			return null;

		} catch (IOException e) {
			System.out.println(e.toString());

			return getError(e.getMessage());
		}
	}
	
	private Map<String, Object> getError(String message) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("error", 1);
		msg.put("message", message);
		return msg;
	}

}

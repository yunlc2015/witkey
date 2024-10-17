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
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.util.StreamUtils;

import com.kfayun.app.witkey.JsonResult;
import com.kfayun.app.witkey.config.AppConfig;
import com.kfayun.app.witkey.model.User;
import com.kfayun.app.witkey.service.UserService;

import com.kfayun.app.witkey.util.CryptoUtil;
import com.kfayun.app.witkey.util.DateUtil;
import com.kfayun.app.witkey.web.TaskPublishContext;
import com.kfayun.app.witkey.web.ProposalUploadContext;

import java.nio.file.Path;

/**
 * 任务文件操作Controller
 * 
 * @author: Billy Zhang (billy_zh@126.com)
 */
@Controller
@RequestMapping("/task")
public class TaskFileController extends BaseController {
    
	@Autowired
	private UserService userService;
	@Autowired
	private AppConfig config;

    /**
     * 任务文件输出
     * 
     * @param month 月份文件夹
     * @param filename 文件名
     */
	@GetMapping("file/{month:\\d+}/{filename}")
    @ResponseBody
    public void fileOutput(
    		@PathVariable("month")String month,
			@PathVariable("filename")String filename,
            HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		User user = getCurrentUser(request);
		
		Path path = Paths.get(config.getUploadPath(), "file", month, filename);
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
     * 任务文件上传
     * 文件数据采用BASE64编码。
     * 
     * @param params 上传参数
     */
    @PostMapping("file/upload")
	@ResponseBody
    public void fileUpload(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            int uid = getCurrentUser(request).getId();

            String name = params.get("name");
            String[] strs = params.get("file").split(",");
            String type = strs[0];
            byte[] fileData = Base64.getDecoder().decode(strs[1]);

            String suffix = "";
            if (name.lastIndexOf(".") > 0) {
                suffix = name.substring(name.lastIndexOf(".") + 1);
            }
            String fileName = uid + "-" + CryptoUtil.MD5(System.currentTimeMillis()+"_"+name).substring(7) + "." + suffix;
        
            String objectPath = "file/" + DateUtil.toMonthStringNoSep(new Date());
            String objectName = objectPath + "/" + fileName;   

            Path path = Paths.get(config.getUploadPath(), objectName);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            ByteArrayInputStream inStream = new ByteArrayInputStream(fileData);
            Files.copy(inStream, path); 

            String key = UUID.randomUUID().toString();
            TaskPublishContext.getInstance(uid, request).addFile(key, objectName);

            response.setContentType( "text/json" );
            response.getWriter().write("{\"key\":\"" + key + "\", \"url\":\"/task/" + objectName + "\"}");
        } catch (Exception ex) {
			handleError(request, ex);
		    response.setContentType( "text/json" );
            response.getWriter().write("{\"err\":\"Unknown error.\"}");
        }
    }

    /**
     * 任务提案文件输出
     * 根据业务设计，此处可加访问鉴权。
     * 
     * @param month 月份文件夹
     * @param filename 文件名
     */
	@GetMapping("proposal/{month:\\d+}/{filename}")
    @ResponseBody
    public void proposalOutput(
    		@PathVariable("month")String month,
			@PathVariable("filename")String filename,
            HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		User user = getCurrentUser(request);
		
		Path path = Paths.get(config.getUploadPath(), "proposal", month, filename);
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
     * 任务提案文件上传
     * 文件数据采用BASE64编码。
     * 
     * @param params 上传参数
     */
    @PostMapping("proposal/upload")
	@ResponseBody
    public void proposalUpload(@RequestParam Map<String, String> params,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            int uid = getCurrentUser(request).getId();

            String name = params.get("name");
            String[] strs = params.get("file").split(",");
            String type = strs[0];
            byte[] fileData = Base64.getDecoder().decode(strs[1]);

            String suffix = "";
            if (name.lastIndexOf(".") > 0) {
                suffix = name.substring(name.lastIndexOf(".") + 1);
            }
            String fileName = uid + "-" + CryptoUtil.MD5(System.currentTimeMillis()+"_"+name).substring(7) + "." + suffix;
        
            String objectPath = "proposal/" + DateUtil.toMonthStringNoSep(new Date());
            String objectName = objectPath + "/" + fileName;   

            Path path = Paths.get(config.getUploadPath(), objectName);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            ByteArrayInputStream inStream = new ByteArrayInputStream(fileData);
            Files.copy(inStream, path); 

            String key = UUID.randomUUID().toString();
            ProposalUploadContext.getInstance(uid, request).addFile(key, objectName);

            response.setContentType( "text/json" );
            response.getWriter().write("{\"key\":\"" + key + "\", \"url\":\"/task/" + objectName + "\"}");
    
        } catch (Exception ex) {
			handleError(request, ex);
		    response.setContentType( "text/json" );
            response.getWriter().write("{\"err\":\"Unknown error.\"}");
        }
    }

}

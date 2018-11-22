package com.jk.luckydraw.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final String url = "upload";

	/**
	 * 上传文件方法
	 * @param file 上传的文件
	 * @param request request对象
	 * @return
	 */
	public static String FileUpload(MultipartFile file, HttpServletRequest request,String location){
		
		//保存文件的目标目录
		String savePath = location;//request.getRealPath("/");
//		System.out.println("图片存储地址"+savePath);
//		String savePath = request.getSession().getServletContext().getRealPath(url);
		
		//获取源文件后缀名称
		//12345.jpg
		int suffixIndex = file.getOriginalFilename().lastIndexOf(".");
		//  .jpg
		String suffixName = file.getOriginalFilename().substring(suffixIndex);
		String newFileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
		//检测目标目录是否存在
		File targetFile = new File(savePath+newFileName);
		// 检测是否存在目录
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();// 新建文件夹
		}
		try {
			// 使用transferTo（dest）方法将上传文件写到服务器上指定的文件。
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newFileName;
	}

}

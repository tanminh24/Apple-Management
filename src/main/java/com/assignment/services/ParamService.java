package com.assignment.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {

	@Autowired
	HttpServletRequest request;

	/**
	 * Đọc chuỗi giá trị của tham số
	 * 
	 * @param name         : tên tham số
	 * @param defaultValue : giá trị mặc định
	 * @return : giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public String getString(String name, String defaultValue) {
		String value = request.getParameter(name);
		if (value == null || value.trim().isEmpty()) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * Đọc số nguyên giá trị của tham số
	 * 
	 * @param name         : tên tham số
	 * @param defaultValue : giá trị mặc định
	 * @return : giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public int getInt(String name, int defaultValue) {
		try {
			return Integer.parseInt(request.getParameter(name));
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	/**
	 * Đọc số thực giá trị của tham số
	 * 
	 * @param name         : tên tham số
	 * @param defaultValue : giá trị mặc định
	 * @return : giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public double getDouble(String name, double defaultValue) {
		try {
			return Double.parseDouble(request.getParameter(name));
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	/**
	 * Đọc giá trị boolean của tham số
	 * 
	 * @param name         : tên tham số
	 * @param defaultValue : giá trị mặc định
	 * @return : giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		try {
			return Boolean.parseBoolean(request.getParameter(name));
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	/**
	 * Đọc giá trị thời gian của tham số
	 * 
	 * @param name    : tên tham số
	 * @param pattern : là định dạng thời gian
	 * @return : giá trị tham số hoặc null nếu không tồn tại
	 * @throws : RuntimeException lỗi sai định dạng
	 */
	public Date getDate(String name, String pattern) {
		try {
			SimpleDateFormat f = new SimpleDateFormat(pattern);
			f.parse(request.getParameter(name));
			return f.parse(request.getParameter(name));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get MultipartFile
	 */
	public MultipartFile getMultiPartFile(String name) {
		try {
			MultipartFile file = (MultipartFile) request.getAttribute(name);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

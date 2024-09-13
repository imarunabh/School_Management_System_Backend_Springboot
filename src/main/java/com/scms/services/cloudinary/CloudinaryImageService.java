package com.scms.services.cloudinary;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {
	
	public Map upload(MultipartFile file);
	public String fetch(String url);

}

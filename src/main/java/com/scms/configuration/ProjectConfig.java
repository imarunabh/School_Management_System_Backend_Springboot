package com.scms.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {
	
	@Bean
	public Cloudinary getCloudinary() {
		Map config = new HashMap<>();
		config.put("cloud_name","dutrha6av");
		config.put("api_key", "677451787745487");
		config.put("api_secret", "vsu_H1TT46-CEWrX88GnYe2rxJw");
		config.put("secure", true);
		return new Cloudinary(config);
		
	}

}

package com.chaos.fission.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix="upload")
public class UploadPropertis {
	
	private Resource rootPath;
	private Resource imagePath;
	private Resource anonymousPicture;
	
	private DefaultResourceLoader getLoader() {
		return new DefaultResourceLoader();
	}
	
	public Resource getRootPath() {
		return rootPath;
	}
	
	public void setRootPath(String rootPath) {
		this.rootPath = getLoader().getResource(rootPath);
	}

	public Resource getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = getLoader().getResource(imagePath);
	}

	public Resource getAnonymousPicture() {
		return anonymousPicture;
	}

	public void setAnonymousPicture(String anonymousPicture) {
		this.anonymousPicture = getLoader().getResource(anonymousPicture);
	}
	
}

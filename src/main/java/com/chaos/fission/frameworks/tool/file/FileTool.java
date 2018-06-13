package com.chaos.fission.frameworks.tool.file;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class FileTool {

	public static FileTool getInstance() {
		return new FileTool();
	}
	
	public String getFileExtension(String name) {
		return name.substring(name.lastIndexOf("."));
	}
	
	public boolean isImage(MultipartFile file) {
		return file.getContentType().startsWith("image");
	}
	
	public boolean createOrValidateDir(String dirname) {
		File dir = new File(dirname);
		if (!dir.exists()) {
			return dir.mkdirs();
		}
		return true;
	}
	
	public Resource createRelativeResource(Resource resource, String relativePath) throws IOException {
		return resource.createRelative(relativePath);
	}

}

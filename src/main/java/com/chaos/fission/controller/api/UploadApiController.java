package com.chaos.fission.controller.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chaos.fission.frameworks.tool.file.ImageUploadTool;

@RestController
@RequestMapping("/api/upload")
public class UploadApiController {
	
	@Autowired
	private ImageUploadTool imageUploadTool;
	
	@RequestMapping(value="/image/single", method=RequestMethod.POST)
	public String imageSingleStore(MultipartFile file) throws IOException {
		if (file.isEmpty() || !imageUploadTool.fileTool.isImage(file)) {
			return "Incorrect file.";
		}
		Resource resource = imageUploadTool.singleImageStore(file);
		if (resource != null) {
			System.out.println(resource.getURL().getPath());
		}
		return "ok";
	}
	
}

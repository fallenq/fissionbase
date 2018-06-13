package com.chaos.fission.frameworks.tool.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.chaos.fission.config.UploadPropertis;
import com.chaos.fission.frameworks.tool.common.TimeTool;

@Repository
public class ImageUploadTool implements UploadNozzle {

	public FileTool fileTool;
	@Autowired
	private UploadPropertis uploadProperty;

	public ImageUploadTool() {
		fileTool = FileTool.getInstance();
	}

	public Resource singleImageStore(MultipartFile file) throws IOException {
		String fileExt = fileTool.getFileExtension(file.getOriginalFilename());
		String dirname = getUploadUrl().concat("/").concat(TimeTool.currentDate("yyyyMMdd"));
		FileSystemResource outFile = null;
		if (fileTool.createOrValidateDir(dirname)) {
			Resource resource = fileTool.createRelativeResource(getUploadResource(), dirname);
			File tempFile = File.createTempFile("pic", fileExt, resource.getFile());
			try (InputStream input = file.getInputStream();OutputStream output = new FileOutputStream(tempFile)) {
				IOUtils.copy(input, output);
				outFile = new FileSystemResource(tempFile); 
			}
		}
		return outFile;
	}

	@Override
	public Resource getUploadResource() {
		return uploadProperty.getImagePath();
	}

	@Override
	public Resource getAnonymousPicture() {
		return uploadProperty.getAnonymousPicture();
	}

	@Override
	public String getUploadUrl() throws IOException {
		return getUploadResource().getURL().getPath();
	}

}

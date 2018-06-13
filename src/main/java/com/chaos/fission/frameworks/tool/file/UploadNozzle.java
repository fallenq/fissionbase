package com.chaos.fission.frameworks.tool.file;

import java.io.IOException;

import org.springframework.core.io.Resource;

interface UploadNozzle {
	public Resource getUploadResource();
	public Resource getAnonymousPicture();
	public String getUploadUrl() throws IOException;
}

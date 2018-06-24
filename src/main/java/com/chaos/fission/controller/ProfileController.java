package com.chaos.fission.controller;

import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.chaos.fission.frameworks.tool.file.ImageUploadTool;
import com.chaos.fission.profile.ProfileForm;
import com.chaos.fission.profile.ProfileSession;
import com.chaos.fission.profile.USLocalDateFormatter;

@Controller
@SessionAttributes("picturePath")
public class ProfileController {
	
	@Autowired
	private ImageUploadTool imageUploadTool;
	
	@Autowired
	private ProfileSession profileSession;
	
	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return USLocalDateFormatter.getPattern(locale);
	}
	
//	@ModelAttribute("picturePath")
//	public Resource picturePath() {
//		return imageUploadTool.getAnonymousPicture();
//	}
	
	@ModelAttribute
	public ProfileForm getProfileForm() {
		return profileSession.toForm();		
	}
	
	@ExceptionHandler(IOException.class)
	public ModelAndView	handleIOException(IOException exception) {
		ModelAndView modelAndView = new ModelAndView("profile/profilePage");
		modelAndView.addObject("error", exception.getMessage());
		modelAndView.addObject("profileForm", profileSession.toForm());
		return modelAndView;
	}
	
	@RequestMapping("/profile")
	public String displayProfile(ProfileForm profileForm) {
		return "profile/profilePage";
	}
	
	@RequestMapping(value="/profile", params= {"save"}, method=RequestMethod.POST)
	public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "profile/profilePage";
		}
		profileSession.saveForm(profileForm);
		System.out.println("save ok" + profileForm);
		return "redirect:/profile";
	}
	
	@RequestMapping(value="/profile", params={"addTaste"})
	public String addRow(ProfileForm profileForm) {
		profileForm.getTastes().add(null);
		return "profile/profilePage";
	}
	
	@RequestMapping(value="/profile", params={"removeTaste"})
	public String removeRow(ProfileForm profileForm, HttpServletRequest req) {
		System.out.println(profileForm.getTastes().size());
		Integer rowId = Integer.valueOf(req.getParameter("removeTaste"));
		profileForm.getTastes().remove(rowId.intValue());
		return "profile/profilePage";
	}
	
	@RequestMapping("/profile/upload")
	public String uploadPage() {
		return "profile/uploadPage";
	}
	
	@RequestMapping(value="/profile", params={"upload"}, method=RequestMethod.POST)
	public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		if (file.isEmpty() || !imageUploadTool.fileTool.isImage(file)) {
			redirectAttributes.addFlashAttribute("error", "Incorrect file.");
			return "redirect:/profile";
		}
		Resource resource = imageUploadTool.singleImageStore(file);
		profileSession.setPicturePath(resource);
		return "redirect:profile";
	}
	
	@RequestMapping(value="/profile/uploadedPicture")
	public void getUploadedPicture(HttpServletResponse response) throws IOException {
		Resource picturePath = profileSession.getPicturePath();
		if (picturePath == null) {
			picturePath = imageUploadTool.getAnonymousPicture();
		}
		response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.getFilename()));
		IOUtils.copy(picturePath.getInputStream(), response.getOutputStream());
	}
	
	@RequestMapping("profile/uploadError")
	public ModelAndView onUploadError(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("profile/profilePage");
		modelAndView.addObject("error", request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE));
		modelAndView.addObject("profileForm", profileSession.toForm());
		return modelAndView;
	}
}

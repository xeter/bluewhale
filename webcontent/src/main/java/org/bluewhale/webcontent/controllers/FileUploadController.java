package org.bluewhale.webcontent.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "Use POST HTTP verb to upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			@RequestParam("name") final String name,
			@RequestParam("file") final MultipartFile file) {
		if (name.contains("/")) {
			return "Folder separators not allowed";
		}
		if (!file.isEmpty()) {
			try {
				final byte[] bytes = file.getBytes();
				final BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				return "File uploaded successfully";
			} catch (final Exception e) {
				return "An error occurred while uploading the file (" + name
						+ " => " + e.getMessage();
			}
		} else {
			return "File is empty";
		}
	}
}

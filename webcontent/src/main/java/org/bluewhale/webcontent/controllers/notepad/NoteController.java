package org.bluewhale.webcontent.controllers.notepad;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("note")
public class NoteController {

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public @ResponseBody String saveNote() {
		return "Use POST HTTP verb to upload";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String saveNote(
			@RequestParam(value = "title") final String title,
			@RequestParam(value = "summary") final String summary,
			@RequestParam(value = "link") final String link,
			@RequestParam(value = "file") final MultipartFile file) {

		if (!file.isEmpty()) {
			final String fileName = generateFileName();
			try {
				final byte[] bytes = file.getBytes();
				final BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(fileName)));
				stream.write(bytes);
				stream.close();
				return "File uploaded successfully " + title + "; " + summary
						+ "; " + link;
			} catch (final Exception e) {
				return "An error occurred while uploading the file ("
						+ fileName + " => " + e.getMessage();
			}
		} else {
			return "File is empty";
		}
	}

	private String generateFileName() {
		return "azmemaz";
	}
}

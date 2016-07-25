package org.bluewhale.webcontent.controllers.notepad;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.bluewhale.webcontent.beans.Note;
import org.bluewhale.webcontent.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("note")
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public @ResponseBody String saveNote() {
		return "Use POST HTTP verb to upload";
	}

	@RequestMapping(value = "/saveNoteFile", method = RequestMethod.POST)
	public @ResponseBody String saveNotesFile(
			@RequestParam(value = "file") final MultipartFile file) {
		return saveFile(file);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String saveNote(
			@RequestParam(value = "id") final Long id,
			@RequestParam(value = "title") final String title,
			@RequestParam(value = "author") final String author,
			@RequestParam(value = "newsDate") final String /**/newsDate,
			@RequestParam(value = "summary") final String summary,
			@RequestParam(value = "link") final String link,
			@RequestParam(value = "file") final MultipartFile file) {

		final String fileName = saveFile(file);

		noteRepository
				.save(new Note(id, title, author, summary, link, fileName));
		return "File uploaded successfully. Title: " + title + //
				"; Author: " + author + //
				"; News date : " + newsDate + //
				"; Summary:  " + summary + //
				"; Link:  " + link + //
				"; FileName " + fileName //
		;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Note> list() {
		final Page<Note> noteList = noteRepository.findAll(new PageRequest(0,
				10));
		return noteList.getContent();
	}

	private String saveFile(final MultipartFile file) {
		if (!file.isEmpty()) {
			final String fileName = generateFileName();
			try {
				final byte[] bytes = file.getBytes();
				final BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(fileName)));
				stream.write(bytes);
				stream.close();
				return fileName;
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

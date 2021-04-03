package com.jesperapps.tracksupervisor.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.service.AttachmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DownloadOrViewFileOrImage {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/view_image/{id}")
	public ResponseEntity<ByteArrayResource> viewImage(@PathVariable("id") Long id) {
		Optional<Attachment> attachment = attachmentService.findById(id);
		if (attachment.isPresent()) {
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(attachment.get().getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"inline; filename=\"" + attachment.get().getFileName() + "\"")
					.body(new ByteArrayResource(attachment.get().getAttachmentByte().getFileByte()));
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("errorCode", 404);
			jsonObject.put("message", "image not found");
			return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/download_image/{id}")
	public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable("id") Long id) {
		Optional<Attachment> attachment = attachmentService.findById(id);
		if (attachment.isPresent()) {
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(attachment.get().getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + attachment.get().getFileName() + "\"")
					.body(new ByteArrayResource(attachment.get().getAttachmentByte().getFileByte()));
		} else {
			ObjectNode jsonObject = objectMapper.createObjectNode();
			jsonObject.put("errorCode", 404);
			jsonObject.put("message", "image not found");
			return new ResponseEntity(jsonObject, HttpStatus.CONFLICT);
		}
	}
}

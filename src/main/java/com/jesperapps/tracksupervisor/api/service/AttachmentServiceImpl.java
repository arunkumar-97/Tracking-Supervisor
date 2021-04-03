package com.jesperapps.tracksupervisor.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.model.Attachment;
import com.jesperapps.tracksupervisor.api.repository.AttachmentRepository;


@Service
public class AttachmentServiceImpl implements AttachmentService{
	
	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	public Attachment save(Attachment attachment) {
		System.out.println("attachment"+attachment);
		return attachmentRepository.save(attachment);
	}

	@Override
	public Optional<Attachment> findById(Long parseLong) {
		return attachmentRepository.findById(parseLong);
	}


}

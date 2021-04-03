package com.jesperapps.tracksupervisor.api.service;

import java.util.Optional;

import com.jesperapps.tracksupervisor.api.model.Attachment;



public interface AttachmentService {

	Attachment save(Attachment attachment);

	Optional<Attachment> findById(Long parseLong);

}

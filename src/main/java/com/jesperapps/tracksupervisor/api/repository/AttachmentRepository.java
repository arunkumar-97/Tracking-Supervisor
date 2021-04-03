package com.jesperapps.tracksupervisor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jesperapps.tracksupervisor.api.model.Attachment;


public interface AttachmentRepository extends JpaRepository<Attachment, Long>{

}

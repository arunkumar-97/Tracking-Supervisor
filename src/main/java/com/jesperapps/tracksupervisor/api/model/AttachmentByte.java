package com.jesperapps.tracksupervisor.api.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jesperapps.tracksupervisor.api.extra.AbstractAuditingEntity;

@Entity
@Table(name = "attachment_byte")
public class AttachmentByte{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attachmentByteId;
	@Lob
	private byte[] fileByte;
	private String status;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Attachment.class)
	@JoinColumn(name = "attachmentId")
	private Attachment attachment;

	public AttachmentByte() {

	}

	public AttachmentByte(AttachmentByte attachmentByte) {
	this.attachmentByteId = attachmentByte.getAttachmentByteId();
	}

	public Long getAttachmentByteId() {
		return attachmentByteId;
	}

	public void setAttachmentByteId(Long attachmentByteId) {
		this.attachmentByteId = attachmentByteId;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "AttachmentByte [attachmentByteId=" + attachmentByteId + ", fileByte=" + Arrays.toString(fileByte)
				+ ", status=" + status + "]";
	}

	
	
}

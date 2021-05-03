package com.jesperapps.tracksupervisor.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "attachment")
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "attachment_id")
	private Long attachmentId;
	private String fileName;
	private String fileType;
	private Long fileSize;
	private String status;

	private String fileDownloadUrl;
	private String fileViewUrl;

	@JsonBackReference("user_attachment")
	 @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;


	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "attachment")
	private AttachmentByte attachmentByte;
	
	@JsonBackReference("organization_attachment")
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public Attachment() {

	}

	// user creation
	public Attachment(Attachment attachment) {
		this.attachmentId = attachment.getAttachmentId();
		this.fileName = attachment.getFileName();
		this.fileType = attachment.getFileType();
		this.fileSize = attachment.getFileSize();
		// this.fileByte = attachment.getFileByte();
		this.attachmentByte = attachment.getAttachmentByte();
		this.attachmentByte.setAttachment(this);
		this.status = attachment.getStatus();
		this.fileDownloadUrl = attachment.getFileDownloadUrl();
		this.fileViewUrl = attachment.getFileViewUrl();
		this.user = attachment.getUser();
//		this.organization=attachment.getOrganization();
	}

	// user list // user by id
	public Attachment(Attachment attachment, Attachment attachment2) {
		this.attachmentId = attachment.getAttachmentId();
		this.fileDownloadUrl = attachment.getFileDownloadUrl();
		this.fileViewUrl = attachment.getFileViewUrl();
		if (attachment.getAttachmentByte() != null) {
			this.attachmentByte = attachment.getAttachmentByte();
		}

	}

	// attendance list by date
	public Attachment(Attachment attachment, Attachment attachment2, Attachment attachment3) {
		this.attachmentId = attachment.getAttachmentId();
		this.fileName = attachment.getFileName();
		this.fileType = attachment.getFileType();
		this.fileSize = attachment.getFileSize();
		this.fileDownloadUrl = attachment.getFileDownloadUrl();
		this.fileViewUrl = attachment.getFileViewUrl();
	}

	public Attachment(Attachment attachment, Organization organizationSaved) {
		this.attachmentId = attachment.getAttachmentId();
		this.fileName = attachment.getFileName();
		this.fileType = attachment.getFileType();
		this.fileSize = attachment.getFileSize();
		// this.fileByte = attachment.getFileByte();
		this.attachmentByte = attachment.getAttachmentByte();
		this.attachmentByte.setAttachment(this);
		this.status = attachment.getStatus();
		this.fileDownloadUrl = attachment.getFileDownloadUrl();
		this.fileViewUrl = attachment.getFileViewUrl();
		this.user = attachment.getUser();
		this.organization = organizationSaved;
	}

	public Attachment(Attachment attachment, Integer organizationId) {
		this.attachmentId = attachment.getAttachmentId();
		this.fileName = attachment.getFileName();
		this.fileType = attachment.getFileType();
		this.fileSize = attachment.getFileSize();
		// this.fileByte = attachment.getFileByte();
		this.attachmentByte = attachment.getAttachmentByte();
		this.attachmentByte.setAttachment(this);
		this.status = attachment.getStatus();
		this.fileDownloadUrl = attachment.getFileDownloadUrl();
		this.fileViewUrl = attachment.getFileViewUrl();
		this.organization = attachment.getOrganization();
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

//	public byte[] getFileByte() {
//		return fileByte;
//	}
//
//	public void setFileByte(byte[] fileByte) {
//		this.fileByte = fileByte;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFileDownloadUrl() {
		return fileDownloadUrl;
	}

	public void setFileDownloadUrl(String fileDownloadUrl) {
		this.fileDownloadUrl = fileDownloadUrl;
	}

	public String getFileViewUrl() {
		return fileViewUrl;
	}

	public void setFileViewUrl(String fileViewUrl) {
		this.fileViewUrl = fileViewUrl;
	}

	public AttachmentByte getAttachmentByte() {
		return attachmentByte;
	}

	public void setAttachmentByte(AttachmentByte attachmentByte) {
		this.attachmentByte = attachmentByte;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}



	

}

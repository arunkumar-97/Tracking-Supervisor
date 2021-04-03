package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.model.User;

public class AttachmentResponseEntity {
	private Long attachmentId;
	private String fileName;
	private String fileType;
	private Long fileSize;

	private byte[] fileByte;
	private String status;
	
	private String fileDownloadUrl;
	private String fileViewUrl;

	private User user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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

	@Override
	public String toString() {
		return "AttachmentResponseEntity [attachmentId=" + attachmentId + ", fileName=" + fileName + ", fileType="
				+ fileType + ", status=" + status + "]";
	}

}

package com.jesperapps.tracksupervisor.api.extra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createDateTime", "updateDateTime" }, allowGetters = true)

public abstract class AbstractAuditingEntity implements Serializable {

	@JsonSerialize(using = ToStringSerializer.class)
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@JsonSerialize(using = ToStringSerializer.class)
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	@Override
	public String toString() {
		return "AbstractAuditingEntity [createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + "]";
	}

}

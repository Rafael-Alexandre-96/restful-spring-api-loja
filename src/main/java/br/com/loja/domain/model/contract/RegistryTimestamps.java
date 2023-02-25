package br.com.loja.domain.model.contract;

import java.time.OffsetDateTime;

public interface RegistryTimestamps {
	OffsetDateTime getCreatedAt();

	void setCreatedAt(OffsetDateTime createdAt);

	OffsetDateTime getUpdatedAt();

	void setUpdatedAt(OffsetDateTime updatedAt);

	OffsetDateTime getDeletedAt();

	void setDeletedAt(OffsetDateTime deletedAt);
	
	boolean isActive();
}
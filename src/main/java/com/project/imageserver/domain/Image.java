package com.project.imageserver.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;
import org.joda.time.DateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Image {
	@Id
	@GeneratedValue
	private Long id;
	private String reference;
	private String extenstion;

	private DateTime createdTime;
	// CreatedTime 넣기
	public Image(String path, String reference, String extenstion) {
		this.reference = reference;
		this.extenstion = extenstion;
	}
}

package com.project.imageserver.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

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

	private String path;

	private String name;

	private String extenstion;

	//private String extension;

	@Builder
	public Image(String path, String name, String extenstion){
		this.path = path;
		this.name = name;
		this.extenstion = extenstion;
	}
}

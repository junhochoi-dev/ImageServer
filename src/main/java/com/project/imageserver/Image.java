package com.project.imageserver;

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

	private String name;

	private String type;

	@Lob
	private byte[] data;

	@Builder
	public Image(String name, String type, byte[] data){
		this.name = name;
		this.type = type;
		this.data = data;
	}
}

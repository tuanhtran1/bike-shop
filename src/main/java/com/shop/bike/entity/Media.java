package com.shop.bike.entity;

import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.MediaType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "media")
@Data
public class Media extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "path")
	private String path;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ActionStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_media")
	private MediaType mediaType;
	
	@Column(name = "original_name")
	private String originalName;
	
	@Column(name = "file_size", precision = 21, scale = 2)
	private BigDecimal fileSize;
	
	@Column(name = "extension")
	private String extension;
	
}

package com.shop.bike.entity;

import com.shop.bike.entity.enumeration.ActionStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "brand")
@Getter
@Setter
public class Brand {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "short_name")
	private String shortName;

	@Column(name ="status")
	@Enumerated(EnumType.STRING)
	private ActionStatus status;

	@Column(name = "user_id")
	private Long userId;

	@Size(max = 10000)
	@Column(name = "description", length = 10000)
	private String description;

}

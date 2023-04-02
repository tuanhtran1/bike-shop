package com.shop.bike.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Wards.
 */
@Entity
@Table(name = "wards")
@Getter
@Setter
public class Wards implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
	
	@Column(name = "district_id")
	private Long districtId;
	
	
	// jhipster-needle-entity-add-field - JHipster will add fields here
}

package com.shop.bike.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shop.bike.entity.enumeration.ActionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Getter
@Setter
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "languages")
    private String languages;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActionStatus status;
	

    // jhipster-needle-entity-add-field - JHipster will add fields here
	
}

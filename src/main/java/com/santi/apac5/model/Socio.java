package com.santi.apac5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Socio")
public class Socio {
    @Id
    private String dni;

    @Column
    private String nom;

    @Column
    private String direccio;

    @ManyToOne
	@JoinColumn(name = "nom", insertable = false, updatable = false)
	@ToString.Exclude
    private Biblioteca biblioteca;
}

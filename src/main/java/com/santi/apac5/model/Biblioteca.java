package com.santi.apac5.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "Biblioteca")
public class Biblioteca {
    @Id
    private String nom;

    @Column
    private String direccio;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "biblioteca")
    @ToString.Exclude
    private List<Socio> listaSocios = new ArrayList<>();
}

package com.santi.apac5.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.santi.apac5.model.Socio;

import lombok.Data;
import lombok.ToString;

@Data
public class SocioDTO implements Serializable{
    private String dni;
    private String nom;
    private String direccio;
    @ToString.Exclude
	@JsonBackReference("listaSocios")
	private BibliotecaDTO bibliotecaDTO;

    public static SocioDTO convertToDTO(Socio socio){
        SocioDTO socioDTO = new SocioDTO();
        socioDTO.setDni(socio.getDni());
        socioDTO.setNom(socio.getNom());
        socioDTO.setDireccio(socio.getDireccio());


        return socioDTO;
    }

    public static Socio convertToEntity(SocioDTO socioDTO){
        Socio socio = new Socio();
        socio.setDni(socioDTO.getDni());
        socio.setNom(socioDTO.getNom());
        socio.setDireccio(socioDTO.getDireccio());

        return socio;
    }
}

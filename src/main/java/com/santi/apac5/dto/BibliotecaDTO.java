package com.santi.apac5.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.santi.apac5.model.Biblioteca;
import com.santi.apac5.model.Socio;

import lombok.Data;
import lombok.ToString;

@Data
public class BibliotecaDTO implements Serializable{
    private String nom;
    private String direccio;

    @ToString.Exclude
	@JsonManagedReference("listaSocios")
	private List<SocioDTO> listaSocios=new ArrayList<>();

    public static BibliotecaDTO convertToDTO(Biblioteca biblioteca){
        BibliotecaDTO bibliotecaDTO = new BibliotecaDTO();

        bibliotecaDTO.setNom(biblioteca.getNom());
        bibliotecaDTO.setDireccio(biblioteca.getDireccio());
        
        for (Socio socio:biblioteca.getListaSocios()){
            SocioDTO socioDTO = SocioDTO.convertToDTO(socio);
            bibliotecaDTO.getListaSocios().add(socioDTO);
        }

        return bibliotecaDTO;
    }

    public static Biblioteca convertToEntity(BibliotecaDTO bibliotecaDTO){
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.setNom(bibliotecaDTO.getNom());
        biblioteca.setDireccio(bibliotecaDTO.getDireccio());
        
        for (int i = 0; i < bibliotecaDTO.getListaSocios().size(); i++) {
            Socio socio = SocioDTO.convertToEntity(bibliotecaDTO.getListaSocios().get(i));
            biblioteca.getListaSocios().add(socio);
        }

        return biblioteca;
    }
}

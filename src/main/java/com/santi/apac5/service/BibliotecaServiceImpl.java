package com.santi.apac5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.santi.apac5.dto.BibliotecaDTO;
import com.santi.apac5.model.Biblioteca;
import com.santi.apac5.repository.BibliotecaRepository;

public class BibliotecaServiceImpl implements BibliotecaService{
    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BibliotecaDTO saveBiblioteca(BibliotecaDTO bibliotecaDTO) {
        Biblioteca biblioteca = BibliotecaDTO.convertToEntity(bibliotecaDTO);
        Biblioteca newBiblioteca = bibliotecaRepository.save(biblioteca);
        return BibliotecaDTO.convertToDTO(newBiblioteca);

    }

    @Override
    public BibliotecaDTO getBibliotecaByNom(String nom) {
        Optional<Biblioteca> biblioteca = bibliotecaRepository.findByNom(nom);
        if (biblioteca.isPresent()) {
            return BibliotecaDTO.convertToDTO(biblioteca.get());
        } else {
            return null;
        }
        
    }

    @Override
    public BibliotecaDTO getBibliotecaByDireccio(String direccio) {
        Optional<Biblioteca> biblioteca = bibliotecaRepository.findByDireccio(direccio);
        if (biblioteca.isPresent()) {
            return BibliotecaDTO.convertToDTO(biblioteca.get());
        } else {
            return null;
        }
    }

    @Override
    public List<BibliotecaDTO> listAllBibliotecas() {
        List<Biblioteca> lista = bibliotecaRepository.findAll();
        List<BibliotecaDTO> listaResultado = new ArrayList<BibliotecaDTO>();

        for (int i = 0; i < lista.size(); i++) {
            listaResultado.add(BibliotecaDTO.convertToDTO(lista.get(i)));
        }

        return listaResultado;
    }

    @Override
    public void deleteBiblioteca(String id) {
        bibliotecaRepository.deleteById(id);
    }
}

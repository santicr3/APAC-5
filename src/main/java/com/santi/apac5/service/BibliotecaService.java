package com.santi.apac5.service;

import java.util.List;

import javax.transaction.Transactional;

import com.santi.apac5.dto.BibliotecaDTO;

@Transactional
public interface BibliotecaService {
    BibliotecaDTO saveBiblioteca(BibliotecaDTO bibliotecaDTO);
    BibliotecaDTO getBibliotecaByNom(String nom);
    BibliotecaDTO getBibliotecaByDireccio(String direccio);
    List<BibliotecaDTO> listAllBibliotecas();
    void deleteBiblioteca(String id);
}

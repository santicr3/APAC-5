package com.santi.apac5.service;

import java.util.List;

import javax.transaction.Transactional;

import com.santi.apac5.dto.BibliotecaDTO;
import com.santi.apac5.dto.SocioDTO;

@Transactional
public interface SocioService {
    SocioDTO saveSocio(SocioDTO SocioDTO);
    SocioDTO getSocioByDNI(String dni);
    SocioDTO getSocioByNom(String nom);
    List<SocioDTO> listAllSocios(BibliotecaDTO bibliotecaDTO);
    void deleteSocio(String DNI);
}

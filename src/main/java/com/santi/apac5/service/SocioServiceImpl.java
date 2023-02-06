package com.santi.apac5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.apac5.dto.SocioDTO;
import com.santi.apac5.model.Socio;
import com.santi.apac5.repository.SocioRepository;

@Service
public class SocioServiceImpl implements SocioService{
    
    @Autowired
    private SocioRepository socioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SocioDTO saveSocio(SocioDTO SocioDTO) {
        Socio socio = SocioDTO.convertToEntity(SocioDTO);
        Socio newSocio = socioRepository.save(socio);

        return SocioDTO.convertToDTO(newSocio);
    }

    @Override
    public SocioDTO getSocioByDNI(String dni) {
        Optional<Socio> socio = socioRepository.findByDni(dni);
        if(socio.isPresent()){
            return SocioDTO.convertToDTO(socio.get());
        }else{
            return null;
        }
    }

    @Override
    public SocioDTO getSocioByNom(String nom) {
        Optional<Socio> socio = socioRepository.findByNom(nom);
        if(socio.isPresent()){
            return SocioDTO.convertToDTO(socio.get());
        }else{
            return null;
        }
    }

    @Override
    public List<SocioDTO> listAllSocios() {
        List<Socio> lista = socioRepository.findAll();
        List<SocioDTO> listaResultado = new ArrayList<SocioDTO>();
        
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(SocioDTO.convertToDTO(lista.get(i)));
        }

        return listaResultado;
    }

    @Override
    public void deleteSocio(String DNI) {
        socioRepository.deleteById(DNI);
    }
}

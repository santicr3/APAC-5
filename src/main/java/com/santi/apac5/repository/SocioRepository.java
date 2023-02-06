package com.santi.apac5.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santi.apac5.model.Socio;

@Repository
@Transactional
public interface SocioRepository extends JpaRepository<Socio, String>{
    Optional<Socio> findByDni(String dni);
    Optional<Socio> findByNom(String nom);
}

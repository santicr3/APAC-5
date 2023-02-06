package com.santi.apac5.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santi.apac5.model.Biblioteca;

@Repository
@Transactional
public interface BibliotecaRepository extends JpaRepository<Biblioteca, String> {
    Optional<Biblioteca> findByNom(String nom);
    Optional<Biblioteca> findByDireccio(String direccio);
}

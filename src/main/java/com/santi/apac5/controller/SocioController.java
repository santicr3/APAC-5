package com.santi.apac5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.santi.apac5.Apac5Application;
import com.santi.apac5.dto.BibliotecaDTO;
import com.santi.apac5.dto.SocioDTO;
import com.santi.apac5.service.BibliotecaService;
import com.santi.apac5.service.SocioService;

@RestController
public class SocioController {
    private static final Logger myLog=LoggerFactory.getLogger(Apac5Application.class);

	@Autowired
	private HttpServletRequest context;

    @Autowired
    private SocioService socioService;

    @Autowired
    private BibliotecaService bibliotecaService;

    @PutMapping("/Biblioteca/{nom}/Socio")
    public ResponseEntity<BibliotecaDTO> addSocioToBiblioteca(
        @PathVariable String nom,
        @RequestBody SocioDTO newSocioDTO){
            
        myLog.info(context.getMethod() + context.getRequestURI());

        BibliotecaDTO bibliotecaDTO = bibliotecaService.getBibliotecaByNom(nom);
        if(bibliotecaDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            newSocioDTO.setBibliotecaDTO(bibliotecaDTO);
            socioService.saveSocio(newSocioDTO);

            BibliotecaDTO bibliotecaUPD = bibliotecaService.getBibliotecaByNom(nom);
            return new ResponseEntity<>(bibliotecaUPD,HttpStatus.OK);
        }
        
    }

    @GetMapping("Biblioteca/{idBiblioteca}/Socios")
    public ResponseEntity<List<SocioDTO>> listSociosBiblioteca(@PathVariable String nom){
        BibliotecaDTO bibliotecaDTO = bibliotecaService.getBibliotecaByNom(nom);
        if (bibliotecaDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<SocioDTO> socios = socioService.listAllSocios();
            if(socios == null || socios.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(socios, HttpStatus.OK);
            }
        }
    }
}

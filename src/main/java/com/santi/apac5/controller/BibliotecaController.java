package com.santi.apac5.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.santi.apac5.Apac5Application;
import com.santi.apac5.service.BibliotecaService;
import com.santi.apac5.service.SocioService;
import com.santi.apac5.dto.BibliotecaDTO;


public class BibliotecaController {
    private static final Logger myLog=LoggerFactory.getLogger(Apac5Application.class);

	@Autowired
	private HttpServletRequest context;

    @Autowired
    private BibliotecaService bibliotecaService;

    @Autowired
    private SocioService socioService;

    @Value("${aplicacion.nombre}")
	private String nombreAplicacion;
	
	@Value("${asignatura}")
	private String nombreAsignatura;

    @GetMapping("/")
    public String index() {
    	myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        return "Welcome to " + nombreAplicacion + " de " + nombreAsignatura;
    } 

    @GetMapping("/bibliotecas")
    public ResponseEntity<List<BibliotecaDTO>> listBibliotecas(){
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        List<BibliotecaDTO> bibliotecas = bibliotecaService.listAllBibliotecas();
        if (bibliotecas == null || bibliotecas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bibliotecas, HttpStatus.OK);
        }
    }

    @GetMapping("/bibliotecas/{nom}")
    public ResponseEntity<BibliotecaDTO> showBibliotecaByNom(@PathVariable String nom){
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        BibliotecaDTO biblioteca = bibliotecaService.getBibliotecaByNom(nom);
        if(biblioteca == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(biblioteca, HttpStatus.OK);
        }
    }

    @PostMapping("/bibliotecas")
    public ResponseEntity<BibliotecaDTO> addBiblioteca(@RequestBody BibliotecaDTO newBiblioteca){
        myLog.info(context.getMethod() + context.getRequestURI());
        BibliotecaDTO biblioteca = bibliotecaService.saveBiblioteca(newBiblioteca);
        if(biblioteca == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(biblioteca, HttpStatus.OK);
        }
    }

    @PutMapping("/bibliotecas")
    public ResponseEntity<BibliotecaDTO> updateBiblioteca(@RequestBody BibliotecaDTO updBiblioteca){
        myLog.info(context.getMethod() + context.getRequestURI());
        BibliotecaDTO biblioteca = bibliotecaService.getBibliotecaByNom(updBiblioteca.getNom());
        if(biblioteca == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            BibliotecaDTO bibliotecaUPD = bibliotecaService.saveBiblioteca(updBiblioteca);
            return new ResponseEntity<>(bibliotecaUPD, HttpStatus.OK);
        }
    }

    @PostMapping(value="/prova",consumes={"application/json"})
    public void prova(@RequestBody BibliotecaDTO newBiblioteca) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	myLog.info(newBiblioteca.toString());
	}

    @DeleteMapping("/bibliotecas/{nom}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable String nom){
        bibliotecaService.deleteBiblioteca(nom);
        return new ResponseEntity<>("Biblioteca borrada", HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleError(MethodArgumentTypeMismatchException e) {
    	myLog.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getMessage());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}

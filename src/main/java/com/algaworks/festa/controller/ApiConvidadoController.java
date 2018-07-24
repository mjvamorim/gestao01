package com.algaworks.festa.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.festa.model.Convidado;
import com.algaworks.festa.repository.Convidados;

@RestController
@RequestMapping("/api/convidados")
public class ApiConvidadoController {

	@Autowired
	private Convidados convidados;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Convidado> listaConvidados() {
		return convidados.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Convidado> getConvidado(@PathVariable("id") Long id) {
		return this.convidados.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Convidado>> listar() {
		return new ResponseEntity<List<Convidado>>(new ArrayList<Convidado>(convidados.findAll()), 
				HttpStatus.OK);
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public Optional<Convidado> removeConvidado(@PathVariable("id") Long id) {
		Optional<Convidado> c = convidados.findById(id);
		convidados.deleteById(id);
		return c;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public  Convidado saveConvidado(Convidado c) {
		return convidados.save(c);
	}

}

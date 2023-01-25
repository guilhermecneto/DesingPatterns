package com.example.hrapigatewayzuul.service;

import org.springframework.stereotype.Service;

import com.example.hrapigatewayzuul.model.Cliente;

@Service
public interface ClienteService {
	
	Iterable<Cliente> buscarTodos();
	Cliente busarPorId(Long id);
	void inserir(Cliente cliente);
	void atualizar(Long id, Cliente cliente);
	void deletar(Long id);
	
}

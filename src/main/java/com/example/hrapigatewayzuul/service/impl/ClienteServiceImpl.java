package com.example.hrapigatewayzuul.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hrapigatewayzuul.model.Cliente;
import com.example.hrapigatewayzuul.model.ClienteRepository;
import com.example.hrapigatewayzuul.model.Endereco;
import com.example.hrapigatewayzuul.model.EnderecoRepository;
import com.example.hrapigatewayzuul.service.ClienteService;
import com.example.hrapigatewayzuul.service.ViaCepService;

public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();

	}

	@Override
	public Cliente busarPorId(Long id) {
		// TODO Auto-generated method stub
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	private void salvarClienteComCep(Cliente cliente) {
		// TODO Auto-generated method stub
		String cep = cliente.getEndereco().getCep();
		long cepL = Long.parseLong(cep);
		Endereco endereco = enderecoRepository.findById(cepL)
			.orElseGet(()->{
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if(clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
		
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}
	
	

}

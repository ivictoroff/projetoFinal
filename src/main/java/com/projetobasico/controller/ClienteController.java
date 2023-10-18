package com.projetobasico.controller;

import java.util.List;

import com.projetobasico.model.Cliente;
import com.projetobasico.model.dao.ClienteJpaDao;

public class ClienteController {
	
	ClienteJpaDao clienteDao;

	public ClienteController() {		
		this.clienteDao = new ClienteJpaDao();
	}
	
	/**
	 * Salva um veiculo
	 * @param veiculo
	 */
	public void salvar (Cliente cliente) {
		clienteDao.salvar(cliente);		
	}
	
	/**
	 * Retorna todas as pessoas cadastradas
	 * @return
	 */
	public List<Cliente> buscarTodos(){
		return clienteDao.buscaTodos();
	}
	
	/**
	 * Retorna todas as pessoas cadastradas com base
	 * nos dados da pessoa informada (filtro)
	 * @param veiculo
	 * @return
	 */
	public List<Cliente> buscarFiltro(Cliente cliente){
		
		if(cliente.getNome().isEmpty() && cliente.getTelefone().isEmpty()) {
			return buscarTodos();
		}else {
			return clienteDao.buscaFiltro(cliente);
		}
	}
	
	/**
	 * Exclui uma pessoa
	 * @param pessoa
	 */
	public void excluir(Cliente cliente) {
		clienteDao.delete(cliente);
	}
	
}

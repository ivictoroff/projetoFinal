package com.projetobasico.controller;

import java.util.List;

import com.projetobasico.model.Veiculo;
import com.projetobasico.model.dao.VeiculoJpaDao;


public class VeiculoController {

	VeiculoJpaDao veiculoDao;

	public VeiculoController() {		
		this.veiculoDao = new VeiculoJpaDao();
	}
	
	/**
	 * Salva uma pessoa
	 * @param veiculo
	 */
	public void salvar (Veiculo veiculo) {
		veiculoDao.salvar(veiculo);		
	}
	
	/**
	 * Retorna todas as pessoas cadastradas
	 * @return
	 */
	public List<Veiculo> buscarTodos(){
		return veiculoDao.buscaTodos();
	}
	
	/**
	 * Retorna todas as pessoas cadastradas com base
	 * nos dados da pessoa informada (filtro)
	 * @param veiculo
	 * @return
	 */
	public List<Veiculo> buscarFiltro(Veiculo veiculo){
		
		if(veiculo.getModelo().isEmpty() && veiculo.getPlaca().isEmpty()
				&& veiculo.getCor().isEmpty() && veiculo.getNomeCliente().isEmpty()
					&& veiculo.getTelefone().isEmpty()) {
			return buscarTodos();
		}else {
			return veiculoDao.buscaFiltro(veiculo);
		}
	}
	
	/**
	 * Exclui uma pessoa
	 * @param pessoa
	 */
	public void excluir(Veiculo veiculo) {
		veiculoDao.delete(veiculo);
	}
	
}

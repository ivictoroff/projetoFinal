package com.projetobasico.controller;

import java.util.List;

import com.projetobasico.model.Veiculo;
import com.projetobasico.modelDao.VeiculoJpaDao;


public class VeiculoController {

	VeiculoJpaDao veiculoDao;

	public VeiculoController() {		
		this.veiculoDao = new VeiculoJpaDao();
	}
	
	public void salvar (Veiculo veiculo) {
		veiculoDao.salvar(veiculo);		
	}

	public List<Veiculo> buscarTodos(){
		return veiculoDao.buscaTodos();
	}
	
	public List<Veiculo> buscarFiltro(Veiculo veiculo){
		
		if(veiculo.getModelo().isEmpty() && veiculo.getPlaca().isEmpty()
				&& veiculo.getCor().isEmpty() && veiculo.getCliente().getNome().isEmpty()
					&& veiculo.getCliente().getTelefone().isEmpty()) {
			return buscarTodos();
		}else {
			return veiculoDao.buscaFiltro(veiculo);
		}
	}
	

	public void excluir(Veiculo veiculo) {
		veiculoDao.delete(veiculo);
	}
	
}

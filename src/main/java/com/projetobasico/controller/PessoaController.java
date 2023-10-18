package com.projetobasico.controller;

/**
 *  As classes Controllers, reduzem a dependências entre o Model e a View.
 *  tornando o projeto mais flexível, expansível e facilita futuras alterações. 
 */

import java.util.List;
import com.projetobasico.model.Pessoa;
import com.projetobasico.model.dao.PessoaJpaDao;

public class PessoaController {

	PessoaJpaDao pessoaDao;

	public PessoaController() {		
		this.pessoaDao = new PessoaJpaDao();
	}
	
	/**
	 * Salva uma pessoa
	 * @param pessoa
	 */
	public void salvar (Pessoa pessoa) {
		pessoaDao.salvar(pessoa);		
	}
	
	/**
	 * Retorna todas as pessoas cadastradas
	 * @return
	 */
	public List<Pessoa> buscarTodos(){
		return pessoaDao.buscaTodos();
	}
	
	/**
	 * Retorna todas as pessoas cadastradas com base
	 * nos dados da pessoa informada (filtro)
	 * @param pessoa
	 * @return
	 */
	public List<Pessoa> buscarFiltro(Pessoa pessoa){
		
		if(pessoa.getNome().isEmpty() && pessoa.getCpf().isEmpty()
				&& pessoa.getEmail().isEmpty() && pessoa.getTelefone().isEmpty()) {
			return buscarTodos();
		}else {
			return pessoaDao.buscaFiltro(pessoa);
		}
	}
	
	/**
	 * Exclui uma pessoa
	 * @param pessoa
	 */
	public void excluir(Pessoa pessoa) {
		pessoaDao.delete(pessoa);
	}
	
}

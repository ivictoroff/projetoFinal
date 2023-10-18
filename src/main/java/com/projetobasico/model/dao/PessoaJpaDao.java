package com.projetobasico.model.dao;

/**
 * Classe especialista para persistência de Pessoa
 */

import java.util.List;
import com.projetobasico.model.Pessoa;

public class PessoaJpaDao extends EntityJpaDao<Long, Pessoa> {
	public void salvar(Pessoa pessoa) {
		
		try {
			begin();
			if(pessoa.getId()==0l) {
				insert(pessoa);			
			}else {
				update(pessoa);			
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}		
	}
	
	public List<Pessoa> buscaFiltro(Pessoa p) {
        return entityManager.createQuery("FROM Pessoa p where p.nome like "
        		+ " CONCAT('%',:nome,'%')or p.cpf like :cpf or p.email "
        		+ " like :email or p.telefone like :telefone order by p.nome ")
        		.setParameter("nome", p.getNome())
        		.setParameter("cpf", p.getCpf())
        		.setParameter("email", p.getEmail())
        		.setParameter("telefone", p.getTelefone())
        		.getResultList();
    }
	
	public List<Pessoa> buscaTodos() {
        return entityManager.createQuery("FROM Pessoa p order by p.nome ")
        		     		.getResultList();
    }
	
	
}

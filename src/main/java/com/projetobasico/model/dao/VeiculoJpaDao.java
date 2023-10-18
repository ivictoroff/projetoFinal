package com.projetobasico.model.dao;

import java.util.List;

import com.projetobasico.model.Veiculo;

public class VeiculoJpaDao extends EntityJpaDao <Long, Veiculo> {

	public void salvar(Veiculo veiculo) {
		
		try {
			begin();
			if(veiculo.getid()==0l) {
				insert(veiculo);			
			}else {
				update(veiculo);			
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}		
	}
	
	public List<Veiculo> buscaFiltro(Veiculo v) {
        return entityManager.createQuery("FROM Veiculo v where v.modelo like "
        		+ " CONCAT('%',:veiculo,'%')or v.placa like :placa or v.cor "
        		+ " like :cor or v.telefone like :telefone or v.NomeCliente like :NomeCliente by p.nome ")
        		.setParameter("nome", v.getModelo())
        		.setParameter("cpf", v.getPlaca())
        		.setParameter("email", v.getCor())
        		.setParameter("telefone", v.getTelefone())
        		.setParameter("NomeCliente", v.getNomeCliente())
        		.getResultList();
    }
	
	public List<Veiculo> buscaTodos() {
        return entityManager.createQuery("FROM Veiculo v order by v.modelo ")
        		     		.getResultList();
    }
	
	
}
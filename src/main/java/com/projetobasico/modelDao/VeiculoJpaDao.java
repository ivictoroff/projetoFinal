package com.projetobasico.modelDao;

import java.util.List;

import com.projetobasico.model.Veiculo;

public class VeiculoJpaDao extends EntityJpaDao <Long, Veiculo> {

	public void salvar(Veiculo veiculo) {
		
		try {
			begin();
			if(veiculo.getId()==0l) {
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
        		.setParameter("nome", v.getCliente().getNome())
        		.setParameter("telefone", v.getCliente().getTelefone())
        		.getResultList();
    }
	
	public List<Veiculo> buscaTodos() {
        return entityManager.createQuery("FROM Veiculo v order by v.modelo ")
        		     		.getResultList();
    }
	
	
}
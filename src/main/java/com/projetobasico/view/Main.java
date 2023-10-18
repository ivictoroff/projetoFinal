package com.projetobasico.view;

import com.projetobasico.model.Pessoa;
import com.projetobasico.model.dao.PessoaJpaDao;

public class Main {

	public static void main(String[] args) {
		
		FrmEditPessoa frm = new FrmEditPessoa();
		//mostra o formulario
		frm.setVisible(true);
		
	}	
}
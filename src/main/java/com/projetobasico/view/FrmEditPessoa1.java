package com.projetobasico.view;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.projetobasico.model.Cliente;
import com.projetobasico.controller.ClienteController;
import com.projetobasico.controller.VeiculoController;
import com.projetobasico.model.Veiculo;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FrmEditPessoa1 extends JFrame {
	//Campos declareados como atributos (Globais na classe)
	private JTextField txtId;
	private JTextField txtModelo;	
	private JTextField txtCor;
	private JTextField txtPlaca;
	private JTextField txtTelefone;
	private JTable tableVeiculo;
	private JTable tableCliente;
	private JTextField txtNome;
	private DefaultTableModel modelo;
	
	//Lista que irá carregar a tabela
	private List<Veiculo> listaVeiculo = new ArrayList<>();
	private List<Cliente> listaCliente = new ArrayList<>();
	//Pessoa que irá espelhar os campos
	private Veiculo veiculoAtual = new Veiculo();
	private Cliente clienteAtual = new Cliente();
	//Controller responsavel por persistir Pessoa
	private VeiculoController veiculoController;
	private ClienteController clienteController;

	/**
	 * Método construtor do formulário
	 */
	public FrmEditPessoa1(){
		veiculoController = new VeiculoController();
		clienteController = new ClienteController();
		
		//cofiguração do formulário
		setTitle("Manter Veiculo");
		setSize(700,495);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//criação dos paineis principais
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JPanel panelRodape = new JPanel();
		getContentPane().add(panelRodape, BorderLayout.SOUTH);
		
		
		/*Inseindo os campos no formulário*/
		JLabel lblTitulo = new JLabel("Cadastro de Veiculo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelSuperior.add(lblTitulo);
		
		//legenda
		JLabel lbId = new JLabel("Código:");
		lbId.setBounds(10, 11, 72, 23);
		lbId.setFont(new Font("Tahoma", Font.BOLD, 12));
		//campo de entrada
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(119, 11, 104, 23);
		txtId.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtId.setColumns(10);
		
		//legenda
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModelo.setBounds(10, 41, 99, 23);
		//campo de entrada
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtModelo.setBounds(119, 41, 543, 23);
		txtModelo.setColumns(40);
		
		//legenda
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPlaca.setBounds(10, 79, 99, 14);
		
		//campo de entrada
		txtPlaca = new JTextField();
		txtPlaca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPlaca.setBounds(119, 75, 182, 23);
		panelCentral.add(txtPlaca);
		txtPlaca.setColumns(10);

		//legenda
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCor.setBounds(371, 79, 99, 14);
		//campo texto
		txtCor = new JTextField();
		txtCor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCor.setBounds(480, 75, 182, 23);
		txtCor.setColumns(10);
		
		//legenda
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(10, 113, 99, 23);
		//campo texto
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefone.setBounds(119, 113, 279, 23);
		txtTelefone.setColumns(10);
		
		//legenda
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(281, 152, 46, 14);
		//campo texto
		txtNome = new JTextField();
		txtNome.setBounds(337, 147, 86, 23);
		txtNome.setColumns(10);
		
		//inserindo os campos no painel Central
		panelCentral.add(lbId);
		panelCentral.add(txtId);
		panelCentral.add(lblModelo);
		panelCentral.add(txtModelo);
		panelCentral.add(lblPlaca);
		panelCentral.add(lblCor);
		panelCentral.add(txtCor);
		panelCentral.add(lblTelefone);
		panelCentral.add(txtTelefone);
		panelCentral.add(lblNome);
		panelCentral.add(txtNome);
				
		//Configurações da Tabela que irá listar as pessoas
		tableVeiculo = new JTable();
		tableVeiculo.addMouseListener(new MouseAdapter() {
			//configuração do evento click na tablea
			@Override
			public void mouseClicked(MouseEvent e) {
				//verifica a linha que foi clicada
				int linha = tableVeiculo.getSelectedRow();
				veiculoAtual = listaVeiculo.get(linha);
				clienteAtual = listaCliente.get(linha);
				preencheCampos(veiculoAtual);
			}
		});
		tableVeiculo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableVeiculo.setBounds(10, 178, 664, 188);
		
		//Definindo as colunas da tabela
        String[] colunas = {"Modelo", "Placa" , "Telefone"};
        String[][] objetos = {{"", "", ""}};

        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });
        tableVeiculo.setModel(modelo);
		
        JScrollPane scroll = new JScrollPane(tableVeiculo);
        scroll.setSize(664, 212);
        scroll.setLocation(10, 177);
		panelCentral.add(scroll);
		
		//Criação e configuração dos botões
		//Botão pesquisar
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				carregaPessoaAtual();
				listaVeiculo = veiculoController.buscarFiltro(veiculoAtual);
				listaCliente = clienteController.buscarFiltro(clienteAtual);
				carregarTabela();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//Botão novo
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veiculoAtual = new Veiculo();
				clienteAtual = new Cliente();
				preencheCampos(veiculoAtual);
				//preencheCampos(clienteAtual);
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//Botão salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregaPessoaAtual();
				veiculoController.salvar(veiculoAtual);
				clienteController.salvar(clienteAtual);
				txtId.setText(veiculoAtual.getid().toString());
				txtId.setText(clienteAtual.getId().toString());
				listaVeiculo = veiculoController.buscarTodos();
				listaCliente = clienteController.buscarTodos();
				carregarTabela();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//Botão excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veiculoController.excluir(veiculoAtual);
				clienteController.excluir(clienteAtual);
				veiculoAtual=new Veiculo();
				clienteAtual=new Cliente();
				preencheCampos(veiculoAtual);
				//preencheCampos(clienteAtual);
				listaVeiculo = veiculoController.buscarTodos();
				listaCliente = clienteController.buscarTodos();
				carregarTabela();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//insterindo os botões no Painel rodapé
		panelRodape.setLayout(new GridLayout(0, 4, 0, 0));
		panelRodape.add(btnPesquisar);
		panelRodape.add(btnNovo);
		panelRodape.add(btnSalvar);
		panelRodape.add(btnExcluir);


	}

	/**
	 * Preenche a tabela com todos os elementos da lista de pessoa
	 */
	public void carregarTabela() {
			String[] colunas = {"Nome", "CPF","e-mail"};
	        			
			String[][] objetos = new String [listaVeiculo.size() + listaCliente.size()][3];
		
			int i=0;
			for(Veiculo veiculo:listaVeiculo) {
				objetos[i]= new String[]{veiculo.getModelo(),veiculo.getPlaca()};
				i++;
			}
			
			for (Cliente cliente:listaCliente) {
				objetos[i]= new String[]{cliente.getTelefone()};
				i++;
			}
			
	        modelo = (new DefaultTableModel(objetos,colunas){
	            @Override
	            public boolean isCellEditable(int row, int column){
	                return false;
	            }
	        });
	        
	        tableVeiculo.setModel(modelo);
	}
	
	/**
	 * Insere nos campos, os dados de uma pessoa
	 * @param veiculo
	 */
	void preencheCampos(Veiculo veiculo) {
		txtId.setText(veiculo.getid().toString());
		txtModelo.setText(veiculo.getModelo());
		//txtTelefone.setText(cliente.getTelefone());
		txtPlaca.setText(veiculo.getPlaca());
		txtCor.setText(veiculo.getCor());
		//txtNome.setText(cliente.getNome());
		txtCor.setText(veiculo.getCor());		
	}
	
	/**
	 * Carrega para a Pessoa Atual os dados que estão no formulário
	 */
	void carregaPessoaAtual() {
		if(!txtId.getText().isEmpty())
			veiculoAtual.setId(Long.parseLong(txtId.getText()));
		veiculoAtual.setPlaca(txtPlaca.getText());
		clienteAtual.setTelefone(txtTelefone.getText());
		veiculoAtual.setModelo(txtModelo.getText());
		clienteAtual.setNome(txtNome.getText());
		veiculoAtual.setCor(txtCor.getText());		
	}
}

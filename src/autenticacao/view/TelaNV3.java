package autenticacao.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import autenticacao.controller.ProdutoraController;
import autenticacao.model.Produtora;
import util.RoundedBorder;

/**
 * Tela onde é exibida os agrotóxicos empregados pelas produtoras agrícolas.
 *
 */
public class TelaNV3 {

	private JFrame frame;
	private JLabel lbTitulo;
	private JLabel lbProdutoras;
	private JComboBox<String> cbxProdutoras;
	private JTextArea taInformacoes;
	private JButton btSair;
	private JButton btConsultar;
	private Cursor cursor = new Cursor(12);
	private JScrollPane spRolagemInformacoes;
	private TelaInicial telaInicial;


	/**
	 * Create the application.
	 */
	public TelaNV3() {
		initialize();
	}

	public TelaNV3(TelaInicial telaInicial) {
		this.telaInicial = telaInicial;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 607, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);
		
		lbTitulo = new JLabel("Agrotóxicos");
		lbTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
		lbTitulo.setBounds(33, 12, 300, 38);
		lbTitulo.setForeground(new Color(0, 175, 156));
		frame.getContentPane().add(lbTitulo);
		
		cbxProdutoras = new JComboBox<>();
		cbxProdutoras.setMaximumRowCount(5);
		cbxProdutoras.setFont(new Font("Dialog", Font.PLAIN, 14));
		cbxProdutoras.setBounds(33, 160, 545, 32);
		cbxProdutoras.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione uma opção..", "Amaggi", "SLC Agrícola", "Bom Futuro", 
				"Informações da Produção Agrícola", "Informações de Dados Fiscais"}));
		cbxProdutoras.setSelectedIndex(0);
		cbxProdutoras.setBackground(Color.WHITE);
		cbxProdutoras.setCursor(cursor);
		frame.getContentPane().add(cbxProdutoras);
		
		lbProdutoras = new JLabel("Produtoras");
		lbProdutoras.setFont(new Font("Dialog", Font.PLAIN, 16));
		lbProdutoras.setBounds(33, 129, 89, 15);
		frame.getContentPane().add(lbProdutoras);
		
		taInformacoes = new JTextArea();
		taInformacoes.setEditable(true);
		taInformacoes.setBounds(33, 300, 545, 258);
		taInformacoes.setFont(new Font("Dialog", Font.PLAIN, 15));
		taInformacoes.setBackground(Color.WHITE);
		taInformacoes.setBorder(new CompoundBorder(new LineBorder(new Color(193, 193, 193), 0, true), new EmptyBorder(0, 10, 0, 0)));
		
		spRolagemInformacoes = new JScrollPane();
		spRolagemInformacoes.setBounds(33, 300, 545, 258);
		spRolagemInformacoes.setBackground(Color.WHITE);
		spRolagemInformacoes.setForeground(new Color(193, 193, 193));
		spRolagemInformacoes.setBorder(new RoundedBorder(5));
//		spRolagemInformacoes.setBorder(new LineBorder(new Color(193, 193, 193), 1, true));
		spRolagemInformacoes.setViewportView(taInformacoes);
		frame.getContentPane().add(spRolagemInformacoes);
		
		btSair = new JButton("Sair");
		btSair.setBounds(33, 580, 117, 38);
		btSair.setFont(new Font("Dialog", Font.BOLD, 14));
		btSair.setBorder(new RoundedBorder(5));
		btSair.setBackground(Color.WHITE);
		btSair.setForeground(new Color(0, 175, 156));
		btSair.setCursor(cursor);
		btSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		frame.getContentPane().add(btSair);
		
		btConsultar = new JButton("Consultar");
		btConsultar.setBounds(461, 580, 117, 38);
		btConsultar.setFont(new Font("Dialog", Font.BOLD, 14));
		btConsultar.setBorder(new LineBorder(new Color(0, 175, 156), 1, true));
		btConsultar.setBackground(new Color(0, 175, 156));
		btConsultar.setForeground(Color.WHITE);
		btConsultar.setCursor(cursor);
		btConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		frame.getContentPane().add(btConsultar);
		
	}
	
	/**
	 * Retorna uma produtora referente a seleção do usuário.
	 */
	private void consultar() {
		
		switch (cbxProdutoras.getSelectedIndex()) {
		case 1:
			listarAgrotoxicos(ProdutoraController.consultarProdutora("Amaggi"));
			break;
		case 2:
			listarAgrotoxicos(ProdutoraController.consultarProdutora("SLC Agrícola"));
			break;
		case 3:
			listarAgrotoxicos(ProdutoraController.consultarProdutora("Bom Futuro"));
			break;
		case 4:
			this.frame.dispose();
			new TelaNV1(telaInicial).exibir();
			break;
		case 5:
			this.frame.dispose();
			new TelaNV2(telaInicial).exibir();;
			break;
		default:
			break;
		}
		
	}

	/**
	 * Lista os agrotóxicos empregados por uma produtora.
	 * 
	 * @param produtora
	 */
	private void listarAgrotoxicos(Produtora produtora) {
		
		taInformacoes.setText("");
	
		for (String agrotoxico : produtora.getAgrotoxicos()) {
			taInformacoes.append(agrotoxico.toUpperCase() + System.lineSeparator());
		}
		
		alertarAgrotoxicosProibidos(produtora.getAgrotoxicos());
	
	}
	
	/**
	 * Emite um alerta a produtora se a mesma utiliza um agrotóxico proibido.
	 * 
	 * @param agrotoxicos
	 */
	public void alertarAgrotoxicosProibidos(List<String> agrotoxicos) {
		
		int contagemAgrotoxicos = 0;
		
		try (Scanner scan = new Scanner(new File("agrotoxicos_proibidos.txt"))) {
			
			while (scan.hasNextLine()) {
				if (agrotoxicos.contains(scan.next()))
					contagemAgrotoxicos++;
			}
			
			if (contagemAgrotoxicos > 0)
				JOptionPane.showMessageDialog(null, "Essa lista contém agrotóxico proibido pela Anvisa", "Alerta", JOptionPane.WARNING_MESSAGE, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void sair() {
		this.frame.dispose();
		this.telaInicial.exibir();
	}
	
	public void exibir() {
		this.frame.setVisible(true);
	}
}


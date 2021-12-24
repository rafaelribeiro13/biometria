package autenticacao.view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import autenticacao.controller.FuncionarioController;
import autenticacao.model.Cargo;
import autenticacao.model.Funcionario;
import util.RoundedBorder;

/**
 * Tela Inicial onde o usuário entra com sua digital e a aplicação,
 * internamente, processa e realiza uma consulta no banco de dados.
 *
 */
public class TelaInicial {

	private JFrame frame;
	private JTextArea taCampoDigital;
	private JLabel lbArrasteSuaDigital;
	private JButton btSair;
	private JButton btEntrar;
	private Cursor cursor = new Cursor(12);
	private JLabel lbTitulo;
	private List<File> digitaisDropadas = new ArrayList<>();
	private Funcionario func;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 607, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
//		frame.getContentPane().setBackground(new Color(250, 255, 255));
		frame.getContentPane().setBackground(Color.WHITE);
		
		taCampoDigital = new JTextArea();
		taCampoDigital.setEditable(false);
		taCampoDigital.setBounds(203, 146, 165, 186);
		taCampoDigital.setBorder(new LineBorder(new Color(193, 193, 193), 2, true));
		taCampoDigital.setDropTarget(new DropTarget() {
			private static final long serialVersionUID = 1L;
			
			/**
			 * Recebe as digitadas soltadas pelo usuário e realiza a consulta da mesma 
			 * no banco de dados.
			 * 
			 * @param evt
			 */
			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		        	
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            digitaisDropadas = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		           
		            for (File digital : digitaisDropadas) {
		            	func = FuncionarioController.consultarFuncionario(digital.getAbsolutePath());
		            	if (func != null) {
		            		JOptionPane.showMessageDialog(null, "Digital analisada com sucesso. Acesso permitido!");
		            		digitaisDropadas.clear();
		            		digitaisDropadas.add(digital);
		            	} else {
		            		JOptionPane.showMessageDialog(null, "Sem permissão de acesso!");
		            	}
		            	
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		frame.getContentPane().add(taCampoDigital);
		
		lbArrasteSuaDigital = new JLabel("Arraste sua digital no campo abaixo.");
		lbArrasteSuaDigital.setFont(new Font("Dialog", Font.PLAIN, 16));
		lbArrasteSuaDigital.setBounds(151, 110, 297, 24);
		frame.getContentPane().add(lbArrasteSuaDigital);
		
		btSair = new JButton("Sair");
		btSair.setBounds(137, 344, 117, 38);
		btSair.setBorder(new RoundedBorder(5));
		btSair.setBackground(new Color(250, 255, 255));
		btSair.setFont(new Font("Dialog", Font.BOLD, 14));
		btSair.setForeground(new Color(0, 175, 156));
		btSair.setCursor(cursor);
		btSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		frame.getContentPane().add(btSair);
		
		btEntrar = new JButton("Entrar");
		btEntrar.setBounds(314, 344, 117, 38);
		btEntrar.setBorder(new RoundedBorder(5));
		btEntrar.setBackground(new Color(250, 255, 255));
		btEntrar.setFont(new Font("Dialog", Font.BOLD, 14));
		btEntrar.setForeground(new Color(0, 175, 156));
		btEntrar.setCursor(cursor);
		btEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (func == null) 
					JOptionPane.showMessageDialog(null, "Por favor, insira sua digital no campo indicado!");
				
				if (digitaisDropadas != null && !digitaisDropadas.isEmpty())
					if (func != null)
						logar();
			}
		});
		frame.getContentPane().add(btEntrar);
		
		lbTitulo = new JLabel("AmbTech");
		lbTitulo.setFont(new Font("Dialog", Font.BOLD, 36));
		lbTitulo.setBounds(31, 26, 197, 43);
		lbTitulo.setForeground(new Color(0, 175, 156));
		frame.getContentPane().add(lbTitulo);
	}
	
	private void sair() {
		this.frame.dispose();
	}
	
	/**
	 * Verifica o cargo de um funcionário e redireciona sua entrada a uma
	 * parte do sistema de acordo com seu nível de relevância.
	 * 
	 */
	private void logar() {
		
		digitaisDropadas.clear();
		this.frame.setVisible(false);
		
		if (func.getCargo().equalsIgnoreCase(Cargo.MINISTRO_AMBIENTE.getCargo())) {
			new TelaNV3(getClasse()).exibir();
		} else if (func.getCargo().contains(Cargo.DIRETOR.getCargo())) {
			new TelaNV2(getClasse()).exibir();
		} else {
			new TelaNV1(getClasse()).exibir();
		}
		
	}
	
	private TelaInicial getClasse() {
		return this;
	}
	
	public void exibir() {
		this.frame.setVisible(true);
	}
	
}

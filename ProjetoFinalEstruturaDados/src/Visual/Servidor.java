package Visual;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Controle.ServidorControle;
import ServidorConexao.ServidorConexao;

public class Servidor extends JFrame implements Runnable
{
	JTextField nomeCliente = null;
	JComboBox<String> atendimentoTipo = null;
	JRadioButton preferenciaSim = null;
	JRadioButton preferenciaNao = null;
	ButtonGroup grupoRadioBu = null;
	JButton ok = null;
	JButton cancelar = null;
	JLabel avisoNome = null;
	JLabel avisoAtendimento = null;
	JLabel avisoPreferencial = null;
	String [] atendimento = {"Abertura de conta","Pagamento","FGTS"};
	
	//Paineis para Layout
	JPanel painelNome = null;
	JPanel painelAtendimento = null;
	JPanel painelPreferencial = null;
	
	ServidorControle controle = null;
	
	Socket servidorCadastro = null;
	DataInputStream entrada = null;
	public DataOutputStream saida = null;
	
	public Servidor () 
	{
		setTitle("Cadastro Fila");
		setSize(300, 400);
		setLayout(new GridLayout(8,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//Inicaiação do componentes visual
		nomeCliente = new JTextField(15);
		atendimentoTipo = new JComboBox<String>(atendimento);
		preferenciaSim = new JRadioButton("Sim");
		preferenciaNao = new JRadioButton("Não");
		grupoRadioBu = new ButtonGroup();
		grupoRadioBu.add(preferenciaSim);
		grupoRadioBu.add(preferenciaNao);
		ok = new JButton("ok");
		cancelar = new JButton("Cancelar");
		avisoNome = new JLabel("Nome: ");
		avisoAtendimento = new JLabel("Tipo de Atendimento: ");
		avisoPreferencial = new JLabel("Atendimento Preferecial? ");
		
	
		preferenciaNao.setActionCommand("Não");
		preferenciaSim.setActionCommand("Sim");
		//Paineis com seus layout
		painelNome = new JPanel();
		painelNome.setLayout( new FlowLayout());
		painelAtendimento = new JPanel();
		painelAtendimento.setLayout(new FlowLayout());
		painelPreferencial =  new JPanel();
		painelPreferencial = new JPanel();
				
		painelNome.add(avisoNome);
		painelNome.add(nomeCliente);
		painelAtendimento.add(avisoAtendimento);
		painelAtendimento.add(atendimentoTipo);
		painelPreferencial.add(avisoPreferencial);
		painelPreferencial.add(preferenciaSim);
		painelPreferencial.add(preferenciaNao);
		
		add(painelNome);
		add(painelAtendimento);		
		add(painelPreferencial);
		add (ok);
		add(cancelar);
		
		controle = new ServidorControle(this);
		setVisible(true);
		ok.addActionListener(controle);
		cancelar.addActionListener(controle);
		
		try{
			servidorCadastro = new Socket("localhost", 3067);
			entrada = new DataInputStream(servidorCadastro.getInputStream());
			saida = new DataOutputStream(servidorCadastro.getOutputStream());
			new Thread(this).start();
			}
			catch(Exception e){}
	}

	public JButton getOk() {
		return ok;
	}

	public void setOk(JButton ok) {
		this.ok = ok;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

	public JTextField getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(JTextField nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try
			{
				String msg = entrada.readUTF();
		
			}catch(Exception e){}
		}
		
	}
	
	public String toString()
	{
		return nomeCliente.getText()+";"+grupoRadioBu.getSelection().getActionCommand() +";"+atendimentoTipo.getSelectedItem()
		+";";
	}
	
	

}


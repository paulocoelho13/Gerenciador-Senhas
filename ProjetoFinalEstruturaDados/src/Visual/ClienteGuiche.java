package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteGuiche extends JFrame implements ActionListener {
	Socket clienteGuiche = null;
	DataInputStream entradaGuiche = null;
	public DataOutputStream saidaGuiche = null;	
	JButton btProximaSenha = null;
	JLabel lblPassarnomecliente = null;
	JLabel lblPassarstatusprefericial = null;
	String mensagem = null;
	
	ServerSocket ClienteConexao = null;
	Socket clienteDisplay = null;
	DataOutputStream saidaClienteConexao = null;
	DataInputStream entradaClienteConexao = null;
	
	
	private JPanel contentPane;

	public ClienteGuiche() {
		setTitle("Guichê");
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btProximaSenha = new JButton("Proxima Senha");
		btProximaSenha.setBounds(138, 213, 123, 23);
		contentPane.add(btProximaSenha);
		
		JLabel lblNewLabel = new JLabel("Guich\u00EA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 11, 226, 23);
		contentPane.add(lblNewLabel);
		
		JLabel IblNumeroGhuiche = new JLabel("01");
		IblNumeroGhuiche.setHorizontalAlignment(SwingConstants.CENTER);
		IblNumeroGhuiche.setFont(new Font("Tahoma", Font.PLAIN, 18));
		IblNumeroGhuiche.setBounds(90, 45, 226, 23);
		contentPane.add(IblNumeroGhuiche);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(42, 104, 46, 14);
		contentPane.add(lblNome);
		
		lblPassarnomecliente = new JLabel("PassarNomeCliente");
		lblPassarnomecliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassarnomecliente.setBounds(90, 104, 100, 14);
		contentPane.add(lblPassarnomecliente);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(254, 104, 46, 14);
		contentPane.add(lblSenha);
		
		JLabel lblPassarsenhacliente = new JLabel("PassarSenhaCliente");
		lblPassarsenhacliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassarsenhacliente.setBounds(310, 104, 95, 14);
		contentPane.add(lblPassarsenhacliente);
		
		JLabel lblPreferecial = new JLabel("Preferecial:");
		lblPreferecial.setBounds(101, 147, 66, 14);
		contentPane.add(lblPreferecial);
		
		lblPassarstatusprefericial = new JLabel("passarStatusPrefericial");
		lblPassarstatusprefericial.setBounds(254, 147, 46, 14);
		contentPane.add(lblPassarstatusprefericial); 
		
		btProximaSenha.addActionListener(this);
		//Inciaciação das variveis de conexao
		try {
			clienteGuiche = new Socket("localhost", 33100);
			entradaGuiche = new DataInputStream(clienteGuiche.getInputStream());
			saidaGuiche = new DataOutputStream(clienteGuiche.getOutputStream());
			new Thread(runServidorConexao).start();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		try {
			ClienteConexao = new ServerSocket(3300);
			new Thread(runClienteDisplay).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	Runnable runServidorConexao = new Runnable() {
		
		@Override
		public void run() 
		{
			while (true)
			{
				try {
					setMensagem(entradaGuiche.readUTF());
					String mensagemVetor [] = getMensagem().split(";");
					String nome = mensagemVetor[0];
					String antendimento = mensagemVetor[1];
					String preferencial = mensagemVetor[2];
					lblPassarnomecliente.setText(nome);
					lblPassarstatusprefericial.setText(preferencial);
					saidaClienteConexao.writeUTF(lblPassarnomecliente.getText());
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	};
Runnable runClienteDisplay = new Runnable() {
		
		@Override
		public void run() {
			try {
				clienteDisplay = ClienteConexao.accept();
				entradaClienteConexao = new DataInputStream(clienteDisplay.getInputStream());
				saidaClienteConexao = new DataOutputStream(clienteDisplay.getOutputStream());
				new Thread(this).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (true)
			{
//				try {
//					saidaClienteConexao.writeUTF(mensagem);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
	
			
		}
	};

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btProximaSenha) 
		{
		
			try {
				saidaGuiche.writeUTF("true");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	

	
}

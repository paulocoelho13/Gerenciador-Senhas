package ServidorConexao;

import Visual.Cliente;
import Class.ClientePessoa;
import Array.PersonalArrayListProf;
import Array.fila;
import Array.pilha;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ClosedByInterruptException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServidorConexao extends JFrame {
	
	JTextArea areaTexto = null;
	JTextField campoTexto = null;
	ServerSocket servidorConexaoGuiche = null;
	ServerSocket servidorConexaoCadastro = null;
	Socket clienteGuiche = null;
	Socket servidorCadastro = null;
	Socket cliente = null;
	DataInputStream entrada = null;
	DataOutputStream saida = null;
	DataInputStream entradaGuiche = null;
	DataOutputStream saidaGuiche = null;
	
	pilha<ClientePessoa> vetorClientePessoa = new pilha<ClientePessoa>();
	
	
	
	public ServidorConexao ()
	{
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Servidor aguardando...");
		setLayout(new BorderLayout());
		
		//Criacao dos objetos visuais
		campoTexto = new JTextField(10);
		areaTexto = new JTextArea(10,10);
		JScrollPane borda = new JScrollPane(areaTexto);
		
		//Adiciona os componenentes a janela
		add(borda, BorderLayout.CENTER);
		
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout());
		painel.add(campoTexto);
		add(painel, BorderLayout.SOUTH);
		
		setVisible(false);
		
		try {
			servidorConexaoCadastro = new     ServerSocket(3067);
			new Thread(run).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			servidorConexaoGuiche = new ServerSocket(33100);
			new Thread(runGuiche).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	Runnable run = new Runnable() {
		
		@Override
		public void run() {
			try {
				servidorCadastro = servidorConexaoCadastro.accept();
				entrada = new DataInputStream(servidorCadastro.getInputStream());
				saida = new DataOutputStream(servidorCadastro.getOutputStream());
				new Thread(this).start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (true){
				
					String msg;
					try {
						msg = entrada.readUTF();
						String mensagemVetor[] = msg.split(";");
						String nome = mensagemVetor[0];
						String preferencial = mensagemVetor[1];
						String anetendimento = mensagemVetor[2];
					    ClientePessoa pessoa = new ClientePessoa(nome,anetendimento,preferencial);
						vetorClientePessoa.adicionaNome(pessoa);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
					System.out.println(vetorClientePessoa.retornaTamanhoLista());

		}
			
			
		}
		
		
		
	};
	
Runnable runGuiche = new Runnable() {
		
	
		@Override
		public void run() {
			
			
			try {
				clienteGuiche = servidorConexaoGuiche.accept();
				entradaGuiche = new DataInputStream(clienteGuiche.getInputStream());
				saidaGuiche = new DataOutputStream(clienteGuiche.getOutputStream());
				new Thread(this).start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while (true){
				try
				{
					String mensagem = entradaGuiche.readUTF();
					if (mensagem.equals("true"))
					{
						saidaGuiche.writeUTF(vetorClientePessoa.removePilha().toString());
					}
					
				}catch(Exception e){}
			
		
			}
		}
	};
//	
//	Runnable runDisplay = new Runnable() {
//		public void run()
//		{
//			try {
//				cliente = servidorConexaoClienteDisplay.accept();
//				entradaClienteDisplay = new DataInputStream(cliente.getInputStream());
//				saidaClienteDisplay = new DataOutputStream(cliente.getOutputStream());
//				new Thread(this).start();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			{
//				while (true){
//				try {
//					if (mensagemGuicherSaida.equals(null))
//					{
//						mensagemGuicherSaida = "";
//					} else {
//					mensagemGuicherSaida = entradaGuiche.readUTF();
//					saidaClienteDisplay.writeUTF(mensagemGuicherSaida);
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				}
//			}
//			
//		}
//	};
	
}
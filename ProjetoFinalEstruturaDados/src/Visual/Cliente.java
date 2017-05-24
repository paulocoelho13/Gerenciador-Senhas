package Visual;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.AbstractListModel;

public class Cliente extends JFrame implements Runnable {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Cliente frame = new Cliente();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	Socket clienteDisplay = null;
	DataInputStream entradaClienteDilplay = null;
	DataOutputStream saidaClienteDisplay = null;
	public JLabel lblAntendimento, lblSenha, lblNewLabel_1, lblGuiche, lblGuicher = null;
	
	public Cliente() {
		
		setTitle("Display");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAntendimento = new JLabel("Atendimento:");
		lblAntendimento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAntendimento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAntendimento.setBounds(11, 11, 412, 50);
		contentPane.add(lblAntendimento);
		
		lblSenha = new JLabel("A0001");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblSenha.setBounds(21, 87, 193, 57);
		contentPane.add(lblSenha);
		
		lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(11, 62, 94, 14);
		contentPane.add(lblNewLabel_1);
		
		lblGuiche = new JLabel("Guicher");
		lblGuiche.setBounds(10, 155, 46, 14);
		contentPane.add(lblGuiche);
		
		lblGuicher = new JLabel("04");
		lblGuicher.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblGuicher.setBounds(21, 191, 193, 50);
		contentPane.add(lblGuicher);
		
		JLabel lblNewLabel_3 = new JLabel("Historico:");
		lblNewLabel_3.setBounds(251, 107, 81, 22);
		contentPane.add(lblNewLabel_3);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"sdfsdf  sadff", "sdfsdf", "sdfsdf"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(204, 143, 150, 98);
		contentPane.add(list);
		
		JLabel lblSenha_1 = new JLabel("Senha:");
		lblSenha_1.setBounds(204, 130, 46, 14);
		contentPane.add(lblSenha_1);
		
		
		JLabel lblGuicher_1 = new JLabel("Guicher");
		lblGuicher_1.setBounds(308, 130, 46, 14);
		contentPane.add(lblGuicher_1);
		
		//Conexao com serviddor
		
		try {
			clienteDisplay = new Socket ("localHost", 3300);
			entradaClienteDilplay = new DataInputStream(clienteDisplay.getInputStream());
			saidaClienteDisplay = new DataOutputStream(clienteDisplay.getOutputStream());
			new Thread(this).start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//

	@Override
	public void run() {
	while(true) {
		try {
			System.out.println(entradaClienteDilplay.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
}

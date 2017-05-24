
import Visual.Servidor;

import java.awt.EventQueue;

import ServidorConexao.ServidorConexao;
import Visual.Cliente;
import Visual.ClienteGuiche;

public class Principal 
{
	public static void main (String []args)
	{
		ServidorConexao servidorConexao = new ServidorConexao();
		Servidor servidor = new Servidor();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGuiche frame = new ClienteGuiche();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		//Mentodo para iniciar a classe Cliente que usa o WindowsBulid
		EventQueue.invokeLater(new Runnable() {
			public void run() {
      try {
					Cliente frame = new Cliente();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

}

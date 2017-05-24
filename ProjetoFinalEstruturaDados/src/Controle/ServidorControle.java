package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Array.fila;
import Visual.Servidor;

public class ServidorControle  implements ActionListener
{

	public Servidor servidor = null;
	public ServidorControle (Servidor copia)
	{
		servidor = copia;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
			try {
				servidor.saida.writeUTF(servidor.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			fila fila = new fila();
			
			fila.adicionar(servidor);
		
	}

	
	
}

package Class;

public class ClientePessoa 
{
	//teste de alteracao de codigo git
	public String nome = null;
	public String atendimento = null;
	public String preferencial = null;
	public ClientePessoa(String nome,String atendimento, String preferencial)
	{
		this.nome = nome;
		this.atendimento = atendimento;
		this.preferencial = preferencial;
		
	}
	public String toString ()
	{
		return nome+";"+atendimento+";"+preferencial;
	}
	

}

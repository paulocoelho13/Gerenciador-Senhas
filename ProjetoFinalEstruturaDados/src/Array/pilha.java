package Array;

public class pilha<T> extends PersonalArrayListProf<T>{
	void inserePilha(T nome)
	{
		super.adicionaNomeEm(nome, 0);
	}
	public T removePilha ()
	{
		return (T) super.removeElemento(0);
	}

}

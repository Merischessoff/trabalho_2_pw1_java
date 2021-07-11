package agencias;

public final class  Agencia {
	private int numero = 0;
	private String endereco = "";
	
	public Agencia() {
	}
	
	public Agencia(int numero, String endereco) {
		super();
		this.numero = numero;
		this.endereco = endereco;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Agencia [numero=" + numero + ", endereco=" + endereco + "]";
	}
	
	
}

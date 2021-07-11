package util;

public enum OpcoesMenu {	
	CADASTRAR(1), 
	PESQUISAR(2), 
	LISTAR(3), 
	CREDITAR(4),
	DEBITAR(5),
	SAIR(6);

	/*1 – Cadastrar Conta Especial
	2 – Pesquisar Conta Especial por nome do cliente
	3 – Listar todas as contas
	4 – Creditar valor na Conta Especial usando o seu número
	5 – Debitar valor da Conta Especial usando o seu número
	6 – Sair*/
	
	private final int valor;
	private String nome = "";
	
	OpcoesMenu(int valorOpcao){
		valor = valorOpcao;
		if(valorOpcao==1) {
			this.nome = "Cadastrar Conta Especial";
		}else if (valorOpcao==2) {
			this.nome = "Pesquisar Conta Especial por nome do cliente";
		}else if (valorOpcao==3) {
			this.nome = "Listar todas as contas";
		}else if (valorOpcao==4) {
			this.nome = "Creditar valor na Conta Especial usando o seu número";
		}else if (valorOpcao==5) {
			this.nome = "Debitar valor da Conta Especial usando o seu número";
		}else if (valorOpcao==6) {
			this.nome = "Sair";
		}
	}
	
	public int getValor(){
		return valor;
	}
	public String getNome() {
		return nome;
	}
	
    @Override
    public String toString(){
    	return this.getValor() + " - " + this.getNome();
    }

}

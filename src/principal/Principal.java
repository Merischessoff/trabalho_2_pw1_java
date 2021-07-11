package principal;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import agencias.Agencia;
import clientes.Cliente;
import contas.ContaEspecial;
import util.Arquivo;
import util.OpcoesMenu;

/**
 2) Reformule as classes do trabalho usando as seguintes observações:
a) Conta deve ser definida como classe abstrata ok
b) Agência deve ser definida como classe final ok
c) Definir na classe Conta uma variável de classe chamada contador, ok
incrementá-la no construtor e criar um método getContador() para acessá-la ok
d) Faça as modificações necessárias na classe ContaEspecial para que seus objetos possam ok
ser armazenados em um conjunto ordenado. Use o número da conta para a ordenação 
dos objetos
 */
public class Principal {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
	  /*3) Crie um menu com os seguintes itens:
		1 – Cadastrar Conta Especial
		2 – Pesquisar Conta Especial por nome do cliente
		3 – Listar todas as contas
		4 – Creditar valor na Conta Especial usando o seu número
		5 – Debitar valor da Conta Especial usando o seu número
		6 – Sair
		
		Observações:
		a) Você deve usar um TreeSet para armazenar os objetos do tipo Conta ok
		b) O nome do arquivo para persistência dos objetos deve ser ArquivoObjetos.bin ok
		c) No item 3 listar também o número total de contas criadas ok
		d) Neste menu antes de cadastrar tem que ler objetos que já estão cadastrados no arquivo de objetos, 
		e sempre ao sair salvar todos os objetos no mesmo arquivo
		e) Enviar projeto elaborado no Eclipse ou compatível, caso contrário o trabalho não será corrigido*/
    	boolean opcaoW = true;
    	TreeSet<ContaEspecial> listaConta = new TreeSet<ContaEspecial>();
    	Arquivo arquivos = new Arquivo();
    	while (opcaoW) {   
        	int opcao = montaMenu();
            switch(opcao){
            	case 1:
            		//1 – Cadastrar Conta Especial
            		boolean cadastra = true;
            		while(cadastra) {
            	    	List <Cliente> listaCliente = new ArrayList<Cliente>();
            	    	int clienteCadastra = 0;
	            		while(clienteCadastra==0) {
	            	    	Cliente c = new Cliente();
		                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente.");
		                    c.setNome(nome);
		                    String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente.");
		                    boolean a = c.validaCpf(cpf);
		                    if(a) {
		                    	c.setCpf(cpf);
		                    }else {
			                    while(!a) {
			                    	int aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Cpf inválido. para inserir novo cpf, digite 1, para sair digite 2"));
			                    	if(aux==1) {
			                    		cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente.");
			                    		a = c.validaCpf(cpf);
			                    		if(a)
			                    			c.setCpf(cpf);
			                    	}else {
			                    		System.exit(0);
			                    	}
			                    }
		                    }
		                    listaCliente.add(c);
		                    clienteCadastra = JOptionPane.showConfirmDialog(null, "Gostaria de cadastrar mais 1 cliente na conta especial?");
	            		}
	                    
	                    int agencia = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da agência."));
	                    String endAgencia = "Padrão";
	                    Agencia aG = new Agencia(agencia, endAgencia);
	                    double saldoConta = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o saldo da conta especial."));
	                    long numConta = Long.parseLong(JOptionPane.showInputDialog(null, "Digite o numero da conta."));
	                    double limContaEspecial = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o limite da conta especial."));
	                    ContaEspecial contaEspecial = new ContaEspecial(saldoConta, numConta, aG, limContaEspecial, listaCliente);
	                    listaConta.add(contaEspecial);
	                    cadastra = false;
            		}
            		String contas = "";
            		for (ContaEspecial aux : listaConta) {
                        if(aux != null) {
                        	contas += aux.toString() + "\n;";
                        }
                    }
            		arquivos.gravaObJetosArquivo(contas);
                    break;
                case 2:
	                //2 – Pesquisar Conta Especial por nome do cliente
	                String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente. ");
	                String contasArquivo = arquivos.LeArquivo();
	                JOptionPane.showMessageDialog(null, "Conta especial encontrada " + contasArquivo);
	                String contasArquivoVetor[] = contasArquivo.split(";");
	               	boolean achou = false;
	               	for (int i=0; i < contasArquivoVetor.length; i++) {
	               		 if(!achou) {
		                	if(contasArquivoVetor[i].contains(nome)) {
		                		JOptionPane.showMessageDialog(null, "Conta especial encontrada " + contasArquivoVetor[i]);
		                		 achou = true;
		                	}
	               		 }
	               	 }
	               	 if (!achou) {
	           			 JOptionPane.showInputDialog("Conta especial não encontrada ");
	           		 }
                	 break;
                case 3:
                	//3 – Listar todas as contas
                	String contasArquivo1 = arquivos.LeArquivo();
                	String contasArquivoVetor1[] = contasArquivo1.split(";");
                	if(!contasArquivoVetor1.equals(null)) {
	                	for (int i=0; i < contasArquivoVetor1.length; i++) {
			                JOptionPane.showMessageDialog(null, "Contas Encontradas  " + contasArquivoVetor1[i]);
		               	}
                	}else {
		                JOptionPane.showMessageDialog(null, "Nenhuma conta encontrada!");
	               	}
                	break; 
                case 4:
                	//Creditar valor na Conta Especial usando o seu número
                	/*ContaEspecial[
        	    	limite, saldo, numero,
        	          	agencia[numero,endereco]
        	 		]
        	 		Cliente[cpf,nome]*/
                    break; 
                case 5:
                	//Debitar valor da Conta Especial usando o seu número
                	/*ContaEspecial[
                	    	limite, saldo, numero,
                	          	agencia[numero,endereco]
                	 ]
                	 Cliente[cpf,nome]*/	
                     break; 
                case 6:
                	opcaoW = false;
                	break;
                }//switch                 
       }
    }
    private static int montaMenu(){
        String str = "";
        for(OpcoesMenu op : OpcoesMenu.values())
            str +=  op.toString() + "\n";
        return Integer.parseInt(JOptionPane.showInputDialog(str));             
    }
}
    
    
    
    

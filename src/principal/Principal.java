package principal;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
		TreeSet<ContaEspecial> listaContaAux = new TreeSet<ContaEspecial>(); 
    	Arquivo arquivos = new Arquivo();
    	while (opcaoW) {   
        	int opcao = montaMenu();
            switch(opcao){
            	case 1:
            		//1 – Cadastrar Conta Especial
            		listaConta = new TreeSet<ContaEspecial>();
            		listaContaAux = arquivos.LeObjetos();
            		boolean cadastra = true;
            		while(cadastra) {
            	    	List <Cliente> listaCliente = new ArrayList<Cliente>();
            	    	int clienteCadastra = 0;
	            		while(clienteCadastra==0) {
	            	    	Cliente c = new Cliente();
		                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente.");
		                    boolean temNoArquivo =  comparaLista(listaContaAux, nome);
		                    if(!temNoArquivo) {
			                    c.setNomeCliente(nome);
			                    String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente.");
			                    boolean a = c.validaCpf(cpf);
			                    if(a) {
			                    	c.setCpfCliente(cpf);
			                    }else {
				                    while(!a) {
				                    	int aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Cpf inválido. para inserir novo cpf, digite 1, para sair digite 2"));
				                    	if(aux==1) {
				                    		cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente.");
				                    		a = c.validaCpf(cpf);
				                    		if(a)
				                    			c.setCpfCliente(cpf);
				                    	}else {
				                    		System.exit(0);
				                    	}
				                    }
			                    }
			                    listaCliente.add(c);
			                    clienteCadastra = JOptionPane.showConfirmDialog(null, "Gostaria de cadastrar mais 1 cliente na conta especial?");
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Nome de cliente ja cadastrado em uma conta especial!");
		                    }
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
            		if(!listaContaAux.isEmpty()) {
            			for(ContaEspecial aux: listaContaAux) {
            				listaConta.add(aux);
            			}
            		}
            		boolean gravou = arquivos.salvaObjetos(listaConta);
            		if(gravou) {
            			JOptionPane.showMessageDialog(null, "Conta gravada com sucesso!");
            		}else {
            			JOptionPane.showMessageDialog(null, "Erro ao gravar conta!");
            		}
                    break;
                case 2:
	                //2 – Pesquisar Conta Especial por nome do cliente
                	listaConta = arquivos.LeObjetos();
	               	String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente.");
	               	boolean achou = false;
	               	achou = comparaLista(listaConta, nome);
	               	if (achou) {
	           			JOptionPane.showMessageDialog(null, "Conta especial encontrada.");
	           		}else {
	           			JOptionPane.showMessageDialog(null, "Conta especial não encontrada.");
	           		}
                	break;
                case 3:
                	//3 – Listar todas as contas
                	listaConta = arquivos.LeObjetos();
                	String msg = "";
                	int tot = listaConta.size();
                	for(ContaEspecial aux: listaConta) {
                        msg += "Conta Especial: \n " + aux.toString() + "\n";
                	}
                	JOptionPane.showMessageDialog(null, "Total de contas " + tot + "\n " + msg);
                	break; 
                case 4:
                	//Creditar valor na Conta Especial usando o seu número
                	listaContaAux = arquivos.LeObjetos();
            		listaConta = new TreeSet<ContaEspecial>();
                	long numero = Long.parseLong(JOptionPane.showInputDialog(null, "Digite o numero da conta."));
                	double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor a ser creditado na conta."));
                	if(!listaContaAux.isEmpty()) {
            			for(ContaEspecial aux: listaContaAux) {
            				if(aux.getNumero()==numero) {
            					aux.credita(valor);
            				}
            				listaConta.add(aux);
            			}
            		}
            		gravou = arquivos.salvaObjetos(listaConta);
            		if(gravou) {
            			JOptionPane.showMessageDialog(null, "Conta Creditada com sucesso!");
            		}else {
            			JOptionPane.showMessageDialog(null, "Erro ao creditar a conta!");
            		}
                    break; 
                case 5:
                	//Debitar valor da Conta Especial usando o seu número
                	listaContaAux = arquivos.LeObjetos();
            		listaConta = new TreeSet<ContaEspecial>();
                	numero = Long.parseLong(JOptionPane.showInputDialog(null, "Digite o numero da conta."));
                	valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor a ser debitado na conta."));
                	if(!listaContaAux.isEmpty()) {
            			for(ContaEspecial aux: listaContaAux) {
            				if(aux.getNumero()==numero) {
            					aux.credita(valor);
            				}
            				listaConta.add(aux);
            			}
            		}
            		gravou = arquivos.salvaObjetos(listaConta);
            		if(gravou) {
            			JOptionPane.showMessageDialog(null, "Conta debitada com sucesso!");
            		}else {
            			JOptionPane.showMessageDialog(null, "Erro ao debitar a conta!");
            		}
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
    
    private static boolean comparaLista(TreeSet<ContaEspecial> lista1, String nome) {
    	boolean achou = false;
    	for (ContaEspecial aux: lista1) {
        	int i = 0;
        	while(i < aux.getClientes().size()) {
           		 if(!achou) {
                	if(aux.getClientes().get(i).getNomeCliente().equalsIgnoreCase(nome)) {
		               	achou = true;
                	}
           		 }
           		 i++;
        	}
        }
		return achou;
    }
}
    
    
    
    

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeSet;

import clientes.Cliente;
import contas.ContaEspecial;


public class Arquivo {	
	
	public static String gravaObJetosArquivo(String string ) {
		File arquivo = new File("ArquivoObjetos.bin");
		try (OutputStream saida = new FileOutputStream(arquivo)){
			saida.write( string.getBytes() );
		return "Conta Gravada com sucesso!";
		}catch(Exception e) {
			System.out.println("Exce��o ao ler ou escrever objeto!");
			return "";
		}
	}
	
	public String LeArquivo() {
		try (InputStream entrada = new FileInputStream("ArquivoObjetos.bin")){
			int content;
			String arquivo = "";
			while ( (content = entrada.read() ) != -1) {
				arquivo += (char) content;
			}
			return arquivo;
		}catch(Exception e) {
			System.out.println("Exce��o ao ler ou escrever objeto!");
			return "";
		}
	}
	
	public  TreeSet<ContaEspecial> montaObjetos() {
		try {
			TreeSet<ContaEspecial> conjunto = new TreeSet<ContaEspecial>();
			String arquivo = LeArquivo();
			String arqConta[] = arquivo.split(";");
			if(arqConta.length > 0) {
				for(int i=0; i < arqConta.length; i++) {
					String arqCliente[] = arqConta[i].split("-");
					ContaEspecial c = new ContaEspecial();
					String arqAux[] = arqConta[i].split(",");
					int j = 0;
					while(j < arqAux.length) {
						if(arqAux[j].contains("limite"))
							c.setLimite(Double.parseDouble(
									arqAux[j].replace("ContaEspecial", "")
									.replace("[", "")
									.replace("limite", "")
									.replace("=", "").trim()));
						if(arqAux[j].contains("saldo"))
							c.setLimite(Double.parseDouble(
									arqAux[j].replace("saldo", "")
									.replace("=", "").trim()));
						if(arqAux[j].contains("numero") && !arqAux[j].contains("Agencia"))
							c.setNumero(Long.parseLong(
									arqAux[j].replace("numero", "")
									.replace("=", "")
									.replace(".0", "").trim()));
						if(arqAux[j].contains("numero") && arqAux[j].contains("Agencia"))
							c.getAgencia().setNumero(Integer.parseInt(
									arqAux[j].replace("numero", "")
									.replace("Agencia", "")
									.replace("[", "")
									.replace("=", "").trim()));
						if(arqAux[j].contains("endereco"))
							c.getAgencia().setEndereco(
									arqAux[j].replace("endereco", "")
									.replace("=", "")
									.replace("]", "").trim());
						j++;
					}
					
					j=0;
					Cliente c2 = new Cliente();
					while(j < arqCliente.length) {
						if(arqCliente[j].contains("cpf"))
							c2.setCpf(arqCliente[j]
									.replace("Cliente", "")
									.replace("cpf", "")
									.replace("[", "")
									.replace("=", "").trim());
						
						if(arqCliente[j].contains("nome"))
							c2.setNome(arqCliente[i]
									.replace("nome", "")
									.replace("=", "")
									.replace("]", "").trim());
							
						j++;
						c.getClientes().add(c2);
					}
					conjunto.add(c);
				}
			}
			return conjunto;
		}catch(Exception e) {
			System.out.println("Exce��o ao ler ou escrever objeto!");
			return null;
		}
	}
	
	
	
}

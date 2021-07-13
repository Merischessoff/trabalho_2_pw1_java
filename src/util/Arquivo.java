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
	
	//Gambiarra monstra para montar os objetos que estão no arquivo
	public  TreeSet<ContaEspecial> montaObjetos() {
		try {
			TreeSet<ContaEspecial> conjunto = new TreeSet<ContaEspecial>();
			String arquivo = LeArquivo();
			String arqConta[] = arquivo.split(";");
			if(arqConta.length > 0) {
				for(int i=0; i < arqConta.length; i++) {
					ContaEspecial c = new ContaEspecial();
					Cliente c2 = new Cliente();
					String arqAux[] = arqConta[i].split(",");
					int j = 0;
					while(j < arqAux.length) {
						if(arqAux[j].contains("limite"))
							c.setLimite(Double.parseDouble(
									arqAux[j]
									.replace("ContaEspecial", "")
									.replace("[", "")
									.replace("limite", "")
									.replace("=", "")
									.trim()));
						if(arqAux[j].contains("saldo"))
							c.setSaldo(Double.parseDouble(
									arqAux[j]
									.replace("saldo", "")
									.replace("=", "")
									.trim()));
						if(arqAux[j].contains("numero") && !arqAux[j].contains("Agencia"))
							c.setNumero(Long.parseLong(
									arqAux[j]
									.replace("numero", "")
									.replace("=", "")
									.replace(".0", "")
									.trim()));
						if(arqAux[j].contains("numeroAg") && arqAux[j].contains("Agencia"))
							c.getAgencia().setNumeroAg(Integer.parseInt(
									arqAux[j]
									.replace("numeroAg", "")
									.replace("Agencia", "")
									.replace("[", "")
									.replace("=", "")
									.trim()));
						if(arqAux[j].contains("endereco"))
							c.getAgencia().setEnderecoAg(
									arqAux[j]
									.replace("enderecoAg", "")
									.replace("=", "")
									.replace("]", "")
									.trim());
						if(arqAux[j].contains("Cliente") && j+1 < arqAux.length) {
							if(arqAux[j].contains("cpfCliente"))
								c2.setCpfCliente(arqAux[j]
										.replace("Cliente", "")
										.replace("[", "")
										.replace("cpf", "")
										.replace("=", "")
										.replace("nome", "")
										.replace("]", "")
										.replace(c2.getNomeCliente(), "").trim());
							
							if(arqAux[j+1].contains("nomeCliente"))
								c2.setNomeCliente(arqAux[j+1]
										.replace("nome", "")
										.replace("Cliente", "")
										.replace("cpf", "")
										.replace("=", "")
										.replace("]", "")
										.replace("[", "")
										.replace(c2.getCpfCliente(), "").trim()
										);
							
							if(!c2.getCpfCliente().equalsIgnoreCase("") && !c2.getNomeCliente().equalsIgnoreCase(""))
								c.getClientes().add(c2); c2 = new Cliente();
						}
						
						j++;
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

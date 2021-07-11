package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeSet;

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
	
	public  TreeSet<ContaEspecial> MontaObjetos(TreeSet<ContaEspecial> conjunto, String arquivo) {
		try {
			/*ContaEspecial[
				limite, saldo, numero,
			    agencia[numero,endereco]
			]
			Cliente[cpf,nome]*/
			String arqAux[] = arquivo.split(";");
			for(int i=0; i<arqAux.length; i++) {
				if(conjunto.size() > 0 && conjunto != null) {
					
				}
			}
			return conjunto;
		}catch(Exception e) {
			System.out.println("Exce��o ao ler ou escrever objeto!");
			return null;
		}
	}
	
	
	
}

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.TreeSet;

import clientes.Cliente;
import contas.ContaEspecial;


public class Arquivo {	
	
	public  boolean salvaObjetos(TreeSet<ContaEspecial> objetos) {
		String nomeArq = "arquivoObjetos.bin";
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArq));){
		      out.writeObject(objetos);
		      return true;
		}catch(FileNotFoundException e) {
		     System.out.println("Arquivo de escrita não encontrado!" );
		}catch(IOException e) {
		     System.out.println("Exc. ao escrever obj!");
		}
		return false;
	}
	
	public  TreeSet<ContaEspecial> LeObjetos(){
		String nomeArq = "arquivoObjetos.bin";
		TreeSet<ContaEspecial> objeto = new TreeSet<ContaEspecial>();
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeArq));){
			System.out.println(input.readObject());
		     return objeto;
		}catch(FileNotFoundException e) {
		     System.out.println("Arquivo de escrita não encontrado!" );
		}catch(IOException e) {
		     System.out.println("Exc. ao escrever obj!");
		}catch(ClassNotFoundException e) {
			 System.out.println("Arquivo não encontrado!");
		}
		return new TreeSet<ContaEspecial>();
	}
}

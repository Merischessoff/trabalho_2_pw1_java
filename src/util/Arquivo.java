package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import contas.ContaEspecial;


public class Arquivo {	
	
	public  boolean salvaObjetos(TreeSet<ContaEspecial> objetos) {
		String nomeArq = "ArquivoObjetos.bin";
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArq));){
		      out.writeObject(objetos);
		      return true;
		}catch(FileNotFoundException e) {
		     System.out.println("Arquivo de escrita não encontrado!" );
		     System.out.println(e);
		}catch(IOException e) {
		     System.out.println("Exc. ao escrever obj!");
		     System.out.println(e);
		}
		return false;
	}
	
	public  TreeSet<ContaEspecial> LeObjetos(){
		String nomeArq = "ArquivoObjetos.bin";
		TreeSet<ContaEspecial> objeto = new TreeSet<ContaEspecial>();
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeArq));){
			objeto = (TreeSet<ContaEspecial>) input.readObject();
			return objeto;
		}catch(FileNotFoundException e) {
		     System.out.println("Arquivo de escrita não encontrado!" );
		}catch(IOException e) {
		     System.out.println("Exc. ao escrever obj!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TreeSet<ContaEspecial>();
	}
	
	
	
}

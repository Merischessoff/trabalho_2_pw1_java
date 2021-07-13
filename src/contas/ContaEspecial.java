package contas;
import java.util.ArrayList;
import java.util.List;
import agencias.Agencia;
import clientes.Cliente;

public class ContaEspecial extends Conta implements Comparable<ContaEspecial>{
	private double limite=0;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public ContaEspecial() {}

	public ContaEspecial(double saldo, long numero, Agencia agencia, double limite, List<Cliente> clientes) {
		super(saldo, numero, agencia);
		this.limite = limite;
		this.setClientes(clientes);
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public double credita(double aux) {
		// TODO Auto-generated method stub
		return super.credita(aux);
	}

	@Override
	public double debita(double aux) {
		// TODO Auto-generated method stub
		return super.debita(aux);
	}

	
	@Override
	public String toString() {
		if(this.clientes.size() > 0) {
			String clientes = "";
			for(Cliente a: this.clientes){
				clientes += "Cliente[cpfCliente=" + a.getCpfCliente() + ",nomeCliente=" + a.getNomeCliente() + "],\n";
			}
			return "ContaEspecial[limite=" +limite  +  ",saldo=" + getSaldo() + ",numero=" + getNumero()+","
					+ "\n"+ getAgencia()+","
					+ "\n" + clientes + "];" ;
		}else {
			return "ContaEspecial necessida de pelo menos 1 cliente cadastrado!";
		}
	}

	@Override
	public int compareTo(ContaEspecial o) {
		if(this.getNumero() > o.getNumero()) return 1;
		if(this.getNumero() < o.getNumero()) return -1;
		return 0;
	}
	
}

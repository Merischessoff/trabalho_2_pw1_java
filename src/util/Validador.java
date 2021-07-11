package util;

public interface Validador {
	public default boolean validaCpf(String CPF) {
		return false;
	}
}

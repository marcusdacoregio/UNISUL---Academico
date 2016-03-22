package br.unisul.model.domain;

import java.util.Arrays;


public class Individuo {
	
	public int[] array = new int[8];
	public Integer qtdColisoes = null;
	private boolean mutante;
	
	public boolean isMutante() {
		return mutante;
	}
	public void setMutante(boolean mutante) {
		this.mutante = mutante;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		result = prime * result + (mutante ? 1231 : 1237);
		result = prime * result
				+ ((qtdColisoes == null) ? 0 : qtdColisoes.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individuo other = (Individuo) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		if (mutante != other.mutante)
			return false;
		if (qtdColisoes == null) {
			if (other.qtdColisoes != null)
				return false;
		} else if (!qtdColisoes.equals(other.qtdColisoes))
			return false;
		return true;
	}

}

package br.unisul.model.domain;

import java.util.List;

public class Geracao {
	
	private int numeroGeracao;
	private List<Individuo> populacao;
	
	public int getNumeroGeracao() {
		return numeroGeracao;
	}
	
	public void setNumeroGeracao(int numeroGeracao) {
		this.numeroGeracao = numeroGeracao;
	}
	
	public List<Individuo> getPopulacao() {
		return populacao;
	}
	
	public void setPopulacao(List<Individuo> populacao) {
		this.populacao = populacao;
	}

}

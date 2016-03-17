package br.unisul.controller.dto;

public class ParametrosDTO {
	
	private boolean pontoCorteAleatorio;
	private int pontoCorte;
	private int taxaMutacao;
	private int taxaRecombinacao;
	private int numeroGeracoes;
	private int tamanhoPopulacaoInicial;
	
	public ParametrosDTO(boolean pontoCorteAleatorio, int pontoCorte,
			int taxaMutacao, int taxaRecombinacao, int numeroGeracoes, int tamanhoPopulacaoInicial) {
		this.pontoCorteAleatorio = pontoCorteAleatorio;
		this.pontoCorte = pontoCorte;
		this.taxaMutacao = taxaMutacao;
		this.taxaRecombinacao = taxaRecombinacao;
		this.numeroGeracoes = numeroGeracoes;
		this.tamanhoPopulacaoInicial = tamanhoPopulacaoInicial;
	}
	
	public boolean isPontoCorteAleatorio() {
		return pontoCorteAleatorio;
	}
	public int getPontoCorte() {
		return pontoCorte;
	}
	public int getTaxaMutacao() {
		return taxaMutacao;
	}
	public int getTaxaRecombinacao() {
		return taxaRecombinacao;
	}
	public int getNumeroGeracoes() {
		return numeroGeracoes;
	}

	public int getTamanhoPopulacaoInicial() {
		return tamanhoPopulacaoInicial;
	}

}

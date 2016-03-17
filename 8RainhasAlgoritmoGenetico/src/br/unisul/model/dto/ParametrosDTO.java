package br.unisul.model.dto;

public class ParametrosDTO {
	
	private boolean pontoCorteAleatorio;
	private int pontoCorte;
	private int taxaMutacao;
	private int taxaRecombinacao;
	private int numeroGeracoes;
	
	public ParametrosDTO(boolean pontoCorteAleatorio, int pontoCorte,
			int taxaMutacao, int taxaRecombinacao, int numeroGeracoes) {
		this.pontoCorteAleatorio = pontoCorteAleatorio;
		this.pontoCorte = pontoCorte;
		this.taxaMutacao = taxaMutacao;
		this.taxaRecombinacao = taxaRecombinacao;
		this.numeroGeracoes = numeroGeracoes;
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

}

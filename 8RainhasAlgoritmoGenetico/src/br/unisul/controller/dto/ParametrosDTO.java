package br.unisul.controller.dto;

import java.util.Random;

import br.unisul.model.enums.TipoSelecao;

public class ParametrosDTO {
	
	private boolean pontoCorteAleatorio;
	private int pontoCorte;
	private int taxaMutacao;
	private int taxaRecombinacao;
	private int numeroGeracoes;
	private int tamanhoPopulacaoInicial;
	private int divisorPopulacao;
	private TipoSelecao tipoSelecaoPais;
	
	public ParametrosDTO(boolean pontoCorteAleatorio, int pontoCorte,
			int taxaMutacao, int taxaRecombinacao, int numeroGeracoes, int tamanhoPopulacaoInicial,
			int divisorPopulacao, TipoSelecao tipoSelecaoPais) {
		this.pontoCorteAleatorio = pontoCorteAleatorio;
		this.pontoCorte = pontoCorte;
		this.taxaMutacao = taxaMutacao;
		this.taxaRecombinacao = taxaRecombinacao;
		this.numeroGeracoes = numeroGeracoes;
		this.tamanhoPopulacaoInicial = tamanhoPopulacaoInicial;
		this.divisorPopulacao = divisorPopulacao;
		this.tipoSelecaoPais = tipoSelecaoPais;
		
		definirPontoDeCorte();
	}
	
	private void definirPontoDeCorte() {
		if(pontoCorteAleatorio) {
			pontoCorte = new Random().nextInt(7) + 1;
		}
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

	public int getDivisorPopulacao() {
		return divisorPopulacao;
	}

	public TipoSelecao getTipoSelecaoPais() {
		return tipoSelecaoPais;
	}

}

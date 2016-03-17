package br.unisul.controller;

import java.util.List;

import br.unisul.controller.dto.ParametrosDTO;
import br.unisul.model.Model;
import br.unisul.model.domain.Individuo;

public class Controller {
	
	ParametrosDTO parametros;
	
	Model model;

	public Controller(boolean pontoCorteAleatorio, int pontoCorte,
			int taxaMutacao, int taxaRecombinacao, int numeroGeracoes, int tamanhoPopulacaoInicial) {
		
		parametros = new ParametrosDTO(pontoCorteAleatorio, pontoCorte,
				taxaMutacao, taxaRecombinacao, numeroGeracoes, tamanhoPopulacaoInicial);
		
		model = new Model();
	}
	
	
	public void processar() {
		
		List<Individuo> populacao = model.inicializarPopulacao(parametros.getTamanhoPopulacaoInicial());
		
		for(int i = 0; i < parametros.getNumeroGeracoes(); i++) {
			
			model.avaliarPopulacao(populacao);
			
		}
		
	}
	
	
}

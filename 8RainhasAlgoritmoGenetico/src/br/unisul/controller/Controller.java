package br.unisul.controller;

import java.util.List;

import br.unisul.controller.dto.ParametrosDTO;
import br.unisul.model.Model;
import br.unisul.model.domain.Geracao;
import br.unisul.model.enums.TipoSelecao;

public class Controller {
	
	ParametrosDTO parametros;
	
	Model model;

	public Controller(boolean pontoCorteAleatorio, int pontoCorte,
			int taxaMutacao, int taxaRecombinacao, int numeroGeracoes, int tamanhoPopulacaoInicial,
			int divisorPopulacao, TipoSelecao tipoSelecaoPais) {
		
		parametros = new ParametrosDTO(pontoCorteAleatorio, pontoCorte,
				taxaMutacao, taxaRecombinacao, numeroGeracoes, tamanhoPopulacaoInicial
				, divisorPopulacao, tipoSelecaoPais);
		
		model = new Model(parametros);
	}
	
	
	public List<Geracao> processar() {
		
		return model.obterMelhorSolucao();
		
	}
	
	
}

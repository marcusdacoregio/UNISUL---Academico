package br.unisul.controller;

import br.unisul.controller.dto.ParametrosDTO;

public class Controller {
	
	ParametrosDTO parametros;

	public Controller(boolean pontoCorteAleatorio, int pontoCorte,
			int taxaMutacao, int taxaRecombinacao, int numeroGeracoes) {
		
		parametros = new ParametrosDTO(pontoCorteAleatorio, pontoCorte,
				taxaMutacao, taxaRecombinacao, numeroGeracoes);
		
	}
	
	
	
}

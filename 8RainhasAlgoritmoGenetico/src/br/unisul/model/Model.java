package br.unisul.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import br.unisul.controller.dto.ParametrosDTO;
import br.unisul.model.domain.Geracao;
import br.unisul.model.domain.Individuo;

public class Model {
	
	private static Random random;
	private ParametrosDTO parametros;
	
	public Model(ParametrosDTO parametros) {
		this.parametros = parametros;
	}
	
	public List<Geracao> obterMelhorSolucao() {
		List<Individuo> populacao = inicializarPopulacao(parametros.getTamanhoPopulacaoInicial());
		
		List<Geracao> geracoes = new ArrayList<Geracao>();
		
		for(int i = 0; i < parametros.getNumeroGeracoes(); i++) {
			Geracao geracao = new Geracao();
			geracao.setNumeroGeracao(i+1);
			geracao.setPopulacao(populacao);
			
			geracoes.add(geracao);
			
			avaliarPopulacao(populacao);
			populacao = selecionarPais(populacao);
			recombinarEaplicarMutacao(populacao);
			System.out.println("Geração " + (i+1) + " - " + populacao.size());
		}
		
		return geracoes;
	}
	
	private List<Individuo> recombinarEaplicarMutacao(List<Individuo> populacaoPais) {
		
		double taxaRecombinacao = ((double) parametros.getTaxaRecombinacao()) / 100;
		
		double resultado = populacaoPais.size() * taxaRecombinacao;
		int quantidadeIndividuosParaRecombinar = (int) resultado;
		
		List<Integer> posicoesAleatorias = gerarPosicoesAleatoriasDeArray(populacaoPais, quantidadeIndividuosParaRecombinar);
		
		recombinar(populacaoPais, posicoesAleatorias);
		
		aplicarMutacao(populacaoPais);
		
		return populacaoPais;
	}
	
	private void aplicarMutacao(List<Individuo> populacao) {
		
	}
	
	private List<Individuo> recombinar(List<Individuo> populacaoPais, List<Integer> posicoesAleatorias) {
		List<Individuo> filhosGerados = new ArrayList<Individuo>();
		
		while(posicoesAleatorias.size() > 0) {
			//Embaralha lista para pegar os pais aleatóriamente
			Collections.shuffle(posicoesAleatorias);
			
			//Verificar se existem duas posições ainda para pegar
			
			Integer posicaoPai = null;
			Integer posicaoMae = null;
			
			if(posicoesAleatorias.size() > 1) {
				posicaoPai = posicoesAleatorias.get(0);
				posicaoMae = posicoesAleatorias.get(1);
				
				//Remove as posições que ja foram usadas
				posicoesAleatorias.remove(0);
				posicoesAleatorias.remove(0);
			} else { // Caso só exista uma posição, precisamos pegar um pai ou mae aleatório na lista completa
				if(posicoesAleatorias.size() != 0) {
					posicaoPai = posicoesAleatorias.get(0);
					posicaoMae = random.nextInt(populacaoPais.size());
					//Remove as posições que ja foram usadas
					posicoesAleatorias.remove(0);
				}
			}

			Individuo pai = populacaoPais.get(posicaoPai);
			Individuo mae = populacaoPais.get(posicaoMae);
			
			int pontoDeCorte = obterPontoDeCorte(pai.array.length);
			
			Individuo filho1 = aplicarCutAndCrossfill(pai, mae, pontoDeCorte);
			Individuo filho2 = aplicarCutAndCrossfill(mae, pai, pontoDeCorte);
			
			filhosGerados.add(filho1);
			filhosGerados.add(filho2);
		}
		
		populacaoPais.addAll(filhosGerados);
		
		return populacaoPais;
	}
	
	private Individuo aplicarCutAndCrossfill(Individuo pai, Individuo mae, int pontoDeCorte) {
		
		int[] arrayEsquerdaPai = new int[pontoDeCorte];
		int[] arrayEsquerdaMae = new int[pontoDeCorte];
		
		int[] arrayDireitaPai = new int[pai.array.length - pontoDeCorte];
		int[] arrayDireitaMae = new int[mae.array.length - pontoDeCorte];
		
		for(int i = 0; i < pontoDeCorte; i++) {
			arrayEsquerdaPai[i] = pai.array[i];
			arrayEsquerdaMae[i] = mae.array[i];
		}
		
		int index = 0;
		for(int i = pontoDeCorte; i < pai.array.length; i++) {
			arrayDireitaPai[index] = pai.array[i];
			arrayDireitaMae[index] = mae.array[i];
			index++;
		}
		
		int[] arrayFilho = pai.array.clone();
		int contador = 0;
		
		for (int i = pontoDeCorte; i < arrayFilho.length; i++) {
			
			arrayFilho[i] = isValorPresenteEmArray(arrayDireitaMae[contador], pai.array) ? arrayFilho[i] : arrayDireitaMae[contador];
			contador++;
			
		}
		
		Individuo filho = new Individuo();
		filho.array = arrayFilho;
		
		return filho;
	}
	
	private int obterPontoDeCorte(int tamanhoArray) {
		
		if(random == null)
			random = new Random();
		
		if(parametros.isPontoCorteAleatorio()) {
			return random.nextInt(tamanhoArray-2) + 1;
		} else {
			if(parametros.getPontoCorte() > tamanhoArray-1) {
				throw new IllegalArgumentException("O ponto de corte deve estar entre 1 e " + (tamanhoArray-1));
			} else {
				return parametros.getPontoCorte();
			}
		}
		
	}
	
	private List<Integer> gerarPosicoesAleatoriasDeArray(List<Individuo> populacao, int quantidadePosicoes) {
		
		Set<Integer> indexIndividuos = new HashSet<Integer>();
		
		while(indexIndividuos.size() < quantidadePosicoes) {
			
			if(random == null){
				random = new Random();
			}
			
			indexIndividuos.add(random.nextInt(populacao.size()));
			
		}
		
		return new ArrayList<Integer>(indexIndividuos);
	}
	
	private Integer escolherNumeroRandomEmLista(List<Integer> lista, List<Integer> numerosJaEscolhidos) {
		if(random == null)
			random = new Random();
		
		Integer numeroRandom = null;
		
		do{
			
			numeroRandom = lista.get(random.nextInt(lista.size()));
			
		} while(numerosJaEscolhidos.contains(numeroRandom));
		
		
		return numeroRandom;
	}
	
	private List<Individuo> inicializarPopulacao(int tamanhoPopulacao) {
		
		List<Individuo> populacao = new ArrayList<Individuo>();
		
		for(int i = 0; i < tamanhoPopulacao; i++) {
			Individuo individuo = new Individuo();
			
			int[] array = {0, 1, 2, 3, 4, 5, 6, 7};
			shuffle(array);
			
			individuo.array = array;
			
			populacao.add(individuo);
		}
		
		return populacao;
	}
	
	private void avaliarPopulacao(List<Individuo> populacao) {
		for (Individuo individuo : populacao) {
			individuo.qtdColisoes = contarConflitos(individuo.array);
		}
	}
	
	private int contarConflitos(int[] array) {
		int conflitos = 0;
		
		for(int posicaoSendoAnalisada = 0; posicaoSendoAnalisada < 8; posicaoSendoAnalisada++) {
			
			int x = 1;
			
			for(int contador = posicaoSendoAnalisada + 1; contador < 8; contador++) {
				
				
				if(array[posicaoSendoAnalisada]+x == array[contador]) {
					conflitos++;
				}
				
				x++;
			}
			
			x = 1;
			
			for(int contador = posicaoSendoAnalisada + 1; contador < 8; contador++) {
				
				if(array[posicaoSendoAnalisada]-x == array[contador]) {
					conflitos++;
				}
				
				x++;
			}
				
		}
		
		return conflitos;
	}
	
	private List<Individuo> selecionarPais(List<Individuo> populacao) {
		
		int quantidadePais = populacao.size() / parametros.getDivisorPopulacao();
		
		switch (parametros.getTipoSelecaoPais()) {
			case ELITISTA:
				return populacao = selecionarPaisModoElitista(populacao, quantidadePais);
				
			case ROLETA:
				
				return null;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<Individuo> selecionarPaisModoElitista(List<Individuo> populacao, int quantidadePais) {
		
		List<Individuo> paisSelecionados = new ArrayList<Individuo>();
		
		Map<Integer, Integer> mapPosicoesEcolisoes = new HashMap<Integer, Integer>();
		
		for (Individuo individuo : populacao) {
			
			mapPosicoesEcolisoes.put(populacao.indexOf(individuo), individuo.qtdColisoes);
			
		}
		
		mapPosicoesEcolisoes = sortByValues(mapPosicoesEcolisoes);
		
		int index = 0;
		
		for (Integer posicao : mapPosicoesEcolisoes.keySet()) {
			
			if(index == quantidadePais)
				break;
			
			paisSelecionados.add(populacao.get(posicao));
			
			index++;
		}
		
		return paisSelecionados;
	}

    /**
     * Code from method java.util.Collections.shuffle();
     */
    private void shuffle(int[] array) {
        if (random == null) random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap sortByValues(Map map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                   .compareTo(((Map.Entry) (o2)).getValue());
             }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        
        return sortedHashMap;
   }
    
    private void exibirPopulacao(List<Individuo> populacao) {
    	
    	for (Individuo individuo : populacao) {
			
    		for (int i : individuo.array) {
				System.out.print(i + ", ");
			}
    		
    		System.out.println("Colisões = " + individuo.qtdColisoes);
		}
    	
    }
    
    private boolean isValorPresenteEmArray(int valor, int[] arrayComparar) {
	for (int j = 0; j < arrayComparar.length; j++) {
		if(valor == arrayComparar[j]) {
			return true;
		}
	}
    	
    	return false;
    }

}

package com.kall;

public class Mao {
	
	public Carta []mao; // armazena as cartas
	private int nivel; // nivel da mao
	private Carta []reps; // armazena quais cartas sao repetidas
	private int indice; // armazena ate que posicao o vetor de repetidas foi preenchido
	public Carta []desempate; // armazena as cartas ordenadas por sua importancia para o desempate
	
	
	public Mao() {
		mao = new Carta[5];
		reps = null;
		desempate = null;
		indice = 0;
		nivel = 1;
	}
	
	public Carta[] getReps() {
		return reps;
	}

	public void setReps(Carta[] reps) {
		this.reps = reps;
	}

	public Carta[] getDesempate() {
		return desempate;
	}

	public void setDesempate(Carta[] desempate) {
		this.desempate = desempate;
	}

	// Preenche o vetor "mao" com as cartas da coluna informada (1 ou 2)
	public void DistribuirCartas(String []linha, int coluna) {
		if (coluna == 1) {
			for (int i = 0; i < 5; i++) {
				mao[i] = new Carta(linha[i]);
			}
		}
		else
		{
			for (int i = 0; i < 5; i++) {
				mao[i] = new Carta(linha[i+5]);
			}
		}
	}

	// Calcula e retorna o nivel da mao
	public int CalcularValorMao() {
		
		// valor de retorno
		int retorno = 1;
		
		// testa se é sequencia
		if (VerificarSequencia()) {
			retorno = 5;
		}
		
		// testa igualdade de naipes - flush 
		if (VerificarIgualdadeDeNaipes()) {
			// verifica se eh Straight Flush
			if (retorno == 5) {
				// verifica se eh Royal Flush
				if (mao[0].valor == 'T'){
					return nivel = 10;
				}
				return nivel = 9;
			}
			
			// eh flush
			retorno = 6;
		}
		
		// Verifica a ocorrencia de pares, trios ou sua concomitância
		int contador = VerificarRepeticaoValores();
		if (contador != 0) {
			
			// Full House
			if (contador == 4) {
				nivel = 7;
				return 7;
			}
			// Four of a Kind
			if (contador == 6) {
				nivel = 8;
				return 8;
			}
			// 1 par, 2 pares ou trio
			if (retorno == 1)
			{
				switch (contador) {
					case 1:	nivel = 2;
						return 2;
					case 2: nivel = 3;
						return 3;
					case 3: nivel = 4; 
						return 4;
				}
			}
		}
		return nivel = retorno;
	}
	
	// Retorna true se o naipe das 5 cartas for o mesmo
	public boolean VerificarIgualdadeDeNaipes() {
		
		char pivo = mao[0].naipe;
		int indx = 1;
		while (indx < 5 && pivo == mao[indx].naipe) {
			indx++;
		}
		return indx == 5;
	}
	
	/*
	 * Verifica repeticao de valores
	 * A variavel 'Contador' retorna o número de "relacoes de igualdade" entre as cartas.
	 * Cada valor para 'contador' corresponde a uma "mao":
	 * 		- contador = 1 -> 1 par
	 * 		- contador = 2 -> 2 pares
	 * 		- contador = 3 -> 1 trio
	 * 		- contador = 4 -> Full House
	 * 		- contador = 6 -> 1 quarteto
	 */		
	public int VerificarRepeticaoValores() {
		int contador = 0;
		for (int i = 0; i<5; i++) {
			for (int j = i; j<4; j++) {
				if (mao[i].val == mao[j+1].val) {
					contador++;
					// armazena qual carta se repetida
					ArmazenarRepeticao(mao[i]);
				}
			}
		}
		return contador;
	}
	
	// Retorna true se as cartas formam uma sequencia
	public boolean VerificarSequencia() {
		
		Carta aux;
		for (int i = 0; i<5; i++) {
			for (int j = i; j<4; j++) {
				if (mao[i].val > mao[j+1].val) {
					aux = mao[i];
					mao[i] = mao[j+1];
					mao[j+1] = aux;
				}
			}
		}
		int i = 1;
		while (i<5 && (mao[i-1].val +1 == mao[i].val)) {
			i++;
		}
		return (i == 5); 
	}
	
	// Metodo chamado quando encontra-se cartas repetidas
	public void ArmazenarRepeticao(Carta c) {
		
		if (reps == null) {
			reps = new Carta[5];
			reps[indice] = c;
			indice++;
		} else {
			
			boolean existe = false;
			for (int i = 0; i < indice; i++) {
				if (reps[i].val == c.val) {
					existe = true;
				}
			}
			if (!existe) {
				reps[indice] = c;
				indice++;
			}
		}
	}
	
	// Em caso de empate os vetores "desempate" de cada mao são comparados
	// Esse metodo ordena o vetor pela importância das cartas nessa comparação
	public void OrdenarVetorDesempate(){
		
		if (desempate == null) {
			desempate = new Carta[5];
		}
		
		switch (nivel) {
			case 2:	// se possuir 1 dupla ou 1 trio
			case 4:
				OrdenarDesempateDuplaTrio();
				break;
			
			case 3:	// se possuir 2 duplas
				OrdenarDesempate2Duplas();
				break;
			
			case 7:	// se for full house
				OrdenarDesempateFullHouse();
				break;
		
			case 8:	// Four of a king
				OrdenarDesempateQuarteto();
				break;
		}
		
	}
	
	// Ordena vetor "desempate" na ocorrencia de uma dupla ou trio
	public void OrdenarDesempateDuplaTrio(){
		desempate[0] = reps[0];
		int d = 1;
		for (int i = 4; i>-1; i--) {
			if (reps[0].val != mao[i].val) {
				desempate[d] = mao[i];
				d++;
			}
		}
	}
	
	// Ordena vetor "desempate" na ocorrencia de 2 duplas
	public void OrdenarDesempate2Duplas(){
		if (reps[0].val > reps[1].val) {
			desempate[0] = reps[0];
			desempate[1] = reps[1];
		} else {
			desempate[0] = reps[1];
			desempate[1] = reps[0];
		}
		for (int i = 0; i<5; i++) {
			if (reps[0].val != mao[i].val && reps[1].val != mao[i].val) {
				desempate[2] = mao[i];
			}
		}
	}

	// Ordena vetor "desempate" na ocorrencia de Full House
	public void OrdenarDesempateFullHouse(){
		int cont = 0;
		for (int i = 0; i < 5; i++) {
			if (reps[0].val == mao[i].val) {
				cont++;
			}
		}
		if (cont == 3) {
			desempate[0] = reps[0];
			desempate[1] = reps[1];
		} else {
			desempate[0] = reps[1];
			desempate[1] = reps[0];
		}
	}
	
	// Ordena vetor "desempate" na ocorrencia de 1 quarteto
	public void OrdenarDesempateQuarteto(){
		desempate[0] = reps[0];
		int i = 0;
		while (i < 5 && reps[0].val == mao[i].val) {
			i++;
		}
		desempate[1] = mao[i];
	}
	
	
	// retorna true se o objeto "this" tiver mao melhor
	// considerando que "this" e "mao2" tem o mesmo nivel
	public boolean Desempatar(Mao mao2) {
		
		if (nivel == 1 || nivel == 5 || nivel == 6 || nivel == 9 || nivel == 10) {
			return compararVetorMao(mao2);
		} else {
			return compararVetorDesempate(mao2);
		}
	}
	
	public boolean compararVetorDesempate(Mao mao2) {
		int i = 0;
		while (i<5 && this.desempate[i].val == mao2.desempate[i].val) {
			i++;
		}
		return this.desempate[i].val > mao2.desempate[i].val;
	}
	
	public boolean compararVetorMao(Mao mao2) {
		int i = 4;
		while (i>-1 && this.mao[i].val == mao2.mao[i].val) {
			i--;
		}
		return this.mao[i].val > mao2.mao[i].val;
	}
}
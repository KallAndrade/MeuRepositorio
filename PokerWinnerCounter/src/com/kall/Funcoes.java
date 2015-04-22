package com.kall;

public class Funcoes {
	
	public static void SepararMaos(String linha, Mao mao1, Mao mao2) {
		
		String []temp = linha.split(" ");
		
		// separa as cartas de cada mao
		for (int i = 0; i < 5; i++) {
 			mao1.mao[i] = new Carta(temp[i]);
		}
		for (int i = 0; i < 5; i++) {
			mao2.mao[i] = new Carta(temp[i+5]);
		}	
	}
	
	// Retorna o nivel da mao
	/*
	 * 
	 */
	public static int CalcularValorMao(Mao mao) {
		
		// valor de retorno
		int retorno = 1;
		
		// testa se é sequencia
		if (VerificarSequencia(mao)) {
			retorno = 5;
		}
		
		// testa igualdade de naipes - flush
		char pivo = mao.mao[0].naipe;
		int indx = 1;
		while (indx < 5 && pivo == mao.mao[indx].naipe) {
			indx++;
		}
		
		// se indx== 5 significa que as 5 cartas tem o mesmo naipe 
		if (indx == 5) {
			// verifica se eh Straight Flush
			if (retorno == 5) {
				// verifica se eh Royal Flush
				if (mao.mao[0].valor == 'T'){
					return 10;
				}
				return 9;
			}
			// eh apenas flush
			// ainda vai testar se eh Full House ou Four of a Kind
			retorno = 6;
		}
		
		// testa repeticao de valores
		/*
		 * A variavel 'Contador' retorna o número de "relacoes de igualdade" entre as cartas.
		 * Cada valor para 'contador' corresponde a uma "mao":
		 * 		- contador = 1 -> 1 par
		 * 		- contador = 2 -> 2 pares
		 * 		- contador = 3 -> 1 trio
		 * 		- contador = 4 -> Full House
		 * 		- contador = 6 -> 1 quarteto
		 * 
		 */
		int contador = 0;
		for (int i = 0; i<5; i++) {
			for (int j = i; j<4; j++) {
				if (mao.mao[i].val == mao.mao[j+1].val) {
					contador++;
					// armazena qual carta se repetida
					mao.ArmazenarRepeticao(mao.mao[i]);
				}
			}
		}
		
		if (contador != 0) {
			
			// Avaliacao da mao. Necessario caso ocorra empate
			//mao.Avaliar(niv, vet);
			
			// testa Full House
			if (contador == 4) {
				return 7;
			}
			// testa Four of a Kind
			if (contador == 6) {
				return 8;
			}
			if (retorno == 1)
			{
				switch (contador) {
					case 1: return 2;
					case 2: return 3;
					case 3: return 4;
				}
			}
		}
		return retorno;
	}
	
	public static boolean VerificarSequencia(Mao mao) {
		
		Carta aux;
		for (int i = 0; i<5; i++) {
			for (int j = i; j<4; j++) {
				if (mao.mao[i].val > mao.mao[j+1].val) {
					aux = mao.mao[i];
					mao.mao[i] = mao.mao[j+1];
					mao.mao[j+1] = aux;
				}
			}
		}
		int i = 1;
		while (i<5 && (mao.mao[i-1].val +1 == mao.mao[i].val)) {
			i++;
		}
		return (i == 5); 
	}

	public static boolean Desempatar(int nivel, Mao mao1, Mao mao2) {
		int i;
		switch (nivel) {
			case 1:
				i = 4;
				while (i>-1 && mao1.mao[i].val == mao2.mao[i].val) {
					i--;
				}
				return mao1.mao[i].val > mao2.mao[i].val;
				
			case 2:
				i = 0;
				while (i<5 && mao1.desempate[i].val == mao2.desempate[i].val) {
					i++;
				}
				return mao1.desempate[i].val > mao2.desempate[i].val;
				
			case 3:
				i = 0;
				while (i<5 && mao1.desempate[i].val == mao2.desempate[i].val) {
					i++;
				}
				return mao1.desempate[i].val > mao2.desempate[i].val;
				
			case 4:
				i = 0;
				while (i<5 && mao1.desempate[i].val == mao2.desempate[i].val) {
					i++;
				}
				return mao1.desempate[i].val > mao2.desempate[i].val;
				
			case 5:
				i = 4;
				while (i>-1 && mao1.mao[i].val == mao2.mao[i].val) {
					i--;
				}
				return mao1.mao[i].val > mao2.mao[i].val;
				
			case 6:
				i = 4;
				while (i>-1 && mao1.mao[i].val == mao2.mao[i].val) {
					i--;
				}
				return mao1.mao[i].val > mao2.mao[i].val;
				
			case 7:
				i = 0;
				while (i<5 && mao1.desempate[i].val == mao2.desempate[i].val) {
					i++;
				}
				return mao1.desempate[i].val > mao2.desempate[i].val;
				
			case 8:
				i = 0;
				while (i<5 && mao1.desempate[i].val == mao2.desempate[i].val) {
					i++;
				}
				return mao1.desempate[i].val > mao2.desempate[i].val;
				
			case 9:
				i = 4;
				while (i>-1 && mao1.mao[i].val == mao2.mao[i].val) {
					i--;
				}
				return mao1.mao[i].val > mao2.mao[i].val;
				
			case 10:
				i = 4;
				while (i>-1 && mao1.mao[i].val == mao2.mao[i].val) {
					i--;
				}
				return mao1.mao[i].val > mao2.mao[i].val;
				
		}
		return true;
	}
}

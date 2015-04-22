package com.kall;

public class Mao {
	
	public Carta []mao; // armazena as cartas
	private Carta []reps; // armazena quais cartas sao repetidas
	private int indice; // armazena ate que posicao o vetor de repetidas foi preenchido
	public Carta []desempate; // armazena as cartas ordenadas por importancia para o desempate
	
	
	public Mao() {
		mao = new Carta[5];
		reps = null;
		desempate = null;
		indice = 0;
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
	
	public void OrdenarVetorDesempate(int nivel){
		
		if (desempate == null) {
			desempate = new Carta[5];
		}
		
		// se possuir 1 dupla ou 1 trio
		if (nivel == 2 || nivel == 4) {
			desempate[0] = reps[0];
			int d = 1;
			for (int i = 4; i>-1; i--) {
				if (reps[0].val != mao[i].val) {
					desempate[d] = mao[i];
					d++;
				}
			}
			return;
		}
		
		// se possuir 2 duplas
		if (nivel == 3) {
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
			return;
		}
		
		// se for full house
		if (nivel == 7) {
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
			
			return;
		}
		
		if (nivel == 8) {
			desempate[0] = reps[0];
			int i = 0;
			while (i < 5 && reps[0].val == mao[i].val) {
				i++;
			}
			desempate[1] = mao[i];
		}
		
	}

}

package com.kall;

public class Carta {
	
	public char naipe;
	public char valor;
	public int val;

	public Carta(String par){
		this.valor = par.substring(0,1).toCharArray()[0];
		this.naipe = par.substring(1,2).toCharArray()[0];
		
		switch (this.valor) {
			case 'T': this.val = 10;
			break;
			case 'J': this.val = 11;
			break;
			case 'Q': this.val = 12;
			break;
			case 'K': this.val = 13;
			break;
			case 'A': this.val = 14;
			break;
			default: this.val = Integer.valueOf(this.valor) - 48;
			break;
		}
	}
}

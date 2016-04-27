package controller;

import java.util.Random;

import model.Ataque;
import model.Pokemon;
import model.Treinador;
import utils.Controller;
import utils.Event;
import utils.Tipo;

public class Batalha extends Controller {
	Treinador t1, t2, tTurno, tAdversario;

	public Batalha(Treinador t1, Treinador t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	private class Atacar extends Event {
		public Atacar(long eventTime) {
			super(eventTime);
		}

		Random random = new Random();
		Ataque at = tTurno.getPokemons()[tTurno.getPokAtivo()].getAtaques()[random.nextInt(4)];

		public void action() {
			int hp = tAdversario.getPokemons()[tAdversario.getPokAtivo()].getHp() - at.getPoder();
			tAdversario.getPokemons()[tAdversario.getPokAtivo()].setHp(hp);
			if (hp <= 0) {
				tAdversario.setPoksDerrotados(tAdversario.getPoksDerrotados() + 1);
				tAdversario.setPokAtivo(tAdversario.getNextPokemon());
			}
		}

		public String description() {
			return tTurno.getPokemons()[tTurno.getPokAtivo()].getNome() + " usou " + at.getNome();
		}
	}

	private class TrocarPokemon extends Event {
		public TrocarPokemon(long eventTime) {
			super(eventTime);
		}

		int pokAntes;

		public void action() {
			pokAntes = tTurno.getPokAtivo();
			tTurno.setPokAtivo(tTurno.getNextPokemon());
		}

		public String description() {
			return tTurno.getNome() + " voltou " + tTurno.getPokemons()[pokAntes].getNome()
					+ " para sua pokebola, e escolheu " + tTurno.getPokemons()[tTurno.getPokAtivo()].getNome() + "!";
		}
	}

	private class UsarItem extends Event {
		public UsarItem(long eventTime) {
			super(eventTime);
		}

		public void action() {
			tTurno.getPokemons()[tTurno.getPokAtivo()].setHp(tTurno.getPokemons()[tTurno.getPokAtivo()].getHp() + 5);
		}

		public String description() {
			return tTurno.getNome() + " usou um item!";
		}
	}

	private class Fugir extends Event {
		public Fugir(long eventTime) {
			super(eventTime);
		}

		public void action() {
			tTurno.setPokAtivo(-1);
			;
		}

		public String description() {
			return tTurno.getNome() + " fugiu!";
		}
	}

	public static void main(String[] args){
		Ataque at1 = new Ataque(20, "Choque do Trovao");
		Ataque at2 = new Ataque(10, "Cauda de Ferro");
		Ataque at3 = new Ataque(30, "Esfera Eletrica");
		Ataque at4 = new Ataque(5, "Investida");
		Ataque at5 = new Ataque(15, "Bola dos Sombras");
		Ataque at6 = new Ataque(25, "Raio de Gelo");
		
		Pokemon pok1 = new Pokemon("Pikachu", Tipo.ELETRICO, 100, at1, at2, at3, at4);
		Pokemon pok2 = new Pokemon("Eevee", Tipo.NORMAL, 100, at2, at5, at4, at6);
		
		Pokemon[] poks = {pok1, pok2}; 
		
		Batalha battle = new Batalha(new Treinador("Ash", poks), new Treinador("Myst", poks));
		//battle.addEvent(new Atacar(1000));
	}

}

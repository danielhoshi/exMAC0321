package controller;

import java.util.Random;

import model.Ataque;
import model.Pokemon;
import model.Treinador;
import utils.Controller;
import utils.Event;
import utils.Tipo;

public class Batalha extends Controller {
	Treinador tTurno, tAdversario;

	public Batalha(Treinador t1, Treinador t2) {
		this.tTurno = t1;
		this.tAdversario = t2;
	}

	public void trocarTurno() {
		Treinador aux = tTurno;
		tTurno = tAdversario;
		tAdversario = aux;
	}

	class Atacar extends Event {
		public Atacar(long eventTime) {
			super(eventTime);
		}

		Random random = new Random();
		int nAtaque = random.nextInt(4);
		int hp, pokAntes;

		public void action() {
			trocarTurno();
			Ataque at = tTurno.getPokemons()[tTurno.getPokAtivo()].getAtaques()[nAtaque];
			int hp = tAdversario.getPokemons()[tAdversario.getPokAtivo()].getHp() - at.getPoder();
			this.hp = hp;
			tAdversario.getPokemons()[tAdversario.getPokAtivo()].setHp(hp);
			if (hp <= 0) {
				pokAntes = tAdversario.getPokAtivo();
				tAdversario.setPoksDerrotados(tAdversario.getPoksDerrotados() + 1);
				tAdversario.setPokAtivo(tAdversario.getNextPokemon());
			}
		}

		public String description() {
			Ataque at = tTurno.getPokemons()[tTurno.getPokAtivo()].getAtaques()[nAtaque];
			String retorno = tTurno.getNome() + " manda " + tTurno.getPokemons()[tTurno.getPokAtivo()].getNome()
					+ " usar " + at.getNome();
			if (hp <= 0) {
				retorno += "\n" + tAdversario.getNome() + " voltou " + tAdversario.getPokemons()[pokAntes].getNome()
						+ " para sua pokebola"
						+ (tAdversario.getPokAtivo() != -1
								? " e escolheu " + tAdversario.getPokemons()[tAdversario.getPokAtivo()].getNome() + "!"
								: " e perdeu a batalha!");
			}
			return retorno;
		}
	}

	private class TrocarPokemon extends Event {
		public TrocarPokemon(long eventTime) {
			super(eventTime);
		}

		int pokAntes;
		boolean bool;

		public void action() {
			trocarTurno();
			bool = tTurno.getPoksDerrotados() < tTurno.getPokemons().length - 1;
			if (bool) {
				pokAntes = tTurno.getPokAtivo();
				tTurno.setPokAtivo(tTurno.getNextPokemon());
			} else
				trocarTurno();
		}

		public String description() {
			if (bool) {
				return tTurno.getNome() + " voltou " + tTurno.getPokemons()[pokAntes].getNome()
						+ " para sua pokebola, e escolheu " + tTurno.getPokemons()[tTurno.getPokAtivo()].getNome()
						+ "!";
			}
			return "O clima fica tenso!";
		}
	}

	private class UsarItem extends Event {
		public UsarItem(long eventTime) {
			super(eventTime);
		}

		public void action() {
			trocarTurno();
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

		int pokAntes;

		public void action() {
			trocarTurno();
			pokAntes = tTurno.getPokAtivo();
			tTurno.setPokAtivo(-1);
		}

		public String description() {
			return tTurno.getNome() + " voltou " + tTurno.getPokemons()[pokAntes].getNome()
					+ " para sua pokebola e fugiu!";
		}
	}

	public static void main(String[] args) {
		Ataque at1 = new Ataque(20, "Choque do Trovao");
		Ataque at2 = new Ataque(10, "Cauda de Ferro");
		Ataque at3 = new Ataque(30, "Esfera Eletrica");
		Ataque at4 = new Ataque(5, "Investida");
		Ataque at5 = new Ataque(15, "Bola das Sombras");
		Ataque at6 = new Ataque(25, "Raio de Gelo");
		Ataque at7 = new Ataque(100, "Soco de Gelo");
		Ataque at8 = new Ataque(100, "Hiper Raio");
		Ataque at9 = new Ataque(100, "Mega Chute");
		Ataque at10 = new Ataque(0, "Ronco");

		Pokemon[] poks1 = { new Pokemon("Pikachu", Tipo.ELETRICO, 100, at1, at2, at3, at4),
				new Pokemon("Eevee", Tipo.NORMAL, 100, at2, at5, at4, at6),
				new Pokemon("Snorlax", Tipo.LUTADOR, 100, at7, at8, at9, at10) };
		Pokemon[] poks2 = { new Pokemon("Pikachu", Tipo.ELETRICO, 100, at1, at2, at3, at4),
				new Pokemon("Eevee", Tipo.NORMAL, 100, at2, at5, at4, at6),
				new Pokemon("Snorlax", Tipo.LUTADOR, 100, at7, at8, at9, at10) };

		long tm;
		Random random = new Random();
		int evento;

		Batalha battle = new Batalha(new Treinador("Ash", poks1), new Treinador("Myst", poks2));

		while (battle.tTurno.getPokAtivo() != -1 && battle.tAdversario.getPokAtivo() != -1) {
			tm = System.currentTimeMillis();
			evento = random.nextInt(5);
			System.out.println(battle.tTurno.getPokemons()[battle.tTurno.getPokAtivo()].getNome() + ": "
					+ battle.tTurno.getPokemons()[battle.tTurno.getPokAtivo()].getHp() + " "
					+ battle.tAdversario.getPokemons()[battle.tAdversario.getPokAtivo()].getNome() + ": "
					+ battle.tAdversario.getPokemons()[battle.tAdversario.getPokAtivo()].getHp());
			switch (evento) {
			case 0:
			case 1:
				battle.addEvent(battle.new Atacar(tm + 127));
				break;
			case 2:
				battle.addEvent(battle.new TrocarPokemon(tm + 127));
				break;
			case 3:
				battle.addEvent(battle.new UsarItem(tm + 127));
				break;
			case 4:
				int aux = random.nextInt(5);
				if (aux == 0)
					battle.addEvent(battle.new Fugir(tm + 127));
				else
					battle.addEvent(battle.new Atacar(tm + 127));
			}
			battle.run();
		}
	}

}

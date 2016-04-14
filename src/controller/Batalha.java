package controller;

import java.util.Random;

import Model.Ataque;
import Model.Treinador;
import Utils.Controller;
import Utils.Event;

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
		Ataque at = tTurno.getPokAtivo().getAtaques()[random.nextInt(4)];

		public void action() {
			int hp = tAdversario.getPokAtivo().getHp() - at.getPoder();
			tAdversario.getPokAtivo().setHp(hp);
			if (true) {
			}
		}

		public String description() {
			return tTurno.getPokAtivo().getNome() + " usou " + at.getNome();
		}
	}
}

package controller;

import java.util.Random;

import model.Ataque;
import model.Pokemon;
import model.Treinador;
import utils.Controller;
import utils.Event;
import utils.Tipo;

public class Mapa extends Controller {

	private boolean[][] mapa;
	private int x, y, w, h, poksDerrotados;
	Treinador tAndante;

	public Mapa(int w, int h, Treinador t) {
		mapa = new boolean[w][h];
		tAndante = t;
		x = 0;
		y = 0;
		setPoksDerrotados(0);
		this.w = w;
		this.h = h;
		Random rand = new Random();
		for (int cont1 = 0; cont1 < h; cont1++) {
			for (int cont2 = 0; cont2 < w; cont2++) {
				mapa[cont2][cont1] = rand.nextInt() % 3 == 0;
			}
		}
	}

	public boolean isGrama(int x, int y) {
		return mapa[x][y];
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getPoksDerrotados() {
		return poksDerrotados;
	}

	public void setPoksDerrotados(int poksDerrotados) {
		this.poksDerrotados = poksDerrotados;
	}

	boolean direcaoPossivel(int dir) {
		switch (dir) {
		case 0:
			if (y > 0)
				y--;
			return true;
		case 1:
			if (x < w - 2)
				x++;
			return true;
		case 2:
			if (y < h - 2)
				y++;
			return true;
		case 3:
			if (x > 0)
				x--;
			return true;
		}
		return false;
	}

	private class Andar extends Event {
		public Andar(long eventTime) {
			super(eventTime);
		}

		public void action() {
			Random rand = new Random();
			int direcao = rand.nextInt(4);
			boolean bool = true;
			while (!direcaoPossivel(direcao))
				direcao = rand.nextInt() % 4;
			if (isGrama(x, y)) {
				if (rand.nextInt() % 3 != 0) {
					Ataque at1 = new Ataque(20, "Choque do Trovao");
					Ataque at2 = new Ataque(10, "Cauda de Ferro");
					Ataque at3 = new Ataque(30, "Esfera Eletrica");
					Ataque at4 = new Ataque(5, "Investida");
					Pokemon[] pokSelvagem = { new Pokemon("Pikachu", Tipo.ELETRICO, 100, at1, at2, at3, at4) };
					Batalha batalha = new Batalha(tAndante, new Treinador("Pokemon Selvagem", pokSelvagem));
					long tm = System.currentTimeMillis();
					while (batalha.tAdversario.getPokAtivo() != -1 && batalha.tTurno.getPokAtivo() != -1) {
						batalha.addEvent(batalha.new Atacar(tm + 100));
						batalha.run();
					}
					if (tAndante.getPokAtivo() != -1) {
						setPoksDerrotados(getPoksDerrotados() + 1);
					}
				}
			}
		}

		public String description() {
			return tAndante.getNome() + " andou por " + (isGrama(x, y) ? "uma grama" : "um chao normal");
		}
	}

	public static void main(String args[]) {
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
		Mapa mapa = new Mapa(100, 10, new Treinador("Ash", poks1));
		while (mapa.tAndante.getPokAtivo() != -1 && mapa.getPoksDerrotados() < 10) {
			mapa.addEvent(mapa.new Andar(System.currentTimeMillis() + 500));
			mapa.run();
		}
	}

}

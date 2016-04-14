package Model;

import Utils.Controller;
import Utils.Tipo;

public class Pokemon extends Controller{

	private String nome;
	private Tipo tipo;
	private int hp;
	private Ataque[] ataques = new Ataque[4];

	public Pokemon(String nome, Tipo tipo, int hp, Ataque ataque1,
			Ataque ataque2, Ataque ataque3, Ataque ataque4) {
		this.nome = nome;
		this.hp = hp;
		this.tipo = tipo;
		this.ataques[0] = ataque1;
		this.ataques[1] = ataque2;
		this.ataques[2] = ataque3;
		this.ataques[3] = ataque4;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Ataque[] getAtaques() {
		return ataques;
	}

	public void setAtaques(Ataque[] ataques) {
		this.ataques = ataques;
	}
}

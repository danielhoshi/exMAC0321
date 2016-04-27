package model;

public class Treinador {

	private Pokemon[] pokemons;
	private String nome;
	private int pokAtivo;
	private int poksDerrotados;

	public Treinador(String nome, Pokemon[] pokemons) {
		this.nome = nome;
		this.pokemons = pokemons;
		poksDerrotados = 0;
		pokAtivo = 0;
	}

	public int getNextPokemon() {
		if (poksDerrotados != pokemons.length) {
			for (int cont = 0; cont < pokemons.length; cont++) {
				if (!(pokemons[cont].getNome().equals(pokemons[pokAtivo].getNome())) && pokemons[cont].getHp() > 0)
					return cont;
			}
		}
		return -1;
	}

	public Pokemon[] getPokemons() {
		return pokemons;
	}

	public void setPokemons(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPokAtivo() {
		return pokAtivo;
	}

	public void setPokAtivo(int pokAtivo) {
		this.pokAtivo = pokAtivo;
	}

	public int getPoksDerrotados() {
		return poksDerrotados;
	}

	public void setPoksDerrotados(int poksDerrotados) {
		this.poksDerrotados = poksDerrotados;
	}
}

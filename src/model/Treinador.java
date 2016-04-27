package model;


public class Treinador{

	private Pokemon[] pokemons;
	private String nome;
	private Pokemon pokAtivo;
	private int poksDerrotados;

	public Treinador(String nome, Pokemon[] pokemons) {
		this.nome = nome;
		this.pokemons = pokemons;
		poksDerrotados = 0;
		pokAtivo = pokemons[0];
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

	public Pokemon getPokAtivo() {
		return pokAtivo;
	}

	public void setPokAtivo(Pokemon pokAtivo) {
		this.pokAtivo = pokAtivo;
	}

	public int getPoksDerrotados() {
		return poksDerrotados;
	}

	public void setPoksDerrotados(int poksDerrotados) {
		this.poksDerrotados = poksDerrotados;
	}
}

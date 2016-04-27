package model;

public class Ataque {
	private int poder;
	private String nome;
	
	public Ataque(int poder, String nome){
		this.poder = poder;
		this.nome = nome;
	}
	
	public int getPoder() {
		return poder;
	}
	
	public void setPoder(int poder) {
		this.poder = poder;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}

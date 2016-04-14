public class Pokemon {

	private String nome;
	private String tipo;
	private int hp;
	private Ataque[] ataques = new Ataque[4];
	
	public Pokemon(String nome){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
}

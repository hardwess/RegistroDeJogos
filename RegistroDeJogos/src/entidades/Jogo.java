package entidades;

public class Jogo {

	private String nome;
	private String dificuldade;
	
	public Jogo(String nome, String dificuldade){
		this.nome = nome;
		this.dificuldade = dificuldade;
	}
	
	public void setNome(String nome){
		
		this.nome = nome;
		
	}
	
	public String getNome(){
	
		return nome;
		
	}
	
	public void setDificuldade(String dificuldade){
		
		this.dificuldade = dificuldade;
		
	}
	
	public String getDificuldade(){
	
		return dificuldade;
		
	}
	
	
	@Override
	public String toString() { 
		
		StringBuffer sb = new StringBuffer();
		sb.append("Nome: "  + getNome() + "\n");
		sb.append("Dificuldade: "  + getDificuldade() + "\n");
		return sb.toString();
		
	}
}

package br.com.game.database.modelo;

import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Resposta implements Comparable<Resposta> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "id_pergunta", nullable = false)
	private Pergunta pergunta;
	@ManyToOne
	@JoinColumn(name = "id_aluno", nullable = false)
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "id_equipe", nullable = false)
	private Equipe equipe;

	private int resposta;

	public Resposta(){
		this.resposta = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public int getResposta() {
		return resposta;
	}

	public void setResposta(int resposta) {
		this.resposta = resposta;
	}

	@Override
	public boolean equals(Object obj) {

		if(!(obj instanceof Resposta))
			return false;
		
		Resposta resposta = (Resposta) obj;
		
		return resposta.getId() == this.getId();
		
	}
	
	@Override
	public int compareTo(Resposta o) {
		return (this.pergunta.getId() > o.pergunta.getId())?1:-1;
	}
	
}

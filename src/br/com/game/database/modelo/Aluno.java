package br.com.game.database.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author alexsandro A matricula do Aluno é representada pelo campo login do
 *         Usuario
 * 
 */
@Entity
public class Aluno extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1841117084413632239L;
	@ManyToOne
	@JoinColumn(nullable = true, name = "id_equipe")
	private Equipe equipe;
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	@Override
	public String toString() {
		return this.login.toString();
	}

}

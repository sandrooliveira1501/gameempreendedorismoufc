package br.com.game.database.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Aposta.listApostas",query="select a from Aposta a"),
	@NamedQuery(name="Aposta.verificaAposta",query="select a from Aposta a "
			+ "where a.formulario = :formulario and "
			+ "a.equipe = :equipe and "
			+ "a.aluno = :aluno")

})
public class Aposta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1203878951762733940L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double valor;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_formulario")
	private Formulario formulario;
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_equipe")
	private Equipe equipe;
	@ManyToOne
	@JoinColumn(nullable = false, name = "id_aluno")
	private Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}

package br.com.game.database.dao;

import java.util.List;

import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Aposta;
import br.com.game.database.modelo.Equipe;
import br.com.game.database.modelo.Formulario;

public interface ApostaDao extends GenericDao<Aposta>{
	
	public List<Aposta> getApostas();
	public boolean salvaAposta(Aposta aposta);
	public double somaValorApostas(Formulario formulario, Aluno aluno, Equipe equipe);
	public double valorApostaEquipe(Equipe equipe, int idFormulario);
	
}

package br.com.game.database.dao;

import java.util.List;

import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Equipe;

public interface EquipeDao extends GenericDao<Equipe>{

	public List<Equipe> getList();
	public void adicionarEquipe(Equipe equipe);
	public List<Aluno> getAlunosSemEquipe();
	public void desativarEquipe(Equipe equipe);
}

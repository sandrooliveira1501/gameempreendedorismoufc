package br.com.game.database.dao;

import java.util.List;

import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Usuario;
import br.com.game.exceptions.ErroLoginException;

public interface AlunoDao extends GenericDao<Aluno>{

	public List<Aluno> getAlunos();
	
/*
	public boolean validacaoLogin(Usuario aluno);
*/	
}

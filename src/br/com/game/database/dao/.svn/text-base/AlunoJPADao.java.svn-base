package br.com.game.database.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Usuario;
import br.com.game.database.util.SenhaCriptografada;
import br.com.game.exceptions.ErroLoginException;

public class AlunoJPADao extends GenericJPADao<Aluno> implements AlunoDao {

	public AlunoJPADao(){
		persistenceClass = Aluno.class;
	}

	@Override
	public List<Aluno> getAlunos() {
		String sql = "select a from Aluno a";
		
		Query query = em.createQuery(sql);
		
		return query.getResultList();
	}
	
}

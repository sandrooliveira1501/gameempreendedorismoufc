package br.com.game.database.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.game.database.modelo.Formulario;
import br.com.game.database.modelo.Pergunta;

public class PerguntaJPADao extends GenericJPADao<Pergunta> implements PerguntaDao{
	
	public PerguntaJPADao(){
		this.persistenceClass = Pergunta.class;
	}

	@Override
	public List<Pergunta> getList(Formulario form) {
		String sql = "select p from Pergunta p where p.formulario = :form";
		Query query = em.createQuery(sql);
		query.setParameter("form", form);
		
		return query.getResultList();
	}

	@Override
	public void removerPergunta(Pergunta pergunta) {
		String hql = "delete from Resposta r where r.pergunta = :pergunta";
		Query query = this.em.createQuery(hql);
		query.setParameter("pergunta", pergunta);
		query.executeUpdate();
		
		delete(pergunta);
	}
	
	
	
}

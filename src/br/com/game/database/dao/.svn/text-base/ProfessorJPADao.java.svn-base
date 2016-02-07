package br.com.game.database.dao;

import br.com.game.database.modelo.Professor;

public class ProfessorJPADao extends GenericJPADao<Professor> implements
		ProfessorDao {

	public ProfessorJPADao() {
		persistenceClass = Professor.class;
	}

	/*@Override
	public boolean validacaoLogin(Usuario professor) {

		String sql = "select p from Professor p where p.login = :login and p.senha = :senha";

		Query query = em.createQuery(sql);
		query.setParameter("login", professor.getLogin());
		query.setParameter("senha", professor.getSenha());

		try {

			Professor professorDaConsulta = (Professor) query.getSingleResult();

			if (professorDaConsulta == null) {
				return false;
			} else {
				return true;
			}
		} catch (NoResultException ex) {
			return false;
		}
	}*/

}

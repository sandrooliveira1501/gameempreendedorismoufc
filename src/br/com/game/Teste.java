package br.com.game;

import javax.persistence.EntityManager;

import br.com.game.database.modelo.Professor;
import br.com.game.database.util.JPAUtil;

public class Teste {

	public static void main(String[] args) {
	
		/*Aluno aluno = new Aluno();
		
		aluno.setLogin("teste");
		aluno.setEmail("teste");
		aluno.setEquipe(null);
		aluno.setNome("teste");
		aluno.setSenha("teste");
		
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		
		AlunoJPADao dao = new AlunoJPADao();
		List<Aluno> list = dao.getAlunos();
		for (Aluno aluno : list) {
			System.out.println("Usuário = " + aluno.getLogin());
			System.out.println("Senha = " + aluno.getSenha() );
		}*/
		
		Professor prof = new Professor();
		prof.setLogin(new Long(102013));
		prof.setEmail("professor@gmail.com");
		prof.setNome("Professor");
		prof.setSenha("123");
		
		
		EntityManager em = JPAUtil.createEntityManager();
		em.getTransaction().begin();
		em.persist(prof);
		em.getTransaction().commit();
		
	}
	
}

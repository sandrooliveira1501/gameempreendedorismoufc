package br.com.game.database.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Aposta;
import br.com.game.database.modelo.Equipe;
import br.com.game.database.modelo.Formulario;

public class ApostaJPADao extends GenericJPADao<Aposta> implements ApostaDao {

	public ApostaJPADao() {
		this.persistenceClass = Aposta.class;
	}

	@Override
	public List<Aposta> getApostas() {

		Query query = this.em.createNamedQuery("Aposta.listApostas");

		return query.getResultList();
	}

	@Override
	public boolean salvaAposta(Aposta aposta) {
		try {

			Query query = this.em.createNamedQuery("Aposta.verificaAposta");
			query.setParameter("formulario", aposta.getFormulario());
			query.setParameter("equipe", aposta.getEquipe());
			query.setParameter("aluno", aposta.getAluno());

			try{
				Aposta apostaDoBanco = (Aposta)query.getSingleResult(); 	
				if(apostaDoBanco != null){
					apostaDoBanco.setValor(aposta.getValor());
					this.em.merge(apostaDoBanco);
				}else{
					throw new NoResultException();
				}
			}catch(NoResultException ex){
				System.out.println("Criando a aposta");
				save(aposta);
			}
			
			return true;
		} catch (PersistenceException ex) {
			ex.printStackTrace();	
			return false;
		}
	}

	@Override
	public double somaValorApostas(Formulario formulario, Aluno aluno, Equipe equipe) {

		String hql = "select sum(ap.valor) from Aposta ap"
				+ " where ap.aluno = :aluno and ap.formulario = :formulario "
				+ "and ap.equipe != :equipe";
		
		Query query = this.em.createQuery(hql);
		query.setParameter("aluno", aluno);
		query.setParameter("formulario", formulario);
		query.setParameter("equipe", equipe);
		
		Double soma = (Double) query.getSingleResult();
		if(soma == null){
			return 0;			
		}else{
			return soma;
		}
	}

	public static void main(String[] args) {
		ApostaDao dao = new ApostaJPADao();
		FormularioDao fDao = new FormularioJPADao();
		AlunoDao aDao = new AlunoJPADao();
		
		dao.somaValorApostas(fDao.formRodada(), aDao.find(new Long(358307)), new Equipe());
		
	}

	@Override
	public double valorApostaEquipe(Equipe equipe, int idFormulario) {
		
		String hql = "select sum(ap.valor) from Aposta ap"
				+ " where ap.equipe = :equipe"
				+ " and ap.formulario = :formulario";
		
		Query query = this.em.createQuery(hql);
		query.setParameter("equipe", equipe);
		query.setParameter("formulario", this.em.find(Formulario.class, idFormulario));
		Double soma = (Double) query.getSingleResult();
		
		if(soma == null){
			return 0;
		}else{
			return soma;
		}
	}
	
}

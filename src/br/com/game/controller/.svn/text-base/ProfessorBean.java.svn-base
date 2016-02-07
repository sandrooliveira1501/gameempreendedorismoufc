package br.com.game.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import br.com.game.database.dao.ProfessorDao;
import br.com.game.database.dao.ProfessorJPADao;
import br.com.game.database.dao.UsuarioDao;
import br.com.game.database.dao.UsuarioJPADao;
import br.com.game.database.modelo.Professor;
import br.com.game.exceptions.ErroLoginException;

@ManagedBean
public class ProfessorBean {

	private Professor professor;
	private String novaSenha;

	public ProfessorBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Object usuario;

		if (session != null
				&& (usuario = session.getAttribute("usuario")) != null
				&& (usuario instanceof Professor)) {
			professor = (Professor) usuario;
		} else {
			this.professor = new Professor();
		}

	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String alterarDados() {

		ProfessorDao dao = new ProfessorJPADao();

		try {

			dao.update(professor);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Erro ao tentar editar dados, tente novamente",
							"Erro"));
		}
		return "/professor/home?faces-redirect=true";
	}

	public String alterarSenha() {

		UsuarioDao dao = new UsuarioJPADao();

		try {

			dao.alterarSenha(professor, novaSenha);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Senha alterada com sucesso",
							"Informação"));
			
		} catch (PersistenceException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao tentar editar senha, tente novamente!",
							"Erro"));
			ex.printStackTrace();
		} catch (ErroLoginException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Senha inválida, tente novamente", "Erro"));
			e.printStackTrace();
		}
		return "";
	}

}

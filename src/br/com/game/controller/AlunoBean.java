package br.com.game.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import br.com.game.database.dao.AlunoDao;
import br.com.game.database.dao.AlunoJPADao;
import br.com.game.database.dao.UsuarioDao;
import br.com.game.database.dao.UsuarioJPADao;
import br.com.game.database.modelo.Aluno;
import br.com.game.database.util.SenhaCriptografada;
import br.com.game.exceptions.ErroLoginException;

@RequestScoped
@ManagedBean(name = "alunoBean")
public class AlunoBean {

	private Aluno aluno;
	private String novaSenha;

	public AlunoBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Object usuario;
		if (session != null &&  (usuario = session.getAttribute("usuario")) != null && (usuario instanceof Aluno)) {
			this.aluno = (Aluno) usuario;
		} else {
			this.aluno = new Aluno();
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String cadastrarAluno() {

		AlunoDao alunoDao = new AlunoJPADao();
		try {
			alunoDao.save(this.aluno);
			alunoDao.commit();
			System.err.println("Cadastrando Aluno");
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Aluno Cadastrado com Sucesso",
									"Aluno Cadastrado"));
		} catch (PersistenceException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Matrí­cula ou e-mail já cadastrados no sistema", "Aviso"));
			ex.printStackTrace();
		}
		return "";
	}

	public String alterarDados() {
		AlunoDao dao = new AlunoJPADao();

		try {

			dao.update(aluno);

		} catch (PersistenceException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Erro ao tentar editar dados, tente novamente",
							"Erro"));
			ex.printStackTrace();
		}


		return "/aluno/home?faces-redirect=true";
	}

	public String alterarSenha() {

		UsuarioDao dao = new UsuarioJPADao();
		try {
			dao.alterarSenha(aluno, novaSenha);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Senha alterada com sucesso!",
							"Sucesso"));
			
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

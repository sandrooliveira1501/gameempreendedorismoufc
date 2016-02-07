package br.com.game.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.game.database.dao.UsuarioDao;
import br.com.game.database.dao.UsuarioJPADao;
import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Professor;
import br.com.game.database.modelo.Usuario;
import br.com.game.exceptions.ErroLoginException;

@ViewScoped
@ManagedBean(name = "loginBean")
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TIPO_ALUNO = "ALUNO";
	public static final String TIPO_PROFESSOR = "PROFESSOR";
	private String login;

	private Usuario usuario;

	public LoginBean() {
		usuario = new Usuario();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String fazerLogin() {
		if(login.matches("\\d+")){
			this.usuario.setLogin(Long.parseLong(login));
			this.usuario.setEmail("");
		}else{
			this.usuario.setLogin(-1l);
			this.usuario.setEmail(login);
		}
		
		UsuarioDao dao = new UsuarioJPADao();
		try {
			usuario = dao.loginUsuario(usuario);
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);
			if (usuario instanceof Aluno) {
				session.setAttribute("usuario", (Aluno) usuario);
				session.setAttribute("tipo", LoginBean.TIPO_ALUNO);
				return "/aluno/home?faces-redirect=true";
			} else if (usuario instanceof Professor) {
				System.out.println("professor");
				session.setAttribute("usuario", (Professor) usuario);
				session.setAttribute("tipo", LoginBean.TIPO_PROFESSOR);

				return "/professor/home?faces-redirect=true";
			}

		} catch (ErroLoginException ex) {
			// ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					"Aviso",
					new FacesMessage("Falha de autenticação",
							"login ou senha inválidos!"));
			System.out.println("Erro senha\n\n\n");
		}

		return "?faces-redirect=true";
	}

	public String realizarLogout() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return "/login?faces-redirect=true";
	}

}

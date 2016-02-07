package br.com.game.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.game.database.dao.FormularioDao;
import br.com.game.database.dao.FormularioJPADao;
import br.com.game.database.dao.RespostaDao;
import br.com.game.database.dao.RespostaJPADao;
import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Equipe;
import br.com.game.database.modelo.Formulario;
import br.com.game.database.modelo.Pergunta;
import br.com.game.database.modelo.Resposta;
import br.com.game.database.modelo.Usuario;

@SessionScoped
@ManagedBean(name = "questionarioBean")
public class QuestionarioBean {

	private Formulario form;
	private Equipe equipe;
	private List<Pergunta> perguntas;
	private List<Resposta> respostas;

	public QuestionarioBean() {
		this.form = new Formulario();
		this.equipe = new Equipe();
	}

	public Formulario getForm() {
		return form;
	}

	public void setForm(Formulario form) {
		this.form = form;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Pergunta> getPerguntas() {
		return this.perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public String preparaResposta() {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		int id = Integer.parseInt(request.getParameter("id_equipe"));
		this.equipe = new Equipe();
		equipe.setId(id);

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		FormularioDao formDao = new FormularioJPADao();
		RespostaDao respDao = new RespostaJPADao();
		try {
			/**
			 * Se for aluno pega do banco o form atual se for professor pega o form que vem da requisição
			 */
			if(usuario instanceof Aluno){
				this.form = formDao.formRodada();									
			}else{
				int idForm = Integer.parseInt(request.getParameter("id_form"));
				this.form.setId(idForm);
			}
			this.respostas = formDao.respostasCadastradas(form, usuario,equipe);
			if (respostas.size() == 0) {
				this.perguntas = formDao.getPerguntasForm(form);
				this.respostas = new ArrayList<>();
				for (Pergunta pergunta : perguntas) {
					Resposta resposta = new Resposta();
					resposta.setEquipe(equipe);
					resposta.setPergunta(pergunta);
					resposta.setUsuario(usuario);
					respostas.add(resposta);
					respDao.save(resposta);
				}
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().
			addMessage(null, new FacesMessage("Não há formulários abertos",
					"Espere a data de abertura de submissão"));
			System.out.println("Nenhum formulário na rodada");
			return "?faces-redirect=true";
		}
		Collections.sort(respostas);
		if(usuario instanceof Aluno){
			return "/aluno/quest";			
		}else{
			Flash flash = FacesContext.getCurrentInstance()
					.getExternalContext().getFlash();
			flash.put("form", this.form.getId());
			return "/professor/quest";
		}
	}

	public String addResposta(){
		
		RespostaDao dao = new RespostaJPADao();
		for (Resposta resposta : respostas) {
			System.out.println(resposta);
			dao.update(resposta);
		}

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if(usuario instanceof Aluno){
			return "/aluno/equipe?faces-redirect=true";			
		}else{
			Flash flash = FacesContext.getCurrentInstance()
					.getExternalContext().getFlash();
			HttpServletRequest request = (HttpServletRequest) 
					FacesContext.getCurrentInstance()
					.getExternalContext().getRequest();
			
			flash.put("form", Integer.parseInt(request.getParameter("formulario")));
			return "/professor/resposta-equipe?faces-redirect=true";
		}
	}
	
}

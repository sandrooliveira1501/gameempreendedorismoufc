package br.com.game.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import br.com.game.database.dao.FormularioDao;
import br.com.game.database.dao.PerguntaDao;
import br.com.game.database.dao.PerguntaJPADao;
import br.com.game.database.modelo.Formulario;
import br.com.game.database.modelo.Pergunta;

@ManagedBean
public class PerguntaFormBean {

	private Formulario formulario;
	private Pergunta pergunta;
	
	public PerguntaFormBean(){
		this.formulario = new Formulario();
		this.pergunta = new Pergunta();
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	public List<Pergunta> getPerguntas(){
		//TODO
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Integer idForm = (Integer)flash.get("formulario");
		if(idForm != null){
			PerguntaDao pDao = new PerguntaJPADao();
			this.formulario = new Formulario();
			this.formulario.setId(idForm);
			
			List<Pergunta> perguntasDao = pDao.getList(this.formulario);
			List<Pergunta> perguntas = new ArrayList<>();
			for (Pergunta pergunta : perguntasDao) {
				Pergunta p = new Pergunta();
				p.setDescricao(pergunta.getDescricao());
				p.setId(pergunta.getId());
				perguntas.add(p);
			}
			return perguntas;
		}else{
			return new ArrayList<Pergunta>();
		}
		
	}

	public String prepararPerguntas(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		
		flash.put("formulario", Integer.parseInt(request.getParameter("idForm")));
		
		return "/professor/perguntas";
	}
	
	public String addPergunta(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("formulario", Integer.parseInt(request.getParameter("idForm")));
		this.formulario = new Formulario();
		this.formulario.setId(Integer.parseInt(request.getParameter("idForm")));
		this.pergunta.setFormulario(formulario);
		
		PerguntaDao dao = new PerguntaJPADao();
		dao.save(this.pergunta);
		
		return "";
	}
	
	public String removerPergunta(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("formulario", Integer.parseInt(request.getParameter("idForm")));
	
		
		PerguntaDao dao = new PerguntaJPADao();
		this.pergunta = dao.find(this.pergunta.getId());
		dao.removerPergunta(this.pergunta);
		
		return "";
	}
}

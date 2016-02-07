package br.com.game.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import br.com.game.database.dao.ApostaDao;
import br.com.game.database.dao.ApostaJPADao;
import br.com.game.database.dao.FatorEquipeDao;
import br.com.game.database.dao.FatorEquipeJPADao;
import br.com.game.database.modelo.Equipe;
import br.com.game.database.modelo.FatorEquipe;
import br.com.game.database.modelo.Formulario;

@ManagedBean(name = "fatorEquipeBean")
public class FatorEquipeBean {

	private FatorEquipe fatorEquipe;

	public FatorEquipeBean() {
		this.fatorEquipe = new FatorEquipe();
	}

	public FatorEquipe getFatorEquipe() {
		return fatorEquipe;
	}

	public void setFatorEquipe(FatorEquipe fatorEquipe) {
		this.fatorEquipe = fatorEquipe;
	}

	public String adicionarFator(){
		
		FatorEquipeDao fDao = new FatorEquipeJPADao();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		Formulario formulario = new Formulario();
		formulario.setId(Integer.parseInt(request.getParameter("id_form")));
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("form", formulario.getId());
		
		Equipe equipe = new Equipe();
		equipe.setId(Integer.parseInt(request.getParameter("id_equipe")));
		System.out.println(equipe.getId() + "Teste de adição de fator em equipe");
		
		this.fatorEquipe.setEquipe(equipe);
		this.fatorEquipe.setFormulario(formulario);
		
		if(fDao.salvaFatorEquipe(fatorEquipe)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("","Fator adicionado com sucesso"));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso","Erro ao adicionar fator na equipe"));	
		}
		
		this.fatorEquipe = new FatorEquipe();
		
		return "?faces-redirect=true";
	}
	
	public String apostasDaEquipe(Equipe equipe, int idFormulario){
		
		ApostaDao apDao = new ApostaJPADao();
		return "R$" + apDao.valorApostaEquipe(equipe, idFormulario);
	}
	
	public double buscaFator(Equipe equipe, int idFormulario){
		
		FatorEquipeDao fDao = new FatorEquipeJPADao();
		this.fatorEquipe.setEquipe(equipe);
		
		Formulario form = new Formulario();
		form.setId(idFormulario);
		this.fatorEquipe.setFormulario(form);
		
		this.fatorEquipe = fDao.buscaFatorEquipe(this.fatorEquipe);
		
		if(this.fatorEquipe == null){
			this.fatorEquipe = new FatorEquipe();
			this.fatorEquipe.setFator(new Double(0));
		}
		
		return this.fatorEquipe.getFator();
	}
	
}

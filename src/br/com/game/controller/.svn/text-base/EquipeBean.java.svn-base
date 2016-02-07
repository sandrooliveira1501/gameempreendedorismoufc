package br.com.game.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.game.database.dao.EquipeDao;
import br.com.game.database.dao.EquipeJPADao;
import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Equipe;

@ManagedBean(name = "equipeBean")
public class EquipeBean {

	private Equipe equipe;
	private List<Equipe> equipes;
	private List<Aluno> alunosSemEquipe;
	
	public EquipeBean(){
		this.equipe = new Equipe();
		EquipeDao dao = new EquipeJPADao();
		equipes = dao.getList();
		alunosSemEquipe = dao.getAlunosSemEquipe();
		
	}
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Aluno> getAlunosSemEquipe() {
		return alunosSemEquipe;
	}

	public void setAlunosSemEquipe(List<Aluno> alunosSemEquipe) {
		this.alunosSemEquipe = alunosSemEquipe;
	}
	
	public String adicionaEquipe(){
		
		EquipeDao dao = new EquipeJPADao();
		this.equipe.setAtivo(true);
		dao.save(this.equipe);
		
		return "/professor/equipe?faces-redirect=true";
	}

	public String prepararParticipantes(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		int id = Integer.parseInt(request.getParameter("idEquipe"));
		this.equipe.setId(id);
		this.equipe.setNome(request.getParameter("nomeEquipe"));
		this.equipe.setAtivo(true);
		return "/professor/adicionar-participantes";
		
	}
	
	public String adicionaParticipante(){
		
		for (Aluno aluno : equipe.getAlunos()) {
			aluno.setEquipe(this.equipe);
		}
			
		EquipeDao dao = new EquipeJPADao();
		this.equipe.setAtivo(true);
		dao.update(this.equipe);

		return "/professor/equipe?faces-redirect=true";
	}
	
	public String removerEquipe(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		int id = Integer.parseInt(request.getParameter("idEquipe"));
		
		Equipe equipe = new Equipe();
		equipe.setId(id);
		
		EquipeDao dao = new EquipeJPADao();
		dao.desativarEquipe(equipe);
		
		return "/professor/equipe?faces-redirect=true";
	}
	
}
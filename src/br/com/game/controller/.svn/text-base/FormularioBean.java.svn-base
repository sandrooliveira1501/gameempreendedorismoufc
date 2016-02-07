package br.com.game.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.game.database.dao.FormularioDao;
import br.com.game.database.dao.FormularioJPADao;
import br.com.game.database.modelo.Formulario;
import br.com.game.database.util.ReportUtil;

@ManagedBean(name = "formularioBean")
public class FormularioBean {

	private Formulario formulario;

	public FormularioBean() {
		this.formulario = new Formulario();
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public String addFormulario() {

		FormularioDao dao = new FormularioJPADao();
		dao.save(formulario);

		return "/professor/formulario?faces-redirect=true";
	}

	public List<Formulario> getFormularios() {
		FormularioDao dao = new FormularioJPADao();
		return dao.getList();
	}

	public String removerFormulario(){

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		int id = Integer.parseInt(request.getParameter("idForm"));
		FormularioDao dao = new FormularioJPADao();
		this.formulario = dao.find(id);
		dao.removerFormulario(this.formulario);
		this.formulario = new Formulario();
		return "?faces-redirect=true";
	}

	public String prepararRespostas() {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		int id = Integer.parseInt(request.getParameter("idForm"));
		FormularioDao dao = new FormularioJPADao();
		this.formulario = dao.findFormularioData(id);

		if (formulario != null) {
			Flash flash = FacesContext.getCurrentInstance()
					.getExternalContext().getFlash();
			flash.put("form", formulario.getId());

			return "/professor/resposta-equipe";
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage("Aviso",
									"Formulário ainda está aberto, espere a data de fechamento!"));
			return "?faces-redirect=true";
		}

	}

	public String calcularPontos() {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		int id = Integer.parseInt(request.getParameter("idForm"));
		FormularioDao dao = new FormularioJPADao();
		this.formulario = dao.find(id);
		try {
			dao.realizaCalculoPontos(this.formulario);
			//Dando commit para os dados que serão usados no formulário
			dao.commit();
			dao.begin();
		} catch (PersistenceException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cálculo de pontos não realizado","Verifique se os gabaritos foram preenchidos"));
		}
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext
				.getExternalContext().getContext();
		String path = scontext.getRealPath("/WEB-INF/relatorios/relatorioPontuacao.jrxml");
		System.out.println(path);
		try {
			ReportUtil util = new ReportUtil();
			byte[] bytes = util.geraRelatorioPontuacao(this.formulario, path);
			if(bytes != null && bytes.length > 0){
				
				HttpServletResponse response = (HttpServletResponse) facesContext
						.getExternalContext().getResponse();

				response.setContentType("application/pdf");

				response.setHeader("Content-disposition",
						"inline; filename=\"relat.pdf\"");

				response.setContentLength(bytes.length);

				ServletOutputStream outputStream;
				outputStream = response.getOutputStream();

				System.out.println("Escrevendo bytes na saída");
				System.out.println(bytes);	
				outputStream.write(bytes);
				
				outputStream.flush();

				outputStream.close();
				
			}
		} catch (Exception e) {	
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage("Aviso",
							"Verifique se as respostas e fatores foram adicionados"));
			e.printStackTrace();
		}
		
		this.formulario = new Formulario();
		
		return "";
	}

	public String carregaFormulario(){
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		int idForm = Integer.parseInt(request.getParameter("idForm"));
		
		FormularioDao dao = new FormularioJPADao();
		
		this.formulario = dao.find(idForm);
		
		return "/professor/visualiza-formulario";
	}
	
	public String atualizaFormulario(){
		
		FormularioDao dao = new FormularioJPADao();
			
		dao.update(formulario);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true); 
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Formulário alterado com sucesso!"));
		
		return "/professor/formulario?faces-redirect=true";
	}

	public String relatorioMelhoresEquipes(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		FormularioDao fDao = new FormularioJPADao();
		Integer idFormulario = Integer.parseInt(request.getParameter("idForm"));
		this.formulario = fDao.find(idFormulario);
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext
				.getExternalContext().getContext();
		String path = scontext.getRealPath("/WEB-INF/relatorios/relatorioMelhorEquipe.jrxml");
		System.out.println(path);
		
		try {
			ReportUtil util = new ReportUtil();
			byte[] bytes = util.geraRelatorioMelhoresEquipes(this.formulario, path);
			if(bytes != null && bytes.length > 0){
				
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

				response.setContentType("application/pdf");

				response.setHeader("Content-disposition",
						"inline; filename=\"relat.pdf\"");

				response.setContentLength(bytes.length);

				ServletOutputStream outputStream;
				outputStream = response.getOutputStream();

				System.out.println("Escrevendo bytes na saída");
				System.out.println(bytes);	
				outputStream.write(bytes);
				
				outputStream.flush();

				outputStream.close();
				
			}
		} catch (Exception e) {	
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage("Aviso",
							"Verifique se as respostas e fatores foram adicionados"));
			e.printStackTrace();
		}

		
		return "/professor/formulario";
	}
	
	public String relatorioMelhoresApostadores(){
		
HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		FormularioDao fDao = new FormularioJPADao();
		Integer idFormulario = Integer.parseInt(request.getParameter("idForm"));
		this.formulario = fDao.find(idFormulario);
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();
		ServletContext scontext = (ServletContext) facesContext
				.getExternalContext().getContext();
		String path = scontext.getRealPath("/WEB-INF/relatorios/relatorioMelhorApostadorRodada.jrxml");
		System.out.println(path);
		
		try {
			ReportUtil util = new ReportUtil();
			byte[] bytes = util.geraRelatorioMelhoresApostadores(this.formulario, path);
			if(bytes != null && bytes.length > 0){
				
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

				response.setContentType("application/pdf");

				response.setHeader("Content-disposition",
						"inline; filename=\"relat.pdf\"");

				response.setContentLength(bytes.length);

				ServletOutputStream outputStream;
				outputStream = response.getOutputStream();

				System.out.println("Escrevendo bytes na saída");
				System.out.println(bytes);	
				outputStream.write(bytes);
				
				outputStream.flush();

				outputStream.close();
				
			}
		} catch (Exception e) {	
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage("Aviso",
							"Verifique se as respostas e fatores foram adicionados"));
			e.printStackTrace();
		}

		
		return "/professor/formulario";
	}
	
}









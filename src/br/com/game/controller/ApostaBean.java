package br.com.game.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.game.database.dao.ApostaDao;
import br.com.game.database.dao.ApostaJPADao;
import br.com.game.database.dao.FormularioDao;
import br.com.game.database.dao.FormularioJPADao;
import br.com.game.database.modelo.Aluno;
import br.com.game.database.modelo.Aposta;
import br.com.game.database.modelo.Equipe;
import br.com.game.database.modelo.Formulario;

@ManagedBean(name = "apostaBean")
public class ApostaBean {

	private static final double VALOR_RODADA = 10000;

	private Aposta aposta;

	public ApostaBean() {
		this.aposta = new Aposta();
		this.aposta.setValor(new Double(0));
	}

	public Aposta getAposta() {
		return aposta;
	}

	public void setAposta(Aposta aposta) {
		this.aposta = aposta;
	}

	public String realizarAposta() {
		
		if (this.aposta.getValor() > this.VALOR_RODADA) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Aviso",
							"Sua aposta deve ter no máximo R$"
									+ ApostaBean.VALOR_RODADA));
			return "";
		} else {
			FormularioDao formDao = new FormularioJPADao();
			try {
				Formulario formulario = formDao.formRodada();
				this.aposta.setFormulario(formulario);
				HttpSession session = (HttpSession) FacesContext
						.getCurrentInstance().getExternalContext()
						.getSession(true);
				Aluno aluno = (Aluno) session.getAttribute("usuario");
				this.aposta.setAluno(aluno);

				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				Equipe equipe = new Equipe();
				equipe.setId(Integer.parseInt(request.getParameter("id_equipe")));
				this.aposta.setEquipe(equipe);

				//Retirar o valor da aposta atual caso ele já esteja feito
				ApostaDao apostaDao = new ApostaJPADao();
				double somaApostas = apostaDao.somaValorApostas(formulario,
						aluno, equipe);
				if (somaApostas + this.aposta.getValor() <= ApostaBean.VALOR_RODADA) {
					apostaDao.salvaAposta(this.aposta);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Concluído", "Aposta realizada, você já gastou : "
									+ "R$ " + (somaApostas + this.aposta.getValor())));
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Aviso",
									"Sua aposta deve ter no máximo R$"
											+ ApostaBean.VALOR_RODADA));

					return "";
				}
			} catch (PersistenceException ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Aviso",
								"A rodada ainda não está aberta"));
				ex.printStackTrace();
			}

			return "?faces-redirect=true";

		}

	}

}

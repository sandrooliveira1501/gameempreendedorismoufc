package br.com.game.database.dao;

import java.util.List;

import br.com.game.database.modelo.Equipe;
import br.com.game.database.modelo.Formulario;
import br.com.game.database.modelo.Pergunta;
import br.com.game.database.modelo.Resposta;
import br.com.game.database.modelo.Usuario;

public interface FormularioDao extends GenericDao<Formulario>{

	public List<Formulario> getList();
	public Formulario formRodada();
	public List<Pergunta> getPerguntasForm(Formulario form);
	public List<Resposta> respostasCadastradas(Formulario form, Usuario usuario,Equipe equipe);
	public void realizaCalculoPontos(Formulario formulario);
	public void removerFormulario(Formulario formulario);
	public Formulario findFormularioData(int id);
	
}

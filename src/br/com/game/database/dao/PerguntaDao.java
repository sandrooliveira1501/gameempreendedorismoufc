package br.com.game.database.dao;

import java.util.List;

import br.com.game.database.modelo.Formulario;
import br.com.game.database.modelo.Pergunta;

public interface PerguntaDao extends GenericDao<Pergunta>{

	public List<Pergunta> getList(Formulario form);
	public void removerPergunta(Pergunta pergunta);
	
}

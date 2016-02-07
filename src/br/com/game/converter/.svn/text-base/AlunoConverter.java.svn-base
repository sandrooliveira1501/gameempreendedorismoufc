package br.com.game.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.game.database.modelo.Aluno;
import br.com.game.database.util.JPAUtil;

@FacesConverter(value="alunoConverter",forClass = Aluno.class)
public class AlunoConverter implements Converter {

	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		//System.err.println("Utilizando Converter\n\n" + object);
		Aluno aluno = (Aluno) object;
		if (aluno == null || aluno.getLogin() == null)
			return null;
		return String.valueOf(aluno.getLogin());
	}

	public Object getAsObject(FacesContext context, UIComponent component,
			String string) {
		//System.err.println("Utilizando Convertern\n\n" + string);
		if (string == null || string.isEmpty())
			return null;
		Long login = Long.valueOf(string);
		Aluno aluno = JPAUtil.createEntityManager().find(Aluno.class, login);
		return aluno;
	}
	
}

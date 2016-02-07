package br.com.game.database.dao;

import br.com.game.database.modelo.Usuario;
import br.com.game.exceptions.ErroLoginException;

public interface UsuarioDao extends GenericDao<Usuario>{
	
	public Usuario loginUsuario(Usuario usuario);
	public void alterarSenha(Usuario usuario, String senhaNova) throws ErroLoginException; 
	
}

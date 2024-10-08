package br.com.germantech.service;

import java.sql.SQLException;
import java.util.List;

import br.com.germantech.entidade.Usuario;

public interface UsuarioServico {

	void cadastrar(Usuario usuario) throws SQLException ;
	
	Usuario buscaUsuarioPorId(Long id) throws SQLException ;
	
	void alterarUsuario(Long id, Usuario usuario);
	
	List<Usuario> listaUsuarios() throws SQLException;
	
	void deleteById(Long id);
}

package br.com.germantech.service;

import java.util.List;

import br.com.germantech.entidade.Usuario;

public interface UsuarioServico {

	Usuario cadastrar(Usuario usuario);
	
	Usuario buscaUsuarioPorId(Long id);
	
	Usuario alterarUsuario(Long id, Usuario usuario);
	
	List<Usuario> listaUsuarios();
	
	void deleteById(Long id);
}

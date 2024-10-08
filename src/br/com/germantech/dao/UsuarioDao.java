package br.com.germantech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.germantech.conexao.DatabaseConnection;
import br.com.germantech.entidade.Usuario;
import br.com.germantech.service.UsuarioServico;

public class UsuarioDao implements UsuarioServico {

    private Connection connection;
    
    @Override
    public void cadastrar(Usuario usuario) throws SQLException {
    	connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO usuario (name, phone, email, cpf, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getName());
            stmt.setString(2, usuario.getPhone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getPassword());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                usuario.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscaUsuarioPorId(Long id) throws SQLException {
    	connection = DatabaseConnection.getConnection();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { 
                usuario = new Usuario(); 
                usuario.setId(rs.getLong("id"));
                usuario.setName(rs.getString("name"));
                usuario.setPhone(rs.getString("phone"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return usuario;
    }


    @Override
    public void alterarUsuario(Long id, Usuario usuario) throws SQLException {
        connection = DatabaseConnection.getConnection();
        String sql = "UPDATE usuario SET name = ?, phone = ?, email = ?, cpf = ?, password = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getName());
            stmt.setString(2, usuario.getPhone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getPassword());
            stmt.setLong(6, id);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum usuário encontrado com o ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


	@Override
	public List<Usuario> listaUsuarios() throws SQLException {
		connection = DatabaseConnection.getConnection();
	    List<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT * FROM usuario";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        
	        while (rs.next()) {
	            Usuario usuario = new Usuario();
	            usuario.setId(rs.getLong("id"));
	            usuario.setName(rs.getString("name"));
	            usuario.setPhone(rs.getString("phone"));
	            usuario.setEmail(rs.getString("email"));
	            usuario.setCpf(rs.getString("cpf"));
	            usuario.setPassword(rs.getString("password"));
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return usuarios;
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean cpfExists(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        }
        return false;
    }


}

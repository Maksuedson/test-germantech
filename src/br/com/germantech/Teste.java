package br.com.germantech;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.germantech.conexao.DatabaseConnection;

public class Teste {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Falha ao estabelecer a conexão: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexão fechada com sucesso.");
                } catch (SQLException e) {
                    System.err.println("Falha ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
}

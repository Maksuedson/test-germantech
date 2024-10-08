package br.com.germantech.view;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import br.com.germantech.dao.UsuarioDao;
import br.com.germantech.entidade.Usuario;

public class JListaUsuarios extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private UsuarioDao usuarioDao;

    public JListaUsuarios() {
        usuarioDao = new UsuarioDao();
        setTitle("Lista de Usuários");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Telefone", "Email", "CPF", "SENHA"}, 0);
        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);

        tabelaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabelaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(150); 
        tabelaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(100); 
        tabelaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(230); 
        
        JButton btnCarregar = new JButton("Carregar Usuários");
        btnCarregar.addActionListener(e -> carregarUsuarios());

        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(btnCarregar, BorderLayout.SOUTH);
    }

    private void carregarUsuarios() {
        try {
            modeloTabela.setRowCount(0);
            List<Usuario> usuarios = usuarioDao.listaUsuarios();
            for (Usuario usuario : usuarios) {
                modeloTabela.addRow(new Object[]{usuario.getId(), usuario.getName(), usuario.getPhone(), usuario.getEmail(), usuario.getCpf(), usuario.getPassword()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JListaUsuarios frame = new JListaUsuarios();
            frame.setVisible(true);
        });
    }
}

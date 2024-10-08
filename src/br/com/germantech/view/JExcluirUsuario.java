package br.com.germantech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.germantech.dao.UsuarioDao;
import br.com.germantech.entidade.Usuario;

public class JExcluirUsuario extends JFrame {
    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private JTextField textId;
    private JButton btn_excluir;
    private DefaultTableModel modeloTabela;
    private JTable tabelaUsuarios;

    public JExcluirUsuario() {
        initialize();
        listeners();
        atualizarTabela(); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 450); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        JLabel lblNewLabel = new JLabel("Excluir Usuário");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(190, 11, 274, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel jLabel1 = new JLabel("Id:");
        jLabel1.setBounds(10, 45, 46, 14);
        frame.getContentPane().add(jLabel1);
        
        textId = new JTextField();
        textId.setBounds(50, 45, 50, 24);
        frame.getContentPane().add(textId);
        textId.setColumns(10);
        
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Telefone", "Email", "CPF"}, 0);
        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.setBounds(10, 80, 460, 250); 
        frame.getContentPane().add(scrollPane);

        btn_excluir = new JButton("Excluir");
        btn_excluir.setBounds(81, 340, 89, 23);
        frame.getContentPane().add(btn_excluir);

        JButton btn_sair = new JButton("Sair");
        btn_sair.addActionListener(e -> frame.dispose());
        btn_sair.setBounds(327, 340, 89, 23);
        frame.getContentPane().add(btn_sair);
        
        btn_excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(textId.getText());
				UsuarioDao dao = new UsuarioDao();
				try {
					dao.deleteById(id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
				atualizarTabela(); 
            }
        });
    }
    
    private void textIdPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Long id = Long.valueOf(textId.getText());
            try {
                buscarUsuario(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void listeners() {
        textId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textIdPressed(evt);
            }
        });
    }
   
    private void buscarUsuario(Long id) throws SQLException {
        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.buscaUsuarioPorId(id);
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(frame, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
        
    }
    
    private void atualizarTabela() {
        UsuarioDao dao = new UsuarioDao();
        try {
            List<Usuario> usuarios = dao.listaUsuarios();
            modeloTabela.setRowCount(0);
            
            for (Usuario usuario : usuarios) {
                modeloTabela.addRow(new Object[]{usuario.getId(), usuario.getName(), usuario.getPhone(), usuario.getEmail(), usuario.getCpf()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

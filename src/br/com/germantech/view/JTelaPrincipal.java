package br.com.germantech.view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTelaPrincipal extends JFrame {

    public JTelaPrincipal() {
        setTitle("Tela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
        JMenuItem itemAlterarUsuario = new JMenuItem("Alterar Usuário");
        JMenuItem itemExcluirUsuario = new JMenuItem("Excluir Usuário");
        JMenuItem itemListarUsuario = new JMenuItem("Listar Usuários");
        JMenuItem itemSair = new JMenuItem("Sair");

        itemCadastrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	UsuarioGui frame = new UsuarioGui();

            }
        });
        
        itemListarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JListaUsuarios frame = new JListaUsuarios();

            }
        });

        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        menuArquivo.add(itemCadastrarUsuario);
        menuArquivo.add(itemListarUsuario);
        menuArquivo.add(itemAlterarUsuario);
        menuArquivo.add(itemExcluirUsuario);
        menuArquivo.add(itemSair);

        menuBar.add(menuArquivo);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTelaPrincipal frame = new JTelaPrincipal();
            frame.setVisible(true);
        });
    }
}

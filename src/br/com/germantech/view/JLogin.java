package br.com.germantech.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.germantech.utils.Criptografia;

public class JLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	JButton btnNewButton;

	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 315);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(49, 62, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(136, 24, 251, 227);
		panel.setBackground(new Color(204,207,208));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(42, 54, 46, 14);
		panel.add(lblNewLabel);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(42, 67, 160, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(42, 118, 46, 14);
		panel.add(lblNewLabel_1);
		
        btnNewButton = new JButton("Entrar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String senhaCriptografada = Criptografia.criptografar(new String(passwordField.getPassword()), Criptografia.MD5);
                System.out.println(senhaCriptografada);
                
                if (!textFieldUsuario.getText().isEmpty() && passwordField.getPassword().length > 0) {
                    if (senhaCriptografada.equals("E10ADC3949BA59ABBE56E057F20F883E")) {
                        JOptionPane.showMessageDialog(btnNewButton, "Informações válidas");
                        dispose();
                        
                        UsuarioGui jUsuario = new UsuarioGui();
                    }
                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
		btnNewButton.setBackground(new Color(49, 62, 64));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(79, 182, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Bem vindo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(74, 11, 99, 14);
		panel.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(42, 132, 160, 23);
		panel.add(passwordField);
		
		textFieldUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyPressed(java.awt.event.KeyEvent evt) {
	        	textFieldUsuarioPressed(evt);
	        }
	    });
		
		passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyPressed(java.awt.event.KeyEvent evt) {
	        	salvarPressed(evt);
	        }
	    });	
	}
	
    private void textFieldUsuarioPressed(java.awt.event.KeyEvent evt) {                                 
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){  
        	passwordField.requestFocus();	    
        }
    }
    
    private void salvarPressed(java.awt.event.KeyEvent evt) {                                 
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        	btnNewButton.doClick();
        }
    }
}
package br.com.germantech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.germantech.dao.UsuarioDao;
import br.com.germantech.entidade.Usuario;
import br.com.germantech.utils.Criptografia;

public class JEditarUsuario extends JFrame {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JTextField textId;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textCpf;
	private JTextField textPassword;
	private JButton btn_salvar;

	public JEditarUsuario() {

		initialize();
		listeners();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JLabel lblNewLabel = new JLabel("Editar Usuários");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(190, 11, 274, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel jLabel1 = new JLabel("Id:");
		jLabel1.setBounds(10, 45, 46, 14);
		frame.getContentPane().add(jLabel1);
		
		JLabel jLabel2 = new JLabel("Nome:");
		jLabel2.setBounds(10, 65, 46, 14);
		frame.getContentPane().add(jLabel2);

		JLabel jLabel3 = new JLabel("CPF:");
		jLabel3.setBounds(10, 90, 46, 14);
		frame.getContentPane().add(jLabel3);

		JLabel jLabel4 = new JLabel("Email:");
		jLabel4.setBounds(10, 115, 80, 14);
		frame.getContentPane().add(jLabel4);

		JLabel jLabel5 = new JLabel("Telefone:");
		jLabel5.setBounds(10, 140, 96, 14);
		frame.getContentPane().add(jLabel5);

		JLabel jLabel6 = new JLabel("Password:");
		jLabel6.setBounds(10, 165, 96, 14);
		frame.getContentPane().add(jLabel6);

		textId = new JTextField();
		textId.setBounds(167, 45, 50, 24);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textNome = new JTextField();
		textNome.setBounds(167, 65, 200, 24);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);

		MaskFormatter cpfFormatter;
		try {
			cpfFormatter = new MaskFormatter("###.###.###-##");
			cpfFormatter.setPlaceholderCharacter('_');
			textCpf = new JFormattedTextField(cpfFormatter);
		} catch (Exception e) {
			e.printStackTrace();
			textCpf = new JFormattedTextField(); // Fallback
		}

		textCpf.setBounds(167, 90, 200, 24);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(167, 115, 200, 24);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		MaskFormatter phoneFormatter;
		try {
			phoneFormatter = new MaskFormatter("(##) 9#### ####");
			phoneFormatter.setPlaceholderCharacter('_');
			textTelefone = new JFormattedTextField(phoneFormatter);
		} catch (Exception e) {
			e.printStackTrace();
			textTelefone = new JFormattedTextField();
		}

		textTelefone.setBounds(167, 140, 200, 24);
		frame.getContentPane().add(textTelefone);
		textTelefone.setColumns(10);

		textPassword = new JPasswordField();
		textPassword.setBounds(167, 165, 200, 24);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);

		btn_salvar = new JButton("Alterar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Usuario usuario = new Usuario();
				usuario.setName(textNome.getText());
				usuario.setPhone(textTelefone.getText());
				usuario.setEmail(textEmail.getText());
				usuario.setCpf(textCpf.getText());
				usuario.setPassword(Criptografia.criptografar(textPassword.getText(), "MD5"));

				if ((textNome.getText().isEmpty()) || (textCpf.getText().isEmpty()) || (textEmail.getText().isEmpty())
						|| (textTelefone.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
					return;
				}

				UsuarioDao dao = new UsuarioDao();
				try {
					Long id = Long.valueOf(textId.getText());
					dao.alterarUsuario(id, usuario);
					JOptionPane.showMessageDialog(null, "Usuário" + textNome.getText() + " Inserido com sucesso");
					limparCampos();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!");
				}

			}

		});
		btn_salvar.setBounds(81, 220, 89, 23);
		frame.getContentPane().add(btn_salvar);

		JButton btn_limpar = new JButton("Limpar");
		btn_limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textNome.setText("");
				textCpf.setText("");
				textEmail.setText("");
				textTelefone.setText("");
				textPassword.setText("");

			}
		});
		btn_limpar.setBounds(208, 220, 89, 23);
		frame.getContentPane().add(btn_limpar);

		JButton btn_sair = new JButton("Sair");
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_sair.setBounds(327, 220, 89, 23);
		frame.getContentPane().add(btn_sair);

	}

	private void textNomeReleased(java.awt.event.KeyEvent evt) {
		textNome.setText(textNome.getText().toUpperCase());
	}

	private void textEmailReleased(java.awt.event.KeyEvent evt) {
		textEmail.setText(textEmail.getText().toLowerCase());
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
	
	private void textNomePressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			textCpf.requestFocus();
		}
	}

	private void textCpfPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			textEmail.requestFocus();
		}
	}

	private void textEmailPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			textTelefone.requestFocus();
		}
	}

	private void textTelefonePressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			textPassword.requestFocus();
		}
	}

	private void textPasswordPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			btn_salvar.doClick();
		}
	}

	private void listeners() {
		
		textId.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				textIdPressed(evt);
			}
		});
		
		textNome.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				textNomePressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				textNomeReleased(evt);
			}
		});

		textCpf.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				textCpfPressed(evt);
			}

		});

		textEmail.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				textEmailPressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				textEmailReleased(evt);
			}
		});

		textTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				textTelefonePressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				textEmailReleased(evt);
			}
		});

		textPassword.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				textPasswordPressed(evt);
			}
		});
	}

	private void limparCampos() {
		textNome.setText("");
		textCpf.setText("");
		textEmail.setText("");
		textTelefone.setText("");
		textPassword.setText("");
	}
	
	private void buscarUsuario(Long id) throws SQLException {
		UsuarioDao dao = new UsuarioDao();
		Usuario usuario = dao.buscaUsuarioPorId(id);
		
	    if (usuario == null) {
	        JOptionPane.showMessageDialog(frame, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
	        limparCampos(); 
	    } else {
	        textNome.setText(usuario.getName());
	        textCpf.setText(usuario.getCpf());
	        textEmail.setText(usuario.getEmail());
	        textTelefone.setText(usuario.getPhone());
	        textPassword.setText(usuario.getPassword());
	    }

	}
}
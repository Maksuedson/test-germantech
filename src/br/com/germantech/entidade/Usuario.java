package br.com.germantech.entidade;

public class Usuario {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private String cpf;
	private String password;
	
	public Usuario() {
	}
	
	public Usuario(String name, String phone, String email, String cpf, String password) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.cpf = cpf;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

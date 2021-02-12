package br.com.ifsc.crud.entities;

public class Usuario {

	private String nome_usuario;
	private String senha;
	private String sal;

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
	    if(nome_usuario.isBlank() || nome_usuario == null) {
	    	throw new IllegalArgumentException("O nome do usuário não pode ser vazio");
	    }
		this.nome_usuario = nome_usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
	    if(senha.isBlank() || senha == null) {
	    	throw new IllegalArgumentException("A senha não pode ser vazia");
	    }
		this.senha = senha;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "Usuario [nome_usuario=" + nome_usuario + ", senha=" + senha + ", sal=" + sal + "]";
	}

}

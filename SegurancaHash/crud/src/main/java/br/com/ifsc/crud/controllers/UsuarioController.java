package br.com.ifsc.crud.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.HashMap;
import br.com.ifsc.crud.entities.Usuario;
import br.com.ifsc.crud.utility.MessageAlert;

public class UsuarioController {
	private HashMap<String, Usuario> listaUsuarios;
	private static UsuarioController usuarioController;
	private SecureRandom secureRandom;
	private Usuario usuarioLogado;

	public static UsuarioController getInstance() {
		if (usuarioController == null) {
			usuarioController = new UsuarioController();
		}
		return usuarioController;
	}

	public void criarConta(String nomeUsuario, String senha, String confirmarSenha) {

		if (getListaUsuarios().containsKey(nomeUsuario)) {
			throw new IllegalArgumentException("Já existe usuário com este nome de usuário cadastrado!");
		} else {
			if (!senha.equals(confirmarSenha)) {
				throw new IllegalArgumentException("As senhas não são iguais!");
			}
			try {
				secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
				String valorSal = String.valueOf(secureRandom.nextInt());
				String senhaHash = gerarHashSenha(confirmarSenha + valorSal);

				Usuario usuario = new Usuario();
				usuario.setNome_usuario(nomeUsuario);
				usuario.setSenha(senhaHash);
				usuario.setSal(valorSal);

				getListaUsuarios().put(usuario.getNome_usuario(), usuario);

			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				MessageAlert.mensagemErro("Erro ao salvar o usuário!");
				e.printStackTrace();
			}
		}
	}

	private String gerarHashSenha(String senha) {
		String sha512 = null;

		MessageDigest digest;

		try {

			digest = MessageDigest.getInstance("SHA-512");
			digest.update(senha.getBytes(), 0, senha.length());

			// Convertendo para hexa

			sha512 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sha512;
	}

	public boolean login(String nomeUsuario, String senha) {

		Usuario usuario = null;

		if (getListaUsuarios().containsKey(nomeUsuario)) {
			usuario = listaUsuarios.get(nomeUsuario);
			String sal = gerarHashSenha(senha + usuario.getSal());
			if (sal.equals(usuario.getSenha())) {
				return true;

			} else {
				throw new IllegalArgumentException("Usuário e/ou senha inválidos");
			}

		} else {
			throw new IllegalArgumentException("Este usuário não está registrado!");

		}
	}

	public HashMap<String, Usuario> getListaUsuarios() {
		if (listaUsuarios == null) {
			listaUsuarios = new HashMap<String, Usuario>();
		}
		return listaUsuarios;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		if (usuarioLogado == null) {
			throw new IllegalArgumentException("O usuário logado não pode ser nulo!");
		}
		this.usuarioLogado = usuarioLogado;
	}

}

package br.com.ifsc.crud.controllersViews;

import br.com.ifsc.crud.App;
import br.com.ifsc.crud.controllers.UsuarioController;
import br.com.ifsc.crud.utility.MessageAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CriarContaController {
	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private PasswordField txtConfirmarSenha;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	private UsuarioController usuarioController;

	@FXML
	public void voltar() {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));

			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

			Scene scene = btnVoltar.getScene();
			Stage stageWindow = (Stage) scene.getWindow();
			stageWindow.close();

		} catch (Exception exception) {
			MessageAlert.mensagemErro(exception.getMessage());
		}

	}

	@FXML
	public void criarConta() {
		String nomeUsuario = txtUsuario.getText().trim();
		String senha = txtSenha.getText();
		String confirmarSenha = txtConfirmarSenha.getText();

		if (nomeUsuario.isBlank() || nomeUsuario == null) {
			MessageAlert.mensagemErro("O nome do usuário não pode ser vazio!");
			return;
		}

		else if (senha.isBlank() || nomeUsuario == null) {
			MessageAlert.mensagemErro("A senha não pode ser vazia!");
			return;
		} else if (confirmarSenha.isBlank() || confirmarSenha == null) {
			MessageAlert.mensagemErro("A confirmação da senha não pode ser vazia!");
			return;
		}
		usuarioController = UsuarioController.getInstance();

		try {
			usuarioController.criarConta(nomeUsuario, senha, confirmarSenha);
			MessageAlert.mensagemRealizadoSucesso("Usuário cadastrado com sucesso!");
			limparCampos();

		} catch (Exception e) {
			MessageAlert.mensagemErro(e.getMessage());
			txtConfirmarSenha.setText("");
			txtSenha.setText("");
		}
	}

	private void limparCampos() {
		txtUsuario.setText("");
		txtSenha.setText("");
		txtConfirmarSenha.setText("");
	}

	public PasswordField getTxtSenha() {
		return txtSenha;
	}

	public PasswordField getTxtConfirmarSenha() {
		return txtConfirmarSenha;
	}

}

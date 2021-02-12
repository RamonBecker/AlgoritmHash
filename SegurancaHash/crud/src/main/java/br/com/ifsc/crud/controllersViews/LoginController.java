package br.com.ifsc.crud.controllersViews;

import br.com.ifsc.crud.App;
import br.com.ifsc.crud.controllers.UsuarioController;
import br.com.ifsc.crud.entities.Usuario;
import br.com.ifsc.crud.utility.MessageAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField textUsuario;

	@FXML
	private PasswordField textSenha;

	@FXML
	private Button btnLogin;

	private UsuarioController usuarioController;

	@FXML
	public void login(ActionEvent e) {
		usuarioController = UsuarioController.getInstance();

		String nomeUsuario = textUsuario.getText();
		String senha = textSenha.getText();

		if (nomeUsuario.isBlank() || nomeUsuario == null) {
			MessageAlert.mensagemErro("O nome do usuário não pode ser vazio!");
			return;
		}

		else if (senha.isBlank() || nomeUsuario == null) {
			MessageAlert.mensagemErro("A senha não pode ser vazia!");
			return;
		}

		try {

			if (usuarioController.login(nomeUsuario, senha)) {
				
				Usuario usuario = usuarioController.getListaUsuarios().get(nomeUsuario);
						
				usuarioController.setUsuarioLogado(usuario);

				try {
					FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("principal.fxml"));

					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.show();

					Scene scene = btnLogin.getScene();
					Stage stageWindow = (Stage) scene.getWindow();
					stageWindow.close();

				} catch (Exception exception) {
					exception.printStackTrace();
				//	MessageAlert.mensagemErro("Não foi possível carregar a tela principal!");
					limparCampos();
				}
			}
		} catch (Exception error) {
			MessageAlert.mensagemErro(error.getMessage());
			limparCampos();
		}
	}

	public void criarConta(ActionEvent e) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("contaNova.fxml"));

			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

			Scene scene = btnLogin.getScene();
			Stage stageWindow = (Stage) scene.getWindow();
			stageWindow.close();

		} catch (Exception exception) {
			MessageAlert.mensagemErro(exception.getMessage());
		}
	}

	private void limparCampos() {
		textUsuario.setText("");
		textSenha.setText("");
	}

}

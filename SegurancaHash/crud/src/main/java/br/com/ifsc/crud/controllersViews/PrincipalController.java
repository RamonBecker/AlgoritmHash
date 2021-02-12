package br.com.ifsc.crud.controllersViews;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ifsc.crud.App;
import br.com.ifsc.crud.controllers.UsuarioController;
import br.com.ifsc.crud.utility.MessageAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

	@FXML
	private Label labelUsuario;

	@FXML
	private Button btnSair;

	private UsuarioController usuarioController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usuarioController = UsuarioController.getInstance();
		labelUsuario.setText(usuarioController.getUsuarioLogado().getNome_usuario());
	}

	@FXML
	public void sair() {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));

			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

			Scene scene = btnSair.getScene();
			Stage stageWindow = (Stage) scene.getWindow();
			stageWindow.close();

		} catch (Exception exception) {
			MessageAlert.mensagemErro(exception.getMessage());
		}
	}

}

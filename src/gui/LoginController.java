package gui;

import java.io.IOException;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import model.entities.User;
import model.services.UserService;

public class LoginController {
	
	private UserService userService = new UserService();
	
	private static User loggedUser = null;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private Hyperlink btnSignIn;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField passField;
	
	public static User getUser() {
		return loggedUser;
	}
	
	@FXML
	public void onBtnLoginAction() {
		String username = txtUsername.getText();
		String password = passField.getText();
		User response = userService.login(username,password);
		if (response == null) {
			Alerts.showAlert("Incorrect credentials", null, "Username or password incorrect", AlertType.ERROR);
		} else {
			loggedUser = response;
			changeView("/gui/Main.fxml");
		}	
	}
	
	@FXML
	public void onHyperLinkBtnSignInAction() {
		changeView("/gui/SignIn.fxml");
	}
	
	public void changeView(String path) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(path));
			
			Scene newScene = new Scene(parent);
			
			Main.getPrimaryStage().setScene(newScene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
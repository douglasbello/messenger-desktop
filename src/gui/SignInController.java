package gui;

import java.io.IOException;

import application.App;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.User;
import model.services.UserService;

public class SignInController {
	private UserService userService = new UserService();
	
	@FXML
	private TextField txtFieldUsername;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button btnSignIn;
	
	@FXML
	private Hyperlink btnHyperLinkLogin;
	
	@FXML
	private Label messageSucessLabel;
	
	@FXML
	public void onBtnSignInAction() {
		String username = txtFieldUsername.getText();
		String password = passwordField.getText();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		int responseCode = userService.signIn(user);
		
		if (txtFieldUsername.getText().length() < 4 || txtFieldUsername.getText().length() > 20) {
			Alerts.showAlert("User error", null, "Username cannot be less than 4 characters or more than 20 characters", AlertType.ERROR);
			return;
		}
		
		if (passwordField.getText().length() < 8 || passwordField.getText().length() > 100) {
			Alerts.showAlert("Password error", null, "Password cannot be less than 8 characters or more than 100 characters.", AlertType.ERROR);
			return;
		}
		if (responseCode < 299) {
			messageSucessLabel.setText("Account created successfully!");
			txtFieldUsername.setText("");
			passwordField.setText("");
			return;
		}
		Alerts.showAlert("Username already in use!", null, "Username is already in use!", AlertType.ERROR);
	
	}
	
	@FXML
	public void onHyperLinkBtnLoginInAction() {
		changeToLoginView("/gui/Login.fxml");
	}
	
	public void changeToLoginView(String path) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(path));
			
			Scene newScene = new Scene(parent);
			
			App.getPrimaryStage().setScene(newScene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
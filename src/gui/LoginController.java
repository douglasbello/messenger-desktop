package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import model.entities.User;
import model.services.UserService;

public class LoginController implements Initializable {
	
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
	
	public static void setUser(User user) {
		loggedUser = user;
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtUsername.setOnKeyPressed(this::handleKeyPressed);
		passField.setOnKeyPressed(this::handleKeyPressed);
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
	
	private void handleKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btnLogin.fire();
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
			
			App.getPrimaryStage().setScene(newScene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
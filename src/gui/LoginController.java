package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.User;
import model.services.UserService;

public class LoginController {
	
	private UserService userService = new UserService();
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField passField;
	
	@FXML
	public void onBtnLoginAction() {
		String username = txtUsername.getText();
		String password = passField.getText();
		User response = userService.login(username,password);
		if (response == null) {
			Alerts.showAlert("Incorrect credentials", null, "Username or password incorrect", AlertType.ERROR);
		} else {
			System.out.println(response);
		}	
	}
}
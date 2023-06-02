package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.entities.User;
import model.services.UserService;

public class MainController implements Initializable {
	private static User loggedUser = null;
	
	private UserService userService = new UserService();
	
	@FXML
	private Button logoutBtn;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private Button friendshipRequestsButton;
	
	@FXML
	public void onLogoutBtnAction() {
		loggedUser = null;
		LoginController.setUser(null);
		changeToView("/gui/Login.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loggedUser = LoginController.getUser();
		VBox friends = new VBox();
		for (User u : userService.getAllFriendsFromUser(loggedUser.getId(), loggedUser.getToken())) {
			Button usernameButton = new Button(u.getUsername());
			usernameButton.setAlignment(Pos.TOP_LEFT);
			usernameButton.setPrefWidth(205);
			usernameButton.setPrefHeight(40);
			Font font = new Font("System", 16);
			usernameButton.setFont(font);
			usernameButton.setStyle("-fx-background-color: transparent; -fx-border-color: #fc7d2d; -fx-border-width: 0px 0px 2px 0px");
			friends.getChildren().add(usernameButton);
		}
		
		scrollPane.setContent(friends);
	}
	
	@FXML
	public void onFriendshipRequestsBtnAction() {
		changeToView("/gui/FriendshipRequests.fxml");
	}
	
	public void changeToView(String path) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(path));
			
			Scene newScene = new Scene(parent);
			
			App.getPrimaryStage().setScene(newScene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
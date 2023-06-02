package gui;

import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.entities.User;
import model.services.UserService;

public class MainController {
	protected static User loggedUser = LoginController.getUser();
	
	private UserService userService = new UserService();
	
	@FXML
	private Button logoutBtn;
	
	
	@FXML
	public void blablabla() {
		Set<User> friends = userService.getAllFriendsFromUser(loggedUser.getId(), loggedUser.getToken());
		
		friends.forEach(System.out::println);
	}
	

}
package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.entities.User;
import model.services.UserService;

public class LoginController {
	
	private UserService userService;
	
	@FXML
	private Button btnLogin;
	
	private void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@FXML
	public void onBtnLoginAction() {
		UserService bla = new UserService();
		User response = bla.login("user01", "user01");
		System.out.println(response);
//		if (responseCode > 299) {
//			System.out.println("Error");
//		} else {
//			System.out.println("Good");
//		}
			
	}
	
	public String login() {
		int responseCode = 403;
        try {
            URL url = new URL("http://localhost:8080/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();
            String responseBody = readResponseBody(connection);

//            System.out.println("Response Code: " + responseCode);
//            System.out.println("Response Body: " + responseBody);
            
            connection.disconnect();
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return "erro";
        }
    }

    private static String readResponseBody(HttpURLConnection connection) throws IOException {
        StringBuilder responseBody = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line + "\n");
            }
        }

        return responseBody.toString();
    }
}
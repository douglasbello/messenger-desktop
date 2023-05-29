package model.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.entities.User;

public class UserService {
	
	private static final String API_URL = "http://localhost:8080";

	public User login(String username, String password) {
		try {
			URL url = new URL(API_URL + "/users/login");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			
			String requestBody = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
			
			OutputStream outputStream = connection.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeBytes(requestBody);
			dataOutputStream.flush();
			dataOutputStream.close();
			
			int responseCode = connection.getResponseCode();
			String responseBody = readResponseBody(connection);
			Gson gson = new GsonBuilder().create();
			
			User user = gson.fromJson(responseBody, User.class);
			
			connection.disconnect();
			return user;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
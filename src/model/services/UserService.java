package model.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.entities.User;

public class UserService {
	
	private static final String API_URL = "http://localhost:8080/users";
	
	public int signIn(User obj) throws IOException {
	    URL url = new URL(API_URL + "/signIn");
	    
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    connection.setRequestProperty("Content-Type", "application/json");
	    
	    String requestBody = "{\"username\":\"" + obj.getUsername() + "\",\"password\":\"" + obj.getPassword() + "\"}";
	    
	    OutputStream outputStream = connection.getOutputStream();
	    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
	    dataOutputStream.writeBytes(requestBody);
	    dataOutputStream.flush();
	    dataOutputStream.close();
	    
	    int statusCode = connection.getResponseCode();
	    
	    connection.disconnect();
	    return statusCode;
	}

	
	public User login(String username, String password) {
		try {
			URL url = new URL(API_URL + "/login");
			
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
			if (responseCode > 299) {
				return null;
			}
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
	
	public Set<User> getAllFriendsFromUser(Integer userId, String userToken) {
		try {
			URL url = new URL(API_URL + "/" + userId + "/friends");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			
			String authorizationHeader = "Bearer " + userToken;
			connection.setRequestProperty("Authorization", authorizationHeader);
			
			int statusCode = connection.getResponseCode();
			
			if (statusCode > 299) {
				connection.disconnect();
				return null;
			} else {
				String response = readResponseBody(connection);
				Set<User> friends = transformResponseToUsers(response);
				connection.disconnect();
				return friends;
			}
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
                responseBody.append(line);
            }
        }

        return responseBody.toString();
    }
    
    private static Set<User> transformResponseToUsers(String response) {
    	Gson gson = new Gson();
        Type setType = new TypeToken<Set<User>>(){}.getType();
        return gson.fromJson(response, setType);
        
    }
}
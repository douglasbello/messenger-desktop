package model.entities;

import java.util.List;
import java.util.Objects;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String imgUrl;
	private List<Integer> friends;
	private List<Integer> chats;
	private String token;
	
	public User() {
	}

	public User(Integer id, String username, String password, String imgUrl, List<Integer> friends, List<Integer> chats) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.imgUrl = imgUrl;
		this.friends = friends;
		this.chats = chats;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<Integer> getFriends() {
		return friends;
	}

	public void setFriends(List<Integer> friends) {
		this.friends = friends;
	}

	public List<Integer> getChats() {
		return chats;
	}

	public void setChats(List<Integer> chats) {
		this.chats = chats;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", imgUrl=" + imgUrl
				+ ", friends=" + friends + ", chats=" + chats + ", token=" + token +"]";
	}
}
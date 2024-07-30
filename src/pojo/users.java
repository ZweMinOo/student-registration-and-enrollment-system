package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * <h1> users </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
@Entity
public class users {
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator="generator")
	private int user_id;
	@Column
	private String username;
	@Column
	private String password;
	/**
	   * Constructor
	   * 
	   */
	public users() {}
	/**
	   * Constructor
	   * 
	   * @param username parameter to receive the user name of users object
	   * @param password parameter to receive the password of users object
	   */
	public users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	   * Method to get the user id of users object
	   * 
	   * @return user_id parameter to return the user id of users object
	   */
	public int getUser_id() {
		return user_id;
	}
	/**
	   * Method to set the user id of users object
	   * 
	   * @param user_id parameter to receive the user id of users object
	   */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	   * Method to get the user name of users object
	   * 
	   * @return username parameter to return the user name of users object
	   */
	public String getUsername() {
		return username;
	}
	/**
	   * Method to set the user name of users object
	   * 
	   * @param username parameter to receive the user name of users object
	   */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	   * Method to get the password of users object
	   * 
	   * @return password parameter to return the password of users object
	   */
	public String getPassword() {
		return password;
	}
	/**
	   * Method to set the password of users object
	   * 
	   * @param password parameter to receive the password of users object
	   */
	public void setPassword(String password) {
		this.password = password;
	}
}
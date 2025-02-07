package own.data.model;


import own.data.enums.UserSexEnum;

import java.io.Serializable;

public class UserMysql implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String user_name;
	private String pass_word;
	private UserSexEnum user_sex;
	private String nick_name;

	public UserMysql() {
		super();
	}

	public UserMysql(String userName, String pass_word, UserSexEnum user_sex) {
		super();
		this.pass_word = pass_word;
		this.user_name = userName;
		this.user_sex = user_sex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public UserSexEnum getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(UserSexEnum user_sex) {
		this.user_sex = user_sex;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName " + this.user_name + ", pasword " + this.pass_word + "sex " + user_sex.name();
	}

}
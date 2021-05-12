package com.revature.models;

public class ErsUsers {
	private int ersUserId;
	private String ersUsername;

	private String ersPassword;

	private String userFirstname;

	private String userLastname;

	private String userEmail;
	private int userRoleId;

	public ErsUsers(int ersUserId, String ersUsername, String ersPassword, String userFirstname, String userLastname,
			String userEmail, int userRoleId) {
		super();
		this.ersUserId = ersUserId;
		this.ersUsername = ersUsername;
		this.ersPassword = ersPassword;
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}

	public int getErsUserId() {
		return ersUserId;
	}

	public void setErsUserId(int ersUserId) {
		this.ersUserId = ersUserId;
	}

	public String getErsUsername() {
		return ersUsername;
	}

	public void setErsUsername(String ersUsername) {
		this.ersUsername = ersUsername;
	}

	public String getErsPassword() {
		return ersPassword;
	}

	public void setErsPassword(String ersPassword) {
		this.ersPassword = ersPassword;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "ErsUsers [ersUserId=" + ersUserId + ", ersUsername=" + ersUsername + ", ersPassword=" + ersPassword
				+ ", userFirstname=" + userFirstname + ", userLastname=" + userLastname + ", userEmail=" + userEmail
				+ ", userRoleId=" + userRoleId + "]";
	}

}

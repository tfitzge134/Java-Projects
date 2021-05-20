package com.revature.models;

public class ErsUserRole {
	private int ersUserroleId;

	private String ersUserrole;
	
	public ErsUserRole() {
	}

	public ErsUserRole(int ersUserroleId, String ersUserrole) {
		super();
		this.ersUserroleId = ersUserroleId;
		this.ersUserrole = ersUserrole;
	}

	public int getErsUserroleId() {
		return ersUserroleId;
	}

	public void setErsUserroleId(int ersUserroleId) {
		this.ersUserroleId = ersUserroleId;
	}

	public String getErsUserrole() {
		return ersUserrole;
	}

	public void setErsUserrole(String ersUserrole) {
		this.ersUserrole = ersUserrole;
	}

	@Override
	public String toString() {
		return "ErsUserRoles [ersUserroleId=" + ersUserroleId + ", ersUserrole=" + ersUserrole + "]";
	}

}

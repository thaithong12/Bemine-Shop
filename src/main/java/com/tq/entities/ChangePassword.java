package com.tq.entities;

public class ChangePassword {
	private int id;
	
	private String oldPass;
	
	private String newPass;
	
	private String reNewPass;
	
	public ChangePassword() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getOldPass() {
		return oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public String getReNewPass() {
		return reNewPass;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public void setReNewPass(String reNewPass) {
		this.reNewPass = reNewPass;
	}

	public ChangePassword(int id, String oldPass, String newPass, String reNewPass) {
		super();
		this.id = id;
		this.oldPass = oldPass;
		this.newPass = newPass;
		this.reNewPass = reNewPass;
	}
	
}

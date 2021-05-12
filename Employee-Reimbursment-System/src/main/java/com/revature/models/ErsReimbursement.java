package com.revature.models;

import java.sql.Date;
import java.util.Arrays;

public class ErsReimbursement {
	private int reimbId;
	private double reimbAmount;
	private Date reimbSubmitted;
	private String reimbDescription;
	private byte[] reimReceipt; // ;//binary stream in java bytea progress
	private int reimbAuthor;
	private Date reimbResolved;
	private int reimbStatusId;
	private int reimbTypeId;
	private int reimId;

	public ErsReimbursement(int reimbId, double reimbAmount, Date reimbSubmitted, String reimbDescription,
			byte[] reimReceipt, int reimbAuthor, Date reimbResolved, int reimbStatusId, int reimbTypeId, int reimId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.reimReceipt = reimReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolved = reimbResolved;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimId = reimId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Date getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Date reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public byte[] getReimReceipt() {
		return reimReceipt;
	}

	public void setReimReceipt(byte[] reimReceipt) {
		this.reimReceipt = reimReceipt;
	}

	public int getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public Date getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Date reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	@Override
	public String toString() {
		return "ErsReimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbDescription=" + reimbDescription + ", reimReceipt="
				+ Arrays.toString(reimReceipt) + ", reimbAuthor=" + reimbAuthor + ", reimbResolved=" + reimbResolved
				+ ", reimbStatusId=" + reimbStatusId + ", reimbTypeId=" + reimbTypeId + ", reimId=" + reimId + "]";
	}

}

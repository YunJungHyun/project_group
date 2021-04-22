package com.spring.vo;

import java.sql.Date;

public class UserVO {
	
	private int unum;
	private String userid;
	private String userpw;
	private String username;
	private String nickname;
	private String strBirth;
	private Date birth;
	private String gender;
	private String phn;
	private String email;
	
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getStrBirth() {
		return strBirth;
	}
	public void setStrBirth(String strBirth) {
		this.strBirth = strBirth;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhn() {
		return phn;
	}
	public void setPhn(String phn) {
		this.phn = phn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserVO [unum=" + unum + ", userid=" + userid + ", userpw=" + userpw + ", username=" + username
				+ ", nickname=" + nickname + ", strBirth=" + strBirth + ", gender=" + gender + ", phn=" + phn
				+ ", email=" + email + "]";
	}
	
	
}

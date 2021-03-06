package com.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.UserDAO;
import com.spring.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	UserDAO userDAO;
	
	
	
	@Override
	public int idCheck(String userid) {
		
		return userDAO.idCheck(userid); 
	}
	@Override
	public int signUp(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.signUp(userVO);
	}
	
	@Override
	public int loginPwChk(UserVO userVO) {
		
		return userDAO.loginPwChk(userVO);
	}
	
	@Override
	public String loginSortChk(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.loginSortChk(userVO);
	}
	
	@Override
	public UserVO getUserInfo(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.getUserInfo(userVO);
	}
	
	@Override
	public int userCodeChk(String usercode) {
		// TODO Auto-generated method stub
		return userDAO.userCodeChk(usercode);
	} 
	@Override
	public int oauthSignUp(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.oauthSignUp(userVO);
	}
	
	@Override
	public int profileUpdate(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.profileUpdate(userVO);
	}
	
	@Override
	public UserVO getUserInfoCode(String usercode) {
		// TODO Auto-generated method stub
		return userDAO.getUserInfoCode(usercode);
	}
	
	@Override
	public int beforePwChk(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.beforePwChk(userVO);
	}
	
	@Override
	public int pwUpdate(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.pwUpdate(userVO);
	}
	
	@Override
	public int updateProfileImg(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDAO.updateProfileImg(userVO);
	}
	
	@Override
	public int secessionPwChk(String inputpw, String userid) {
		// TODO Auto-generated method stub
		return userDAO.secessionPwChk(inputpw , userid);
	}
	
	@Override
	public void userDelete(UserVO gui) { 
	
		userDAO.userDelete(gui);
	}
}	

package com.spring.dao;

import com.spring.vo.UserVO;

public interface UserDAO {

	int idCheck(String userid);

	int signUp(UserVO userVO);

	int loginPwChk(UserVO userVO);

	String loginSortChk(UserVO userVO);

	UserVO getUserInfo(UserVO userVO);

	int userCodeChk(String usercode);

	int oauthSignUp(UserVO userVO);

	int profileUpdate(UserVO userVO);

	UserVO getUserInfoCode(String usercode);

	int beforePwChk(UserVO userVO);

	int pwUpdate(UserVO userVO);

	int updateProfileImg(UserVO userVO);

	int secessionPwChk(String inputpw, String userid);

	void userDelete(UserVO gui);


}

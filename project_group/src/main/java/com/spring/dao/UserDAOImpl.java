package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.spring.vo.UserVO;

@Service
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "com.spring.mapper.userMapper";
	
	
	@Override
	public int idCheck(String userid) {
		
		//System.out.println("userDAOImpl :"+userid);
		int result = sqlSession.selectOne(Namespace+".idCheck", userid);
		
		return result;
	}

	@Override
	public int signUp(UserVO userVO) {
		int result = sqlSession.insert(Namespace+".signUp", userVO);
		return result;
	}
	
	@Override
	public int loginPwChk(UserVO userVO) {
		int result = sqlSession.selectOne(Namespace+".loginPwChk",userVO);
		return result;
	}
	
	@Override
	public String loginSortChk(UserVO userVO) {
		String result = sqlSession.selectOne(Namespace+".loginSortChk",userVO);
		return result;
	}
	
	@Override
	public UserVO getUserInfo(UserVO userVO) {
		UserVO result = sqlSession.selectOne(Namespace+".getUserInfo",userVO);
		return result;
	}
	
	@Override
	public int userCodeChk(String usercode) {
		int result = sqlSession.selectOne(Namespace+".userCodeChk",usercode);
		return result;
	}
	
	@Override
	public int oauthSignUp(UserVO userVO) {
		int result = sqlSession.insert(Namespace+".oauthSignUp", userVO);
		return result;
	}
	
	@Override
	public int profileUpdate(UserVO userVO) {
		
		int result =sqlSession.update(Namespace+".profileUpdate",userVO);
		return result;
	}
	
	@Override
	public UserVO getUserInfoCode(String usercode) {
		UserVO result =sqlSession.selectOne(Namespace+".getUserInfoCode",usercode);
		return result;
	}
	
	@Override
	public int beforePwChk(UserVO userVO) {
		int result =sqlSession.selectOne(Namespace+".beforePwChk",userVO);
		return result;
	}
	
	@Override
	public int pwUpdate(UserVO userVO) {
		int result =sqlSession.update(Namespace+".pwUpdate",userVO);
		return result;
	}
	
	@Override
	public int updateProfileImg(UserVO userVO) {
		int result =sqlSession.update(Namespace+".updateProfileImg",userVO);
		return result;
	}
	
	@Override
	public int secessionPwChk(String inputpw, String userid) {
		HashMap map = new HashMap();
		
		String sql = "SELECT COUNT(*) FROM user"
					+ " WHERE userid = '"+userid+"'"
					+ " AND userpw = '"+inputpw+"'";
		
		map.put("sql", sql);
		
		int result = sqlSession.selectOne(Namespace+".secessionPwChk", map);
	
		return result;
	}
	
	@Override
	public void userDelete(UserVO gui) {
		
		HashMap map1 = new HashMap();
		
		String sql1 = "DROP TABLE `diary_"+gui.getUsercode()+"`";
		
		map1.put("sql", sql1);
		
		HashMap map2 = new HashMap();
		
		String sql2 = "DROP TABLE `planner_"+gui.getUsercode()+"`";
		
		map2.put("sql", sql2);
		
		HashMap map3 = new HashMap();
		
		String sql3 = "DELETE FROM user"
					+" WHERE usercode = '"+gui.getUsercode()+"'";
		
		
		
		map3.put("sql", sql3);
		
		
		sqlSession.insert(Namespace+".userDeleteDiary", map1);
		sqlSession.insert(Namespace+".userDeletePlanner", map2);
		sqlSession.insert(Namespace+".userDelete", map3);
		
	}
}

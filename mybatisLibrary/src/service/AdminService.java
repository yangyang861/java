package service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.IAdminDAO;
import util.MybatisUtils;

public class AdminService {
	private dao.IAdminDAO adminDAO = null;
	private SqlSession sqlSession = null;

	public AdminService() {
		this.sqlSession = MybatisUtils.getSqlSession();
		this.adminDAO = (IAdminDAO) sqlSession.getMapper(IAdminDAO.class);
	}

	// 登录检查，Map形式返回检查结果
	public Map<String, Object> checkLogin2(vo.Admin admin) throws Exception {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		
		try {
			vo.Admin foundUser = this.adminDAO.get(admin.getAdminID());
			if (foundUser == null) {
				mapResult.put("code", 1);
				mapResult.put("msg", "用户名不存在！");
			} else {
				
				if (!foundUser.getAdminPassword().equals(admin.getAdminPassword())) {
					mapResult.put("code", 1);
					mapResult.put("msg", "密码不正确！");
				} else {
					mapResult.put("code", 0);
					mapResult.put("msg", "登录成功！");
					mapResult.put("user", foundUser.getAdminName());
				}
			}
		} catch (Exception e) {
			mapResult.put("code", 1);
			mapResult.put("msg", e.getMessage());
		}
		finally{sqlSession.close();}

		return mapResult;
	}
	

}

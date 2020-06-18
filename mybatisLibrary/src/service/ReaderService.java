package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import util.MybatisUtils;
import vo.Reader;

public class ReaderService {
	private dao.IReaderDAO readerDAO = null;
	private SqlSession sqlSession = null;

	public ReaderService() {
		this.sqlSession = MybatisUtils.getSqlSession();
		this.readerDAO = sqlSession.getMapper(dao.IReaderDAO.class);
	}

	// 读者登录检查
	public Map<String, Object> checkLogin(vo.Reader reader) throws Exception {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		try {
			vo.Reader foundUser = this.readerDAO.get(reader.getReaderID());
			if (foundUser == null) {
				mapResult.put("code", 1);
				mapResult.put("msg", "用户名不存在！");
			} else {
				if (!foundUser.getReaderPassword().equals(reader.getReaderPassword())) {
					mapResult.put("code", 1);
					mapResult.put("msg", "密码不正确！");
				} else {
					mapResult.put("code", 0);
					mapResult.put("msg", "登录成功！");
					mapResult.put("user", foundUser.getReaderName());
				}
			}
		} catch (Exception e) {
			mapResult.put("code", 1);
			mapResult.put("msg", e.getMessage());
		} 
		finally{sqlSession.close();}
		return mapResult;
	}
	//修改除ID外的读者信息
	public void change(){
	Scanner scan=new Scanner(System.in);
		try {
			System.out.println("输入待修改的读者ID：");
			vo.Reader foundUser = this.readerDAO.get(scan.nextInt());
			if(foundUser!=null) {
			System.out.println("输入修改后的信息\n读者名称\n读者密码:");
			vo.Reader reader=new vo.Reader(foundUser.getReaderID(),scan.next(),scan.next());
		int n=this.readerDAO.update(reader);
		sqlSession.commit();

			
			if (n==0) {
					System.out.println("修改失败！");
			} 	
			else {
				
					System.out.println("修改成功！");
				
			}
		}
			else {
				System.out.println("不存在该读者ID！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{sqlSession.close();}

	}
	//修改读者密码
		public void changepassword(vo.Reader reader){
		Scanner scan=new Scanner(System.in);
			try {
				System.out.println("输入修改后的信息\n读者密码:");
				vo.Reader reader2=new vo.Reader(reader.getReaderID(),null,scan.next());
			int n=this.readerDAO.update(reader2);
			sqlSession.commit();

				
				if (n==0) {
						System.out.println("修改失败！");
				} 	
				else {
					
						System.out.println("修改成功！");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			finally{sqlSession.close();}

		}
	//增加读者
	public void addreader(){
		Scanner scan=new Scanner(System.in);
		System.out.println("输入增加的读者信息:读者名称:\n读者密码:\n");
		vo.Reader reader=new vo.Reader(0,scan.next(),scan.next());
		try {
			
		int n=this.readerDAO.add(reader);
		sqlSession.commit();

			
			if (n==0) {
				System.out.println("增加失败！");
			} 
			else {
				System.out.println("增加成功！");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}
	//删除读者
	public void deletereader(){
		Scanner scan=new Scanner(System.in);
		System.out.println("输入待删除的读者ID：");
		int readerID=scan.nextInt();
		try {
			
		int n=this.readerDAO.delete(readerID);
		sqlSession.commit();

			
			if (n==0) {
				System.out.println( "删除失败！");
			} 
			else {
				System.out.println("删除成功！");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}
	
}

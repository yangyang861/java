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

	// ���ߵ�¼���
	public Map<String, Object> checkLogin(vo.Reader reader) throws Exception {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		try {
			vo.Reader foundUser = this.readerDAO.get(reader.getReaderID());
			if (foundUser == null) {
				mapResult.put("code", 1);
				mapResult.put("msg", "�û��������ڣ�");
			} else {
				if (!foundUser.getReaderPassword().equals(reader.getReaderPassword())) {
					mapResult.put("code", 1);
					mapResult.put("msg", "���벻��ȷ��");
				} else {
					mapResult.put("code", 0);
					mapResult.put("msg", "��¼�ɹ���");
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
	//�޸ĳ�ID��Ķ�����Ϣ
	public void change(){
	Scanner scan=new Scanner(System.in);
		try {
			System.out.println("������޸ĵĶ���ID��");
			vo.Reader foundUser = this.readerDAO.get(scan.nextInt());
			if(foundUser!=null) {
			System.out.println("�����޸ĺ����Ϣ\n��������\n��������:");
			vo.Reader reader=new vo.Reader(foundUser.getReaderID(),scan.next(),scan.next());
		int n=this.readerDAO.update(reader);
		sqlSession.commit();

			
			if (n==0) {
					System.out.println("�޸�ʧ�ܣ�");
			} 	
			else {
				
					System.out.println("�޸ĳɹ���");
				
			}
		}
			else {
				System.out.println("�����ڸö���ID��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{sqlSession.close();}

	}
	//�޸Ķ�������
		public void changepassword(vo.Reader reader){
		Scanner scan=new Scanner(System.in);
			try {
				System.out.println("�����޸ĺ����Ϣ\n��������:");
				vo.Reader reader2=new vo.Reader(reader.getReaderID(),null,scan.next());
			int n=this.readerDAO.update(reader2);
			sqlSession.commit();

				
				if (n==0) {
						System.out.println("�޸�ʧ�ܣ�");
				} 	
				else {
					
						System.out.println("�޸ĳɹ���");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			finally{sqlSession.close();}

		}
	//���Ӷ���
	public void addreader(){
		Scanner scan=new Scanner(System.in);
		System.out.println("�������ӵĶ�����Ϣ:��������:\n��������:\n");
		vo.Reader reader=new vo.Reader(0,scan.next(),scan.next());
		try {
			
		int n=this.readerDAO.add(reader);
		sqlSession.commit();

			
			if (n==0) {
				System.out.println("����ʧ�ܣ�");
			} 
			else {
				System.out.println("���ӳɹ���");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}
	//ɾ������
	public void deletereader(){
		Scanner scan=new Scanner(System.in);
		System.out.println("�����ɾ���Ķ���ID��");
		int readerID=scan.nextInt();
		try {
			
		int n=this.readerDAO.delete(readerID);
		sqlSession.commit();

			
			if (n==0) {
				System.out.println( "ɾ��ʧ�ܣ�");
			} 
			else {
				System.out.println("ɾ���ɹ���");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}
	
}

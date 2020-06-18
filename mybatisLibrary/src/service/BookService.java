package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import util.MybatisUtils;
import vo.Book;

public class BookService {
private static dao.IBookDAO bookDAO = null;
private static SqlSession sqlSession = null;

public BookService() {
	this.sqlSession = MybatisUtils.getSqlSession();
	this.bookDAO = sqlSession.getMapper(dao.IBookDAO.class);
}

	//����������ѯͼ�飬List����
	public static List<Book> querybookbyname()throws Exception{
		
		System.out.println("�����������в�ѯ��");
		Scanner scan=new Scanner(System.in);
		String bookName=scan.next();
		List<Book> foundBook = null;
		try {
			foundBook = bookDAO.query(bookName);
	
					if (foundBook.size()==0) {
						System.out.println("ͼ�鲻���ڣ�");
					} else {
						for(Book ss:foundBook) {
						System.out.println(ss.toString());}
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{sqlSession.close();}
		return foundBook;
			
	}
	//�������Ų�ѯͼ�飬Book����
	public static Book querybookbyid(){
		System.out.println("����ͼ���ͼ����");
		Scanner scan=new Scanner(System.in);
		String bookID=scan.next();
		Book foundBook = null;
	
		try {
				foundBook = bookDAO.query2(bookID);
				if (foundBook == null) {
					System.out.println("ͼ��������Ų����ڣ�");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally{sqlSession.close();}
		return foundBook;
	}
	//�޸�ͼ������
	public void change() throws Exception {
		Scanner scan=new Scanner(System.in);
		vo.Book foundBook=querybookbyid();
		if(foundBook!=null) {
			System.out.println("�����޸ĺ��ͼ������:");
			vo.Book book=new vo.Book(foundBook.getBookID(),scan.next(),null);
		int n=this.bookDAO.update(book);
		sqlSession.commit();

			
			if (n==0) {
					System.out.println("�޸�ʧ�ܣ�");
			} 
			else {
				System.out.println("�޸ĳɹ���");
				
			}
		}
		sqlSession.close();

	}
	//����ͼ��
	public void addbook() throws Exception {
		Scanner scan=new Scanner(System.in);
		System.out.println("��������ӵ�ͼ����Ϣ\nͼ���������:\nͼ������:\n");
		try {
				Date date =new Date();					
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				vo.Book book=new vo.Book(scan.next(),scan.next(),time);
				int n=this.bookDAO.add(book);
				sqlSession.commit();
	
				if (n==0) {
					System.out.println( "����ʧ�ܣ�");
				} 
				else {
					
				System.out.println("���ӳɹ���");
					
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{sqlSession.close();}

	}
	//ɾ��ͼ�飬Map��ʽ���ؼ����
	public void deletebook() throws Exception {
		Scanner scan=new Scanner(System.in);
		System.out.println("�����ɾ����ͼ���������\nͼ���������:");

		try {
			
		int n=this.bookDAO.delete(scan.next());
		sqlSession.commit();

			
			if (n==0) {
					System.out.println("ɾ��ʧ�ܣ�");
			} 
			else {
				
					System.out.println("ɾ���ɹ���");
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}

}

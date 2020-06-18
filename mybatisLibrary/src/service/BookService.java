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

	//根据书名查询图书，List返回
	public static List<Book> querybookbyname()throws Exception{
		
		System.out.println("输入书名进行查询：");
		Scanner scan=new Scanner(System.in);
		String bookName=scan.next();
		List<Book> foundBook = null;
		try {
			foundBook = bookDAO.query(bookName);
	
					if (foundBook.size()==0) {
						System.out.println("图书不存在！");
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
	//根据书编号查询图书，Book返回
	public static Book querybookbyid(){
		System.out.println("输入图书的图书编号");
		Scanner scan=new Scanner(System.in);
		String bookID=scan.next();
		Book foundBook = null;
	
		try {
				foundBook = bookDAO.query2(bookID);
				if (foundBook == null) {
					System.out.println("图书条形码号不存在！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally{sqlSession.close();}
		return foundBook;
	}
	//修改图书名称
	public void change() throws Exception {
		Scanner scan=new Scanner(System.in);
		vo.Book foundBook=querybookbyid();
		if(foundBook!=null) {
			System.out.println("输入修改后的图书名称:");
			vo.Book book=new vo.Book(foundBook.getBookID(),scan.next(),null);
		int n=this.bookDAO.update(book);
		sqlSession.commit();

			
			if (n==0) {
					System.out.println("修改失败！");
			} 
			else {
				System.out.println("修改成功！");
				
			}
		}
		sqlSession.close();

	}
	//增加图书
	public void addbook() throws Exception {
		Scanner scan=new Scanner(System.in);
		System.out.println("输入待增加的图书信息\n图书条形码号:\n图书名称:\n");
		try {
				Date date =new Date();					
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				vo.Book book=new vo.Book(scan.next(),scan.next(),time);
				int n=this.bookDAO.add(book);
				sqlSession.commit();
	
				if (n==0) {
					System.out.println( "增加失败！");
				} 
				else {
					
				System.out.println("增加成功！");
					
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{sqlSession.close();}

	}
	//删除图书，Map形式返回检查结果
	public void deletebook() throws Exception {
		Scanner scan=new Scanner(System.in);
		System.out.println("输入待删除的图书条形码号\n图书条形码号:");

		try {
			
		int n=this.bookDAO.delete(scan.next());
		sqlSession.commit();

			
			if (n==0) {
					System.out.println("删除失败！");
			} 
			else {
				
					System.out.println("删除成功！");
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}

}

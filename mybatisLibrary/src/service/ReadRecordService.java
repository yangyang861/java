package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;



//import util.MD5Util;
import util.MybatisUtils;
import vo.Book;
import vo.ReadRecord;
import vo.Reader;

public class ReadRecordService {

	private dao.IReadrecordDAO readRecordDAO = null;
	private dao.IBookDAO bookDAO=null;
	private SqlSession sqlSession = null;

	public ReadRecordService() {
		this.sqlSession = MybatisUtils.getSqlSession();
		this.readRecordDAO = sqlSession.getMapper(dao.IReadrecordDAO.class);
		this.bookDAO = sqlSession.getMapper(dao.IBookDAO.class);

	}

	// 借阅图书
	public void borrowbook(vo.Reader reader) throws Exception{
		//查找要借阅的书籍
		List<vo.Book> found =BookService.querybookbyname();
		try {
			
			if(found.size()!=0) {
			
				Book found2 =service.BookService.querybookbyid();
				//输入所借书目的条形码号正确
				if(found2!=null) {
					vo.ReadRecord panduanrecord=new vo.ReadRecord(0,found2.getBookID(),null,0,null,null,"0");
				List<vo.ReadRecord> panduan=this.readRecordDAO.query(panduanrecord);
				//图书未被借走
					if(panduan.size()==0) {		
						Date date =new Date();					
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
						String time=format.format(date);
						//增加借书记录
						ReadRecord record=new ReadRecord(0,found2.getBookID(),found2.getBookName(),reader.getReaderID(),reader.getReaderName(),time,"0");
						int n = this.readRecordDAO.add(record);
					
						sqlSession.commit();
						if (n == 0) {
							System.out.println("借阅失败！");
						} else {				
								System.out.println("借阅成功！");
						}
					}else {
						System.out.println("图书已被借阅！");
						
					}
				}
			}
			
		else {
				System.out.println("请退出后重新输入图书名");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} 
		finally{sqlSession.close();}

	}
	//根据已知记录信息查询借阅记录
	public void queryrecord(){
		Scanner scan=new Scanner(System.in);
		System.out.println("选择待查询的记录信息：\n1.流水号\n2.图书条形码号\n3.图书名称\n4.读者ID\n5.读者名称");
		int n=scan.nextInt();
		vo.ReadRecord record=null;
		if(n==1)record=new ReadRecord(scan.nextInt(),null,null,0,null,null,null);
		else if(n==2)record=new ReadRecord(0,scan.next(),null,0,null,null,null);
		else if(n==3)record=new ReadRecord(0,null,scan.next(),0,null,null,null);
		else if(n==4)record=new ReadRecord(0,null,null,scan.nextInt(),null,null,null);
		else if(n==5)record=new ReadRecord(0,null,null,0,scan.next(),null,null);
		else System.out.println("选择无效请重新输入");
		try {
			List<vo.ReadRecord> found = this.readRecordDAO.query(record);
			if (found.size()==0) {
			System.out.println("无借阅记录！");
			} else {	
				for(ReadRecord ss:found) {
					System.out.println(ss);}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}
	//根据读者id查询借阅记录
		public void queryrecordbyreaderid(int readerid){
			vo.ReadRecord oneRecord=new ReadRecord(0,null,null,readerid,null,null,null);
			try {
				List<vo.ReadRecord> found = this.readRecordDAO.query(oneRecord);
				if (found.size()==0) {
					System.out.println("查询失败！");
				} else {		
					for(ReadRecord ss:found) {
						System.out.println(ss);}
					
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			finally{sqlSession.close();}

		}

	 //归还图书
	public void backbook(int readerID){
		try {
			vo.ReadRecord book=new ReadRecord(0,null,null,readerID,null,null,"0");
			List<vo.ReadRecord> found=this.readRecordDAO.query(book);
			//还有未归还的图书
			if(found.size()!=0) {
			System.out.println(found.toString());
				System.out.println("\n输入待归还记录的流水号");
				//输入记录流水号，更改归还时间
					Scanner scan=new Scanner(System.in);
					int serial=scan.nextInt();	
					Date date =new Date();					
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					String time=format.format(date);
	
					ReadRecord record=new ReadRecord(serial,null,null,0,null,null,time);
					int n = this.readRecordDAO.back(record);
					sqlSession.commit();
						if (n == 0) {
					System.out.println("归还失败！");
						} else {				
					System.out.println("归还成功！");
						}
					
				}else {
				System.out.println("没有待归还的图书！");
				}
			} catch (Exception e) {	
				e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}

	//进行借阅统计
	public void count(){
		Scanner scan=new Scanner(System.in);
		System.out.println("选择待查询的记录信息\n1.图书条形码号\n2.图书名称\n3.读者ID\n4.读者名称");
		//选择需要统计的项目
		int n=scan.nextInt();
		vo.ReadRecord record=null;
		if(n==1)record=new ReadRecord(0,scan.next(),null,0,null,null,null);
		else if(n==2)record=new ReadRecord(0,null,scan.next(),0,null,null,null);
		else if(n==3)record=new ReadRecord(0,null,null,scan.nextInt(),null,null,null);
		else if(n==4)record=new ReadRecord(0,null,null,0,scan.next(),null,null);
		else System.out.println("选择无效!");

		
		try {
		int count=this.readRecordDAO.count(record);
			
			if (count==0) {
					System.out.println( "无记录！");
			} 
			else {
				
					System.out.println("统计数目："+count); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{sqlSession.close();}

	}
	

}

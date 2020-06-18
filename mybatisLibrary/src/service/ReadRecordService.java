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

	// ����ͼ��
	public void borrowbook(vo.Reader reader) throws Exception{
		//����Ҫ���ĵ��鼮
		List<vo.Book> found =BookService.querybookbyname();
		try {
			
			if(found.size()!=0) {
			
				Book found2 =service.BookService.querybookbyid();
				//����������Ŀ�����������ȷ
				if(found2!=null) {
					vo.ReadRecord panduanrecord=new vo.ReadRecord(0,found2.getBookID(),null,0,null,null,"0");
				List<vo.ReadRecord> panduan=this.readRecordDAO.query(panduanrecord);
				//ͼ��δ������
					if(panduan.size()==0) {		
						Date date =new Date();					
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
						String time=format.format(date);
						//���ӽ����¼
						ReadRecord record=new ReadRecord(0,found2.getBookID(),found2.getBookName(),reader.getReaderID(),reader.getReaderName(),time,"0");
						int n = this.readRecordDAO.add(record);
					
						sqlSession.commit();
						if (n == 0) {
							System.out.println("����ʧ�ܣ�");
						} else {				
								System.out.println("���ĳɹ���");
						}
					}else {
						System.out.println("ͼ���ѱ����ģ�");
						
					}
				}
			}
			
		else {
				System.out.println("���˳�����������ͼ����");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} 
		finally{sqlSession.close();}

	}
	//������֪��¼��Ϣ��ѯ���ļ�¼
	public void queryrecord(){
		Scanner scan=new Scanner(System.in);
		System.out.println("ѡ�����ѯ�ļ�¼��Ϣ��\n1.��ˮ��\n2.ͼ���������\n3.ͼ������\n4.����ID\n5.��������");
		int n=scan.nextInt();
		vo.ReadRecord record=null;
		if(n==1)record=new ReadRecord(scan.nextInt(),null,null,0,null,null,null);
		else if(n==2)record=new ReadRecord(0,scan.next(),null,0,null,null,null);
		else if(n==3)record=new ReadRecord(0,null,scan.next(),0,null,null,null);
		else if(n==4)record=new ReadRecord(0,null,null,scan.nextInt(),null,null,null);
		else if(n==5)record=new ReadRecord(0,null,null,0,scan.next(),null,null);
		else System.out.println("ѡ����Ч����������");
		try {
			List<vo.ReadRecord> found = this.readRecordDAO.query(record);
			if (found.size()==0) {
			System.out.println("�޽��ļ�¼��");
			} else {	
				for(ReadRecord ss:found) {
					System.out.println(ss);}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}
	//���ݶ���id��ѯ���ļ�¼
		public void queryrecordbyreaderid(int readerid){
			vo.ReadRecord oneRecord=new ReadRecord(0,null,null,readerid,null,null,null);
			try {
				List<vo.ReadRecord> found = this.readRecordDAO.query(oneRecord);
				if (found.size()==0) {
					System.out.println("��ѯʧ�ܣ�");
				} else {		
					for(ReadRecord ss:found) {
						System.out.println(ss);}
					
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			finally{sqlSession.close();}

		}

	 //�黹ͼ��
	public void backbook(int readerID){
		try {
			vo.ReadRecord book=new ReadRecord(0,null,null,readerID,null,null,"0");
			List<vo.ReadRecord> found=this.readRecordDAO.query(book);
			//����δ�黹��ͼ��
			if(found.size()!=0) {
			System.out.println(found.toString());
				System.out.println("\n������黹��¼����ˮ��");
				//�����¼��ˮ�ţ����Ĺ黹ʱ��
					Scanner scan=new Scanner(System.in);
					int serial=scan.nextInt();	
					Date date =new Date();					
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					String time=format.format(date);
	
					ReadRecord record=new ReadRecord(serial,null,null,0,null,null,time);
					int n = this.readRecordDAO.back(record);
					sqlSession.commit();
						if (n == 0) {
					System.out.println("�黹ʧ�ܣ�");
						} else {				
					System.out.println("�黹�ɹ���");
						}
					
				}else {
				System.out.println("û�д��黹��ͼ�飡");
				}
			} catch (Exception e) {	
				e.printStackTrace();
		} 
		finally{sqlSession.close();}

	}

	//���н���ͳ��
	public void count(){
		Scanner scan=new Scanner(System.in);
		System.out.println("ѡ�����ѯ�ļ�¼��Ϣ\n1.ͼ���������\n2.ͼ������\n3.����ID\n4.��������");
		//ѡ����Ҫͳ�Ƶ���Ŀ
		int n=scan.nextInt();
		vo.ReadRecord record=null;
		if(n==1)record=new ReadRecord(0,scan.next(),null,0,null,null,null);
		else if(n==2)record=new ReadRecord(0,null,scan.next(),0,null,null,null);
		else if(n==3)record=new ReadRecord(0,null,null,scan.nextInt(),null,null,null);
		else if(n==4)record=new ReadRecord(0,null,null,0,scan.next(),null,null);
		else System.out.println("ѡ����Ч!");

		
		try {
		int count=this.readRecordDAO.count(record);
			
			if (count==0) {
					System.out.println( "�޼�¼��");
			} 
			else {
				
					System.out.println("ͳ����Ŀ��"+count); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{sqlSession.close();}

	}
	

}

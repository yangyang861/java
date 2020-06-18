
package driver;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import java.util.Scanner;

import service.AdminService;
import service.BookService;
import service.ReadRecordService;
import service.ReaderService;
//import sun.print.resources.serviceui;
import vo.ReadRecord;
import vo.Reader;

public class libraryDriver {
	public static void main(String[] args) throws Exception{
		ReaderService readerService = new ReaderService();
		AdminService adminService = new AdminService();
		BookService bookService = new BookService();
		ReadRecordService readRecordService=new ReadRecordService();
		int choice=menu();
		while(choice!=3) {
			switch(choice) {
			case 1:login(1);break;
			case 2:login(2);break;
			default:System.out.println("ѡ����Ч����������");
	
			}
			
			choice=menu();

		}

	}

	public static int menu() {
		int choice;
		Scanner scan=new Scanner(System.in);
					Date date =new Date();					
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					String time=format.format(date);
					System.out.println(time);
					
		System.out.println("============��ӭʹ��ͼ���ϵͳ===========");
		System.out.println("===ѡ���¼���===");
		System.out.println("1.����");
		System.out.println("2.����Ա");
		System.out.println("3.�˳�");
		System.out.println("��ѡ��1-3��");
		choice=scan.nextInt();
		return choice;

	}
	public static int menu1() {
		int choice1;
		System.out.println("============���߲˵�===========");
		System.out.println("1.��ѯͼ��");
		System.out.println("2.����ͼ��");
		System.out.println("3.�黹ͼ��");
		System.out.println("4.��ѯ���ļ�¼");
		System.out.println("5.�޸�����");
		System.out.println("6.�˳���½");
		System.out.println("��ѡ��1-6��");
		Scanner scan=new Scanner(System.in);
		choice1=scan.nextInt();
		return choice1;
	
	}
	public static int menu2() {
		int choice2;
		System.out.println("===========����Ա�˵�===========");
		System.out.println("1.ͼ������");
		System.out.println("2.ͼ���޸�");
		System.out.println("3.ͼ��ɾ��");
		System.out.println("4.��������");
		System.out.println("5.�����޸�");
		System.out.println("6.����ɾ��");
		System.out.println("7.���Ĳ�ѯ");
		System.out.println("8.����ͳ��");
		System.out.println("9.�˳���¼");
		System.out.println("��ѡ��1-9��");
		Scanner scan=new Scanner(System.in);
		choice2=scan.nextInt();
		return choice2;
	
	}
	public static void login(int s) throws Exception{
		Scanner scan=new Scanner(System.in);
		ReaderService readerService = new ReaderService();
		ReadRecordService readRecordService=new ReadRecordService();
		BookService bookService=new BookService();
		AdminService adminService = new AdminService();
		System.out.println("�����½��Ϣ��\nID:\n����:");
		//�����½��Ϣ
		int ID=scan.nextInt();
		String password=scan.next();
	
		//������ݵ�¼�ɹ�
		if(s==1) {
			vo.Reader reader=new Reader(ID,null,password);
				Map<String, Object> map =readerService.checkLogin(reader);

				if(map.get("code").equals(0)) {
					System.out.println("��ӭ���ߣ�"+map.get("user")+"��¼��");
			
					int choice=menu1();
					while(choice!=6) {

						switch(choice) {
						case 1:bookService.querybookbyname();break;
						case 2:readRecordService.borrowbook(reader);break;
						case 3:readRecordService.backbook(ID);break;
						case 4:readRecordService.queryrecordbyreaderid(ID);break;
						case 5:readerService.changepassword(reader);break;
						default:System.out.println("ѡ����Ч����������");
				
						}
						
						choice=menu1();

					}		
					int choice1=menu();
					while(choice1!=3) {
						switch(choice1) {
						case 1:login(1);break;
						case 2:login(2);break;
						default:System.out.println("ѡ����Ч����������");
				
						}
						
						choice1=menu();
					}					
				}else {
					System.out.println(map.get("msg"));
					
					
					
				}


			
		}else {	//����Ա��ݵ�¼
			vo.Admin admin=new vo.Admin(ID,null,password);
			Map<String, Object> map = adminService.checkLogin2(admin);
				if(map.get("code").equals(0)) {
					System.out.println("��ӭ����Ա��"+map.get("user")+"��¼��");

					int choice=menu2();
					while(choice!=9) {
						switch(choice) {
						case 1:bookService.addbook();break;
						case 2:bookService.change();break;
						case 3:bookService.deletebook();break;
						case 4:readerService.addreader();break;
						case 5:readerService.change();break;
						case 6:readerService.deletereader();break;
						case 7:readRecordService.queryrecord();break;
						case 8:readRecordService.count();break;
						default:System.out.println("ѡ����Ч����������");
				
						}
						
						choice=menu2();

					}		
					int choice1=menu();
					while(choice1!=3) {
						switch(choice1) {
						case 1:login(1);break;
						case 2:login(2);break;
						default:System.out.println("ѡ����Ч����������");
				
						}
						
						choice1=menu();

					}
										
				}else {
					System.out.println(map.get("msg"));
															
				}
						
			} 
			
	}
	

	
}

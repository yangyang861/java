package dao;
import java.util.Date;
import java.util.List;
import vo.Book;


public interface IBookDAO{
	public List<Book> query(String bookName);
	public Book query2(String bookID);
	public int add(Book book);
	public int update(Book book);
	public int delete(String bookID);
}


package dao;
import java.util.Date;
import java.util.List;
import vo.Reader;

public interface IReaderDAO{
	
	public Reader get(int readerID);
	
	public int add(Reader reader);
	public int update(Reader reader);
	public int delete(int readerID);
}
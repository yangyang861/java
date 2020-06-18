package dao;
import java.util.Date;
import java.util.List;
import vo.ReadRecord;;

public interface IReadrecordDAO{

	public List<ReadRecord> query(ReadRecord record);

	public int add(ReadRecord readRecord);


	//ΩË‘ƒÕ≥º∆
	public int count(ReadRecord readRecord);

	public int back(ReadRecord record);

}
package dao;
import java.util.Date;
import java.util.List;
import vo.ReadRecord;;

public interface IReadrecordDAO{

	public List<ReadRecord> query(ReadRecord record);

	public int add(ReadRecord readRecord);


	//����ͳ��
	public int count(ReadRecord readRecord);

	public int back(ReadRecord record);

}
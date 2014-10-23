import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.LinkedList;
import com.mongodb.DBCursor;

public class NGSContainerMgr {
	private PersistenceMgr _persistenceMgr;
	private List<SeqRead> _seqReads = new LinkedList<SeqRead>();
	
	public NGSContainerMgr(String dbServer, String dbName, String dbCollectionName) throws UnknownHostException
	{
		_persistenceMgr = new MongoDBPersistenceMgr(dbServer, dbName, dbCollectionName);
	}
	
	public NGSContainerMgr(String fastaFileName) throws IOException
	{ 
		_persistenceMgr = new FastaPersistenceMgr(fastaFileName);	 
	}
	
	public DBCursor findAll()
	{
		return ((MongoDBPersistenceMgr)_persistenceMgr).findAll();
	}
	
	public List<SeqRead> getAll()
	{
		_seqReads = _persistenceMgr.find();
		return _seqReads;
	}
	
	public List<SeqRead> find(String key, String value)
	{
		_seqReads = _persistenceMgr.find(key, value);
		return _seqReads;
	}
	
	public PersistenceMgr getPersistenceMgr()
	{
		return _persistenceMgr;
	}
	
	
}

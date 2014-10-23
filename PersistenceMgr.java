import java.util.List;

public interface PersistenceMgr {
	
	public void write(TransformedSequence sequence, String readName, String refName);
	public void update(String readName, TransformedSequence sequence);
	public void delete(String readName);
	public List<SeqRead> find();
	public List<SeqRead> find(String key, String value);
	public List<SeqRead> find(TransformedSequence sequence);
	public void close();
}

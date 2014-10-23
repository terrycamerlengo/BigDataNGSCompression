
public class HT_SOSASeqRead extends SeqRead {
	
	private String _referenceName = "";
	
	public HT_SOSASeqRead(String id, TransformedSequence sequence)
	{
		super(id, sequence);
		this._referenceName = sequence.getReferenceName();
	}
	
	public String getReferenceName()
	{
		return _referenceName;
	}
}

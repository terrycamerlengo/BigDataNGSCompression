
public abstract class SeqRead 
{
	private String _id = "";
	private TransformedSequence _transformedSeq = null;
	
	public SeqRead(String id, TransformedSequence sequence) 
	{
		this._id = id;
		this._transformedSeq = sequence;
	}
	
	public String getId()
	{
		return _id;
	}
	
	public String getSequenceString()
	{
		return _transformedSeq.getNormalSequence();
	}
	
	public String getTransformedString()
	{
		return _transformedSeq.get();
	}
	
	public String getReference()
	{
		return _transformedSeq.getReferenceName();
	}
	 
}

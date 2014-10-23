
public abstract class TransformedSequence {
	
	private String _sequence = "";
	private String _transformedSequence = "";
	private String _reference = "";
	
	
	//@Factory method  /////////////////////////////////////////////
	public static TransformedSequence 
	createTransformedSequenceFactory(boolean unmapped, String cigar, 
									 String sequence, String referenceName)
	{
		TransformedSequence tSeq = null;
		
		
		if (unmapped)
		{
			//tSeq = new Encoded4bitSequence(sequence, referenceName);
			try {
				tSeq = new EncodedDictionarySequence(sequence, referenceName);
			}
			catch(Exception exc)
			{
				System.out.println("error processing Transformed Sequence " + exc.getMessage());
			}
		}
		else
		{
			tSeq = new ReferenceCompressedSequence(sequence, cigar, referenceName);
		}
		
		return tSeq;
	}
	
	public TransformedSequence(String sequence, String reference)
	{
		this._sequence = sequence;
		this._reference = reference;
	}
	
	public String getNormalSequence()
	{
		return _sequence;
	}
	
	public String get()
	{
		return _transformedSequence;
	}
	
	protected void setTransformedSequence(String tseq)
	{
		_transformedSequence = tseq;
	}
	
	public String getReferenceName()
	{
		return _reference;
	}
	
	abstract boolean isAligned();
 
}

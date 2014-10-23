
public class ReferenceCompressedSequence extends TransformedSequence {
	
	public ReferenceCompressedSequence(String normalSequence, String cigar, String referenceName)
	{
		super(normalSequence, referenceName);
		this.setTransformedSequence(cigar);
	}
	
	@Override
	boolean isAligned() {
		return true;
	}

	public static String getNormalSequenceFromCigar(String cigar, String tSeq) {
		String normalSequence = "";
		
		return normalSequence;
	}
	
}

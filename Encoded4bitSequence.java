
public class Encoded4bitSequence extends TransformedSequence {
	
	public Encoded4bitSequence(String normalSequence, String referenceName)
	{
		super(normalSequence, referenceName);
		this.setTransformedSequence(Base4bitEncoder.convert(normalSequence.getBytes()));
		
	}
	
	@Override
	boolean isAligned() {
		return false;
	}
}

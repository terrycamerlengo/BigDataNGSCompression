
public class EncodedDictionarySequence extends TransformedSequence {

	public EncodedDictionarySequence(String normalSequence, String referenceName) throws Exception
	{
		super(normalSequence, referenceName);
		this.setTransformedSequence(BasicDictionaryEncoder.convert(normalSequence.getBytes()));
	}
	
	@Override
	boolean isAligned() {
		return false;
	}
	
}

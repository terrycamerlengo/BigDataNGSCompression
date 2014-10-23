
public class ReferenceCompressionMgr {
	public final static String Eta_Control	= "ATCTCGGCATCAGCAATGTTGACCCAACTCAATGTCGATCCA";
	public final static String Iota_Control = "GCTCCGGCATCAGCAATGTTGACCCAACTCAATGTCGATCCA";
	public final static String Kappa_Control = "CACTCGGCATCAGCAATGTTGACCCAACTCAATGTCGATCCA";
	public final static String Iota_Damage	= "TGGACGGCATCAGCAATGTTGACCCAACTCAATGTCGATCCA";
	public final static String Eta_Damage	= "ATAGCGGCATCAGCAATGTTGACCCAACTCAATGTCGATCCA";
	public final static String Kappa_Damage	= "CAAGCGGCATCAGCAATGTTGACCCAACTCAATGTCGATCCA";
	public final static String Unknown = "";
	
	public static String GetReferenceName(String sequence)
	{
		String referenceName = "Unknown";
		
		String barcode = "";
		
		if (sequence.length() == 4)
		{
			barcode = sequence;
		}
		else if (sequence.length() > 4)
		{
			barcode = sequence.substring(0,4);
		}

		switch(barcode)
		{
			case "ATCT":
				referenceName = "Eta_Control";
				break;
			case "GCTC":
				referenceName = "Iota_Control";
				break;
			case "CACT":
				referenceName = "Kappa_Control";
				break;
			case "TGGA":
				referenceName = "Iota_Damage";
				break;
			case "ATAG":
				referenceName = "Eta_Damage";
				break;
			case "CAAG":
				referenceName = "Kappa_Damage";
				break;
			default:
				break;
		}
		
		return referenceName;
	}
	
	public static String GetReferenceSequence(String refName)
	{
		String reference = "";
		
		switch(refName)
		{
			case "Eta_Control":
				reference = Eta_Control;
				break;
			case "Iota_Control":
				reference = Iota_Control;
				break;
			case "Kappa_Control":
				reference = Kappa_Control;
				break;
			case "Iota_Damage":
				reference = Iota_Damage;
				break;
			case "Eta_Damage":
				reference = Eta_Damage;
				break;
			case "Kappa_Damage":
				reference = Kappa_Damage;
				break;
			default:
				reference = Unknown;
				break;
		}
		
		return reference;
	}
}

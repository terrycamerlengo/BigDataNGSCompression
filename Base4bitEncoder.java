import java.lang.StringBuffer;

public class Base4bitEncoder {
	/*  4-bits
	 *    0000  N  (in determinate, low-quality read)   
	 *    0001  A   
	 *    0010  T 
	 *    0011  C
	 *    0100  G
	 *    0101  -  (no read, empty)
	 *    0110
	 *    0111
	 *    1000
	 *    1001
	 *    1010
	 *    1011
	 *    1100
	 *    1101
	 *    1110
	 *    1111
	 * 
	 * 1-byte
	 *    0000 0000      NN    33 
	 *    0000 0001      NA
	 *    0000 0010      NT
	 *    0000 0011      NC
	 *    0000 0100      NG
	 *    0000 0101      N-
	 *    0001 0110      AN
	 *    0001 0111      AA
	 *    0001 1000      AT
	 *    0001 1001      AC
	 *    0001 1010      AG
	 *    0001 1011      A-
	 *    0010 1100      TN
	 *    0010 1101      TA
	 *    0010 1110      TT
	 *    0010 1111      TC
	 *    0010 0000      TG
	 *    0010 0001      T-
	 *    0011 0010      CN
	 *    0011 0011      CA
	 *    0011 0100      CT
	 *    0011 0101      CC
	 *    0011 0110      CG
	 *    0011 0111      C-
	 *    0100 1000      GN
	 *    0100 1001      GA
	 *    0100 1010      GT
	 *    0100 1011      GC
	 *    0100 1100      GG
	 *    0100 1101      G-
	 *    0101 1110      -N
	 *    0000 1111      -A
	 *    0000 0000      -T
	 *    0000 0001      -C
	 *    0000 0010      -G
	 *    0000 0011      --
	 * */ 
	
	
	public static String convert(byte[] bases)
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i<bases.length; i+=2 )
		{
			char b2;
			char b1 = (char)bases[i];
			
			if (i==(bases.length -1))
			{
				b2 = '-';
			}
			else
			{
				b2 = (char)bases[i + 1];
			}
			
			char twoBases = (char)Base4bitEncoder.convertBases(b1, b2);
			sb.append(twoBases);
		}
		
		return sb.toString();
	}
	
	public static byte convertBases(char base1, char base2)
	{
		byte b; //= (byte)0;
		char[] cpair = {base1 , base2 };
		String spair = new String(cpair);
		switch (spair)
		{
			case "NN":
				b =(byte)33;
				break;
			case "NA":
				b =(byte)34;
				break;
			case "NT":
				b =(byte)35;
				break;
			case "NC":
				b =(byte)36;
				break;
			case "NG":
				b =(byte)37;
				break;
			case "N-":
				b =(byte)38;
				break;
			case "AN":
				b =(byte)39;
				break;
			case "AA":
				b =(byte)40;
				break;
			case "AT":
				b =(byte)41;
				break;
			case "AC":
				b =(byte)42;
				break;
			case "AG":
				b =(byte)43;
				break;
			case "A-":
				b =(byte)44;
				break;
			case "TN":
				b =(byte)45;
				break;
			case "TA":
				b =(byte)46;
				break;
			case "TT":
				b =(byte)47;
				break;
			case "TC":
				b =(byte)48;
				break;
			case "TG":
				b =(byte)49;
				break;
			case "T-":
				b =(byte)50;
				break;
			case "CN":
				b =(byte)51;
				break;
			case "CA":
				b =(byte)52;
				break;
			case "CT":
				b =(byte)53;
				break;
			case "CC":
				b =(byte)54;
				break;
			case "CG":
				b =(byte)55;
				break;
			case "C-":
				b =(byte)56;
				break;
			case "GN":
				b =(byte)57;
				break;
			case "GA":
				b =(byte)58;
				break;
			case "GT":
				b =(byte)59;
				break;
			case "GC":
				b =(byte)60;
				break;
			case "GG":
				b =(byte)61;
				break;
			case "G-":
				b =(byte)62;
				break;
			case "-N":
				b =(byte)63;
				break;
			case "-A":
				b =(byte)64;
				break;
			case "-T":
				b =(byte)65;
				break;
			case "-C":
				b =(byte)66;
				break;
			case "-G":
				b =(byte)67;
				break;
			case "--":
			default:
				b =(byte)68;
				break;
		}
		
		return b;
	}
	
	public static String convert4bitToRegBaseEncoding(String a4bitEncodedSequence)
	{
		StringBuffer sb = new StringBuffer(); 
		byte[] sequenceAsBytes = a4bitEncodedSequence.getBytes();
		
		for (byte s : sequenceAsBytes)
		{
			switch(s)
			{
				case ((byte)33):
					sb.append("NN");
					break;
				case ((byte)34):
					sb.append("NA");
					break;
				case ((byte)35):
					sb.append("NT");
					break;
				case ((byte)36):
					sb.append("NC");
					break;
				case ((byte)37):
					sb.append("NG");
					break;
				case ((byte)38):
					sb.append("N-");
					break;
				case ((byte)39):
					sb.append("AN");
					break;
				case ((byte)40):
					sb.append("AA");
					break;
				case ((byte)41):
					sb.append("AT");
					break;
				case ((byte)42):
					sb.append("AC");
					break;
				case ((byte)43):
					sb.append("AG");
					break;
				case ((byte)44):
					sb.append("A-");
					break;
				case ((byte)45):
					sb.append("TN");
					break;
				case ((byte)46):
					sb.append("TA");
					break;
				case ((byte)47):
					sb.append("TT");
					break;
				case ((byte)48):
					sb.append("TC");
					break;
				case ((byte)49):
					sb.append("TG");
					break;
				case ((byte)50):
					sb.append("T-");
					break;
				case ((byte)51):
					sb.append("CN");
					break;
				case ((byte)52):
					sb.append("CA");
					break;
				case ((byte)53):
					sb.append("CT");
					break;
				case ((byte)54):
					sb.append("CC");
					break;
				case ((byte)55):
					sb.append("CG");
					break;
				case ((byte)56):
					sb.append("C-");
					break;
				case ((byte)57):
					sb.append("GN");
					break;
				case ((byte)58):
					sb.append("GA");
					break;
				case ((byte)59):
					sb.append("GT");
					break;
				case ((byte)60):
					sb.append("GC");
					break;
				case ((byte)61):
					sb.append("GG");
					break;
				case ((byte)62):
					sb.append("G-");
					break;
				case ((byte)63):
					sb.append("-N");
					break;
				case ((byte)64):
					sb.append("-A");
					break;
				case ((byte)65):
					sb.append("-T");
					break;
				case ((byte)66):
					sb.append("-C");
					break;
				case ((byte)67):
					sb.append("-G");
					break;
				case ((byte)68):
				default:
					sb.append("--");
					break;
			}
		}
		
		return sb.toString();
	}
	
	//<07<)4<(1/:67*0:10:05" , "normalSequence" : "GCTCCGGCATCAGCAATGTTGACCCGACTCGATGTCGATCCT"
	public static String testConvert4bitToRegBaseEncoding(String encodedSeq)
	{
		String s = Base4bitEncoder.convert4bitToRegBaseEncoding(encodedSeq);
		return s;
	}
}

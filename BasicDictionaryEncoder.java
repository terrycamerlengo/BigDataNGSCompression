import java.util.Map;


public class BasicDictionaryEncoder {
	
	private static java.util.HashMap<Byte, String> _hashEntries = null;
	
	public static void init()
	{
		if (_hashEntries == null)
		{
			_hashEntries = new java.util.HashMap<Byte, String>(156,1);
			
			//single words - 5
			_hashEntries.put((byte)0x0, "A--");
			_hashEntries.put((byte)0x1, "T--");
			_hashEntries.put((byte)0x2, "C--");
			_hashEntries.put((byte)0x3, "G--");
			_hashEntries.put((byte)0x4, "N--");
			
			//double words - 25
			_hashEntries.put((byte)0x5, "AA-");
			_hashEntries.put((byte)0x6, "AT-");
			_hashEntries.put((byte)0x7, "AC-");
			_hashEntries.put((byte)0x8, "AG-");
			_hashEntries.put((byte)0x9, "AN-");
			_hashEntries.put((byte)0xA, "TA-");
			_hashEntries.put((byte)0xB, "TT-");
			_hashEntries.put((byte)0xC, "TC-");
			_hashEntries.put((byte)0xD, "TG-");
			_hashEntries.put((byte)0xE, "TN-");
			_hashEntries.put((byte)0xF, "CA-");			
			_hashEntries.put((byte)0x10, "CT-");
			_hashEntries.put((byte)0x11, "CC-");
			_hashEntries.put((byte)0x12, "CG-");
			_hashEntries.put((byte)0x13, "CN-");
			_hashEntries.put((byte)0x14, "GA-");
			_hashEntries.put((byte)0x15, "GT-");
			_hashEntries.put((byte)0x16, "GC-");
			_hashEntries.put((byte)0x17, "GG-");			
			_hashEntries.put((byte)0x18, "GN-");
			_hashEntries.put((byte)0x19, "NA-");
			_hashEntries.put((byte)0x1A, "NT-");
			_hashEntries.put((byte)0x1B, "NC-");
			_hashEntries.put((byte)0x1C, "NG-");
			_hashEntries.put((byte)0x1D, "NN-");
			
			//As - 25
			_hashEntries.put((byte)0x1E, "AAA");
			_hashEntries.put((byte)0x1F, "AAT");
			_hashEntries.put((byte)0x20, "AAC");
			_hashEntries.put((byte)0x21, "AAG");
			_hashEntries.put((byte)0x22, "AAN");
			_hashEntries.put((byte)0x23, "ATA");
			_hashEntries.put((byte)0x24, "ATT");
			_hashEntries.put((byte)0x25, "ATC");
			_hashEntries.put((byte)0x26, "ATG");
			_hashEntries.put((byte)0x27, "ATN");
			_hashEntries.put((byte)0x28, "ACA");
			_hashEntries.put((byte)0x29, "ACT");
			_hashEntries.put((byte)0x2A, "ACC");
			_hashEntries.put((byte)0x2B, "ACG");
			_hashEntries.put((byte)0x2C, "ACN");
			_hashEntries.put((byte)0x2D, "AGA");
			_hashEntries.put((byte)0x2E, "AGT");
			_hashEntries.put((byte)0x2F, "AGC");
			_hashEntries.put((byte)0x30, "AGG");
			_hashEntries.put((byte)0x31, "AGN");
			_hashEntries.put((byte)0x32, "ANA");
			_hashEntries.put((byte)0x33, "ANT");
			_hashEntries.put((byte)0x34, "ANC");
			_hashEntries.put((byte)0x35, "ANG");
			_hashEntries.put((byte)0x36, "ANN");
			
			//Ts - 25
			_hashEntries.put((byte)0x37, "TAA");
			_hashEntries.put((byte)0x38, "TAT");
			_hashEntries.put((byte)0x39, "TAC");
			_hashEntries.put((byte)0x3A, "TAG");
			_hashEntries.put((byte)0x3B, "TAN");
			_hashEntries.put((byte)0x3C, "TTA");
			_hashEntries.put((byte)0x3D, "TTT");
			_hashEntries.put((byte)0x3E, "TTC");
			_hashEntries.put((byte)0x3F, "TTG");		
			_hashEntries.put((byte)0x40, "TGN");
			_hashEntries.put((byte)0x41, "TNA");
			_hashEntries.put((byte)0x42, "TNT");
			_hashEntries.put((byte)0x43, "TNC");
			_hashEntries.put((byte)0x44, "TNG");
			_hashEntries.put((byte)0x45, "TNN");
			_hashEntries.put((byte)0x46, "TTN");
			_hashEntries.put((byte)0x47, "TCA");
			_hashEntries.put((byte)0x48, "TCT");
			_hashEntries.put((byte)0x49, "TCC");
			_hashEntries.put((byte)0x4A, "TCG");
			_hashEntries.put((byte)0x4B, "TCN");
			_hashEntries.put((byte)0x4C, "TGA");
			_hashEntries.put((byte)0x4D, "TGT");
			_hashEntries.put((byte)0x4E, "TGC");
			_hashEntries.put((byte)0x4F, "TGG");
			
		
			_hashEntries.put((byte)0x50, "CAA");
			_hashEntries.put((byte)0x51, "CAT");
			_hashEntries.put((byte)0x52, "CAC");
			_hashEntries.put((byte)0x53, "CAG");
			_hashEntries.put((byte)0x54, "CAN");
			_hashEntries.put((byte)0x55, "CTA");
			_hashEntries.put((byte)0x56, "CTT");
			_hashEntries.put((byte)0x57, "CTC");			
			_hashEntries.put((byte)0x58, "CTG");
			_hashEntries.put((byte)0x59, "CTN");
			_hashEntries.put((byte)0x5A, "CCA");
			_hashEntries.put((byte)0x5B, "CCT");
			_hashEntries.put((byte)0x5C, "CCC");
			_hashEntries.put((byte)0x5D, "CCG");
			_hashEntries.put((byte)0x5E, "CCN");
			_hashEntries.put((byte)0x5F, "CGA");	
			_hashEntries.put((byte)0x60, "CGT");
			_hashEntries.put((byte)0x61, "CGC");
			_hashEntries.put((byte)0x62, "CGG");
			_hashEntries.put((byte)0x63, "CGN");
			_hashEntries.put((byte)0x64, "CNA");
			_hashEntries.put((byte)0x65, "CNT");
			_hashEntries.put((byte)0x66, "CNC");
			_hashEntries.put((byte)0x67, "CNG");
			_hashEntries.put((byte)0x68, "CNN");
			
			//Gs - 25
			_hashEntries.put((byte)0x69, "GAA");
			_hashEntries.put((byte)0x6A, "GAT");
			_hashEntries.put((byte)0x6B, "GAC");
			_hashEntries.put((byte)0x6C, "GAG");
			_hashEntries.put((byte)0x6E, "GAN");
			_hashEntries.put((byte)0x6F, "GTA");
			_hashEntries.put((byte)0x70, "GTT");
			_hashEntries.put((byte)0x71, "GTC");
			_hashEntries.put((byte)0x72, "GTG");
			_hashEntries.put((byte)0x73, "GTN");
			_hashEntries.put((byte)0x74, "GCA");
			_hashEntries.put((byte)0x75, "GCT");
			_hashEntries.put((byte)0x76, "GCC");
			_hashEntries.put((byte)0x77, "GCG");
			_hashEntries.put((byte)0x78, "GCN");
			_hashEntries.put((byte)0x79, "GGA");
			_hashEntries.put((byte)0x7A, "GGT");
			_hashEntries.put((byte)0x7B, "GGC");
			_hashEntries.put((byte)0x7C, "GGG");
			_hashEntries.put((byte)0x7D, "GGN");
			_hashEntries.put((byte)0x7E, "GNA");
			_hashEntries.put((byte)0x7F, "GNT");
			_hashEntries.put((byte)0x80, "GNC");
			_hashEntries.put((byte)0x81, "GNG");
			_hashEntries.put((byte)0x82, "GNN");
			
			
			_hashEntries.put((byte)0x83, "NAA");
			_hashEntries.put((byte)0x84, "NAT");
			_hashEntries.put((byte)0x85, "NAC");
			_hashEntries.put((byte)0x86, "NAG");
			_hashEntries.put((byte)0x87, "NAN");
			_hashEntries.put((byte)0x88, "NTA");
			_hashEntries.put((byte)0x89, "NTT");
			_hashEntries.put((byte)0x8A, "NTC");
			_hashEntries.put((byte)0x8B, "NTG");
			_hashEntries.put((byte)0x8C, "NTN");
			_hashEntries.put((byte)0x8D, "NCA");
			_hashEntries.put((byte)0x8E, "NCT");
			_hashEntries.put((byte)0x8F, "NCC");
			_hashEntries.put((byte)0x90, "NCG");			
			_hashEntries.put((byte)0x91, "NCN");
			_hashEntries.put((byte)0x92, "NGA");
			_hashEntries.put((byte)0x93, "NGT");
			_hashEntries.put((byte)0x94, "NGC");
			_hashEntries.put((byte)0x95, "NGG");
			_hashEntries.put((byte)0x96, "NGN");
			_hashEntries.put((byte)0x97, "NNA");
			_hashEntries.put((byte)0x98, "NNT");
			_hashEntries.put((byte)0x99, "NNC");
			_hashEntries.put((byte)0x9A, "NNG");
			_hashEntries.put((byte)0x9B, "NNN");  //155
			
			//next unoccupies 101
			/*
			_hashEntries.put((byte)0x9C, "A");
			_hashEntries.put((byte)0x9D, "A");
			_hashEntries.put((byte)0x9E, "A");
			_hashEntries.put((byte)0x9F, "A");
			_hashEntries.put((byte)0xA0, "A");
			_hashEntries.put((byte)0xA1, "A");
			_hashEntries.put((byte)0xA2, "A");
			_hashEntries.put((byte)0xA3, "A");
			_hashEntries.put((byte)0xA4, "A");
			_hashEntries.put((byte)0xA5, "A");
			_hashEntries.put((byte)0xA6, "A");
			_hashEntries.put((byte)0xA7, "A");
			_hashEntries.put((byte)0xAB, "A");
			_hashEntries.put((byte)0xAE, "A");
			_hashEntries.put((byte)0xAF, "A");
			_hashEntries.put((byte)0xB0, "A");
			_hashEntries.put((byte)0xB1, "A");
			_hashEntries.put((byte)0xB2, "A");
			_hashEntries.put((byte)0xB3, "A");
			_hashEntries.put((byte)0xB4, "A");
			_hashEntries.put((byte)0xB5, "A");
			_hashEntries.put((byte)0xB6, "A");
			_hashEntries.put((byte)0xB7, "A");
			_hashEntries.put((byte)0xB8, "A");
			_hashEntries.put((byte)0xB9, "A");
			_hashEntries.put((byte)0xBA, "A");
			_hashEntries.put((byte)0xBB, "A");
			_hashEntries.put((byte)0xBC, "A");
			_hashEntries.put((byte)0xBD, "A");
			_hashEntries.put((byte)0xBE, "A");
			_hashEntries.put((byte)0xBF, "A");
			_hashEntries.put((byte)0xC0, "A");
			_hashEntries.put((byte)0xC1, "A");
			_hashEntries.put((byte)0xC2, "A");
			_hashEntries.put((byte)0xC3, "A");
			_hashEntries.put((byte)0xC4, "A");
			_hashEntries.put((byte)0xC5, "A");
			
			_hashEntries.put((byte)0xC6, "A");
			_hashEntries.put((byte)0xC7, "A");
			_hashEntries.put((byte)0xC8, "A");
			_hashEntries.put((byte)0xC9, "A");
			_hashEntries.put((byte)0xCA, "A");
			_hashEntries.put((byte)0xCB, "A");
			_hashEntries.put((byte)0xCC, "A");
			_hashEntries.put((byte)0xCD, "A");
			
			_hashEntries.put((byte)0xCE, "A");
			_hashEntries.put((byte)0xCF, "A");
			_hashEntries.put((byte)0xD0, "A");
			_hashEntries.put((byte)0xD1, "A");
			_hashEntries.put((byte)0xD2, "A");
			_hashEntries.put((byte)0xD3, "A");
			_hashEntries.put((byte)0xD4, "A");
			_hashEntries.put((byte)0xD5, "A");
			
			_hashEntries.put((byte)0xD6, "A");
			_hashEntries.put((byte)0xD7, "A");
			_hashEntries.put((byte)0xD8, "A");
			_hashEntries.put((byte)0xD9, "A");
			_hashEntries.put((byte)0xDA, "A");
			_hashEntries.put((byte)0xDB, "A");
			_hashEntries.put((byte)0xDC, "A");
			_hashEntries.put((byte)0xDD, "A");
			
			_hashEntries.put((byte)0xDE, "A");
			_hashEntries.put((byte)0xEF, "A");
			_hashEntries.put((byte)0xE0, "A");
			_hashEntries.put((byte)0xE1, "A");
			_hashEntries.put((byte)0xE2, "A");
			_hashEntries.put((byte)0xE3, "A");
			_hashEntries.put((byte)0xE4, "A");
			_hashEntries.put((byte)0xE5, "A");
			
			_hashEntries.put((byte)0xE6, "A");
			_hashEntries.put((byte)0xE7, "A");
			_hashEntries.put((byte)0xE8, "A");
			_hashEntries.put((byte)0xE9, "A");
			_hashEntries.put((byte)0xEA, "A");
			_hashEntries.put((byte)0xEB, "A");
			_hashEntries.put((byte)0xEC, "A");
			_hashEntries.put((byte)0xED, "A");
			
			_hashEntries.put((byte)0xEE, "A");
			_hashEntries.put((byte)0xEF, "A");
			_hashEntries.put((byte)0xF0, "A");
			_hashEntries.put((byte)0xF1, "A");
			_hashEntries.put((byte)0xF2, "A");
			_hashEntries.put((byte)0xF3, "A");
			_hashEntries.put((byte)0xF4, "A");
			_hashEntries.put((byte)0xF5, "A");
			
			_hashEntries.put((byte)0xF6, "A");
			_hashEntries.put((byte)0xF7, "A");
			_hashEntries.put((byte)0xF8, "A");
			_hashEntries.put((byte)0xF9, "A");
			_hashEntries.put((byte)0xFA, "A");
			_hashEntries.put((byte)0xFB, "A");
			_hashEntries.put((byte)0xFC, "A");
			_hashEntries.put((byte)0xFD, "A");
			
			_hashEntries.put((byte)0xFE, "A");
			_hashEntries.put((byte)0xFF, "A");*/
			 
		}
	}
	
	/////////////////////////////////////////////////////////////////////////
	//encoded->decoded
	//
	////////////////////////////////////////////////////////////////////////
	public static String convert(byte[] bases) throws Exception
	{
		init();
		StringBuffer strBases = new StringBuffer();

		for (int i=0; i<bases.length; i+=3 )
		{
			StringBuffer sb = new StringBuffer();
			
			char b1 = '-';
			char b2 = '-';
			char b3 = '-';
			
			if (i==(bases.length -1))
			{
				b1 = (char)bases[i];
			}
			else if (i==(bases.length -2))
			{
				b1 = (char)bases[i];
				b2 = (char)bases[i+1];
			}
			else
			{				
				b1 = (char)bases[i];
				b2 = (char)bases[i+1];
				b3 = (char)bases[i+2];
				sb.append(b1);
				sb.append(b2);
				sb.append(b3);
			}
			
			
			byte encodedBases = BasicDictionaryEncoder.convertBases(sb.toString());
			strBases.append((char)encodedBases);
		}
		
		return strBases.toString();
	}
	
	public static byte convertBases(String threebases) throws Exception
	{
		init();
		 for (Map.Entry<Byte, String> entry : _hashEntries.entrySet()) {
		        if (threebases.equals(entry.getValue())) {
		            return entry.getKey();
		        }
		    }
		 
		    throw new Exception("Base Pattern Not Found");
	}
	
	
	public static String convertToRegBaseEncoding(String encodedSequence)
	{
		StringBuffer sb = new StringBuffer(); 
		byte[] encodedByteSequence = encodedSequence.getBytes(); 
		for (byte es : encodedByteSequence)
		{
			String word = 	_hashEntries.get(es);
			sb.append(word);
		}
		
		return sb.toString();
	}
}

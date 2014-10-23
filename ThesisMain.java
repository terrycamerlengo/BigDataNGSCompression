import java.io.File;
import java.util.*;

import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;

public class ThesisMain {
	public static void main(String[] args)
	{
		try {
			
			//writes out 3 fasta files: regular (control), compressed/encoded, header only
			//NGSDataQueryMgr.writeToFastaFile("C:\\NGSPositionCounter\\s_5_unmapped_PF_Q30.sam", "s_5_unmapped_PF_Q30.fasta", false, false);
			//NGSDataQueryMgr.writeToFastaFile("C:\\NGSPositionCounter\\s_5_unmapped_PF_Q30.sam", "s_5_unmapped_PF_Q30_compressed.fasta", true, false);
			//NGSDataQueryMgr.writeToFastaFile("C:\\NGSPositionCounter\\s_5_unmapped_PF_Q30.sam", "s_5_unmapped_PF_Q30_headerOnly.fasta", false, true);
			
			//just IOTA Control
			//NGSDataQueryMgr.writeToFastaFile("C:\\NGSPositionCounter\\Iota_Control.sam", "Iota_Control.fasta", false, false);
			//NGSDataQueryMgr.writeToFastaFile("C:\\NGSPositionCounter\\Iota_Control.sam", "Iota_Control_encoded.fasta", true, false);
			//NGSDataQueryMgr.writeToFastaFile("C:\\NGSPositionCounter\\Iota_Control.sam", "Iota_Control_headerOnly.fasta", false, true);

			
			//Mongodb tests
			//writeSAMFileToMongoDB("C:\\NGSPositionCounter\\Iota_Control.sam", "localhost", "NGS_DB", "Iota_Control");
			writeSAMFileToCompressedFASTA("E:\\data\\wt.1.flt.sam");
			//writeSAMFileToMongoDB("C:\\Users\\camerlengot\\Documents\\samtools\\data\\WT.1.flt.bam", "localhost", "NGS_DB", "PreliminaryResults"); 
		}
		catch(Exception exc)
		{
			System.out.println("NGSDataQueryMgr static main() exception: " + exc.getMessage());
		} 
		
		//Mongo Stuff
		//SamToMongoReader.writeToDB("Iota_Control.sam");  //creates a new repository/collection
		 
		//NGSDataQueryMgr.runQuery("referenceName", "Iota_Con_TT");
		
		//<07<)4<(1/:67*0:10:05" , "normalSequence" : "GCTCCGGCATCAGCAATGTTGACCCGACTCGATGTCGATCCT"
		//String s = Base4bitEncoder.testConvert4bitToRegBaseEncoding("<07<)4<(1/:67*0:10:05");
		//System.out.println(s);
	}
	
/////////////////////////////////////////////////////////////////////////////////
//writeToFastaFile takes a SAM file (unmapped/unaligned) and writes it out as 
//                 both a regular non-encoded fasta file and a 4- bit encoded fasta file.
//                 Makes use of the barcodes to determine Reference Sequence used.
//                 This is not a generic method that can be used on any SAM file, but specific a test step
//                 on the HT_SOSA data
/////////////////////////////////////////////////////////////////////////////////
public static void writeToFastaFile(String inputSAMFileName, String outputFileName, boolean transformSequence, boolean headerOnly) 
throws Exception 
{
	NGSContainerMgr ngsContainerMgr = new NGSContainerMgr(outputFileName);
	FastaPersistenceMgr fastaFile =  (FastaPersistenceMgr)ngsContainerMgr.getPersistenceMgr();
	fastaFile.write(inputSAMFileName, transformSequence, headerOnly);
	fastaFile.close();
}


public static void writeSAMFileToCompressedFASTA(String inputSAMFileName) 
throws Exception
{
	NGSContainerMgr ngsAlignedFastaContainerMgr = new NGSContainerMgr(inputSAMFileName.replace(".sam", "_hcompressed.fasta"));
	
	FastaPersistenceMgr alignedFastaFile =  (FastaPersistenceMgr)ngsAlignedFastaContainerMgr.getPersistenceMgr();

	SAMFileReader inputSam = new SAMFileReader(new File(inputSAMFileName));

	int i=0, alignedCnt=0, unalignedCnt=0;

	for (final SAMRecord samRecord : inputSam) 
	{	
		i++;
		/*String cigar = samRecord.getCigarString();
		String readName = samRecord.getReadName();
		String referenceName = samRecord.getReferenceName();
		
		List<net.sf.samtools.AlignmentBlock> alignmentBlocks = samRecord.getAlignmentBlocks();
		int alignmentStart = samRecord.getAlignmentStart();
		int alignmentEnd = samRecord.getAlignmentEnd();
		int mappingQuality = samRecord.getMappingQuality();
		int flags = samRecord.getFlags();
		List<net.sf.samtools.SAMRecord.SAMTagAndValue> attributes = samRecord.getAttributes();
		byte[] qualities = samRecord.getBaseQualities();
		String qualityString = samRecord.getBaseQualityString();
		String samString = samRecord.getSAMString();
		boolean duplicateFlag = samRecord.getDuplicateReadFlag();
		*/
		
		String samString = samRecord.getSAMString();
		boolean readUnmapped = samRecord.getReadUnmappedFlag();
		String referenceName = samRecord.getReferenceName();
		String cigar = samRecord.getCigarString();
		String regularSequence = samRecord.getReadString();
		TransformedSequence tSeq = TransformedSequence.createTransformedSequenceFactory(/*readUnmapped*/true, cigar, regularSequence, referenceName);
		
		if (readUnmapped) { unalignedCnt++; } else {alignedCnt++;} 
		 
		
		//String compressedSeq = tSeq.get();
		String compSamString = samString.replaceAll(regularSequence, "");
		
		//alignedFastaFile.write(tSeq, samRecord.getReadName(), samRecord.getReferenceName());
		//alignedFastaFile.write(regularSequence, samRecord.getReadName(), samRecord.getReferenceName(), !readUnmapped);
		alignedFastaFile.write(tSeq, compSamString);
		
		//String strAttributes = "";
		//for (net.sf.samtools.SAMRecord.SAMTagAndValue att : attributes)
		//{
		//	strAttributes += att.tag + ":" + att.value;
		//	String s = att.toString();
		//}
		
		//if (i>10000000)
		//{
		//	break;
		//}
	}
	System.out.println("Aligned " + alignedCnt + " , unaligned " + unalignedCnt + ", total " + i);
	alignedFastaFile.close();		
}

public static void writeSAMFileToMongoDB(String inputSAMFileName, String dbServer, String dbName, String dbCollectionName) 
throws Exception
{
	NGSContainerMgr ngsContainerMgr = new NGSContainerMgr(dbServer, dbName, dbCollectionName);
	//NGSContainerMgr ngsAlignedContainerMgr = new NGSContainerMgr(dbServer, dbName, "aligned_" + dbCollectionName);
	NGSContainerMgr ngsAlignedFastaContainerMgr = new NGSContainerMgr(inputSAMFileName.replace(".sam", "_aligned.fasta"));
	
	MongoDBPersistenceMgr mongoDbConn =  (MongoDBPersistenceMgr)ngsContainerMgr.getPersistenceMgr();
	//MongoDBPersistenceMgr mongoDbAlignedConn =  (MongoDBPersistenceMgr)ngsAlignedContainerMgr.getPersistenceMgr();
	FastaPersistenceMgr alignedFastaFile =  (FastaPersistenceMgr)ngsAlignedFastaContainerMgr.getPersistenceMgr();

	mongoDbConn.writeToDB(inputSAMFileName);

	com.mongodb.DBCursor cursor = ngsContainerMgr.findAll();

	while(cursor.hasNext()) {
		com.mongodb.DBObject dbObj = cursor.next();
		
		String refName = (String)dbObj.get("reference");
		String tSeq = (String)dbObj.get("tSeq");
		String cigar = (String)dbObj.get("cigarString");
		
		boolean unmapped = (Boolean)dbObj.get("readUnmapped");
		String readName = (String)dbObj.get("readName");
		String normalSequence = "";
	
		if (unmapped == false)
		{
			normalSequence =  ReferenceCompressedSequence.getNormalSequenceFromCigar(cigar, tSeq);
		}
		else
		{
			normalSequence = Base4bitEncoder.convert4bitToRegBaseEncoding(tSeq);
		}
	
	
		TransformedSequence transformedSeq = TransformedSequence.createTransformedSequenceFactory(unmapped, cigar, normalSequence, refName);
	
		HT_SOSASeqRead seqRead = new HT_SOSASeqRead(readName, transformedSeq);
	
		TransformedSequence transformedSequence = null;
	
		if (seqRead.getSequenceString().contains("N"))
		{
			transformedSequence = TransformedSequence.createTransformedSequenceFactory(true, "", seqRead.getSequenceString(), seqRead.getReference());
		}
		else
		{
			needle.AlignmentMgr nw = new needle.AlignmentMgr();
			String sequence = seqRead.getSequenceString();
			String reference = ReferenceCompressionMgr.GetReferenceSequence(seqRead.getReference());
			nw.run(sequence, reference);
			List<String> results = nw.getResults();
			String cigar2 = getReferenceCompressionString(results);
			transformedSequence = TransformedSequence.createTransformedSequenceFactory(false, cigar2, seqRead.getSequenceString(), seqRead.getReference());
		}
		//mongoDbAlignedConn.write(transformedSequence, seqRead.getId(), transformedSequence.getReferenceName());
		alignedFastaFile.write(transformedSequence, seqRead.getId(), transformedSequence.getReferenceName());
	}

	cursor.close();
	alignedFastaFile.close();

}


public static String getReferenceCompressionString(List<String> seqs1) throws Exception
{
	String auditString = ""; 
	
	
	int counter_pos = 0;
	
	if (seqs1.size() > 1)
	{	
		String reference = seqs1.get(0);
		String query = seqs1.get(1);
		
		for (int g=0; ((g<seqs1.size()) && (seqs1.size() > 2)); g++)
		{
			String tempRef = seqs1.get(g);
			if (tempRef.charAt(tempRef.length()-1) == '-')
			{
				//use this sequence
				//tempRef = tempRef.subSequence(0, tempRef.length()-2) + "A";
				reference = tempRef;
				query = seqs1.get(g+1);
			}
			
			g++;
		}
		
		String[] trimmedSeqs = trimSequences(query, reference);
		
		reference = trimmedSeqs[0];
		query = trimmedSeqs[1];
		
		if (reference.length() == query.length())
		{
			int consec_deletion_count = 0;
			int consec_insertion_count = 0;
			
			
			for (int i=0; i<reference.length(); i++)
			{
				char queryBase = query.charAt(i);
				char refBase = reference.charAt(i);
				
				if (queryBase == '-')
				{
					//deletion
					auditString += "D@" + counter_pos + ",";									
					counter_pos++;
					consec_deletion_count++;
				}
				else if (refBase == '-')
				{	
					//insertion
					auditString += "I@" + counter_pos + ",";
					consec_insertion_count++;
				}
				else if (queryBase == refBase)
				{						
					counter_pos++;
				}
				else
				{
					//mismatch
					auditString += "S@" + counter_pos + queryBase + ",";
					
					counter_pos++;
				}								
			}
				
			String msg = auditString + "\n" + "\nR:" 
						+ reference + "\n" + "Q:" 
						+ query + "\n" 
						+ "#######################################\n";
			System.out.println(msg);
		}
		else
		{
			System.out.println(reference.length() + "!=" + query.length());
		}
	}
	else
	{
		throw new  Exception("Query longer than reference!");
		
	}
	
	return auditString;
}

//////////////////////////////////////////////////////
//
//
/////////////////////////////////////////////////////
protected static String[] trimSequences(String query, String reference)
{
	String[] seqs = new String[2]; 
	int query_endGap=0, reference_endGap=0, trim_len = 0;
	
	for (int i=query.length()-1; i>=0; i--)
	{
		char q = query.charAt(i);
		if (q=='-')
		{
			query_endGap++;
		}
		else
		{
			break;
		}
	}
	
	for (int k = reference.length()-1; k>=0; k--)
	{
		char r = reference.charAt(k);
		if (r == '-')
		{
		reference_endGap++;
		}
		else
		{
			break;
		}
	}
	
	if (reference_endGap >= query_endGap ) 
	{
		trim_len= reference_endGap;
	}
	else if (query_endGap > reference_endGap)
	{
		trim_len = query_endGap; 
	}
	
	if (trim_len > 0)
	{
		query = query.substring(0, query.length()-trim_len);
		reference = reference.substring(0, reference.length()-trim_len);
	}
	
	seqs[0] = reference;
	seqs[1] = query;
	
	return seqs;
}


}

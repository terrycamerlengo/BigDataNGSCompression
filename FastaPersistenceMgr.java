import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.RuntimeException;
import java.util.List;

import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;


public class FastaPersistenceMgr implements PersistenceMgr {
	
	private PrintWriter _fastaFile = null;
	private String _fileName = "";
	
	public FastaPersistenceMgr(String fileName) throws java.io.IOException
	{
		//look up in config root
		_fastaFile = new PrintWriter(new FileWriter(fileName));	
		this._fileName = fileName;
	}

	public void write(String sequence)
	{
		_fastaFile.println(">");
		writeSequenceIn80chars(sequence);
	}
	public void write(String sequence, String readName, String refName, boolean isAligned)
	{
		_fastaFile.println(">lcl|" + readName + "|" + refName + "|" + isAligned);
		writeSequenceIn80chars(sequence);
	}
	
	
	
	public void write(TransformedSequence sequence, String readName, String refName)
	{
		_fastaFile.println(">" + readName + "\t" + refName + "\t" + sequence.isAligned());
		writeSequenceIn80chars(sequence.get());
	}
	
	public void write(TransformedSequence sequence, String description)
	{
		_fastaFile.println(">" + description);
		writeSequenceIn80chars(sequence.get());
	}
	
	private void writeSequenceIn80chars(String seq)
	{
		if (seq.length() > 80)
		{
			String line = seq.substring(0, 79);
			_fastaFile.println(line);
			writeSequenceIn80chars(seq.substring(80, seq.length()));
		}
		else
		{
			_fastaFile.println(seq);
		}
	}
	
	public void close()
	{
		_fastaFile.flush();
		_fastaFile.close();
	}
	
	///Converts a sam file to a fasta file
	//A utility method
	public void write(String samFile, boolean transformSequence, boolean HeaderOnly) throws java.io.IOException {
 		
		SAMFileReader inputSam = new SAMFileReader(new File(samFile));
		
		for (final SAMRecord samRecord : inputSam) 
		{	
			String samString = samRecord.getSAMString();
			String regularSequence = samRecord.getReadString();
			boolean readUnmapped = samRecord.getReadUnmappedFlag();
			String cigar = samRecord.getCigarString();
			String referenceName =ReferenceCompressionMgr.GetReferenceName(regularSequence);
			TransformedSequence transformedSeq = TransformedSequence.createTransformedSequenceFactory(readUnmapped, cigar, regularSequence, referenceName);
			
			
			//header 
			_fastaFile.println("\n>lcl|" + samRecord.getReadName() + "|" + referenceName);
			
			if (HeaderOnly == false)
			{
				if ((transformSequence)){
					writeSequenceIn80chars(transformedSeq.get());
				}
				else
				{
					writeSequenceIn80chars(regularSequence);
				}
			}
 			
			//Testing step - make sure we can go back and forth between encoded and regular
			//String convert4bitToRegular = Base4bitEncoder.convert4bitToRegBaseEncoding(fourBitBaseSeq);			
		}

		this.close();
	}


	@Override
	public void update(String readName, TransformedSequence sequence) {
		throw new RuntimeException("update in FastaPersistenceMgr not implemented");
	}


	@Override
	public void delete(String readName) {
		throw new RuntimeException("delete in FastaPersistenceMgr not implemented");		
	}


	@Override
	public List<SeqRead> find() {
		throw new RuntimeException("find() in FastaPersistenceMgr not implemented");
	}


	@Override
	public List<SeqRead> find(String key, String value) {
		throw new RuntimeException("find(String key, String value) in FastaPersistenceMgr not implemented");
		
	}


	@Override
	public List<SeqRead> find(TransformedSequence sequence) {
		throw new RuntimeException("find(TransformedSequence sequence) in FastaPersistenceMgr not implemented");
		
	}
	
}

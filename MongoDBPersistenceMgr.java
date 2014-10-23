import java.io.File;
import java.net.UnknownHostException;
import java.util.List;

import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.ServerAddress;
import com.mongodb.DBCollection;



public class MongoDBPersistenceMgr implements PersistenceMgr {

private MongoClient dbConn;
private DB catalog;
private com.mongodb.DBCollection collection;
private String dbName, collectionName;


public MongoDBPersistenceMgr(String server, String dbName, String collectionName) throws UnknownHostException  
{	 	 
	//server = "localhost" with default port
	dbConn = new MongoClient( server , 27017 );
	catalog  = dbConn.getDB(dbName);
	collection = catalog.getCollection(collectionName);
	this.collectionName = collectionName;
	this.dbName = dbName;
}
	
@Override
public void write(TransformedSequence tSequence, String readName, String refName) {
	// TODO Auto-generated method stub
	 
	boolean readUnmapped = tSequence.isAligned();
	com.mongodb.BasicDBObject jsonDoc = new com.mongodb.BasicDBObject("readName", readName);
	
	jsonDoc.append("reference", refName);
	jsonDoc.append("tSeq", tSequence.get());
	jsonDoc.append("readUnmapped", readUnmapped);
	collection.insert(jsonDoc);
}

@Override
public void update(String readName, TransformedSequence sequence) {
	// TODO Auto-generated method stub
	
}

@Override
public void delete(String readName) {
	// TODO Auto-generated method stub
	
}



@Override
public List<SeqRead> find() {
	
	List<SeqRead> results = new java.util.LinkedList<SeqRead>();
	
	DBCursor cursor = null;

	try 
	{
		
	 	System.out.println("Mongo DB query Find All");	
		cursor = collection.find();
		
		while(cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			
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
			results.add(seqRead);
		}

	} 
	catch(Exception exc)
	{
		System.err.println("Exception in runQuery(string, string)" + exc.getMessage());
	}
	finally {
		cursor.close();
	}
	
	return results;

}

public DBCursor findAll() {
	
	List<SeqRead> results = new java.util.LinkedList<SeqRead>();
	
	DBCursor cursor = null;

	try 
	{		
	 	System.out.println("Mongo DB query Find All");	
		cursor = collection.find();
		
		/*
		while(cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			
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
			results.add(seqRead);
		}*/

	} 
	catch(Exception exc)
	{
		System.err.println("Exception in runQuery(string, string)" + exc.getMessage());
	}
 	
	return cursor;

}


@Override
public List<SeqRead> find(String key, String value) {

	List<SeqRead> results = new java.util.LinkedList<SeqRead>();
	
	DBCursor cursor = null;
	
	try 
	{
		com.mongodb.BasicDBObject jsonQuery = new com.mongodb.BasicDBObject(key, value);
		cursor = collection.find( jsonQuery);
		
		while(cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			
			String refName = (String)dbObj.get("reference");
			String tSeq = (String)dbObj.get("tSeq");
			String cigar = (String)dbObj.get("cigarString");
			String normalSequence = ReferenceCompressedSequence.getNormalSequenceFromCigar(cigar, tSeq);
			boolean unmapped = (Boolean)dbObj.get("readUnmapped");
			String readName = (String)dbObj.get("readName");
			
			TransformedSequence transformedSeq = TransformedSequence.createTransformedSequenceFactory(unmapped, cigar, normalSequence, refName);
			
			SeqRead seqRead = new HT_SOSASeqRead(readName, transformedSeq);
			results.add(seqRead);
		}
	} 
	catch(Exception exc)
	{
		System.err.println("Exception in runQuery(string, string)" + exc.getMessage());
	}
	finally {
		cursor.close();
	}
	
	 
	return results;
}

@Override
public List<SeqRead> find(TransformedSequence sequence) {
	// TODO Auto-generated method stub
	throw new RuntimeException("find in MongoDBPersistenceMgr not implemented");
}

////////////////////////////////////////////
//Utility Method to populate a mongodb database from a samFile
//
/////////////////////////////////////////////
public void writeToDB(String samFile) {
 
	//empty collection and start over
	if (collection != null) {
		String collectionname = collection.getFullName();
		System.out.println("Removing collection " + collectionname);
		collection.drop();
		//{ capped : true, size : 5242880, max : 5000 }
		catalog.createCollection(collectionName, new BasicDBObject("capped", false) );
	}

 
	SAMFileReader inputSam = new SAMFileReader(new File(samFile));

	int i=0;

	for (final SAMRecord samRecord : inputSam) 
	{	
		i++;
		String cigar = samRecord.getCigarString();
		String readName = samRecord.getReadName();
		String reference = ReferenceCompressionMgr.GetReferenceName(samRecord.getReadString());

		boolean readUnmapped = samRecord.getReadUnmappedFlag();
		String regularSequence = samRecord.getReadString();
		com.mongodb.BasicDBObject jsonDoc = new com.mongodb.BasicDBObject("readName", readName);
		
		TransformedSequence tSeq = TransformedSequence.createTransformedSequenceFactory(readUnmapped, cigar, regularSequence, reference);
		
		jsonDoc.append("reference", reference);
		jsonDoc.append("tSeq", tSeq.get());
		jsonDoc.append("cigarString", cigar);
		jsonDoc.append("readUnmapped", readUnmapped);
		collection.insert(jsonDoc);

		//figure out a better way to get up to, without surpassing 2 GB limit
		//if (i>1000) 
		//	break;

	}
}

@Override
public void close() {
	this.dbConn.close();
}



}

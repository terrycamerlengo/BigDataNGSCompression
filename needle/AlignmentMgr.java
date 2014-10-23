/**
 * 
 */
package needle;

import java.util.List;

/**
 *source code from google code
 *http://code.google.com/p/gal2009/source/browse/trunk/app/GalServer/src/main/java/gal/needleman/?r=22#needleman%2Fwunsch
 *Modifications to work with our process
 *Should rename this class to some wrapper of the NW
 */
public class AlignmentMgr {

	public static final int SIM_MATRIX_1 = 1;
	public static final int SIM_MATRIX_2 = 2;
	

	
	NeedlemanWunsch _nw = null;
	private double _gapPenalty = -2;


	
	public final static double[][] SimilarityMatrix = {	
			{ 1,-1,-1,-1},
			{-1, 1,-1,-1},
			{-1,-1, 1,-1},
			{-1,-1,-1, 1}};
	
	
	public List<String> getResults()
	{
		if (_nw != null)
		{
			return _nw.getSequences();
		}
		
		return null;
	}
	
	
	
 

	//
	public void run(String query, String reference)
	{
		_nw = null;
		RequestDTO request = new RequestDTO();
		request.setGapPenalty(_gapPenalty);
		request.setSimilarityMatrix(SimilarityMatrix);
		request.setSequence1(reference);
		request.setSequence2(query);
		_nw = new NeedlemanWunsch(request);
		 
		_nw.run();
	}
 	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		double gapPenalty = -2;
		RequestDTO request = new RequestDTO();
		request.setGapPenalty(gapPenalty);
		request.setSimilarityMatrix(SimilarityMatrix);
		request.setSequence1("AAACCTGAGACTTGAAAGAAAGAAAGAAAGAAAGAAAGAAAGAAAGAAAGAAAGAAAGAAAGAAA");
		request.setSequence2("ACCTAGAACCTGAGACTTGAAA");
		NeedlemanWunsch nw = new NeedlemanWunsch(request);
		nw.run();
	}

}

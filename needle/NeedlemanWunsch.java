/**
 * 
 */
package needle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NeedlemanWunsch {
	
	private static final int MAX_RESULTS_COUNT = 100;
	
	
	int resultsCount = 0;
	
	private String sequence1;
	private String sequence2;
	
	private FMatrixElement[][] fMatrix;
	private double[][] similarityMatrix;
	private double gapPenalty;
	
	private ResultDTO result = null;
	
	public NeedlemanWunsch(RequestDTO request) {
		this.sequence1 = request.getSequence1();
		this.sequence2 = request.getSequence2();
		this.similarityMatrix = request.getSimilarityMatrix();
		this.gapPenalty = request.getGapPenalty();
	}
	
	public void run() {
		this.compileFMatrix();
		this.prepareResults();
		this.printResultsAbrrev();
	}
	
	private void compileFMatrix() {
		this.fMatrix = new FMatrixElement[this.sequence1.length()+1][this.sequence2.length()+1];
		
		for (int y = 0; y <= this.sequence1.length(); y++) {  //inicjalizacja macierzy
			this.fMatrix[y][0] = new FMatrixElement();
			this.fMatrix[y][0].setValue(this.gapPenalty * y);
		}
		
		for (int x = 0; x <= this.sequence2.length(); x++) { //inicjalizacja macierzy
			this.fMatrix[0][x] = new FMatrixElement();
			this.fMatrix[0][x].setValue(this.gapPenalty * x);
		}
		
		for (int y = 1; y < this.sequence1.length() + 1; y++) {  //wyliczenie macierzy korzystajc z macierzy podobienstwa
			for (int x = 1; x < this.sequence2.length() +1; x++) {
				this.fMatrix[y][x] = new FMatrixElement();
				double k = this.fMatrix[y-1][x-1].getValue() + getSimilarity(this.sequence1.charAt(y-1), this.sequence2.charAt(x-1));
				double l = this.fMatrix[y-1][x].getValue() + this.gapPenalty;
				double m = this.fMatrix[y][x-1].getValue() + this.gapPenalty;
				double max = Math.max(k,l);
				max = Math.max(max, m);
				this.fMatrix[y][x].setValue(max);
				if(k == max)
					this.fMatrix[y][x].setDiagonal(true);
				if(l == max)
					this.fMatrix[y][x].setUp(true);
				if(m == max)
					this.fMatrix[y][x].setLeft(true);
			}
		}
	}
	
	private double getSimilarity(char first, char second) {
		return this.similarityMatrix[nucleobasePosition(first)][nucleobasePosition(second)];
	}
	
	private int nucleobasePosition(char ch) {
		switch (ch) {
			case 'A':
				return 0;
			case 'C':
				return 1;
			case 'G':
				return 2;
			case 'T':
				return 3;
		}
		return -1;
	}
	
	private void prepareResults() {
		this.result = new ResultDTO();
		this.result.setListOfSequences(this.generateListOfSequences(this.sequence1.length(), this.sequence2.length(), null, null));
		this.result.setSimilarityValue(this.fMatrix[this.sequence1.length()][this.sequence2.length()].getValue());
	}
	
	private List<String> generateListOfSequences(int seq1Pos, int seq2Pos, String ch1, String ch2) {
		if(this.resultsCount >= MAX_RESULTS_COUNT)
			return new ArrayList<String>();
		
		List<String> resultList = new ArrayList<String>();
		StringBuilder chain1;
		StringBuilder chain2;
		if(ch1 == null)
			chain1 = new StringBuilder();
		else
			chain1 = new StringBuilder(ch1);
		if(ch2 == null)
			chain2 = new StringBuilder();
		else
			chain2 = new StringBuilder(ch2);
		
		int sequence1Pos = seq1Pos;
		int sequence2Pos = seq2Pos;
		
		while(sequence1Pos > 0 && sequence2Pos > 0) {
			int count = 0;
			if(fMatrix[sequence1Pos][sequence2Pos].isDiagonal())
				count ++;
			if(fMatrix[sequence1Pos][sequence2Pos].isLeft())
				count ++;
			if(fMatrix[sequence1Pos][sequence2Pos].isUp())
				count ++;
			
			int tmpSeq1Pos = sequence1Pos;
			int tmpSeq2Pos = sequence2Pos;
			
			if(fMatrix[tmpSeq1Pos][tmpSeq2Pos].isDiagonal()) {
				if(count > 1) {
					resultList.addAll(generateListOfSequences(tmpSeq1Pos-1, tmpSeq2Pos-1, new StringBuilder(chain1).insert(0, sequence1.charAt(tmpSeq1Pos-1)).toString(), new StringBuilder(chain2).insert(0, sequence2.charAt(tmpSeq2Pos-1)).toString()));
					count --;
				} else {
					sequence1Pos --;
					sequence2Pos --;
					chain1.insert(0, sequence1.charAt(sequence1Pos));
					chain2.insert(0, sequence2.charAt(sequence2Pos));
				}
			}
			
			if(fMatrix[tmpSeq1Pos][tmpSeq2Pos].isLeft()) {
				if(count > 1) {
					resultList.addAll(generateListOfSequences(tmpSeq1Pos, tmpSeq2Pos-1, new StringBuilder(chain1).insert(0, '-').toString(), new StringBuilder(chain2).insert(0, sequence2.charAt(tmpSeq2Pos-1)).toString()));
					count --;
				} else {
					sequence2Pos --;
					chain1.insert(0, '-');
					chain2.insert(0, sequence2.charAt(sequence2Pos));
				}
			}
			
			if(fMatrix[tmpSeq1Pos][tmpSeq2Pos].isUp()) {
				sequence1Pos --;
				chain1.insert(0, sequence1.charAt(sequence1Pos));
				chain2.insert(0, '-');
			}
		}
		
		while(sequence1Pos>0) {
			sequence1Pos --;
			chain1.insert(0, sequence1.charAt(sequence1Pos));
			chain2.insert(0, '-');
		}
		
		while(sequence2Pos>0) {
			sequence2Pos --;
			chain1.insert(0, '-');
			chain2.insert(0, sequence2.charAt(sequence2Pos));
		}
		
		if(this.resultsCount < 100) {
			resultList.add(chain1.toString());
			resultList.add(chain2.toString());
			this.resultsCount++;
		}
		return resultList;
	}
	
	/**
	 * @return the sequence1
	 */
	public String getSequence1() {
		return sequence1;
	}

	/**
	 * @param sequence1 the sequence1 to set
	 */
	public void setSequence1(String sequence1) {
		this.sequence1 = sequence1;
	}

	/**
	 * @return the sequence2
	 */
	public String getSequence2() {
		return sequence2;
	}

	/**
	 * @param sequence2 the sequence2 to set
	 */
	public void setSequence2(String sequence2) {
		this.sequence2 = sequence2;
	}

	/**
	 * @return the fMatrix
	 */
	public FMatrixElement[][] getFMatrix() {
		return fMatrix;
	}
	
	/**
	 * @param matrix the fMatrix to set
	 */
	public void setFMatrix(FMatrixElement[][] matrix) {
		fMatrix = matrix;
	}
	
	/**
	 * @return the similarityMatrix
	 */
	public double[][] getSimilarityMatrix() {
		return similarityMatrix;
	}
	
	/**
	 * @param similarityMatrix the similarityMatrix to set
	 */
	public void setSimilarityMatrix(double[][] similarityMatrix) {
		this.similarityMatrix = similarityMatrix;
	}
	
	/**
	 * @return the gapPenalty
	 */
	public double getGapPenalty() {
		return gapPenalty;
	}
	
	/**
	 * @param gapPenalty the gapPenalty to set
	 */
	public void setGapPenalty(double gapPenalty) {
		this.gapPenalty = gapPenalty;
	}

	/**
	 * @return the result
	 */
	public ResultDTO getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(ResultDTO result) {
		this.result = result;
	}
	
	private void printResults() {
		for(int j = 0; j<=this.sequence2.length(); j++) {
			for(int i = 0; i<=this.sequence1.length();i++) {
				System.out.print(this.fMatrix[i][j].getValue()+", ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		if(this.result != null && this.result.getListOfSequences() != null) {
			boolean flag = true;
			for(String s : this.result.getListOfSequences()) {
				System.out.println(s);
				flag = !flag;
				if(flag)
					System.out.println();
			}
		}
	System.out.println();
	}

	
	private void printResultsAbrrev() {
		/*
		for(int j = 0; j<=this.sequence2.length(); j++) {
			for(int i = 0; i<=this.sequence1.length();i++) {
				System.out.print(this.fMatrix[i][j].getValue()+", ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		if(this.result != null && this.result.getListOfSequences() != null) {
			boolean flag = true;
			for(String s : this.result.getListOfSequences()) {
				System.out.println(s);
				flag = !flag;
				if(flag)
					System.out.println();
			}
		}
	System.out.println();*/
	}
	
	public List<String> getSequences()
	{
		return this.result.getListOfSequences();
	}
	
}

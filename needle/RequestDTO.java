/**
 * 
 */
package needle;

/**
 *
 */
public class RequestDTO {

	private String sequence1;
	private String sequence2;
	private double[][] similarityMatrix;
	private double gapPenalty;
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
	
}

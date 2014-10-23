/**
 * 
 */
//package pl.edu.pw.elka.mbi;
package needle;

/**
 *
 */
public class FMatrixElement {

	double value;
	boolean up;
	boolean left;
	boolean diagonal;
	
	/**
	 * 
	 */
	public FMatrixElement() {
		this.up = false;
		this.left = false;
		this.diagonal = false;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the up
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * @return the diagonal
	 */
	public boolean isDiagonal() {
		return diagonal;
	}

	/**
	 * @param diagonal the diagonal to set
	 */
	public void setDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

}

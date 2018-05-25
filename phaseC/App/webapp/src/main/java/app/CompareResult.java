/**
 * 
 */
package app;

/**
 * @author Deepak
 *
 */
public class CompareResult {

	private String fileName1;
	private String fileName2;
	
	private Double score;
	
	CompareResult(String fileName1, String fileName2, Double score){
		this.setFileName1(fileName1);
		this.setFileName2(fileName2);
		this.setScore(score);
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}

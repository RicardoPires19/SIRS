
public class Token {

	private int randomNum;
	private long timeStamp;

	public Token() {

	}

	public Token(int randomNum, long timeStamp) {

	}

	public int getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(int randomNum) {
		this.randomNum = randomNum;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Token))
			return false;
		Token t = (Token) o;

		return (this.randomNum == t.getRandomNum()) && (t.getTimeStamp() == t.getRandomNum());

	}

}

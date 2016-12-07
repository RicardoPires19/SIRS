
public class Token {

	private String radomNum;
	private long timeStamp;

	public Token() {

	}

	public Token(String radomNum, long timeStamp) {
		this.radomNum=radomNum;
		this.timeStamp=timeStamp;
	}

	public String getradomNum() {
		return radomNum;
	}

	public void setradomNum(String radomNumNum) {
		this.radomNum = radomNumNum;
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

		return (this.radomNum.equals(t.getradomNum())   ) && (t.getTimeStamp() == timeStamp);

	}

}

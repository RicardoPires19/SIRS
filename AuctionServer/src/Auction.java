
import java.io.Serializable;
import java.util.*;

public class Auction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String ownerId;

	public Date date;

	public String id;

	public int highestBid;

	public String highestBidderId;

	public String itemId;

	public Auction() {

	}

	public Auction(String id, String ownerId, Date date, int highestBid, String highestBidderId, String itemId) {
		this.date=date;
		this.id=id;
		this.ownerId=ownerId;
		this.highestBid=highestBid;
		this.highestBidderId=highestBidderId;
		this.itemId=itemId;
		
	}
}

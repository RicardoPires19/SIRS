
import java.io.Serializable;
import java.util.*;

public class Auction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ownerId;

	private String name;

	private Date date;

	private String data;

	private String id;

	private int highestBid;

	private String highestBidderId;

	private String itemId;

	public Auction() {

	}

	public Auction(String id, String ownerId, Date date, int highestBid, String highestBidderId, String itemId) {
		this.date = date;
		this.id = id;
		this.ownerId = ownerId;
		this.highestBid = highestBid;
		this.highestBidderId = highestBidderId;
		this.itemId = itemId;

	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(int highestBid) {
		this.highestBid = highestBid;
	}

	public String getHighestBidderId() {
		return highestBidderId;
	}

	public void setHighestBidderId(String highestBidderId) {
		this.highestBidderId = highestBidderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "{ownerId" + ":" + ownerId + "," + " name" + ":" + name + "," + " date" + ":" + date + "," + " data"
				+ ":" + data + "," + " id" + ":" + id + "," + " highestBid" + ":" + highestBid + ","
				+ " highestBidderId" + ":" + highestBidderId + "," + " itemId" + ":" + itemId + "}";
	}
}

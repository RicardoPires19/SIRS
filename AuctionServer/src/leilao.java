import java.sql.Date;

public class leilao {
	private int id;
	private int ownerId;
	private int hBidderId;
	private int hBid;
	private Date endDate;
	private int itemId;
	
	
	public leilao(int id, int ownerId, int hBidderId, int hBid, Date endDate, int itemId) {
		super();
		this.id = id;
		this.ownerId = ownerId;
		this.hBidderId = hBidderId;
		this.hBid = hBid;
		this.endDate = endDate;
		this.itemId = itemId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}


	public int gethBidderId() {
		return hBidderId;
	}


	public void sethBidderId(int hBidderId) {
		this.hBidderId = hBidderId;
	}


	public int gethBid() {
		return hBid;
	}


	public void sethBid(int hBid) {
		this.hBid = hBid;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
	
}

import java.io.Serializable;
import java.sql.Date;

public class leilao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int ownerId;
	private int hBidderId;
	private int hBid;
	private Date endDate;
	private String itemDescription;
	
	public leilao(){}
	
	public leilao(int id, int ownerId, int hBidderId, int hBid, Date endDate, String itemDescription) {
		super();
		this.id = id;
		this.ownerId = ownerId;
		this.hBidderId = hBidderId;
		this.hBid = hBid;
		this.endDate = endDate;
		this.itemDescription = itemDescription;
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


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
	
}

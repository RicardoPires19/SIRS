import java.io.Serializable;
import java.util.List;

public class Auctions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Auction> auctions;

	public List<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
	public Auctions(List<Auction> auctions) {
		this.auctions = auctions;

	}
	public Auctions(Auction[] auctions){
		
	}
	public Auctions(){}
	@Override
	public String toString() {
		String aux ="{ Auctions :[" ;
		int counter=0;
		for(Auction a : auctions){
			counter++;
			if(counter==auctions.size())
			aux+=a.toString();
			else
				aux+=a.toString()+",";
			
		}
		aux+="]}";
		
		
		 return aux;
	}
}

import java.io.Serializable;
import java.util.List;

public class Auctions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<leilao> auctions;

	public List<leilao> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<leilao> auctions) {
		this.auctions = auctions;
	}
	public Auctions(List<leilao> auctions) {
		this.auctions = auctions;

	}
	public Auctions(leilao[] auctions){
		
	}
	public Auctions(){}
	@Override
	public String toString() {
		String aux ="{ Auctions :[" ;
		int counter=0;
		for(leilao a : auctions){
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

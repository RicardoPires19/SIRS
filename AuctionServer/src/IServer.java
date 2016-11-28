import javax.ws.rs.core.Response;

public interface IServer {
	
	public Response Regist(String firsthName,String SurName,String passWord,String email);
	
	public Response Loggin(String email,String passWord);
	
	public Response CreatAuction(int ownerID,String date,int ItemID );

}

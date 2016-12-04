
/**
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.StringEscapeUtils;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

@Path("/AuctionServer")
public class Server {

	/**
	 * @param args
	 */
	private static final SQLProcedures queries = new SQLProcedures(); 
	private static final File KEYSTORE = new File("./AuctionServer.jks");
	private static final char[] JKS_PASSWORD = "e4HutkkfcHR4aj8vEA8UrzUzGm3fswHbTvxrXu3A".toCharArray();
	private static final char[] KEY_PASSWORD = "2pQAkKWfq7v2VM4Re4aJVXLw3YvbjJUBc9Veq5cu".toCharArray();
	private static final String PUBLIC_PW = "jBtp6AAsP96rPWjECoCcA==";
	private static String myURL;

	public static void main(String[] args) throws Exception {
		int port = args.length > 0 ? Integer.parseInt(args[0]) : 9000;
		System.out.println("Server runing on port: " + port);

		InetAddress s = localhostAddress();
		// myURL= String.format("http://%s:%s/",s.getCanonicalHostName(),port);
		myURL = String.format("https://%s:%s/", "localhost", port);
		// myURL= String.format("http://127.0.0.0:%s/",port);
		System.out.println("Server url: " + myURL);
		URI baseUri = UriBuilder.fromUri(myURL).build();
		ResourceConfig config = new ResourceConfig();

		config.register(Server.class);
		config.register(CORSFilter.class);

		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		KeyStore keyStore = KeyStore.getInstance("JKS");
		try (InputStream is = new FileInputStream(KEYSTORE)) {
			keyStore.load(is, JKS_PASSWORD);
		}
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(keyStore, KEY_PASSWORD);
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(keyStore);
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config, sslContext);

		System.err.println("REST Server ready... ");

	}

	/**
	 * 
	 * @return Localhost real adress
	 */
	private static InetAddress localhostAddress() {
		try {
			try {
				Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
				while (e.hasMoreElements()) {
					NetworkInterface n = e.nextElement();
					Enumeration<InetAddress> ee = n.getInetAddresses();
					while (ee.hasMoreElements()) {
						InetAddress i = ee.nextElement();
						if (i instanceof Inet4Address && !i.isLoopbackAddress())
							return i;
					}
				}
			} catch (SocketException e) {
				// do nothing
			}
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	/**
	 * -------------------------------------------------------------------------------------------------------------
	 * -------------------------------------------------------------------------------------------------------------
	 * -------------------------------Methods available for web
	 * saite--------------------------------------------------
	 * -------------------------------------------------------------------------------------------------------------
	 * -------------------------------------------------------------------------------------------------------------
	 */

	@GET
	@Path("/Test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response Teste() {

		System.out.println("teste server");
		Response rep = Response.ok("SUCESSO").build();
		// rep.getHeaders().add("Access-Control-Allow-Origin",
		// "https://localhost");
		return rep;
	}

	@POST
	@Path("/User")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Regist(String user) {

		JSONParser parser = new JSONParser();
		JSONObject res;
		Response rep = null;
		try {
			res = (JSONObject) parser.parse(user);

			String firstName = StringEscapeUtils.escapeHtml4((String) res.get("firstName"));
			String surName =StringEscapeUtils.escapeHtml4((String) res.get("surName"));
			String email =StringEscapeUtils.escapeHtml4((String) res.get("email"));
			String passWord = StringEscapeUtils.escapeHtml4((String) res.get("passWord"));
			String nib = StringEscapeUtils.escapeHtml4((String) res.get("nib"));

			firstName=StringEscapeUtils.escapeHtml4(firstName);
			// ...
			System.out.println("Resgist User: " + firstName + " " + surName + " " + email + " " + passWord + " " + nib);

			// stattus =3 email já existe
			// status =4 nib já existe; url/?id=value

			rep = Response.status(200).build();
		} catch (ParseException e) {

			rep = Response.status(415).build();
		}
		return rep;
	}

	@POST
	@Path("/Loggin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loggin(String loggin) {

		JSONParser parser = new JSONParser();
		JSONObject res;
		Response rep = null;
		try {
			res = (JSONObject) parser.parse(loggin);
			
			String email =StringEscapeUtils.escapeHtml4((String) res.get("email"));
			String passWord = StringEscapeUtils.escapeHtml4((String) res.get("passWord"));
			System.out.println("Loggin User unescaped: " + email + " " + passWord);
			System.out.println("Loggin User escaped: " + email + " " + passWord);

			// acess BD to check if exists
			// userName nao existe = 1
			// password errada =2
			rep = Response.status(200).build();
		} catch (ParseException e) {

			rep = Response.status(415).build();
		}

		return rep;
	}

	@GET
	@Path("/Auction")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListAuctions() {
		// returna lista de leilloes
		System.out.println("resquest leiloes");
		//Auction auction = new Auction("1", "1", new Date(System.currentTimeMillis()), 10, "2", "1");


		Auction a1=new Auction();
		a1.setName("Computador");
		a1.setHighestBid(200);
		a1.setData("09/12/2016");
		a1.setId("1");
		
		Auction a2=new Auction();
		a2.setName("Telemovel");
		a2.setHighestBid(150);
		a2.setData("08/12/2016");
		a2.setId("2");
		
		Auction a3=new Auction();
		a3.setName("Tablet");
		a3.setHighestBid(175);
		a3.setData("07/12/2016");
		a3.setId("3");
		List<Auction> l = new LinkedList<Auction>();
		l.add(a1);
		l.add(a2);
		l.add(a3);
		Auctions teste= new Auctions();
		teste.setAuctions( l);
		
		 ObjectMapper mapper = new ObjectMapper(); 
		 String json=null;
		try {
			json = mapper.writeValueAsString(teste);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(json);
//		String[] teste = new String[3];
//		teste[0]="Computador&200&09/12/2016";
//		teste[1]="Telemovel&150&08/12/2016";
//		teste[2]="Tablet&175&07/12/2016";
		System.out.println(json);
		Response res = Response.ok(json).build();
		return res;
	}
	
	 @POST
	 @Path("/Auction/bid")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response getAuction(String bid) {
	
		 
		 JSONParser parser = new JSONParser();
			JSONObject res;
			Response rep = null;
			try {
				res = (JSONObject) parser.parse(bid);
				
				String auctionId =StringEscapeUtils.escapeHtml4((String) res.get("id"));
				String bidValue = StringEscapeUtils.escapeHtml4((String) res.get("bid"));
				
				 System.out.println("leilao id= "+ auctionId +" e valor da bid "+ bidValue);
				
				rep = Response.status(200).build();
			} catch (ParseException e) {

				rep = Response.status(415).build();
			}
		return rep;
	 }
	 
	//
	// @POST
	// @Path("/Auction")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response createAuction() {
	// //returna lista de leilloes
	// System.out.println("resquest leiloes");
	// User u = new User("paulo", "anjos", "pass", "mail");
	// User u2 = new User("paulo", "anjos", "pass", "mail");
	// User[] users = new User[2];
	// users[0] = u;
	// users[1] = u2;
	// Response res = Response.ok(users).build();
	// res.getHeaders().add("Access-Control-Allow-Origin", "https://localhost");
	// return res;
	// }
	//
	
	//
	// @GET
	// @Path("/Auction/{id}/bid")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response bid(@PathParam("id") String auctionID ) {
	// //return um leilao
	//status =5 bid mais baixa que a curent
	// Response res = Response.ok().build();
	// res.getHeaders().add("Access-Control-Allow-Origin", "https://localhost");
	// return res;
	// }

	// @POST
	// @Path("/User")
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response Regist(String u) {
	//
	// System.out.println("teste Resgist recebeu "+u +" como parametro");
	//
	// JSONParser parser = new JSONParser();
	// JSONObject res =null;
	// try {
	// res = (JSONObject) parser.parse(u);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// JSONObject json = ((JSONObject) res.get("data"));
	// String name = (String)json.get("firsthName");
	//
	// System.out.println("teste Resgist recebeu "+name+" e "+" como
	// parametro");
	// Response rep =Response.status(200).build();
	// //rep.getHeaders().add("Access-Control-Allow-Origin", "*");
	// return rep;
	// }

}

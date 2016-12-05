
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.commons.lang3.*;

import java.sql.*;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

@Path("/AuctionServer")
public class Server {

	/**
	 * @param args
	 */
	private static final File KEYSTORE = new File("./keystore.jks");
	private static final char[] JKS_PASSWORD = "e4HutkkfcHR4aj8vEA8UrzUzGm3fswHbTvxrXu3A".toCharArray();
	private static final char[] KEY_PASSWORD = "2pQAkKWfq7v2VM4Re4aJVXLw3YvbjJUBc9Veq5cu".toCharArray();
	private static String myURL;
	private static final int DELTA_TIME = 2 * 60 * 1000; // 2 min?
	private static final int SESSION_TIMEOUT = 30 * 60 * 1000; // 30 min?
	private static ConcurrentHashMap<String, Token> usersLoggedIn;
	private static SQLProcedures bd;
	private static final int EMAIL_EXISTS = 3;
	private static final int EMAIL_NOT_EXISTS = 1;
	private static final int WRONG_PASS = 2;
	private static final int LOW_BID = 5;
	private static final int BAD_TOKEN = 6;
	private static final int AUCTION_NOT_EXISTS = 8;
	private static Random random;

	public static void main(String[] args) throws Exception {
		int port = args.length > 0 ? Integer.parseInt(args[0]) : 9000;
		System.out.println("Server runing on port: " + port);
		java.nio.file.Path currentRelativePath = Paths.get("");
		String s1 = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s1);
		//InetAddress s = localhostAddress();
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

		usersLoggedIn = new ConcurrentHashMap<String, Token>();
		bd = new SQLProcedures();
		random = new Random();

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

	@POST
	@Path("/User")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Regist(String user) {

		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(user);
			if (!checkFreshness(json))
				return Response.status(400).build();
			String email = StringEscapeUtils.escapeHtml4((String) json.get("email"));

			if (bd.getUserByEmail(email) != null)
				return Response.status(EMAIL_EXISTS).build();

			String firstName = StringEscapeUtils.escapeHtml4((String) json.get("firstName"));
			String surName = StringEscapeUtils.escapeHtml4((String) json.get("surName"));
			String passWord = StringEscapeUtils.escapeHtml4((String) json.get("passWord"));

			System.out.println("Resgist User: " + firstName + " " + surName + " " + email + " " + passWord);
			bd.insertUser(firstName, surName, passWord, email);

			return GenToken(email);
		} catch (Exception e1) {
			return Response.status(400).build();
		}

	}

	@POST
	@Path("/Loggin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loggin(String loggin) {

		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(loggin);
			if (!checkFreshness(json))
				return Response.status(400).build();
			String email = StringEscapeUtils.escapeHtml4((String) json.get("email"));
			String passWord = StringEscapeUtils.escapeHtml4((String) json.get("passWord"));
			System.out.println("Loggin User unescaped: " + email + " " + passWord);
			System.out.println("Loggin User escaped: " + email + " " + passWord);

			User u = bd.getUserByEmail(email);
			if (u == null)
				return Response.status(EMAIL_NOT_EXISTS).build();
			if (!u.getPassWord().equals(passWord))
				return Response.status(WRONG_PASS).build();
			return GenToken(email);
		} catch (Exception e) {

			return Response.status(400).build();
		}

	}

	@GET
	@Path("/Auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ListAuctions(String params) {
		// returna lista de leilloes
		System.out.println("resquest leiloes");

		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(params);
			String email = StringEscapeUtils.escapeHtml4((String) json.get("email"));
			if (!checkFreshness(json))
				return Response.status(400).build();
			if (bd.getUserByEmail(email) == null)
				return Response.status(EMAIL_NOT_EXISTS).build();

			if (!ValidToken(json, email))
				return Response.status(BAD_TOKEN).build();
			Auctions auctions = new Auctions(bd.listLeiloes());

			ObjectMapper mapper = new ObjectMapper();
			String auctionsJson = mapper.writeValueAsString(auctions);

			System.out.println(auctionsJson);
			System.out.println(json);
			return Response.ok(auctionsJson).build();
		} catch (Exception e) {
			return Response.status(400).build();
		}

	}

	@POST
	@Path("/Auction/bid")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBid(String params) {

		JSONParser parser = new JSONParser();
		JSONObject json;

		try {
			json = (JSONObject) parser.parse(params);
			String email = StringEscapeUtils.escapeHtml4((String) json.get("email"));
			if (!checkFreshness(json))
				return Response.status(400).build();
			User u = bd.getUserByEmail(email);
			if (u == null)
				return Response.status(EMAIL_NOT_EXISTS).build();

			if (!ValidToken(json, email))
				return Response.status(BAD_TOKEN).build();

			String auctionId = StringEscapeUtils.escapeHtml4((String) json.get("id"));
			String bidValue = StringEscapeUtils.escapeHtml4((String) json.get("bid"));
			int leilaoId = Integer.parseInt(auctionId);
			leilao l = bd.getAuctionById(leilaoId);
			if (l == null)
				return Response.status(AUCTION_NOT_EXISTS).build();

			int bvalue = Integer.parseInt(bidValue);

			if (bvalue <= l.gethBid())
				return Response.status(LOW_BID).build();
			bd.updateBid(leilaoId, u.getId(), bvalue);

			System.out.println("leilao id= " + auctionId + " e valor da bid " + bidValue + "user id" + u.getId());

			return Response.status(200).build();
		} catch (Exception e) {

			return Response.status(400).build();
		}

	}

	@POST
	@Path("/Auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddAuction(String params) {
		JSONParser parser = new JSONParser();
		JSONObject json;

		try {
			json = (JSONObject) parser.parse(params);
			String email = StringEscapeUtils.escapeHtml4((String) json.get("email"));
			if (!checkFreshness(json))
				return Response.status(400).build();
			User u = bd.getUserByEmail(email);
			if (u == null)
				return Response.status(EMAIL_NOT_EXISTS).build();
			if (!ValidToken(json, email))
				return Response.status(BAD_TOKEN).build();

			String itemDescription = StringEscapeUtils.escapeHtml4((String) json.get("ItemDescription"));
			int baseBid = Integer.parseInt((String) json.get("bid"));
			String closingtime = (String) json.get("closingtime");

			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
			// date java util
			Date aux = (Date) sdf.parse(closingtime);
			System.out.println();
			// date sql
			Date date = new Date(aux.getTime());
			bd.insertLeilao(u.getId(), u.getId(), baseBid, date, itemDescription);

			return Response.status(200).build();

		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	@POST
	@Path("/Auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Loggout(String params) {
		JSONParser parser = new JSONParser();
		JSONObject json;

		try {
			json = (JSONObject) parser.parse(params);
			String email = StringEscapeUtils.escapeHtml4((String) json.get("email"));
			if (!checkFreshness(json))
				return Response.status(400).build();
			User u = bd.getUserByEmail(email);
			if (u == null)
				return Response.status(EMAIL_NOT_EXISTS).build();
			if (!ValidToken(json, email))
				return Response.status(BAD_TOKEN).build();
			usersLoggedIn.remove(email);
			return Response.status(200).build();

		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	private Response GenToken(String email) throws JsonProcessingException {
		Token token = new Token(random.nextInt(Integer.MAX_VALUE), System.currentTimeMillis());
		usersLoggedIn.put(email, token);
		ObjectMapper mapper = new ObjectMapper();
		String tokenJson = null;
		tokenJson = mapper.writeValueAsString(token);
		return Response.ok(tokenJson, MediaType.APPLICATION_JSON).build();
	}

	private boolean checkFreshness(JSONObject res) {

		long clientTime = Long.valueOf((String) res.get("time")).longValue();
		long serverTime = System.currentTimeMillis();
		return Math.abs(serverTime - clientTime) < DELTA_TIME;

	}

	private boolean ValidToken(JSONObject json, String email) {
		JSONObject token = (JSONObject) json.get("token");
		int randomValue = Integer.valueOf((String) token.get("randomNum"));
		long tokenTime = Long.valueOf((String) token.get("timeStamp")).longValue();

		Token cToken = new Token(randomValue, tokenTime);

		Token sToken = usersLoggedIn.get(email);
		boolean valid = cToken.equals(sToken) && checkTokenTimeStamp(sToken.getTimeStamp());
		if (!valid)
			usersLoggedIn.remove(email);
		return valid;

	}

	private boolean checkTokenTimeStamp(long tokenTime) {
		long serverTime = System.currentTimeMillis();
		return Math.abs(serverTime - tokenTime) < SESSION_TIMEOUT;
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
	// status =5 bid mais baixa que a curent
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
	// Auction a1 = new Auction();
	// a1.setName("Computador");
	// a1.setHighestBid(200);
	// a1.setData("09/12/2016");
	// a1.setId("1");
	//
	// Auction a2 = new Auction();
	// a2.setName("Telemovel");
	// a2.setHighestBid(150);
	// a2.setData("08/12/2016");
	// a2.setId("2");
	//
	// Auction a3 = new Auction();
	// a3.setName("Tablet");
	// a3.setHighestBid(175);
	// a3.setData("07/12/2016");
	// a3.setId("3");
	// List<Auction> l = new LinkedList<Auction>();
	// l.add(a1);
	// l.add(a2);
	// l.add(a3);
	// Auctions teste = new Auctions();
	// teste.setAuctions(l);

	// }

}

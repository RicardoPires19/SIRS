
/**
 * 
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;
import com.sun.net.httpserver.HttpServer;

@Path("/AuctionServer")
public class Server {

	/**
	 * @param args
	 */
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
	public Response Regist(final User u) {

		System.out.println("Resgist User: " + u.firsthName+ " "+ u.surName+ " "+u.email+ " "+u.passWord);
		Response rep = Response.status(200).build();
		return rep;
	}
	
	@POST
	@Path("/Loggin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loggin(@QueryParam("email") String email,String passWord) {

		System.out.println("Loggin User: " + email+" "+passWord);
		Response rep = Response.status(200).build();
		return rep;
	}
	
//
//	@GET
//	@Path("/Auction")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response ListAuctions() {
//		//returna lista de leilloes
//		System.out.println("resquest leiloes");
//		User u = new User("paulo", "anjos", "pass", "mail");
//		User u2 = new User("paulo", "anjos", "pass", "mail");
//		User[] users = new User[2];
//		users[0] = u;
//		users[1] = u2;
//		Response res = Response.ok(users).build();
//		res.getHeaders().add("Access-Control-Allow-Origin", "https://localhost");
//		return res;
//	}
//	
//	@POST
//	@Path("/Auction")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response createAuction() {
//		//returna lista de leilloes
//		System.out.println("resquest leiloes");
//		User u = new User("paulo", "anjos", "pass", "mail");
//		User u2 = new User("paulo", "anjos", "pass", "mail");
//		User[] users = new User[2];
//		users[0] = u;
//		users[1] = u2;
//		Response res = Response.ok(users).build();
//		res.getHeaders().add("Access-Control-Allow-Origin", "https://localhost");
//		return res;
//	}
//	
//	@GET
//	@Path("/Auction/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAuction(@PathParam("id") String auctionID ) {
//		//return um leilao
//		Response res = Response.ok().build();
//		res.getHeaders().add("Access-Control-Allow-Origin", "https://localhost");
//		return res;
//	}
//	
//	@GET
//	@Path("/Auction/{id}/bid")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response bid(@PathParam("id") String auctionID ) {
//		//return um leilao
//		Response res = Response.ok().build();
//		res.getHeaders().add("Access-Control-Allow-Origin", "https://localhost");
//		return res;
//	}
	
	

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

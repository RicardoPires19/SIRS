
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


public class ClientTestSever {
	private static WebTarget target;
	private static Client client;
	private static String url="https://desktop-85c51k9:9000/";

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		SSLContext sc = SSLContext.getInstance("TLSv1");

		TrustManager[] trustAllCerts = { new InsecureTrustManager() };
		sc.init(null, trustAllCerts, new java.security.SecureRandom());

		client = ClientBuilder.newBuilder()
				.hostnameVerifier(new InsecureHostnameVerifier())
				.sslContext(sc)
				.build();
		target = client.target(UriBuilder.fromUri(url).build());
		
		System.out.println("cliente tenta servidpor");
		String s=target.path(String.format("/AuctionServer/Test")).request()
		.accept(MediaType.APPLICATION_JSON).get(String.class);
		
		System.out.println("SErver respondeu com: "+s);
		

	}
	static public class InsecureHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
	static public class InsecureTrustManager implements X509TrustManager {
	    @Override
	    public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
	    }

	    @Override
	    public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
	    	Arrays.asList( chain ).forEach( i -> {
	    		System.err.println( "type: " + i.getType() + "from: " + i.getNotBefore() + " to: " + i.getNotAfter() );
	    	});
	    }

	    @Override
	    public X509Certificate[] getAcceptedIssuers() {
	        return new X509Certificate[0];
	    }
	}

}

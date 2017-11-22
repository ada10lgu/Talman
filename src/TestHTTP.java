import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHTTP {
	public static void main(String[] args) throws IOException {

		URL ur = new URL("http://dev-mezz.dsek.se/app/search.php?stil=dat15dah&key=lars");
		HttpURLConnection yc = (HttpURLConnection) ur.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();

	}
}

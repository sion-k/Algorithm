import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		if (S.matches(".*U.*C.*P.*C.*")) {
			System.out.println("I love UCPC");
		} else {
			System.out.println("I hate UCPC");
		}
	}

}

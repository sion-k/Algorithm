import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String[] S = new String[N];
		for (int i = 0; i < N; i++) 
			S[i] = st.nextToken();
		char comp = S[0].charAt(0);
		boolean ok = true; 
		for (int i = 1; i < N; i++) 
			if (S[i].charAt(0) != comp) {
				ok = false; break;
			}
		System.out.println(ok ? 1 : 0);
	}
    
}

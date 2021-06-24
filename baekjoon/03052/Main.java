import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] remain = new boolean[42];
		for (int i = 0; i < 10; i++) {
			int N = Integer.parseInt(br.readLine());
			remain[N % 42] = true;
		}
		int cnt = 0;
		for (int i = 0; i < 42; i++) {
			if (remain[i]) {cnt++;}
		}
		System.out.println(cnt);
	}

}

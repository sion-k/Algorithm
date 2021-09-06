import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = N - 1;
		long sum = N;
		int j = 0;
		for (int i = 1; i <= K; i = j + 1) {
			j = K / (K / i);
			sum += (long)(K / i) * (j - i + 1);
		}
		System.out.println(sum);
	}

}

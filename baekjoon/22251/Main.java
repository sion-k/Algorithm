import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] LED = 
	{ 0b1110111, 
	0b0010010, 
	0b1011101, 
	0b1011011, 
	0b0111010, 
	0b1101011, 
	0b1101111, 
	0b1010010, 
	0b1111111, 
	0b1111011 };
	
	static int[][] adj = new int[10][10];
	
	static {
		for (int i = 0; i <= 9; i++)
			for (int j = i + 1; j <= 9; j++)
				adj[i][j] = adj[j][i] = Integer.bitCount(LED[i] ^ LED[j]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int cnt = 0;
		for (int Y = 1; Y <= N; Y++) {
			// X -> Y를 만들 수 있는지 확인
			int cost = 0; 
			int temp1 = X; int temp2 = Y;
			for (int i = 0; i < K; i++) {
				cost += adj[X % 10][Y % 10];
				X /= 10; Y /= 10;
			}
			X = temp1; Y = temp2; 
			if (1 <= cost && cost <= P) cnt++;
		}
		System.out.println(cnt);
	}

}

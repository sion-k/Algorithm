import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] S = new int[N][4];
		for (int i = 0; i < N; i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) S[i][j] = Integer.parseInt(st.nextToken());
		}
		int[] A = new int[N * N]; int[] B = new int[N * N];
		int index = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				A[index] = S[i][0] + S[j][1];
				B[index] = S[i][2] + S[j][3];
				index++;
			}
		Arrays.sort(B);
		long cnt = 0;
		int lo = 0; int hi = B.length;
		int mid = 0;
		int s = 0; int e = B.length;
		for (int i = 0; i < A.length; i++) {
			lo = 0; hi = B.length;
			while (lo < hi) {
				mid = (lo + hi) >> 1;
				if (B[mid] <= -A[i]) lo = mid + 1;
				else hi = mid;
			}
			e = lo;
			lo = 0; hi = B.length;
			while (lo < hi) {
				mid = (lo + hi) >> 1;
				if (B[mid] >= -A[i]) hi = mid;
				else lo = mid + 1;
			}
			s = lo;
			cnt += (e - s);
		}
		System.out.println(cnt);
	}
	
}

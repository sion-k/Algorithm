import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; static int[] S;
	
	// i번째 레슨부터 담기 시작할 때,
	// 전체 레슨을 size크기의 m개의 블루레이에 담을 수 있는지 반환
	static boolean f(int size, int m) {
		int capacity = size; m--;
		for (int i = 0; i < N; i++) {
			if (S[i] > size) return false;
			if (capacity >= S[i]) capacity -= S[i];
			else if (m != 0) {capacity = size - S[i]; m--;}
			else return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		S = new int[N];
		int lo = 0; int hi = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			hi = Math.max(hi, S[i]);
		}
		hi *= N;
		// f(lo) == false && f(hi) == true인 hi를 반환
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if (f(mid, M)) hi = mid;
			else lo = mid;
		}
		System.out.println(hi);
	}
	
}

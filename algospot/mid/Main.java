import java.io.*;
import java.util.*;

public class Main {
	
	static int getMid(ArrayList<Integer> S) {
		int mid = S.size() / 2;
		if (S.size() % 2 == 0) return (S.get(mid - 1) + S.get(mid)) / 2;
		else return S.get(mid);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> Y = new ArrayList<>();
		ArrayList<Integer> X = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Y.add(Integer.parseInt(st.nextToken()));
			X.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(Y); Collections.sort(X);
		int y = getMid(Y); int x = getMid(X);
		System.out.printf("%d %d\n", y, x);
	}

}

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] S = new int[N];
		for (int i = 0; i < N; i++) {
			S[i] = sc.nextInt();
		}
		sc.close();
		int sum = 0;
		for (int i = N - 2; i >= 0; i--) {
			if (S[i] >= S[i + 1]) {
				int dec = S[i] - S[i + 1] + 1;
				S[i] -= dec;
				sum += dec;
			}
		}
		System.out.println(sum);
	}

}

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] L = new int[N];
		for (int i = 0; i < N; i++) {
			L[i] = sc.nextInt();
		}
		sc.close();

		LinkedList<Integer> S = new LinkedList<>();
		for (int i = N - 1; i >= 0; i--) {
			S.add(L[i], i + 1);
		}

		for (int i = 0; i < N; i++) {
			System.out.print(S.get(i));
			if (i != N - 1) {
				System.out.print(" ");
			}
		}
	}

}

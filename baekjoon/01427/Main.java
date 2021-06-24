import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] N = sc.nextLine().toCharArray();
		sc.close();
		Arrays.sort(N);
		for (int i = N.length - 1; i >=0; i--) {
			System.out.print(N[i]);
		}
		System.out.println();
	}

}

import java.util.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Main {
	
	private static int ptole(int a, int b, int c) {
		return ((int) (sqrt((pow(a, 2) - pow(c, 2)) * (pow(a, 2) - pow(b, 2)))) - b * c) / a;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		if (pow(a, 2) <= pow(b, 2) + pow(c, 2)) {
			System.out.println("-1");
			sc.close();
			return;
		}
		System.out.println(ptole(a, b, c));
		sc.close();
	}

}

import java.util.Scanner;

public class Main {
	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(); int b = sc.nextInt();
		sc.close();
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		int gcd = gcd(a, b);
		System.out.println(gcd);
		System.out.println(a * b / gcd);
		
	}

}

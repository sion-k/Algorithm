import java.util.Scanner;

public class Main {
	
	static int sn(int con) {
		int ret = con;
		while(con != 0) {
			ret += con % 10;
			con /= 10;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		int constructor = 0;
		for (int i = 1; i < N; i++) {
			if(sn(i) == N) {constructor = i; break;}
		}
		System.out.println(constructor);
	}
}

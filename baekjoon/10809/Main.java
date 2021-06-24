import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();
		sc.close();
		int[] ch = new int[26];
		Arrays.fill(ch, -1);
		for (int i = 0; i < S.length(); i++) {
			int pos = S.charAt(i) - 'a';
			if (ch[pos] == -1) {ch[pos] = i;}
		}
		
		for (int i = 0; i < ch.length; i++) {
			System.out.print(ch[i]);
			if(i != ch.length - 1) {System.out.print(" ");}
		}
	}
}

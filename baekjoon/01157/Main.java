import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] count = new int[26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if(ch >= 'a') {
				count[ch - 'a']++;
			} else {
				count[ch - 'A']++;
			}
		}
		br.close();
		
		int max = 0;
		for (int i = 0; i < 26; i++) {
			max = count[i] > count[max] ? i : max; 
		}
		
		for (int i = 0; i < 26; i++) {
			if (i == max) {continue;}
			if (count[i] == count[max]) {
				max = -1; break;
			}
		}
		
		if(max == -1) {
			System.out.println("?");
		} else {
			System.out.println((char)('A' + max));
		}
	}
	
}

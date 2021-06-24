import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = "";
		while(!(line = br.readLine()).equals(".")) {
			Stack<Character> st = new Stack<>();
			boolean stable = true;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (c == '(' || c == '[') {st.push(c);}
				else if (c == ')') {
					if (st.isEmpty() || st.pop() != '(') {
						stable = false; break;
					}
				} 
				else if (c == ']') {
					if (st.isEmpty() || st.pop() != '[') {
						stable = false; break;
					}
				}
			}
			if(stable && st.isEmpty()) {bw.write("yes"); bw.newLine();}
			else {bw.write("no"); bw.newLine();}
		}
		br.close();
		bw.close();
	
	}

}

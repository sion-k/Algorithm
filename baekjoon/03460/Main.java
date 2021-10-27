import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            String S = Integer.toBinaryString(Integer.parseInt(br.readLine()));
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(S.length() - i - 1) == '1') bw.append(i).append(" ");
            }
            bw.append("\n");
        }
        System.out.print(bw);
    }

}

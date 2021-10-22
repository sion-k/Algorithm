import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            long N = Long.parseLong(br.readLine());
            bw.append(N * 23).append("\n");
        }
        System.out.print(bw);
    }

}

package baekjoon.p1003;

import java.io.*;

public class Main {
  private static long[][] cache = new long[41][2];
  static {
    cache[0][0] = 1; cache[0][1] = 0;
    cache[1][0] = 0; cache[1][1] = 1;
    for (int i = 2; i < 41; i++) {
      cache[i][0] = cache[i - 1][0] + cache[i - 2][0];
      cache[i][1] = cache[i - 1][1] + cache[i - 2][1];  
    }
  }

  public static void main(String[] args) throws
  IOException {
    BufferedReader br = new BufferedReader(
    new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(
    new OutputStreamWriter(System.out));
    
    int T = Integer.parseInt(br.readLine());
    
    for (int c = 0; c < T; c++) {
      int N = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      sb.append(cache[N][0]).
      append(" ").append(cache[N][1]);
      bw.write(sb.toString());
      bw.newLine();
    }
    br.close();
    bw.close();
  }

}

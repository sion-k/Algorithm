package baekjoon.p10162;

import java.util.*;

public class Main {
  private static int[] unit = {300, 60, 10};
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    sc.close();
    if (T % unit[2] != 0) {
      System.out.println(-1);
      return;
    }
    
    int[] pushed = new int[3];
    
    for (int u = 0; u < unit.length; u++){
      int q = 0;
      if ((q = T / unit[u]) > 0){
        T -= (unit[u] * q);
        pushed[u] += q;
      }
    }
    System.out.println(pushed[0] + " " + pushed[1] + " " + pushed[2]);
 }
}

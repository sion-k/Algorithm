package baekjoon.p1080;

import java.util.*;
public class Main {
  private static int[][] a;
  private static int[][] b;
  private static final int[][] d = 
  {
   {-1, -1}, {-1,  0}, {-1,  1},
   { 0, -1}, { 0,  0}, { 0,  1},
   { 1, -1}, { 1,  0}, { 1,  1}
  };
  
  private static void calc(int i, int j) {
    for (int n = 0; n < 9; n++) {
      int y = i + d[n][0];
      int x = j + d[n][1];
      a[y][x] = (a[y][x] == 0 ? 1 : 0);
    }
  }
  
  private static boolean isSame(){
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++) {
        if (a[i][j] != b[i][j]){return false;}
      }
    }
    return true;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();int m = sc.nextInt();
    sc.nextLine();
    a = new int[n][m];
    
    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      for (int j = 0; j < m; j++){
        a[i][j] = Integer.parseInt(line.charAt(j) +"");
      }
    }
    b = new int[n][m];
    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      for (int j = 0; j < m; j++){
        b[i][j] = Integer.parseInt(line.charAt(j) + "");
      }
    }  
    if (n < 3 || m < 3) {
      System.out.println(isSame()? 0 : -1);
    } else {
    
    int computed = 0;
    for (int i = 1; i < n - 1; i++){ 
      for (int j = 1; j < m - 1; j++){
        if (a[i - 1][j - 1] != b[i - 1][j - 1]) {
          calc(i, j);
          computed++;
        }
      }
    }
    System.out.println(isSame()? computed : -1);
    }
    sc.close();
  }
}

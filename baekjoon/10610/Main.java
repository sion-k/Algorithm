import java.util.*;
import java.io.*;

public class Main {
  private static char[] number;
  
  public static void main(String[] args) {
    BufferedReader br =
    new BufferedReader
    (new InputStreamReader(System.in));
    try {
      number = br.readLine().toCharArray();
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    Arrays.sort(number);
    int sum = 0;
    for (int i = 0; i < number.length; i++) {
      sum = sum + (number[i] - '0');
    }
    if (sum % 3 != 0 || number[0] != '0') {
      System.out.println("-1");
    } else {
      for (int i = number.length - 1; i >= 0; i--) {
        System.out.print(number[i]);
      }
      System.out.println();
    }
  }

}

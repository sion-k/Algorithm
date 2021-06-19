import java.util.*;

public class Main {
  private static double dis(int x1, int y1, int x2, int y2) {
    return Math.sqrt((int)Math.pow(x2 - x1, 2) + (int)Math.pow(y2 - y1, 2));
  }
  
  private static int posCase(int x1, int y1, int r1,
  int x2, int y2, int r2) {
    double d = dis(x1, y1, x2, y2);
    double sum = r1 + r2;
    double dif = (double)Math.abs(r1 - r2);
    
    if (Double.compare(d, 0) == 0) {
      return r1 == r2 ? -1 : 0;
    }
    if (Double.compare(d, dif) == -1) {return 0;}
    if (Double.compare(d, dif) == 0) {return 1;}
    if (Double.compare(d, dif) == 1 &&
    Double.compare(d, sum) == -1) {return 2;}
    if (Double.compare(d, sum) == 0) {return 1;}
    if (Double.compare(d, sum) == 1) {return 0;}
    return -1;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = Integer.parseInt(sc.nextLine());
    
    for (int c = 0; c < T; c++) {
      int x1 = sc.nextInt();
      int y1 = sc.nextInt();
      int r1 = sc.nextInt();
      int x2 = sc.nextInt();
      int y2 = sc.nextInt();
      int r2 = sc.nextInt();

      System.out.println(posCase(x1, y1, r1, x2, y2, r2));
      sc.close();
    }
  }

}

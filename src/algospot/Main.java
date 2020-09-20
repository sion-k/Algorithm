package ALGOSPOT;
import java.util.Scanner;

public class Main{
  int[] cache;
  int[] seq;
  
  public Main(int n){
    this.cache = new int[n];
    this.seq = new int[n];
  }
  private int lisLen(int start){
    int ret = cache[start];
    if(ret!=0) return ret;
    int max =1;
    for(int i=start+1;i<seq.length;i++)
      if(seq[i]>seq[start])
        max = max(max, lisLen(i)+1);
    return cache[start] = max;
  }
  
  private static int max(int a, int b){
    return a>b?a:b;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int cn = sc.nextInt();
    for(int i =0; i<cn; i++){
      Main a = new Main(sc.nextInt());
      
      for(int ni =0; ni<a.seq.length; ni++)
        a.seq[ni] = sc.nextInt();
        
      int max = 0;
      for(int si =0; si<a.seq.length; si++){
        max = max(max, a.lisLen(si));
      }
      System.out.println(max);
    }
    sc.close();
  }

}

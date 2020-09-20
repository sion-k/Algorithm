package ALGOSPOT;
import java.util.Scanner;

public class LIS {
  int[] cache;
  int[] seq;
  
  public LIS(int n){
    this.cache = new int[n];
    this.seq = new int[n];
  }
  private int lisLen(int start){
    if(start==seq.length-1) return 1;
    int ret = cache[start];
    if(ret!=0) return ret;
    int max =1;
    for(int i=start+1;i<seq.length;i++)
      if(seq[i]>seq[start])
        max = max(max, lisLen(i));
    return cache[start] = max +1;
  }
  
  private static int max(int a, int b){
    return a>b?a:b;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int cn = sc.nextInt();
    for(int i =0; i<cn; i++){
      LIS a = new LIS(sc.nextInt());
      for(int ni =0; ni<a.seq.length; ni++)
        a.seq[ni] = sc.nextInt();
      System.out.println(a.lisLen(0));
    }
    sc.close();
  }

}

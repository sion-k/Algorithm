import java.util.Scanner;

public class Main {
    private static int[] unit;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        unit = new int[n];
        for (int i = 0; i < n; i++)
            unit[i] = sc.nextInt();
            
        int min = 0;
        for (int u = n - 1; u >= 0; u--){
            if (k == 0) break;
            int q = 0;
            if ((q = k / unit[u]) > 0){
                k -= unit[u]*q;
                min += q;
            }
        }
        System.out.println(min);
        sc.close();
    }

}

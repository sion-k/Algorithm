package algospot.WILDCARD;

import java.util.Scanner;

public class Main {
  private static boolean matches(String rex, String str){
    if (rex.equals("*")){return true;}
    if (rex.equals("?"))
      return str.length() == 1;
    if (
    ((rex.length() == 1)&& 
    (rex.charAt(0) != '*')&&
    (rex.charAt(0) != '?'))||
    str.length() == 1
    ){
      return rex.equals(str); 
    }
    switch (rex.charAt(0)) {
      case '*':
        for (int i = 0; i < str.length(); i++){
          if (matches(rex.substring(1),
          str.substring(i)))
            return true;
        }
        return false;
      case '?':
        return matches(rex.substring(1),
        str.substring(1));
      default:
        return (rex.charAt(0) == str.charAt(0)) &&
        matches(rex.substring(1),
        str.substring(1));
    } 
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int c = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < c; i++) {
      String rex = sc.nextLine();
      int n =
      Integer.parseInt(sc.nextLine());
      for (int j = 0; j < n; j++){
        String str = sc.nextLine();
        if (matches(rex, str)){
          System.out.println(str);
        }
      }
    }
    sc.close();
  }

}

import java.util.*;
public class velha {
  public record ParI(int first, int second){}

  
  public static void drawMat(char[][] mat){
    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 3; j ++){
        if (j != 0) System.out.printf(" | ");
        System.out.print(mat[i][j]);
      }
      System.out.printf("\n");
      if (i != 2) System.out.println("-----------");
    }
  }
  public static void iniciarMat(char[][] mat){
    for (int i = 0; i < 3; i ++){
      Arrays.fill(mat[i], ' ');
    }
  }

  public static ParI IAjoga(char[][]mat){
    ParI par = new ParI(-1, -1);
    for (int i = 0; i < 3; i ++){
      for (int j = 0; j < 3; j ++){
        if (mat[i][j] == ' '){
          par = new ParI(i, j);
          return par;
        }
      }
    }
    return par;
  }

  public static boolean jogGanha(char[][] mat, ParI par, char p){
    int x = par.first();
    int y = par.second();
    if (mat[x][0] == p && mat[x][1] == p && mat[x][2] == p) return true;

    if (mat[0][y] == p && mat[1][y] == p && mat[2][y] == p) return true;

    if (x == y) {
        if (mat[0][0] == p && mat[1][1] == p && mat[2][2] == p) return true;
    }

    if (x + y == 2) {
        if (mat[0][2] == p && mat[1][1] == p && mat[2][0] == p) return true;
    }
    return false;
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    char[][] mat = new char[3][3];
    iniciarMat(mat);
    drawMat(mat);
    boolean vezJog = true;
    while (true){
      if (vezJog){
        System.out.println("Vez do jogador: ");
        int x = sc.nextInt(), y = sc.nextInt();
        x--; y--;
        while (mat[x][y] != ' '){
          System.out.printf("Posição ocupada\n");
          System.out.println("Vez do jogador: ");
          x = sc.nextInt(); y = sc.nextInt();
          x--; y--;
        }
        mat[x][y] = 'O';
        drawMat(mat);
        ParI par = new ParI(x, y);
        if (jogGanha(mat, par, 'O')){
          System.out.println("Jogador ganhou!");
          break;
        }
        vezJog = false;
      }
      else{
        ParI par = IAjoga(mat);
        mat[par.first()][par.second()] = 'X';
        drawMat(mat);
        if (jogGanha(mat, par, 'X')){
          System.out.println("CPU ganhou!");
          break;
        }
        vezJog = true;
      }
    }
  }
}

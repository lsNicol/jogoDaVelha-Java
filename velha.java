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

  public static boolean jogGanha(char[][] mat, int[] par, char p){
    int x = par[0], y = par[1];
    boolean[] ganhou = new boolean[]{true, true, true, true};
    int I = 0;
    for (char i : mat[x]){
      if (i != p){
        ganhou[I] = false;
        break;
      }
    }
    if (ganhou[I]) return true;
    I++;
    for (int i = 0; i < 3; i ++){
      if (mat[i][y] != p){
        ganhou[I] = false;
        break;
      }
    }
    if (ganhou[I]) return true;
    I++;
    if (x == y){
      for (int i = 0; i < 3; i ++){
        if (mat[i][i] != p){
          ganhou[I] = false;
          break;
        }
      }
    if (ganhou[I]) return true;
    }
    I++;
    if (x+y == 2){
      for (int i = 0; i < 3; i ++){
        if (mat[i][2-i] != p){
          ganhou[I] = false;
          break;
        }
      }
    if (ganhou[I]) return true;
    }
    I++;
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

import java.util.Scanner;
import java.util.Random;
public class Problem1 {
    public static void main(String[] args){
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    String[] availableMoves = {"Rock", "Paper", "Scissors"};
    int wins = 0;
    int losses = 0;
    int draws = 0;
    System.out.print("Enter Number of Rounds: ");
    int N = scanner.nextInt();
    scanner.nextLine();
    for(int i =0; i<N; i++){
        System.out.print("Enter your move (Rock, Paper, Scissors): ");
        String playerMove = scanner.nextLine();
        String computerMove = availableMoves[random.nextInt(3)];
        System.out.println("Computer move: " + computerMove);
        if (computerMove.equals(playerMove)){
            System.out.println("Draw");
            draws++;
        }
        if (computerMove.equals("Rock")&& playerMove.equals("Scissors") || computerMove.equals("Paper") && playerMove.equals("Rock") || computerMove.equals("Scissors") && playerMove.equals("Paper")){
            System.out.println("You Lose");
            losses++;
        }
        if (computerMove.equals("Rock") && playerMove.equals("Paper") || computerMove.equals("Paper") && playerMove.equals("Scissors") || computerMove.equals("Scissors") && playerMove.equals("Rock")){
            System.out.println("You Win");
            wins++;
            }
        }
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
    }
}

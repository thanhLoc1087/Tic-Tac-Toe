import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
class Java {
    static ArrayList<Integer> playerMoves = new ArrayList<Integer>();
    static ArrayList<Integer> cpuMoves = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        char[][] board = {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'}, 
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'}, 
            {' ','|',' ','|',' '},
        };
        PrintBoard(board);
        int playerPos;
        int cpuPos;
        while (true) {
            do {
                System.out.print("Enter pos (1-9): ");
                playerPos = scanner.nextInt();
            } while (playerMoves.contains(playerPos) || cpuMoves.contains(playerPos));
            playerMoves.add(playerPos);
            PlacePiece(board, playerPos, "player");
            PrintBoard(board);
            if (CheckWin(board) == 1) {
                System.out.println("Player won!");
                break;
            }
            System.out.println("---Opponent's turn---");
            do {
                cpuPos = rand.nextInt(9) + 1;
            } while (playerMoves.contains(cpuPos) || cpuMoves.contains(cpuPos));
            cpuMoves.add(cpuPos);
            PlacePiece(board, cpuPos, "cpu");
            PrintBoard(board);
            if (CheckWin(board) == 2) {
                System.out.println("CPU won!");
                break;
            }
            if (playerMoves.size() + cpuMoves.size() == 8) {
                System.out.println("Ties!");
                break;
            }
            scanner.close();
        }
    }
    public static void PrintBoard(char[][] board) {
        for (char[] row : board) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }
    public static void PlacePiece(char[][] board, int pos, String user) {
        user = user.trim().toLowerCase();
        char u = user == "player" ? 'X' : 'O';
        switch(pos){
            case 1:
            board[0][0] = u;
            break; 
            case 2:
            board[0][2] = u;
            break; 
            case 3:
            board[0][4] = u;
            break; 
            case 4:
            board[2][0] = u;
            break; 
            case 5:
            board[2][2] = u;
            break; 
            case 6:
            board[2][4] = u;
            break; 
            case 7:
            board[4][0] = u;
            break; 
            case 8:
            board[4][2] = u;
            break; 
            case 9:
            board[4][4] = u;
            break; 
        }
    }
    public static int CheckWin(char[][] board) {
        // 0 if no result
        // 1 if player win
        // 2 if cpu wins
        // Crosses
        if (board[0][0] != ' ' && board[0][0] == board[2][2] && board[2][2] == board[4][4]) 
        return board[0][0] == 'X' ? 1 : 2;
        if (board[0][4] != ' ' && board[0][4] == board[2][2] && board[2][2] == board[4][0]) 
        return board[0][0] == 'X' ? 1 : 2;
        // Rows
        for (int i = 0; i <= 4; i+=2) {
            if (board[i][0] != ' ' && board[i][0] == board[i][2] && board[i][0] == board[i][4]) 
            return board[i][0] == 'X' ? 1 : 2;
        }
        // Columns
        for (int i = 0; i <= 4; i+=2) {
            if (board[0][i] != ' ' && board[0][i] == board[2][i] && board[0][i] == board[4][i]) 
            return board[0][i] == 'X' ? 1 : 2;
        }
        return 0;
    }
}
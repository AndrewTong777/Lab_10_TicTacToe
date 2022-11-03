import java.util.*;

class SafeInput
{
    public static int getRangedInt(Scanner console, String prompt, int low, int max) {

        int result;
        do {
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                console.nextLine();
                System.out.print(prompt);
            }

            result = console.nextInt();
        } while (result < low || result > max);

        return result;
    }

    public static boolean getYNConfirm(Scanner console, String prompt)
    {
        String str;
        System.out.print(prompt);

        str = console.next();
        if (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("y"))
            return true;

        return false;
    }
}

class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    private static void clearBoard()
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                board[row][col] = " ";
    }

    private static void display()
    {
        System.out.println("\n   1    2    3\n");
        for(int row = 0; row < 3; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col] + " ");
                if (col != 2)
                    System.out.print(" |");
            }
            System.out.println();
            if (row != 2){
                System.out.println(" ____|____|____");
                System.out.println("     |    |    ");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col)
    {
        if (row>=0 && row<ROW && col>=0 && col<COL && board[row][col].equals(" "))
            return true;

        return false;
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
            return true;
        return false;
    }

    private static boolean isColWin(String player)
    {
        for(int col=0; col<COL; col++)
        {
            if(board[0][col].equals(player) && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]))
                return true;
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for(int row=0; row<ROW; row++)
        {
            if(board[row][0].equals(player) && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]))
                return true;
        }
        return false;
    }

    private static boolean isDiagnalWin(String player)
    {
        if(board[1][1].equals(player) && ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))))
            return true;
        return false;
    }

    private static boolean isTie()
    {
        for(int row=0; row<ROW; row++)
        {
            for(int col=0; col<COL; col++)
            {
                if(board[row][col].equals(" "))
                    return false;
            }
        }
        return true;
    }

    public static void main (String[] args)
    {
        Scanner console = new Scanner(System.in);

        do
        {
            String player = "X";

            clearBoard();

            display();

            while(true)
            {
                System.out.println ("Player " + player);
                //get a move
                int rowMove = SafeInput.getRangedInt(console, "Enter row: ", 1, 3);
                int colMove = SafeInput.getRangedInt(console, "Enter col: ", 1, 3);
                rowMove--;
                colMove--;
                board[rowMove][colMove] = player;

                display();
                if(isWin(player))
                {
                    System.out.println (player + " won!");
                    break;
                }
                else if(isTie())
                {
                    System.out.println ("Game TIE!");
                    break;
                }

                if(player == "X")
                    player = "O";
                else
                    player = "X";

            }

        }while(SafeInput.getYNConfirm(console, "\nContinue? "));

    }
}
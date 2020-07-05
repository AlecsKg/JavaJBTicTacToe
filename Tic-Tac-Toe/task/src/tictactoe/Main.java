package tictactoe;

import java.util.Scanner;

public class Main {

    static char[][] table = new char[3][3];

    public static void main(String[] args) {
        //FillTableWithPredefValues();
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter cells: ");
        //char[] input = scanner.nextLine().toCharArray();
        FillTableWithPredefValues();
        PrintTheTable();
        boolean toRun = true;
        boolean xChar = true;
        while (toRun)
        {
            System.out.print("Enter the coordinates: ");
            String inPut = scanner.nextLine().trim();
            if (inPut.length() < 3)
            {
                System.out.println("Write two coordinates!");
                continue;
            }
            if (inPut.length() > 3)
            {
                System.out.println("Too long!");
                continue;
            }
            if (inPut.contains("[a-zA-Z]+"))
            {
                System.out.println("You should enter numbers!");
                continue;
            }

            Scanner scan2 = new Scanner(inPut);
            int x = scan2.nextInt();
            int y = scan2.nextInt();


            if (x > 3 || x < 1 || y > 3 || y < 0)
            {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            var oldC = table[3 - y][x - 1];
            if (oldC == 'X' || oldC == 'O')
            {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            table[3 - y][x - 1] =  xChar ? 'X' : 'O';
            xChar = !xChar;
            PrintTheTable();
            toRun = !AnalyzeTheTable();

        }


       // AnalyzeTheTable();

    }

    static boolean AnalyzeTheTable() {

        int counterXx = 0;
        int counterOo = 0;
        boolean found = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = table[i][j];
                if (c == 'X') {
                    counterXx++;
                }
                if (c == 'O') {
                    counterOo++;
                }
            }
        }

        int firstSum = counterOo + counterXx;
        if (Math.abs(counterOo - counterXx) >= 2)
        {
            System.out.println("Impossible");
            found = true;
        } else
        {
            int[] counterX = new int[8];
            int[] counterO = new int[8];


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char c = table[i][j];
                    if (c == 'X') {
                        counterX[i]++;
                    }
                    if (c == 'O') {
                        counterO[i]++;
                    }
                    if (i == j)
                    {
                        if (c == 'X') {
                            counterX[6]++;
                        }
                        if (c == 'O') {
                            counterO[6]++;
                        }
                    }
                    if (j == 2 - i)
                    {
                        if (c == 'X') {
                            counterX[7]++;
                        }
                        if (c == 'O') {
                            counterO[7]++;
                        }
                    }
                }
            }

            for ( int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    char c = table[i][j];
                    if (c == 'X') {
                        counterX[j + 3]++;
                    }
                    if (c == 'O') {
                        counterO[j + 3]++;
                    }
                }
            }

            boolean xWon = false;
            boolean oWon = false;

            for (int i = 0; i < 8; i++)
            {
                if (counterX[i] == 3)
                {
                    xWon = true;
                }
                if (counterO[i] == 3)
                {
                    oWon = true;
                }
            }

            if (xWon && oWon)
            {
                System.out.println("Impossible");
                found = true;
            } else if (xWon)
            {
                System.out.println("X wins");
                found = true;
            }
            else if (oWon)
            {
                System.out.println("O wins");
                found = true;
            }


            if (!found)
            {
                 if (firstSum < 9)
                 {
                   //System.out.println("Game not finished");
                 }
                 else
                 {
                     System.out.println("Draw");
                     found = true;
                 }
            }

        }
        return found;
    }



    static void FillTableWithCharValues(char[] input)
    {
        int counter = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                table[i][j] = input[counter++];
            }
        }
    }

    static void FillTableWithPredefValues()
    {
        table[0][0] = '_';
        table[0][1] = '_';
        table[0][2] = '_';
        table[1][0] = '_';
        table[1][1] = '_';
        table[1][2] = '_';
        table[2][0] = '_';
        table[2][1] = '_';
        table[2][2] = '_';
    }

    static void PrintTheTable()
    {
        for (int i = 0; i < 3; i++)
        {
            if (i == 0)
            {
                PrintHorizontalLines();
            }

            for (int j = 0; j < 3; j++)
            {
                if (j == 0)
                {
                    PrintVerticalLine();
                    System.out.print(" ");
                }
                System.out.print(table[i][j]);
                System.out.print(" ");
                if (j == 2)
                {
                    PrintVerticalLine();
                }
            }
            System.out.println();
            if (i == 2)
            {
                PrintHorizontalLines();
            }
        }
    }

    static void PrintVerticalLine()
    {
        System.out.print("|");
    }

    static void PrintHorizontalLines()
    {
        for (int k = 0; k < 9; k++)
        {
            System.out.print("-");
        }
        System.out.println();
    }
}

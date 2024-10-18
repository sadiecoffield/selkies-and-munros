import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
/**
 * Initialises the board, displays the board and displays the objects on the board.
 *
 * @author Sadie Coffield
 * @version 1
 */
public class Board
{
    private String[][] board = new String[10][10];
    private int updatedPlayer1Col = 0;
    private int updatedPlayer1Row = 9;
    private int updatedPlayer2Col = 0;
    private int updatedPlayer2Row = 9;
    private int currentPlayer1Col = 0;
    private int currentPlayer1Row = 9;
    private int currentPlayer2Col = 0;
    private int currentPlayer2Row = 9;
    
    /**
     * Method that initialises each space on the board to be empty.
     */
    public void initialiseBoard()
    {
        int i, j = 0;
        
        for (i = 0; i < 10; i++)
        {
            for (j = 0; j < 10; j++)
            {
                board[i][j] = "";
            }
        }
    }
    
    /**
     * Method that displays the board.
     */
    public void displayBoard()
    {
        int i, j;
        
        for (i = 0; i < 10; i++)
        {
            for (j = 0; j < 10; j++)
            {
                System.out.print("------");
            }
            System.out.print("- ");
            System.out.print("\n"); 
            for (j = 0; j < 10; j++)
            {
                String basicStringAtBoardLocation_i_j = board[i][j];
                String paddedVersionOfString = String.format("%-5s", basicStringAtBoardLocation_i_j);
                System.out.print("|" + paddedVersionOfString);
            }
            System.out.print("|");
            System.out.print("\n"); 
        }
        
        for (i = 0; i < 10; i++)
        {
            for (j = 0; j < 1; j++)
            {
                System.out.print("------");
            }
        }
        System.out.print("- ");
        System.out.print("\n");
    }
    
    /**
     * Method that displays the selkies, munros and whisky boosts
     * in specific positions on the board.
     */
    public void displayActivitySquares()
    {
        String selkie1 = board[4][8] = "🐍";
        String selkie2 = board[7][5] = "🐍";
        String selkie3 = board[3][1] = "🐍";
        String selkie4 = board[0][3] = "🐍";
        
        String munro1 = board[9][3] = "🗻";
        String munro2 = board[6][8] = "🗻";
        String munro3 = board[5][2] = "🗻";
        String munro4 = board[2][7] = "🗻";
        
        board[8][7] = "🍷";
        board[1][3] = "🍷";
        board[4][6] = "🍷";
    }
    
    /**
     * Method that initialises the players positions on the board.
     */
    public void initialisePlayerLocation()
    {
        String player1 = "1";
        String player2 = "2";
        
        board[9][0] = player1 + player2;
    }
    
    /**
     * Method to get the column player 1 is currently in.
     * 
     * @return Player 1's current column
     */
    public int getP1Col()
    {
        return currentPlayer1Col;
    }
    
    /**
     * Method to get the row player 1 is currently in.
     * 
     * @return Player 1's current row
     */
    public int getP1Row()
    {
        return currentPlayer1Row;
    }
    
    /**
     * Method to get the column player 2 is currently in.
     * 
     * @return Player 2's current column
     */
    public int getP2Col()
    {
        return currentPlayer2Col;
    }
    
    /**
     * Method to get the row player 2 is currently in.
     * 
     * @return Player 2's current row
     */
    public int getP2Row()
    {
        return currentPlayer2Row;
    }
    
    /**
     * Method that generates a random number and moves player 1 
     * that number of spaces, ensuring the player stays within the
     * boundaries of the grid. Checks if the current space contains
     * a selkie, munro, or whisky boost and carries out the correct
     * action that is required.
     */
    public void updateP1BoardPos()
    {
        Player player = new Player();
        
        int diceRoll1 = player.generateP1DiceRoll();
        
        board[currentPlayer1Row][currentPlayer1Col] = board[currentPlayer1Row][currentPlayer1Col].replace("1", " ");
        board[currentPlayer1Row][currentPlayer1Col] = board[currentPlayer1Row][currentPlayer1Col].trim();
        
        for (int i = 0; i < diceRoll1; i++)
        {   
            if (currentPlayer1Row % 2 != 0)
            {   
                if (currentPlayer1Col == 9)
                {
                    updatedPlayer1Row = updatedPlayer1Row - 1;
                
                    while (i < diceRoll1)
                    {
                        updatedPlayer1Col = updatedPlayer1Col - 1;
                        i++;
                    }
                }
                
                if (updatedPlayer1Col != 9 && currentPlayer1Row % 2 != 0)
                {
                    updatedPlayer1Col = updatedPlayer1Col + 1;
                }
                
                if (updatedPlayer1Col == 9 && i != diceRoll1 - 1)
                {
                    updatedPlayer1Row = currentPlayer1Row - 1;
                    i++;
                    while (i != diceRoll1 - 1 && i < diceRoll1)
                    {
                        updatedPlayer1Col = updatedPlayer1Col - 1;
                        i++;
                    }
                }
            }
            
            if(currentPlayer1Row % 2 == 0)
            {
                if (currentPlayer1Col == 0)
                {
                    updatedPlayer1Row = updatedPlayer1Row - 1;
                    
                    while (i < diceRoll1)
                    {
                        updatedPlayer1Col = updatedPlayer1Col + 1;
                        i++;
                    }
                }
                
                if (updatedPlayer1Col != 0 && currentPlayer1Row % 2 == 0)
                {
                    updatedPlayer1Col = updatedPlayer1Col - 1;
                }
                
                if (updatedPlayer1Col == 0 && updatedPlayer1Row != 0 && i != diceRoll1 - 1)
                {
                    updatedPlayer1Row = currentPlayer1Row - 1;
                    i++;
                    while (i != diceRoll1 - 1 && i < diceRoll1)
                    {
                        updatedPlayer1Col = updatedPlayer1Col + 1;
                        i++;
                    }
                    
                }
                
                if (updatedPlayer1Col == 0 && updatedPlayer1Row == 0)
                {
                    System.out.println("Player 1 wins!");
                    break;
                }
            }
        }
        
        if (board[updatedPlayer1Row][updatedPlayer1Col].contains("🍷") && updatedPlayer1Row % 2 != 0)
        {
            System.out.println("You've landed on a whisky boost!");
            updatedPlayer1Col = updatedPlayer1Col + 5;
        }
        
        if (board[updatedPlayer1Row][updatedPlayer1Col].contains("🍷") && updatedPlayer1Row % 2 == 0)
        {
            System.out.println("You've landed on a whisky boost!");
            updatedPlayer1Col = updatedPlayer1Col - 5;
        }
        
        Selkies selkies = new Selkies();
        if (board[updatedPlayer1Row][updatedPlayer1Col].contains("🐍"))
        {
            System.out.println("You've landed on a selkie!");
            if (updatedPlayer1Row == 4)
            {
                updatedPlayer1Col = selkies.getSelkie1PlayerPos_X();
                updatedPlayer1Row = selkies.getSelkie1PlayerPos_Y();
            }
            else if (updatedPlayer1Row == 7)
            {
                updatedPlayer1Col = selkies.getSelkie2PlayerPos_X();
                updatedPlayer1Row = selkies.getSelkie2PlayerPos_Y();
            }
            else if (updatedPlayer1Row == 3)
            {
                updatedPlayer1Col = selkies.getSelkie3PlayerPos_X();
                updatedPlayer1Row = selkies.getSelkie3PlayerPos_Y();
            }
            else if (updatedPlayer1Row == 0)
            {
                updatedPlayer1Col = selkies.getSelkie4PlayerPos_X();
                updatedPlayer1Row = selkies.getSelkie4PlayerPos_Y();
            }
        }
        
        Munros munros = new Munros();
        if (board[updatedPlayer1Row][updatedPlayer1Col].contains("🗻"))
        {
            System.out.println("You've landed on a munro!");
            if (updatedPlayer1Row == 9)
            {
                updatedPlayer1Col = munros.getMunro1PlayerPos_X();
                updatedPlayer1Row = munros.getMunro1PlayerPos_Y();
            }
            else if (updatedPlayer1Row == 6)
            {
                updatedPlayer1Col = munros.getMunro2PlayerPos_X();
                updatedPlayer1Row = munros.getMunro2PlayerPos_Y();
            }
            else if (updatedPlayer1Row == 5)
            {
                updatedPlayer1Col = munros.getMunro3PlayerPos_X();
                updatedPlayer1Row = munros.getMunro3PlayerPos_Y();
            }
            else if (updatedPlayer1Row == 2)
            {
                updatedPlayer1Col = munros.getMunro4PlayerPos_X();
                updatedPlayer1Row = munros.getMunro4PlayerPos_Y();
            }
        }
        
        board[updatedPlayer1Row][updatedPlayer1Col] = board[updatedPlayer1Row][updatedPlayer1Col].concat("1");
        
        currentPlayer1Col = updatedPlayer1Col;
        currentPlayer1Row = updatedPlayer1Row;
    }
    
    /**
     * Method that generates a random number and moves player 2 
     * that number of spaces, ensuring the player stays within the
     * boundaries of the grid. Checks if the current space contains
     * a selkie, munro, or whisky boost and carries out the correct
     * action that is required.
     */
    public void updateP2BoardPos()
    {
        Player player = new Player();

        int diceRoll2 = player.generateP2DiceRoll();
        
        board[currentPlayer2Row][currentPlayer2Col] = board[currentPlayer2Row][currentPlayer2Col].replace("2", " ");
        board[currentPlayer2Row][currentPlayer2Col] = board[currentPlayer2Row][currentPlayer2Col].trim();
        
        for (int i = 0; i < diceRoll2; i++)
        {   
            if (currentPlayer2Row % 2 != 0)
            {
                if (currentPlayer2Col == 9)
                {
                    updatedPlayer2Row = updatedPlayer2Row - 1;
                    
                    while (i < diceRoll2)
                    {
                        updatedPlayer2Col = updatedPlayer2Col - 1;
                        i++;
                    }
                }
                
                if (updatedPlayer2Col != 9 && currentPlayer2Row % 2 != 0)
                {
                    updatedPlayer2Col = updatedPlayer2Col + 1;
                }
                
                if (updatedPlayer2Col == 9 && i != diceRoll2 - 1)
                {
                    updatedPlayer2Row = currentPlayer2Row - 1;
                    i++;
                    while (i != diceRoll2 - 1 && i < diceRoll2)
                    {
                        updatedPlayer2Col = updatedPlayer2Col - 1;
                        i++;
                    }
                }
            }
            
            if(currentPlayer2Row % 2 == 0)
            {
                if (currentPlayer2Col == 0)
                {
                    updatedPlayer2Row = updatedPlayer2Row - 1;
                    
                    while (i < diceRoll2)
                    {
                        updatedPlayer2Col = updatedPlayer2Col + 1;
                        i++;
                    }
                }
                
                if (updatedPlayer2Col != 0 && currentPlayer2Row % 2 == 0)
                {
                    updatedPlayer2Col = updatedPlayer2Col - 1;
                }
                
                if (updatedPlayer2Col == 0 && updatedPlayer2Row != 0 && i != diceRoll2 - 1)
                {
                    updatedPlayer2Row = currentPlayer2Row - 1;
                    i++;
                    while (i != diceRoll2 - 1 && i < diceRoll2)
                    {
                        updatedPlayer2Col = updatedPlayer2Col + 1;
                        i++;
                    }
                }
                
                if (updatedPlayer2Col == 0 && updatedPlayer2Row == 0)
                {
                    System.out.println("Player 2 wins!");
                    break;
                }
            }
        }
        
        if (board[updatedPlayer2Row][updatedPlayer2Col].contains("🍷") && updatedPlayer2Row % 2 != 0)
        {
            System.out.println("You've landed on a whisky boost!");
            updatedPlayer2Col = updatedPlayer2Col + 5;
        }
        
        else if (board[updatedPlayer2Row][updatedPlayer2Col].contains("🍷") && updatedPlayer2Row % 2 == 0)
        {
            System.out.println("You've landed on a whisky boost!");
            updatedPlayer2Col = updatedPlayer2Col - 5;
        }
        
        Selkies selkies = new Selkies();
        if (board[updatedPlayer2Row][updatedPlayer2Col].contains("🐍"))
        {
            System.out.println("You've landed on a selkie!");
            if (updatedPlayer2Row == 4)
            {
                updatedPlayer2Col = selkies.getSelkie1PlayerPos_X();
                updatedPlayer2Row = selkies.getSelkie1PlayerPos_Y();
            }
            else if (updatedPlayer2Row == 7)
            {
                updatedPlayer2Col = selkies.getSelkie2PlayerPos_X();
                updatedPlayer2Row = selkies.getSelkie2PlayerPos_Y();
            }
            else if (updatedPlayer2Row == 3)
            {
                updatedPlayer2Col = selkies.getSelkie3PlayerPos_X();
                updatedPlayer2Row = selkies.getSelkie3PlayerPos_Y();
            }
            else if (updatedPlayer2Row == 0)
            {
                updatedPlayer2Col = selkies.getSelkie4PlayerPos_X();
                updatedPlayer2Row = selkies.getSelkie4PlayerPos_Y();
            }
        }
        
        Munros munros = new Munros();
        if (board[updatedPlayer2Row][updatedPlayer2Col].contains("🗻"))
        {
            System.out.println("You've landed on a munro!");
            if (updatedPlayer2Row == 9)
            {
                updatedPlayer2Col = munros.getMunro1PlayerPos_X();
                updatedPlayer2Row = munros.getMunro1PlayerPos_Y();
            }
            else if (updatedPlayer2Row == 6)
            {
                updatedPlayer2Col = munros.getMunro2PlayerPos_X();
                updatedPlayer2Row = munros.getMunro2PlayerPos_Y();
            }
            else if (updatedPlayer2Row == 5)
            {
                updatedPlayer2Col = munros.getMunro3PlayerPos_X();
                updatedPlayer2Row = munros.getMunro3PlayerPos_Y();
            }
            else if (updatedPlayer2Row == 2)
            {
                updatedPlayer2Col = munros.getMunro4PlayerPos_X();
                updatedPlayer2Row = munros.getMunro4PlayerPos_Y();
            }
        }

        board[updatedPlayer2Row][updatedPlayer2Col] = board[updatedPlayer2Row][updatedPlayer2Col].concat("2");
        
        currentPlayer2Col = updatedPlayer2Col;
        currentPlayer2Row = updatedPlayer2Row;
    }
    
    /**
     * Method that writes the current position of each player to file. 
     */
    public void saveGame()
    {
        Scanner s = new Scanner(System.in);
    
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        try
        {
            outputStream = new FileOutputStream("savedGame.txt");
            printWriter = new PrintWriter(outputStream);
            
            printWriter.println(currentPlayer1Col);
            printWriter.println(currentPlayer1Row);
            printWriter.println(currentPlayer2Col);
            printWriter.println(currentPlayer2Row);
        }
        catch (IOException e)
        {
            System.out.println("There is a problem with saving the game.");
        }
        finally
        {
            if (printWriter != null)
            {
                printWriter.close();
            }
        }   
    }
    
    /**
     * Method that reads in each players position from the previously
     * saved game and displays the players in those positions.
     */
    public void loadGame()
    {
        Scanner s = new Scanner(System.in);
        
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        
        try 
        {
            fileReader = new FileReader("savedGame.txt");
            bufferedReader = new BufferedReader(fileReader);
            
            String line1 = bufferedReader.readLine();
            String line2 = bufferedReader.readLine();
            String line3 = bufferedReader.readLine();
            String line4 = bufferedReader.readLine();
            
            currentPlayer1Col = Integer.parseInt(line1);
            currentPlayer1Row = Integer.parseInt(line2);
            currentPlayer2Col = Integer.parseInt(line3);
            currentPlayer2Row = Integer.parseInt(line4);
        
            board[currentPlayer1Row][currentPlayer1Col] = "1";
            board[currentPlayer2Row][currentPlayer2Col] = "2";
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file was not found");
        }
        catch (IOException e)
        {
            System.out.println("There is a problem with loading the game.");
        }
        finally
        {
            try
            {
                bufferedReader.close();
            }
            catch (IOException e)
            {
                System.out.println("There is a problem closing the file");
            }
        }
    }
}

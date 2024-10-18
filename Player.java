import java.util.Random;
import java.util.Scanner;
/**
 * Contains the actions that need to be carried out for each player.
 *
 * @author Sadie Coffield
 * @version 1
 */
public class Player
{   
    /**
     * Method to generate a random number between 1 and 6 
     */
    public int generateDiceRoll()
    {   
        Random random = new Random();
        
        int randomNumber = random.nextInt(6);
        
        randomNumber += 1;
        
        System.out.println("You rolled " + randomNumber);
        
        return randomNumber;
    }
    
    /**
     * Method that assigns a random number generated to player 1.
     */
    public int generateP1DiceRoll()
    {
        int random1 = generateDiceRoll();
        return random1;
    }
    
    /**
     * Method that assigns a random number generated to player 2.
     */
    public int generateP2DiceRoll()
    {
        int random2 = generateDiceRoll();
        return random2;
    }
    
    /**
     * Method that changes between each player in turn. The players 
     * will be able to roll the dice, pause the game or exit the game
     * on every turn.
     */
    public void playerTurn()
    {
        Scanner s = new Scanner(System.in);
        Board board = new Board();
        
        int currentPlayer = 1;
        int nextTurnOrExit;
        int player1Col;
        int player1Row;
        int player2Col;
        int player2Row;
        
        board.initialiseBoard();
        board.initialisePlayerLocation();
        
        do
        {
            player1Col = board.getP1Col();
            player1Row = board.getP1Row();
            player2Col = board.getP2Col();
            player2Row = board.getP2Row();
            
            System.out.println("");
            System.out.println("Enter 1 for next player's turn, 2 to pause game or 0 to exit the game");
            System.out.println("");
            nextTurnOrExit = s.nextInt();
            if (nextTurnOrExit == 1)
            {
                if (currentPlayer == 1)
                {
                    System.out.println("Current player is player " + currentPlayer);

                    board.displayActivitySquares();
                    board.updateP1BoardPos();
                    board.displayBoard();
                    
                    currentPlayer = currentPlayer + 1;
                }
                else
                {
                    System.out.println("Current player is player " + currentPlayer);

                    board.displayActivitySquares();
                    board.updateP2BoardPos();
                    board.displayBoard();
                    
                    currentPlayer = currentPlayer - 1;
                }
            }
            else if (nextTurnOrExit == 2)
            {
                int menuChoice;
                do
                {
                    Menu menu = new Menu();
                    menu.displayPauseMenu();
                        
                    menuChoice = s.nextInt();
                        
                    if (menuChoice == 1)                    
                    {
                        System.out.println("Game resumed");
                        board.displayBoard();
                        
                    }
                    else if (menuChoice == 2)
                    {
                        board.saveGame();
                        System.out.println("Game Saved");
                        System.out.println("");
                    }
                    else if (menuChoice == 3)
                    {
                        menu.displayRules();
                    }
                    else
                    {
                        System.out.println("Invalid option was selected");
                        System.out.println("");
                    }
                }
                while (menuChoice != 1);
            }
            else if (nextTurnOrExit == 0)
            {
                System.out.println("Game exited");
                System.out.println("");
            }
            else
            {
                System.out.println("Invalid option was selected");
                System.out.println("");
            }
        }
        while (nextTurnOrExit != 0 || nextTurnOrExit == 1);
    }
}

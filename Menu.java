import java.util.Scanner;
/**
 * Displays the menu to the user and processes their choices
 *
 * @author Sadie Coffield
 * @version 1
 */
public class Menu
{
    /**
     * Main method
     * @param args Command line parameters
     */
    public static void main(String[] args)
    {
        Menu menu1 = new Menu();
        
        menu1.processUserChoice();
    }
    
    /**
     * Method that displays the main menu to the user 
     */
    public void displayMenu()
    {
        System.out.println("");
        System.out.println("Main Menu");
        System.out.println("Please select one of the following options: ");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Rules");
        System.out.println("0. Exit");
    }
    
    /**
     * Method that displays the pause menu to the user 
     */
    public void displayPauseMenu()
    {
        System.out.println("");
        System.out.println("Game Paused");
        System.out.println("Please select one of the following options: ");
        System.out.println("1. Resume Game");
        System.out.println("2. Save Game");
        System.out.println("3. Rules");
        System.out.println("");
    }
    
    /**
     * Method that processes the user's choices 
     */
    public int processUserChoice()
    {
        Scanner s = new Scanner(System.in);
        Board board = new Board();
        Player player = new Player();
        
        int userChoice;
        
        do
        {
            displayMenu();
            
            userChoice = s.nextInt();
            System.out.println("");
            
            if (userChoice == 1)
            {       
                board.initialiseBoard();
                board.initialisePlayerLocation();
                board.displayActivitySquares();
                board.displayBoard();
                player.playerTurn();
            }
            else if (userChoice == 2)
            {
                board.initialiseBoard();
                board.loadGame();
                board.displayActivitySquares();
                board.displayBoard();
                System.out.println("Game Loaded");
                player.playerTurn();
            }
            else if (userChoice == 3)
            {
                displayRules();
            }
            else if (userChoice == 0)
            {
                System.out.println("Goodbye");
            }
            else
            {
                System.out.println("Invalid option was selected");
                System.out.println("");
            }
        }
        while (userChoice != 0);
        
        return userChoice;
    }
    
    /**
     * Displays the rules of the game to the user
     */
    public void displayRules()
    {
        System.out.println("");
        System.out.println("1. Each player will take turns to have a random number generated and will move the corresponding number of spaces along the board.");
        System.out.println("2. If you land on a munro (🗻) you will move a number of spaces up the board automatically.");
        System.out.println("3. If you land on a selkie (🐍) you will move a number of spaces down the board automatically.");
        System.out.println("4. If you land on a whisky boost (🍷) you will move a further 5 spaces along the board.");
        System.out.println("5. The first player to reach the 100th space on the board wins!");
        System.out.println("");
    }
}

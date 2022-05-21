import java.util.Scanner;

public class Main {

    static boolean gameWon;
    static int numGuesses;
    static int minRows = 4;
    static int minColumns = 4;
    static int maxRows = 36;
    static int maxColumns = 36;

    public static void main(String[] args) {

        startGame();

    }

    public static void startGame() {
        System.out.println("!--------------Welcome To Minesweeper--------------!");

        gameWon = false;
        numGuesses = 0;

        int numRows;
        int numColumns;

        do {
            System.out.println("How many rows? Min 4, Max 36");
            numRows = getInput();
            if (numRows < minRows || numRows > maxRows) {
                System.out.println("Needs to be between " + minRows +  " and " + maxRows + " rows.");
            }
        } while (numRows < minRows || numRows > maxRows);

        do {
            System.out.println("How many columns? Min 4, Max 36");
            numColumns = getInput();
            if (numColumns < minColumns || numColumns > maxColumns) {
                System.out.println("Needs to be between " + minRows +  " and " + maxRows + " rows.");
            }
        } while (numColumns < minColumns || numColumns > maxColumns);

        Field newField = new Field(numRows, numColumns);

        System.out.println("\nOn a " + numRows + " by " + numColumns + " grid, there will be " + (newField.numEmptyCells) + " empty cells, and " +
                newField.numMines + " mines!");

        gameLoop(newField);
    }

    public static void gameLoop(Field newField) {

        int chosenX;
        int chosenY;


            while (!gameWon) {
                newField.displayField();
                do {
                    System.out.println("\nPlease choose Row Number: ");
                    chosenX = getInput();
                    if (chosenX > newField.numRows || chosenX <1) {
                        System.out.println("\nRow + " + chosenX +" not on board, try again.");

                    }

                } while (chosenX > newField.numRows || chosenX <1);

                do {
                    System.out.println("\nPlease choose Column Number: ");
                    chosenY = getInput();
                    if (chosenY > newField.numColumns || chosenY <1 ) {
                        System.out.println(("\nColumn " + chosenY + " not on board, try again."));
                    }
                } while (chosenY > newField.numColumns || chosenY <1 );


                // Decrement chosen X and Y to get correct cell
                if (newField.getCell(chosenX - 1, chosenY - 1).isVisible()) {

                    System.out.println("You selected Row: " + chosenX + " and Column: " + chosenY + " again.\nA Cell can only be selected once, choose another.\nYou have had " + numGuesses + " guesses!");

                }else {
                    if (newField.getCell(chosenX - 1, chosenY - 1).isMine()) {
                        numGuesses++;
                        //newField.numMines--;
                        System.out.println("You selected Row: " + chosenX + " and Column: " + chosenY + ".\nThis is a mine!");
                        newField.getCell(chosenX - 1, chosenY - 1).setVisible(true);
                        newField.visibleField();
                        loseGame();

                    } else {
                        numGuesses++;
                        newField.numEmptyCells--;
                        if (newField.numEmptyCells > 0) {
                            System.out.println("You selected Row: " + chosenX + " and Column: " + chosenY + ".\nThis is NOT a mine! You have had " + numGuesses + " guesses. " + newField.numEmptyCells + " empty cells remain.");

                            System.out.println("There are " + newField.checkAdjacentMines(chosenX - 1, chosenY - 1)  + " mines around your chosen cell!");
                        } else {
                            newField.visibleField();
                            winGame();
                        }

                }
            }
        }
    }

    private static void winGame() {
        gameWon = true;

        System.out.println("Congrats, you cleared the field with " + numGuesses + " guesses! Do you want to play again? 1 = yes, 0 = no");

        if (getInput() == 1) {
            startGame();
        } else {
            System.exit(0);
        }
    }

    public static void loseGame() {
        System.out.println("Oh no, You lost with " + numGuesses + " guesses. Do you want to play again? 0 = No, 1 = Yes");

        if (getInput() == 1) {
            startGame();
        } else {
            System.exit(0);
        }
    }

    public static int getInput() {
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        if (input.hasNextInt()) {
            return input.nextInt();
        } else {
            return 0;
        }
    }
}




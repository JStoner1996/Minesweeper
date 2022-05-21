import java.util.Random;

public class CellsGenerator {

    // Attributes
    int numRows;
    int numColumns;
    int numMines;


    Random rand = new Random();
    // Constructor
    public CellsGenerator(int numRows, int numColumns) {

        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numMines = (this.numRows * this.numColumns) / 5;
    }

    // Methods

    // 2D Array to store X and Y pos
    public Cells[][] buildField() {
        Cells[][] field = new Cells[this.numRows][this.numColumns];
        addCells(field);
        addMine(field);
        return field;
    }

    // Add empty cells to the field
    public void addCells(Cells[][] field) {
        for (int row = 0; row < this.numRows; row++) {
            for (int column = 0; column < this.numColumns; column++) {
                field[row][column] = new Cells();


            }
        }
    }

    public void addMine(Cells[][] field) {
        int mines = 0;
        for (int row = 0; row < this.numRows; row++) {
            for (int column = 0; column < this.numColumns; column++) {
                if (mines < numMines) {
                    field[row][column].setMine(true);

                    //adjacentMines(field, row, column);

                    mines++;

                }
            }
        }
        randomizeField(field);
    }

//    private void adjacentMines(Cells[][] field, int row, int column) {
//
//
//        if (isValidCell(field, row - 1, column - 1) && field[row - 1][column - 1].isMine()) {
//            field[row][column].addAdjacentMines(); // NW
//
//        }
//
//        if (isValidCell(field, row, column - 1) && field[row][column -1].isMine()){
//            field[row][column].addAdjacentMines(); // N
//        }
//        if (isValidCell(field, row + 1, column - 1) && field[row +1][column -1].isMine()  ){
//            field[row][column].addAdjacentMines(); // NE
//        }
//        if (isValidCell(field, row + 1 , column) && field[row +1][column].isMine()){
//            field[row][column].addAdjacentMines(); // E
//        }
//        if (isValidCell(field, row + 1, column + 1) && field[row +1][column +1].isMine() ){
//            field[row][column].addAdjacentMines(); // SE
//        }
//        if (isValidCell(field, row, column + 1) && field[row][column +1].isMine()){
//            field[row][column].addAdjacentMines(); // S
//        }
//        if (isValidCell(field, row - 1, column + 1) && field[row -1][column +1].isMine()){
//            field[row][column].addAdjacentMines(); //SW
//        }
//        if (isValidCell(field, row - 1 , column) && field[row -1][column].isMine()){
//            field[row][column].addAdjacentMines(); // W
//        }
//    }
//
//     public boolean isValidCell(Cells field[][], int row, int column) {
//         // Take 1 from row and column to get correct values
//
//         try {
//             if (field[row][column] != null) {
//                 return true;
//             }
//         } catch (Exception e) {
//
//            // System.out.println("Invalid Cell at " + row + "," + column);
//
//             return false;
//         }
//
//         return false;
//     }
    public void randomizeField(final Cells[][] field) {


        for (int row = field.length - 1; row > 0; row--) {
            for (int column = field[row].length - 1; column > 0; column--) {
                int randomRow = rand.nextInt(row + 1);
                int randomColumn = rand.nextInt(column + 1);

                Cells tempField = field[row][column];
                field[row][column] = field[randomRow][randomColumn];
                field[randomRow][randomColumn] = tempField;

            }
        }


    }


}

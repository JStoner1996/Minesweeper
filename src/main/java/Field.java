public class Field {

    private final Cells[][] gameField;
    int numRows;
    int numColumns;
    int numMines;
    int numEmptyCells;
    //int adjacentMines = 0;

    public Field(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numMines = (this.numRows * this.numColumns) / 5;
        this.numEmptyCells = (this.numRows * this.numColumns) - numMines;
        CellsGenerator generator = new CellsGenerator(numRows, numColumns);
        this.gameField = generator.buildField();
    }


    // Gets cell data
    public Cells getCell(int row, int column) {
        return gameField[row][column];
    }

    // Shows the field to the screen.
    public void displayField() {
        printHeader();
        //setAdjacentMines();

        for (int row = 0; row < numRows; row++) {

            String index = String.format("%1$2s", row + 1);
            System.out.print(index + " |");
            for (int column = 0; column < numColumns; column++) {

                System.out.print(getCell(row, column).getDisplayCharacter(this.getCell(row, column).adjacentMines)); // Prints either '?', 'number ' or '*'
                //checkAdjacentMines(row, column);
            }

            System.out.println();
        }
    }

    public void visibleField() {
        printHeader();

        for (int row = 0; row < numRows; row++) {
            String index = String.format("%1$2s", row - 1);
            System.out.print(index + " |");
            for (int column = 0; column < numColumns; column++) {
                getCell(row, column).setVisible(true);
                checkAdjacentMinesCascade(row, column);
                System.out.print(getCell(row, column).getDisplayCharacter(this.getCell(row, column).adjacentMines)); // Prints either '?', 'number ' or '*'
            }

            System.out.println();
        }

    }

    // Prints the top row (header)
    private void printHeader() {
        String headColumns = "   |";
        String headerLine = "---|";

        for (int i = 0; i < numColumns; i++) {
            String index = String.format("%1$2s", i + 1);
            headColumns = headColumns.concat(index + " ");
            headerLine = headerLine.concat("---");
        }
        System.out.println(headColumns);
        System.out.println(headerLine);
    }

    //Checks how many mines around a given cell
    public int checkAdjacentMinesCascade(int row, int column) {
        // Checks each direction for a mine
        if (!getCell(row, column).isVisible && !getCell(row, column).isMine) {

            getCell(row, column).setVisible(true);

            //System.out.println("NW is valid: " + isValidCell(row - 1, column - 1) + (row -1) + "," + (column-1));
            if (isValidCell(row - 1, column - 1)) {
                if (getCell(row - 1, column - 1).isMine) { // NW
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row - 1, column - 1).adjacentMines == 0) {

                    checkAdjacentMinesCascade(row - 1, column - 1);
                    getCell(row - 1, column - 1).setVisible(true);
                }
            }


            //System.out.println("N is valid: " + isValidCell(row, column - 1));
            if (isValidCell(row, column - 1)) {
                if (getCell(row, column - 1).isMine) { // N
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row, column - 1).adjacentMines == 0) {

                    checkAdjacentMinesCascade(row, column - 1);
                   // getCell(row, column - 1).setVisible(true);
                }
            }


            //System.out.println("NE is valid: " + isValidCell(row + 1, column - 1));
            if (isValidCell(row + 1, column - 1)) {
                if (getCell(row + 1, column - 1).isMine) { // NE
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row + 1, column - 1).adjacentMines == 0) {

                    checkAdjacentMinesCascade(row + 1, column - 1);
                    //getCell(row + 1, column - 1).setVisible(true);
                }
            }

            //System.out.println("E is valid: " + isValidCell(row + 1, column));
            if (isValidCell(row + 1, column)) {
                if (getCell(row + 1, column).isMine) { // E
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row + 1, column).adjacentMines == 0) {

                    checkAdjacentMinesCascade(row + 1, column);
                    //getCell(row + 1, column).setVisible(true);

                }
            }

            // System.out.println("SE is valid: " + isValidCell(row + 1, column + 1));
            if (isValidCell(row + 1, column + 1)) {
                if (getCell(row + 1, column + 1).isMine) { // SE
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row + 1, column + 1).adjacentMines == 0) {

                    checkAdjacentMinesCascade(row + 1, column + 1);
                    //getCell(row + 1, column + 1).setVisible(true);
                }
            }

            //System.out.println("S is valid: " + isValidCell(row, column - 1));
            if (isValidCell(row, column + 1)) {
                if (getCell(row, column + 1).isMine) { // S
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row, column + 1).adjacentMines == 0) {
                    checkAdjacentMinesCascade(row, column + 1);
                    //getCell(row, column + 1).setVisible(true);

                }
            }

            //System.out.println("SW is valid: " + isValidCell(row - 1, column + 1));
            if (isValidCell(row - 1, column + 1)) {
                if (getCell(row - 1, column + 1).isMine) { // SW
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row - 1, column + 1).adjacentMines == 0) {


                    checkAdjacentMinesCascade(row - 1, column + 1);
                    //getCell(row - 1, column + 1).setVisible(true);
                }
            }

            //System.out.println("W is valid: " + isValidCell(row - 1, column));
            if (isValidCell(row - 1, column)) {
                if (getCell(row - 1, column).isMine) { // W
                     this.getCell(row, column).adjacentMines++;
                } else if (this.getCell(row - 1, column).adjacentMines == 0) {


                    checkAdjacentMinesCascade(row - 1, column);
                    //getCell(row - 1, column).setVisible(true);
                }
            }
        }
        return getCell(row, column).adjacentMines;
    }
    public int checkAdjacentMines(int row, int column) {
        // Checks each direction for a mine
        if (!getCell(row, column).isVisible && !getCell(row, column).isMine) {

            getCell(row, column).setVisible(true);

            //System.out.println("NW is valid: " + isValidCell(row - 1, column - 1) + (row -1) + "," + (column-1));
            if (isValidCell(row - 1, column - 1)) {
                if (getCell(row - 1, column - 1).isMine) { // NW
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row - 1, column - 1).adjacentMines == 0) {

                    //checkAdjacentMines(row - 1, column - 1);
                    //getCell(row - 1, column - 1).setVisible(true);
                }
            }


            //System.out.println("N is valid: " + isValidCell(row, column - 1));
            if (isValidCell(row, column - 1)) {
                if (getCell(row, column - 1).isMine) { // N
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row, column - 1).adjacentMines == 0) {

                   // checkAdjacentMines(row, column - 1);
                    //getCell(row, column - 1).setVisible(true);
                }
            }


            //System.out.println("NE is valid: " + isValidCell(row + 1, column - 1));
            if (isValidCell(row + 1, column - 1)) {
                if (getCell(row + 1, column - 1).isMine) { // NE
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row + 1, column - 1).adjacentMines == 0) {

                    //checkAdjacentMines(row + 1, column - 1);
                    //getCell(row + 1, column - 1).setVisible(true);
                }
            }

            //System.out.println("E is valid: " + isValidCell(row + 1, column));
            if (isValidCell(row + 1, column)) {
                if (getCell(row + 1, column).isMine) { // E
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row + 1, column).adjacentMines == 0) {

                    //checkAdjacentMines(row + 1, column);
                    //getCell(row + 1, column).setVisible(true);

                }
            }

            // System.out.println("SE is valid: " + isValidCell(row + 1, column + 1));
            if (isValidCell(row + 1, column + 1)) {
                if (getCell(row + 1, column + 1).isMine) { // SE
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row + 1, column + 1).adjacentMines == 0) {

                    //checkAdjacentMines(row + 1, column + 1);
                    //getCell(row + 1, column + 1).setVisible(true);
                }
            }

            //System.out.println("S is valid: " + isValidCell(row, column - 1));
            if (isValidCell(row, column + 1)) {
                if (getCell(row, column + 1).isMine) { // S
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row, column + 1).adjacentMines == 0) {
                    //checkAdjacentMines(row, column + 1);
                   // getCell(row, column + 1).setVisible(true);

                }
            }

            //System.out.println("SW is valid: " + isValidCell(row - 1, column + 1));
            if (isValidCell(row - 1, column + 1)) {
                if (getCell(row - 1, column + 1).isMine) { // SW
                    this.getCell(row, column).addAdjacentMines();
                } else if (this.getCell(row - 1, column + 1).adjacentMines == 0) {


                    //checkAdjacentMines(row - 1, column + 1);
                    //getCell(row - 1, column + 1).setVisible(true);
                }
            }

            //System.out.println("W is valid: " + isValidCell(row - 1, column));
            if (isValidCell(row - 1, column)) {
                if (getCell(row - 1, column).isMine) { // W
                    this.getCell(row, column).adjacentMines++;
                } else if (this.getCell(row - 1, column).adjacentMines == 0) {


                    //checkAdjacentMines(row - 1, column);
                    //getCell(row - 1, column).setVisible(true);
                }
            }
        }
        return getCell(row, column).adjacentMines;
    }




    public boolean isValidCell(int row, int column) {
        // Take 1 from row and column to get correct values

        try {
            if (getCell(row, column) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

            //System.out.println("Invalid Cell at " + row + "," + column);

            return false;
        }

    }
}


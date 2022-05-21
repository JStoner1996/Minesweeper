public class Cells{

    // Attributes
    int visibleNumber;
    char character;
    boolean isFlag;
    boolean isMine;
    boolean isVisible;
    boolean isNumber;
    int adjacentMines = 0;

    public Cells() {


        this.visibleNumber = 0;
        //this.character = '?';
        this.isNumber = false;
        this.isFlag = false;
        this.isMine = false;
        this.isMine = false;
        this.isVisible = false;
        this.adjacentMines = 0;
    }

    // Methods
    public String getDisplayCharacter(int numAdjacentMines) {

        if (!this.isVisible) {
            return "[?]";
        } else if (this.isVisible && !this.isMine) {
            if (numAdjacentMines == 0) {
                return (" . ");
            }else {
                return (" " + numAdjacentMines + " ");
            }
        }
        else if (this.isMine) {
            return " * ";
        } else if (this.isFlag) {
            return " ! ";
        } else {
            return "   ";
        }
    }




    // Getters & Setters
    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;

    }
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    
    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public void addAdjacentMines() {
       this.adjacentMines++;

    }


}

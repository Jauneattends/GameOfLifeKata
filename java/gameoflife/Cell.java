package gameoflife;

public class Cell {
    private boolean isAlive;

    Cell() {
        this.isAlive = false;
    }

    Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String toString() {
        return isAlive ? "X" : ".";
    }

    boolean isAlive() {
        return isAlive;
    }

    void setIsAlive(boolean newState) {
        this.isAlive = newState;
    }

    static boolean processState(boolean isAlive, int nbNeighbourCellsAlive) {
        //Condition that checks if the cell has 2 or 3 neighbors and return 0 to not kill the cell
        if (!(isAlive && nbNeighbourCellsAlive == 2 || nbNeighbourCellsAlive == 3)) {
            isAlive = false;
        }
        //Condition that checks if the cell is dead and allows it to live
        if (!isAlive && nbNeighbourCellsAlive == 3) {
            isAlive = true;
        }
        //Return the value of "isAlive"
        return isAlive;
    }
}

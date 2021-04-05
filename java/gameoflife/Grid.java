package gameoflife;

import java.util.Random;

public class Grid {
    private Cell[][] cells;
    private int sizeGrid;
    private Random rd;
    private String res = "";
    private int count = 0;
    private Cell[][] tmp;
    private boolean statement;

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        this.cells = new Cell[sizeGrid][sizeGrid];
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
    }

    private void Corner(int a, int b) {
        if (a == 0 && b == 0) {
            // Cells at the top on the left
            if (tmp[a][b + 1].isAlive()) count++;
            if (tmp[a + 1][b + 1].isAlive()) count++;
            if (tmp[a + 1][b].isAlive()) count++;
            statement = Cell.processState(cells[a][b].isAlive(), count);
            cells[a][b].setIsAlive(statement);

        } else if (a == (sizeGrid - 1) && b == 0) {
            // Cells at the bottom on the left
            if (tmp[a - 1][b].isAlive()) count++;
            if (tmp[a - 1][b + 1].isAlive()) count++;
            if (tmp[a][b + 1].isAlive()) count++;
            statement = Cell.processState(cells[a][b].isAlive(), count);
            cells[a][b].setIsAlive(statement);

        } else if (a == (sizeGrid - 1) && b == (sizeGrid - 1)) {
            // Cells at the bottom on the right
            if (tmp[a][b - 1].isAlive()) count++;
            if (tmp[a - 1][b - 1].isAlive()) count++;
            if (tmp[a - 1][b].isAlive()) count++;
            statement = Cell.processState(cells[a][b].isAlive(), count);
            cells[a][b].setIsAlive(statement);

        } else if (a == 0 && b == (sizeGrid - 1)) {
            // Cells at the top on the right
            if (tmp[a + 1][b].isAlive()) count++;
            if (tmp[a + 1][b - 1].isAlive()) count++;
            if (tmp[a][b - 1].isAlive()) count++;
            statement = Cell.processState(cells[a][b].isAlive(), count);
            cells[a][b].setIsAlive(statement);

        }
    }

    private void Middle(int c, int d) {
        // Check cells around a cell[c][d] on the middle;
        if ((c > 0 && c != (sizeGrid - 1)) && (d > 0 && d != sizeGrid - 1)) {
            if (tmp[c - 1][d - 1].isAlive()) count++;
            if (tmp[c - 1][d].isAlive()) count++;
            if (tmp[c - 1][d + 1].isAlive()) count++;
            if (tmp[c][d + 1].isAlive()) count++;
            if (tmp[c + 1][d + 1].isAlive()) count++;
            if (tmp[c + 1][d].isAlive()) count++;
            if (tmp[c + 1][d - 1].isAlive()) count++;
            if (tmp[c][d - 1].isAlive()) count++;
            statement = Cell.processState(cells[c][d].isAlive(), count);
            cells[c][d].setIsAlive(statement);
        }
    }

    private void Check_left_side(int e, int f) {
        // Check cells around a cell[e][f] on the left side;
        if (e > 0 && e < (sizeGrid - 1) && f == 0) {
            if (tmp[e - 1][f].isAlive()) count++;
            if (tmp[e - 1][f + 1].isAlive()) count++;
            if (tmp[e][f + 1].isAlive()) count++;
            if (tmp[e + 1][f + 1].isAlive()) count++;
            if (tmp[e + 1][f].isAlive()) count++;
            statement = Cell.processState(cells[e][f].isAlive(), count);
            cells[e][f].setIsAlive(statement);
        }
    }

    private void Check_right_side(int g, int h) {
        // Check cells around a cell[g][h] on the right side;
        if (g > 0 && g < (sizeGrid - 1) && h == (sizeGrid - 1)) {
            if (tmp[g + 1][h].isAlive()) count++;
            if (tmp[g + 1][h - 1].isAlive()) count++;
            if (tmp[g][h - 1].isAlive()) count++;
            if (tmp[g - 1][h - 1].isAlive()) count++;
            if (tmp[g - 1][h].isAlive()) count++;
            statement = Cell.processState(cells[g][h].isAlive(), count);
            cells[g][h].setIsAlive(statement);
        }
    }

    private void Check_top_side(int i, int j) {
        // Check cells around a cell[i][j] on the top side;
        if (i == 0 && j > 0 && j < (sizeGrid - 1)) {
            if (tmp[i][j + 1].isAlive()) count++;
            if (tmp[i + 1][j + 1].isAlive()) count++;
            if (tmp[i + 1][j].isAlive()) count++;
            if (tmp[i + 1][j - 1].isAlive()) count++;
            if (tmp[i][j - 1].isAlive()) count++;
            statement = Cell.processState(cells[i][j].isAlive(), count);
            cells[i][j].setIsAlive(statement);
        }
    }

    private void Check_bottom_side(int k, int l) {
        // Check cells around a cell[k][l] on the bottom side;
        if (k == (sizeGrid - 1) && l > 0 && l < (sizeGrid - 1)) {
            if (tmp[k][l - 1].isAlive()) count++;
            if (tmp[k - 1][l - 1].isAlive()) count++;
            if (tmp[k - 1][l].isAlive()) count++;
            if (tmp[k - 1][l + 1].isAlive()) count++;
            if (tmp[k][l + 1].isAlive()) count++;
            statement = Cell.processState(cells[k][l].isAlive(), count);
            cells[k][l].setIsAlive(statement);
        }
    }

    private void generateRandomInitialState() {
        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {
                if (this.rd.nextInt() % 2 == 0) {
                    this.cells[i][j] = new Cell(false);
                } else {
                    this.cells[i][j] = new Cell(true);
                }
            }
        }
    }

    public void generateNextState() {
        //Initial a copy og cell
        this.tmp = new Cell[this.sizeGrid][this.sizeGrid];
        for (int a = 0; a < this.sizeGrid; a++) {
            for (int b = 0; b < this.sizeGrid; b++) {
                this.tmp[a][b] = new Cell(cells[a][b].isAlive());
            }
        }

        for (int i = 0; i < sizeGrid; i++) {
            for (int j = 0; j < sizeGrid; j++) {

                // Check cells on the each corner of the grid
                Corner(i, j);

                // Check cells on the middle of the grid
                Middle(i, j);

                // Check cells on the left side of the grid
                Check_left_side(i, j);

                // Check cells on the right side of the grid
                Check_right_side(i, j);

                // Check cells at the top side of the grid
                Check_top_side(i, j);

                // Check cells at the bottom side of the grid
                Check_bottom_side(i, j);

                count = 0;
            }
        }
    }

    public String toString() {
        //Loop that allows vertical increments
        for (int i = 0; i < sizeGrid; i++) {
            //Loop that allows to increment horizontally
            for (int j = 0; j < sizeGrid; j++) {
                //Condition to choose if you need a space or no
                if (j == (sizeGrid - 1)) {
                    res += cells[i][j];
                } else {
                    res += cells[i][j] + " ";
                }
            }
            //Condition to return to the line at the right time
            if (i < (sizeGrid - 1)) {
                res += "\n";
            }
        }

        return res;
    }
}

import java.util.Arrays;

public class Generation {

    protected char[] nextGen;
    protected int genCounter = 0;
    protected Cell currentCell;

    public Generation(Cell cell) {
        nextGen = new char[cell.getLength()];

        for (int i = 0; i < cell.getLength(); i++) {
            nextGen[i] = cell.getChar(i);
        }
        this.nextGen = nextGen;
        currentCell = new Cell(nextGen);
    }

    public Generation(Rule thisRule, Cell cell, Generation g) {

        char[] nextGen = new char[cell.getLength()];

        for (int i = 0; i < 1; i++) {
            char a = cell.getChar(cell.getLength() - 1);
            char b = cell.getChar(i);
            char c = cell.getChar(i + 1);
            nextGen[i] = thisRule.calculateRule(a, b, c);
        }

        for (int i = 1; i < cell.getLength() - 1; i++) {

            char a = cell.getChar(i - 1);
            char b = cell.getChar(i);
            char c = cell.getChar(i + 1);
            nextGen[i] = thisRule.calculateRule(a, b, c);

        }

        for (int i = cell.getLength() - 1; i > cell.getLength() - 2; i--) {
            char a = cell.getChar(cell.getLength() - 2);
            char b = cell.getChar(i);
            char c = cell.getChar(0);
            nextGen[i] = thisRule.calculateRule(a, b, c);
        }
        this.nextGen = nextGen;
        cell = new Cell(nextGen);
        currentCell = cell;

        genCounter++;

    }

    public int getGen() {
        return genCounter;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public char[] getChar() {
        return nextGen;
    }

    public char getCharElement(int i) {
        return nextGen[i];
    }

    @Override
    public String toString() {
        String nextGenString = "";
        for (int i = 0; i < nextGen.length; i++) {
            if (nextGen[i] == '0') {
                nextGenString += "0";
            }
            else if (nextGen[i] == '1') {
                nextGenString += "1";
            }
        }
        return nextGenString;
    }
}

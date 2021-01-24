/**
 * This class serves as the class that creates Cell constructors as well as their respective methods.
 * @author Jett Sturges
 * @version 1.0
 */

public class Cell {

    /**
     * The cells field variable is responsible for carrying out all initial operations in creating and transforming cells.
     */

    protected char[] cells;

    /**
     * This is a Cell constructor that takes in a String argument. It takes in a String, parses each element and creates
     * a new, equivalent char array.
     * @param s the String argument.
     */

    public Cell(String s) {
        cells = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cells[i] = s.charAt(i);
        }
    }

    /**
     * This is a Cell constructor that takes in a boolean array argument. It takes in a boolean array, parses each element
     * and create a new, equivalent char array full of 1s and 0s. The true/false values are read as '1' and '0', respectively.
     * @param nextGen the boolean array argument.
     */

    public Cell(boolean[] nextGen) {
        cells = new char[nextGen.length];
        for (int i = 0; i < cells.length; i++) {
            if (nextGen[i] == true) {
                cells[i] = '1';
            }
            else if (nextGen[i] == false) {
                cells[i] = '0';
            }
        }
    }

    /**
     * This is a Cell constructor that takes in a char array argument. It takes in a char array, parses each element
     * and create a new, equivalent char array.
     * @param nextGen the char array argument.
     */

    public Cell(char[] nextGen) {
        cells = new char[nextGen.length];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = nextGen[i];
        }
    }

    /**
     * This is a method that takes in an int index argument. It takes in an index, and returns the element specified at
     * that index for the given char array.
     * @param index the int argument.
     * @return cells[index] the element at cells[index].
     */

    public char getChar(int index) {
        return cells[index];
    }

    /**
     * This is a method that returns the length of a given char array of cells.
     * @return cells.length;
     */

    public int getLength() {
        return cells.length;
    }



}

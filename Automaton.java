/**
 * This class serves as the class that creates Automaton constructors as well as their respective methods.
 * @author Jett Sturges
 * @version 1.0
 */

import java.io.*;
import java.util.ArrayList;

public class Automaton {

    /**
     * These are the field variables required to make the Automaton complete.
     */

    protected int ruleNum;
    protected char trueSymbol;
    protected char falseSymbol;
    protected Rule currentRule;
    protected Cell currentCell;
    protected Generation currentGen;
    protected Generation initialGen;
    protected ArrayList<String> initFile = new ArrayList<String>();
    protected ArrayList<Generation> generations = new ArrayList<Generation>();

    /**
     * This is an Automaton constructor that takes in an int argument and a boolean array argument.
     * @param ruleNum the int argument.
     * @param initState the boolean array argument.
     */

    public Automaton(int ruleNum, boolean[] initState) {
            this.ruleNum = ruleNum;
            Rule thisRule = new Rule(ruleNum);
            setFalseSymbol('0');
            setTrueSymbol('1');
            currentRule = thisRule;
            Cell firstCell = new Cell(initState);
            currentCell = firstCell;
            initialGen = new Generation(currentCell);
            generations.add(initialGen);
            currentGen = initialGen;
            currentRule.setTrueFalseDefault(getTrueSymbol(), getFalseSymbol());
            currentRule.makeTFArray();
    }

    /**
     * This is an Automaton constructor that takes in a String argument read in from a .txt file.
     * I created a helper method load(filename) to handle most of the work reading/parsing the file and its contents.
     * I made a wrap around to the Rule class to extract and set true and false values from this Automaton
     * class into the Rule class.
     * @param filename the String argument.
     */

    public Automaton(String filename) throws IOException {
            load(filename);
            currentRule.setTrueFalse(filename);
            currentRule.makeTFArray();
    }

    /**
     * This is a method that returns an int.
     * @return int that represents the decimal representation of the current rule number.
     */

    public int getRuleNum() {
        return this.ruleNum;
    }

    /**
     * This is a method that evolves a current state (row) of cells a certain number of steps.
     * @param numSteps the number of steps/generations you want to evolve the current cell to.
     * @return void.
     */

    public void evolve(int numSteps) {
        for (int i = 0; i < numSteps; i++) {
            if (currentGen.getGen() == 0) {
                currentGen = new Generation(currentRule, currentCell, initialGen);
                generations.add(currentGen);
                currentCell = currentGen.getCurrentCell();
            }
            else {
                currentGen = new Generation(currentRule, currentCell, currentGen);
                generations.add(currentGen);
                currentCell = currentGen.getCurrentCell();
            }
        }
    }

    /**
     * This is a method that returns an int.
     * @return int that represents the decimal representation of the current total number of steps from the first
     * generation.
     */

    public int getTotalSteps() {
        return currentGen.getGen();
    }

    /**
     * This is a method that returns a boolean array.
     * @param stepNum the number of steps (generation) that represents the state of evolved cells.
     * @return boolean array that represents the current state of a row of cells for any given generation (stepNum).
     */

    public boolean[] getState(int stepNum) {
        boolean[] b = new boolean[generations.get(stepNum).getChar().length];
        for (int i = 0; i < generations.get(stepNum).getChar().length; i++) {
            if (generations.get(stepNum).getCharElement(i) == trueSymbol) {
                b[i] = true;
            }
            else if (generations.get(stepNum).getCharElement(i) == falseSymbol) {
                b[i] = false;
            }
        }
        return b;
    }

    /**
     * This is a method that returns a String.
     * @param stepNum the number of steps (generation) that represents the state of evolved cells.
     * @return String that represents the current state of a row of cells for any given generation (stepNum) with their
     * respective true/false symbols.
     */

    public String getStateString(int stepNum) {
        String temp =  "";
        for (int i = 0; i < generations.get(stepNum).getChar().length; i++) {
            if (generations.get(stepNum).getCharElement(i) == '1') {
                temp += this.getTrueSymbol();
            }
            else {
                temp += this.getFalseSymbol();
            }
        }
        return temp;
    }

    /**
     * This is a method that returns a String.
     * @return String that represents the total rows of cells for all given generations.
     */

    @Override
    public String toString() {
        String temp = "";
        temp = getStateString(0);
        for (int i = 1; i < generations.size(); i++) {
            temp += "\n";
            temp += this.getStateString(i);
        }
        return temp;
    }

    /**
     * This is a method that writes and saves the all generations to a file.
     * @param filename the new file name that is to be written to.
     * @return void.
     */

    public void save(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        File file = new File(filename);

        String ECA = this.toString();

        bw.write(ECA);

        bw.close();
    }

    /**
     * This is a method that returns a char.
     * @return char that represents the current false symbol of the automaton.
     */

    public char getFalseSymbol() {
        return this.falseSymbol;
    }

    /**
     * This is a method that sets a false symbol using a char.
     * @param symbol the symbol used to be set false.
     * @return void.
     */

    public void setFalseSymbol(char symbol) {
        falseSymbol = symbol;
    }

    /**
     * This is a method that returns a char.
     * @return char that represents the current true symbol of the automaton.
     */

    public char getTrueSymbol() {
        return this.trueSymbol;
    }

    /**
     * This is a method that sets a true symbol using a char.
     * @param symbol the symbol used to be set true.
     * @return void.
     */

    public void setTrueSymbol(char symbol) {
        trueSymbol = symbol;
    }

    /**
     * This is a helper method for the Automaton constructor that reads in a file and parses the data.
     * @param filename the file that is to be read in.
     * @return void.
     */

    private void load(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String info;

        while ((info = br.readLine()) != null) {
            initFile.add(info);
        }

        String ruleNumS = initFile.get(0);
        ruleNum = Integer.parseInt(ruleNumS);
        String[] boolParts = initFile.get(1).split(" ", 2);
        String bool1 = boolParts[0];
        String bool2 = boolParts[1];
        char f = bool1.charAt(0);
        char t = bool2.charAt(0);
        setFalseSymbol(f);
        setTrueSymbol(t);
        currentRule = new Rule(ruleNum);
        currentCell = new Cell(initFile.get(2));
        initialGen = new Generation(currentCell);
        generations.add(initialGen);
        currentGen = initialGen;
    }

}

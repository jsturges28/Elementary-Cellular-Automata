/**
 * This class serves as the class that creates Rule constructors as well as their respective methods.
 * @author Jett Sturges
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Rule {

    /**
     * These are the field variables required to make the Rule constructor complete.
     */

    protected char[] charArray = new char[8];
    protected char[] symbolArray = new char[8];
    protected ArrayList<String> initFile = new ArrayList<String>();
    protected char f;
    protected char t;

    /**
     * This is a Rule constructor that takes in an int argument. It takes in an int and creates the String binary
     * representation of the decimal rule number argument, and stores it into a char array.
     * @param ruleNum the int argument.
     */

    public Rule(int ruleNum) {

        String binaryString = Integer.toBinaryString(ruleNum);
        binaryString = String.format("%08d", Integer.parseInt(binaryString));
        for (int i = 0; i < binaryString.length(); i++) {
            charArray[i] = binaryString.charAt(i);
        }
    }

    /**
     * This is a method that determines the true/false values in the Rule class. If an element in the char array is '0',
     * then it is set to false. If it is '1' then it is set to true.
     * @return void.
     */

    public void makeTFArray() {
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '0') {
                symbolArray[i] = f;
            }
            else if (charArray[i] == '1') {
                symbolArray[i] = t;
            }
        }
    }

    /**
     * This is a method that calculates the next generation of cells depending on the rule implemented. Given 3 arbitrary
     * cells in a generation, depending on their true/false state from the given characters, the next cell in the "middle"
     * of the 3 arbitrary cells will be the result of the states of those 3 cells. This is calculated for every cell in
     * the initial row, and yields a new row when implemented by the Generation class.
     * @param a the first character.
     * @param b the second character.
     * @param c the third character.
     * @return symbolArray[i].
     */

    public char calculateRule(char a, char b, char c) {
            if      (a == t && b == t && c == t) return symbolArray[0];
            else if (a == t && b == t && c == f) return symbolArray[1];
            else if (a == t && b == f && c == t) return symbolArray[2];
            else if (a == t && b == f && c == f) return symbolArray[3];
            else if (a == f && b == t && c == t) return symbolArray[4];
            else if (a == f && b == t && c == f) return symbolArray[5];
            else if (a == f && b == f && c == t) return symbolArray[6];
            else if (a == f && b == f && c == f) return symbolArray[7];
            return 0;
    }

    /**
     * This is an alternative method that determines the true/false values in the Rule class. Given a file, there are two
     * values at index 1 of the FileReader. The element at index 0 will be set to false, while the element at index 1 will
     * be set to true.
     * @param filename the file name of the String.
     * @return void.
     */

    public void setTrueFalse(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String info;

        while ((info = br.readLine()) != null) {
            initFile.add(info);
        }
        String[] boolParts = initFile.get(1).split(" ", 2);
        String bool1 = boolParts[0];
        String bool2 = boolParts[1];
        f = bool1.charAt(0);
        t = bool2.charAt(0);
    }

    /**
     * This is a method that sets the true/false values in the Rule class when given two char arguments.
     * @param a the true character.
     * @param b the false character.
     */

    public void setTrueFalseDefault(char a, char b) {
        t = a;
        f = b;
    }

}






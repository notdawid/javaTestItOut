import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class NewerIntToRomanNumeral {

    public static void main(String[] args) {

        //Store known roman numerals in a hashmap
        LinkedHashMap romanNumberalMap = loadRomanNumeralMap();
        //Obtain scanner to read input
        Scanner scan = new Scanner(System.in);

        //Ask user for number
        System.out.print("Enter a number between 1 - 3999 (enter 0 to test 1-3999) : ");

        //Store to a variable, surround with a try catch to make sure user only enters a number
        int numberToConvert = 0;
        try {
            numberToConvert = Integer.parseInt(scan.next());
        } catch (NumberFormatException e) {
            System.out.println("Please ensure you only enter an integer");
            System.exit(1);
        }

        //run test if input is 0
        if (numberToConvert == 0) {
            runTest(romanNumberalMap);
            System.exit(0);
        }

        //Check to make sure number is between the specified range
        if (!(numberToConvert > 0 && numberToConvert < 4000)) {
            System.out.println("Please make sure the number entered is between 1 and 3999");
            System.exit(1);
        }

        //method to perform coversion
        convertToRomanNumeral(numberToConvert, romanNumberalMap);
    }

    //test for program
    private static void runTest(LinkedHashMap romanNumeralMap) {
        for (int i = 1; i < 4000; i++) {
            convertToRomanNumeral(i, romanNumeralMap);
            System.out.println();
        }
    }

    //Initial hashmap with known Roman Numeral values, using linkedhashmap for order preservation
    private static LinkedHashMap loadRomanNumeralMap() {
        LinkedHashMap romanNumeralMap = new LinkedHashMap();
        romanNumeralMap.put(1000, "M");
        romanNumeralMap.put(900, "CM");
        romanNumeralMap.put(500, "D");
        romanNumeralMap.put(400, "CD");
        romanNumeralMap.put(100, "C");
        romanNumeralMap.put(90, "XC");
        romanNumeralMap.put(50, "L");
        romanNumeralMap.put(40, "XL");
        romanNumeralMap.put(10, "X");
        romanNumeralMap.put(9, "IX");
        romanNumeralMap.put(5, "V");
        romanNumeralMap.put(4, "IV");
        romanNumeralMap.put(1, "I");
        return romanNumeralMap;
    }

    //Method that will perform conversion
    private static void convertToRomanNumeral(int numberToConvert, LinkedHashMap romanNumberalMap) {
        String output = "";
        int newInt = numberToConvert;
        int remainder = numberToConvert;

        Iterator iterator = romanNumberalMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry curEntry = (Map.Entry) iterator.next();
            newInt = remainder / (int) curEntry.getKey();
            if (newInt > 0) {
                for (int i = 0; i < newInt; i++) {
                    output = output.concat(curEntry.getValue().toString());
                }
                remainder = remainder % (int) curEntry.getKey();
            }
        }


        System.out.println(String.format("Input: %s", numberToConvert));
        System.out.println(String.format("Input number in roman numeral representation: %s",
                output));

    }
}

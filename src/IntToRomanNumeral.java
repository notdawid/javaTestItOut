import java.util.*;

public class
IntToRomanNumeral {

    public static void main(String[] args) {

        //Store known roman numerals in a hashmap
        HashMap romanNumberalMap = loadRomanNumeralMap();
        //Obtain scanner to read input
        Scanner scan = new Scanner(System.in);

        //Ask user for number
        System.out.print("Enter a number between 1 - 3999 (enter 0 to test " +
                "1-3999) : ");

        //Store to a variable, surround with a try catch to make sure user
        // only enters a number
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
            System.out.println("Please make sure the number entered is " +
                    "between 1 and 3999");
            System.exit(1);
        }

        //Parse input Integer and separate into HashMap
        HashMap digits = parseDigits(numberToConvert);

        //Print details of parsed digits
        printDetails(digits);

        //method to perform coversion
        convertToRomanNumeral(numberToConvert, digits, romanNumberalMap);
    }

    //test for program
    private static void runTest(HashMap romanNumeralMap) {
        for (int i = 1; i < 4000; i++) {
            HashMap digits = parseDigits(i);
            convertToRomanNumeral(i, digits, romanNumeralMap);
            System.out.println();
        }
    }

    //Initial hashmap with known Roman Numeral values
    private static HashMap loadRomanNumeralMap() {
        HashMap romanNumeralMap = new HashMap();
        romanNumeralMap.put(1, "I");
        romanNumeralMap.put(5, "V");
        romanNumeralMap.put(10, "X");
        romanNumeralMap.put(50, "L");
        romanNumeralMap.put(100, "C");
        romanNumeralMap.put(500, "D");
        romanNumeralMap.put(1000, "M");
        return romanNumeralMap;
    }

    //method to parse out ones, tens, hundreds, and thousands from input
    private static HashMap parseDigits(int numberToConvert) {
        HashMap digitMap = new HashMap();
        //Obtain the place of each integer
        digitMap.put("ones", numberToConvert % 10);
        digitMap.put("tens", (numberToConvert % 100) / 10);
        digitMap.put("hundreds", (numberToConvert % 1000) / 100);
        digitMap.put("thousands", (numberToConvert % 10000) / 1000);

        return digitMap;

    }

    //method to print out the details in the hashmap submitted
    private static void printDetails(HashMap digits) {

        //Print out number split out
        System.out.println(String.format("In the ones place you have: %s",
                digits.get("ones")));
        System.out.println(String.format("In the tens place you have: %s",
                digits.get("tens")));
        System.out.println(String.format("In the hundreds place you have: " +
                "%s", digits.get("hundreds")));
        System.out.println(String.format("In the thousands place you have: " +
                "%s", digits.get("thousands")));
    }

    //Method that will perform conversion
    private static void convertToRomanNumeral(int numberToConvert, HashMap
            digits, HashMap romanNumberalMap) {

        //Obtain the place of each integer
        int ones = (int) digits.get("ones");
        int tens = (int) digits.get("tens");
        int hundreds = (int) digits.get("hundreds");
        int thousands = (int) digits.get("thousands");

        String output = "";

        //Case statement to handle tens
        switch (thousands) {
            case 1:
                output = romanNumberalMap.get(1000).toString();
                break;
            case 2:
            case 3:
                while (thousands > 0) {
                    output = output.concat(romanNumberalMap.get(1000)
                            .toString());
                    thousands--;
                }
                break;
        }

        //Case statement to handle hundreds
        switch (hundreds) {
            case 1:
                output = output.concat(romanNumberalMap.get(100).toString());
                break;
            case 2:
            case 3:
                while (hundreds > 0) {
                    output = output.concat(romanNumberalMap.get(100).toString
                            ());
                    hundreds--;
                }
                break;
            case 4:
                output = output.concat(romanNumberalMap.get(100).toString()
                        .concat(romanNumberalMap.get(500).toString()));
                break;
            case 5:
                output = output.concat(romanNumberalMap.get(500).toString());
                break;
            case 6:
            case 7:
            case 8:
                output = output.concat(romanNumberalMap.get(500).toString());
                while (hundreds > 5) {
                    output = output.concat(romanNumberalMap.get(100).toString
                            ());
                    hundreds--;
                }
                break;
            case 9:
                output = output.concat(romanNumberalMap.get(100).toString()
                        .concat(romanNumberalMap.get(1000).toString()));
                break;
        }

        //Case statement to handle tens
        switch (tens) {
            case 1:
                output = output.concat(romanNumberalMap.get(10).toString());
                break;
            case 2:
            case 3:
                while (tens > 0) {
                    output = output.concat(romanNumberalMap.get(10).toString());
                    tens--;
                }
                break;
            case 4:
                output = output.concat(romanNumberalMap.get(10).toString()
                        .concat(romanNumberalMap.get(50).toString()));
                break;
            case 5:
                output = output.concat(romanNumberalMap.get(50).toString());
                break;
            case 6:
            case 7:
            case 8:
                output = output.concat(romanNumberalMap.get(50).toString());
                while (tens > 5) {
                    output = output.concat(romanNumberalMap.get(10).toString());
                    tens--;
                }
                break;
            case 9:
                output = output.concat(romanNumberalMap.get(10).toString()
                        .concat(romanNumberalMap.get(100).toString()));
                break;
        }

        //Case statement to handle ones
        switch (ones) {
            case 1:
                output = output.concat(romanNumberalMap.get(1).toString());
                break;
            case 2:
            case 3:
                while (ones > 0) {
                    output = output.concat(romanNumberalMap.get(1).toString());
                    ones--;
                }
                break;
            case 4:
                output = output.concat(romanNumberalMap.get(1).toString()
                        .concat(romanNumberalMap.get(5).toString()));
                break;
            case 5:
                output = output.concat(romanNumberalMap.get(5).toString());
                break;
            case 6:
            case 7:
            case 8:
                output = output.concat(romanNumberalMap.get(5).toString());
                while (ones > 5) {
                    output = output.concat(romanNumberalMap.get(1).toString());
                    ones--;
                }
                break;
            case 9:
                output = output.concat(romanNumberalMap.get(1).toString()
                        .concat(romanNumberalMap.get(10).toString()));
                break;
        }

        System.out.println(String.format("Input: %s", numberToConvert));
        System.out.println(String.format("Input number in roman numeral " +
                "representation: %s", output));

    }
}

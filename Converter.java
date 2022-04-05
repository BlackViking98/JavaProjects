import java.util.InputMismatchException;
import java.util.Scanner;

public class Converter {
    public static Scanner in = new Scanner(System.in);
    public static Integer input;
    public static Integer max = Integer.MAX_VALUE;

    public static void main(String[] args) throws InterruptedException {
        boolean workingFlag = true;

        while (workingFlag) {
            System.out.println("\nChoose action:");
            System.out.println("1. Conversion from decimal (10) to base hexadecimal (16) ");
            System.out.println("2. Conversion from decimal (10) to binary (2)");
            System.out.println("3. Conversion from binary (2) to decimal (10)");
            System.out.println("4. Conversion from decimal (10) to any entered numeral system");
            System.out.println("5. Exit console application");
            String n = in.nextLine();
            String output = "";

            switch (n) {
                case "1":
                    if (enter()) {
                        output = Integer.toString(input, 16).toUpperCase();
                        System.out.println("Converted " + input + " to hexadecimal :" + output);
                    }
                    break;
                case "2":
                    if (enter()) {
                        output = Integer.toString(input, 2);
                        System.out.println("Converted " + input + " to binary: " + output);
                    }
                    break;
                case "3":
                    try {
                        System.out.println("Please enter the number to be converted:");
                        input = in.nextInt();
                        Integer num = Integer.parseInt(input.toString(), 2);
                        in.nextLine();
                        System.out.println("Converted " + input + " to decimal: " + num.toString());
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong number format! You can use only 0 and 1 digits in sequence.");
                        in.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong number format! You can use numbers from 0 to 1111111111 and only 0 and 1 digits in sequence.");
                        in.nextLine();
                    }
                    break;
                case "4":
                    if (enter()) {
                        System.out.println("Please enter the number system to be converted to (maximum is 32):");
                        Integer ultimateNum = in.nextInt();
                        in.nextLine();
                        if (input < 0) {
                            System.out.println("Number is bigger than 10000 or less than 0, please enter again.");
                            break;
                        }
                        if (ultimateNum > 32 || ultimateNum < 2) {
                            System.out.println("Entered number system is bigger than 32 or less than 2.");
                            break;
                        } else {
                            output = Integer.toString(input, ultimateNum).toUpperCase();
                            System.out.println("Converted " + input + " to " + ultimateNum + "-decimal number system: " + output);
                        }
                    }
                    break;
                case "5":
                    workingFlag = false;
                    System.out.println("Closing application...");
                    Thread.sleep(1000);
                    break;
                default:
                    System.out.println("Wrong input! Please enter again.\n");
                    break;
            }
        }

    }


    public static boolean enter() {
        try {
            System.out.println("Please enter the number from 0 to 10000 to be converted:");
            input = in.nextInt();
            in.nextLine();
            if (input < 0 || input > 10000) {
                System.out.println("Number is bigger than 10000 or less than 0, please enter again.");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Wrong number format! You can use only digits from 0 to " + max + "!");
            in.nextLine();
            return false;
        }
    }
}

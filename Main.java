import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static boolean isWorking = true;
    public static String filePath1 = "C:/Users/Public/Documents/file1.txt";
    public static String filePath2 = "C:/Users/Public/Documents/file2.txt";

    public static void main(String[] args) throws IOException {
        findFile();
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Console Dictionary!\n");
        while (isWorking) {
            boolean dictionaryIsNotChosen = true;
            while (dictionaryIsNotChosen) {
                System.out.println("Choose action:\n1. Open Latin-Russian Dictionary\n2. Open Numeric-Russian Dictionary\n3. Exit from console app");
                int dictNumber = 0;
                try {
                    dictNumber = in.nextInt();
                } catch (Exception e) {
                    System.out.println("Incorrect input!");
                }
                in.nextLine();
                if (dictNumber == 1) {
                    dictionaryIsNotChosen = false;
                    dictionaryUsage(dictNumber, filePath1, in);
                } else if (dictNumber == 2) {
                    dictionaryIsNotChosen = false;
                    dictionaryUsage(dictNumber, filePath2, in);
                } else if (dictNumber == 3) {
                    System.out.print("Are you sure? y/n: ");
                    String workYN = in.nextLine();
                    workYN = workYN.toLowerCase();
                    if (workYN.charAt(0) == 'y') {
                        isWorking = false;
                        dictionaryIsNotChosen = false;
                    } else if (workYN.charAt(0) == 'n') {
                        isWorking = true;
                    } else {
                        System.out.println("Unknown character!\n");
                    }
                } else {
                    System.out.println("Please, enter right number\n");
                }
            }
        }
        in.close();
        System.out.println("\nClosing the Dictionary...");
    }


    // метод работы со словарем
    private static void dictionaryUsage(int dictNumber, String filepath, Scanner in) {
        Dictionary dict = new Dictionary(dictNumber, filepath);
        int dictMenu;
        String word;
        boolean flag = true;
        while (flag) {
            System.out.println("Choose action for dictionary #"+dictNumber+":\n1)Read all pairs key-value\n2)Search value\n3)Add value\n4)Delete value\n5)Go back to main menu");
            dictMenu = in.nextInt();
            in.nextLine();
            switch (dictMenu) {
                case 1: // Read all data from dictionary
                    System.out.println("Dictionary #" + dictNumber + " :\n");
                    dict.readFile();
                    System.out.println("\n\n");
                    break;
                case 2: //Search value
                    System.out.print("Enter key you want to find: ");
                    word = in.nextLine();
                    dict.searchStr(word);
                    break;
                case 3: //Add value
                    System.out.print("Enter key: ");
                    String key = in.nextLine();
                    System.out.print("Enter value: ");
                    String value = in.nextLine();
                    dict.writeToMap(key, value);
                    break;
                case 4: //Delete value
                    System.out.print("Enter key you want to delete: ");
                    word = in.nextLine();
                    dict.removeWord(word);
                    break;
                case 5: //Go back to main menu
                    System.out.println("Main menu");
                    flag = false;
                    break;
                default:
                    System.out.println("Action is not chosen!");
            }
        }
    }


    //метод проверки наличия файла в системе
    private static void findFile() {
        System.out.println("Checking txt files in path...");
        if (Files.exists(Paths.get(filePath1))) {
            System.out.println("Dictionary #1 found");
        } else {
            System.out.println("Dictionary #1 is not found, creating file in path C:/Users/Public/Documents");
            File dir = new File(filePath1);
            try {
                boolean created = dir.createNewFile();
                if (created)
                    System.out.println("File #1 has been created\n");
            } catch (IOException e) {
                e.printStackTrace();
                isWorking = false;
            }

        }
        if (Files.exists(Paths.get(filePath2))) {
            System.out.println("Dictionary #2 found");
        } else {
            System.out.println("Dictionary #2 is not found, creating file in path C:/Users/Public/Documents");
            File dir = new File(filePath2);
            try {
                boolean created = dir.createNewFile();
                if (created)
                    System.out.println("File #2 has been created\n");
            } catch (IOException e) {
                e.printStackTrace();
                isWorking = false;
            }
        }
    }
}

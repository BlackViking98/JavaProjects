import java.io.*;
import java.util.*;

public class Dictionary {

    Map<String, String> map = new HashMap<String, String>();
    String filePath;
    private int number;
    private int ignoreCounter=0;

    //конструктор
    Dictionary(int number, String filePath) {
        if (number == 1) {
            this.filePath = filePath;
            this.number = number;
        } else if (number == 2) {
            this.filePath = filePath;
            this.number = number;
        }
    }

//список публичных методов

    //поиск слова в словаре
    void searchStr(String str) {
        putDataInMap();
        if (this.map.containsKey(str)) {
            System.out.println("Value of key \"" + str + "\" is \"" + this.map.get(str) + "\"\n");
        } else {
            System.out.println("Key \"" + str + "\" is not found in this dictionary\n");
        }
    }

    //чтение всех пар в файле
    void readFile() {
        putDataInMap();
        for (String key : map.keySet()) {
            System.out.println(key + "-" + map.get(key));
        }
        if (ignoreCounter>0)
        System.out.println("\nService is ignoring lines with wrong format. Total count of ignored lines: "+ignoreCounter);
    }

    //добавление записи при условии соответствия требованиям конкретного словаря
    void writeToMap(String key, String value) {
        putDataInMap();
        if (this.number == 1) {
            boolean keyHaveOnlyLatin = key.matches("[A-Za-z]+");
            boolean valueHaveOnlyLatin = value.matches("[A-Za-z]+");
            if (key.length() > 4 || value.length() > 4 || !keyHaveOnlyLatin || !valueHaveOnlyLatin) {
                System.out.println("Unsupported data! Key/value can contain only string with 4 letters of latin alphabet\n");
            } else if (this.map.containsKey(key)) {
                System.out.println("Value with key \"" + key + "\" is already exists!\n");
            } else {
                this.map.put(key, value);
                System.out.println("Key \"" + key + "\" with value \"" + value + "\" added successfully!\n");
                putMapInFile();
            }

        } else if (this.number == 2) {
            boolean keyHaveOnlyNumber = key.matches("\\d+");
            boolean valueHaveOnlyNumber = value.matches("\\d+");
            if (key.length() > 5 || value.length() > 5 || !keyHaveOnlyNumber || !valueHaveOnlyNumber) {
                System.out.println("Unsupported data! Key/value can contain only string with 5 digits.\n");
            } else if (this.map.containsKey(key)) {
                System.out.println("Value with key \"" + key + "\" is already exists!\n");
            } else {
                this.map.put(key, value);
                System.out.println("Key \"" + key + "\" with value \"" + value + "\" added successfully!\n");
                putMapInFile();
            }
        }
    }

    //удаление записи по ключу
    void removeWord(String str) {
        putDataInMap();
        if (this.map.containsKey(str)) {
            this.map.remove(str);
            System.out.println("Value with key \"" + str + "\" is deleted\n");
        } else {
            System.out.println("Key \"" + str + "\" is not found in this dictionary\n");
        }
        putMapInFile();
    }


    //приватные методы для работы внутри словаря

    //получение данных из тектового файла
    private void putDataInMap() {
        String line = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.filePath));
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] parts = line.split("-", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            } else {
                this.ignoreCounter++;
                // System.out.println("ignoring line: " + line + " - this line is not correct!"); //расскомментировать, если нужно отображать неверные записи в текстовом файле
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putMapInFile() {
        // new file object
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.filePath));
            // итерируем map entries
            for (Map.Entry<String, String> entry :
                    map.entrySet()) {
                writer.write(entry.getKey() + "-" + entry.getValue()); // вводим ключ со значением через разделитель, указанный в ""
                writer.newLine(); // новая строка
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
        this.map.clear(); //очищаем во избежание дублирования информации
    }
}
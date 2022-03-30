import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class Searcher {

    public static Map<Path, String> files = new HashMap<>();
    public static List<Path> foundFiles;
    public static Path target = Path.of("C:\\DirectoryToDelete");
    public static SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
    public static boolean deleteForever = false;

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        String s = "";
        String menu = "";
        boolean flag = true;
        boolean menuFlag = true;



        if (!(new File("C:\\DirectoryToDelete").exists())) {
            new File("C:\\DirectoryToDelete").mkdir();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "windows-1251"));


        info();
        searchFileVisitor.setPartOfName("");
        Files.walkFileTree(target, searchFileVisitor);
        foundFiles = searchFileVisitor.getFoundFiles();

        while (menuFlag) {
            System.out.println("\nChoose action and enter number:\n1) Enter a part of file name to search\n2) Choose path to search (inner catalogues will be checked too)\n3) Show list of found files\n4) Enter target path for deleting\n5) Look info\n6) Change deleting type (Current: delete forever-"+deleteForever+")\n7) Exit app");
            menu = reader.readLine();

            switch (menu) {
                case "1":
                    System.out.println("Write a part of file's name: ");
                    s = reader.readLine();
                    searchFileVisitor.setPartOfName(s);
                    // searchFileVisitor.setPartOfContent("programmer");
                    // searchFileVisitor.setMinSize(1);
                    // searchFileVisitor.setMaxSize(Integer.MAX_VALUE);
                    break;

                case "2":
                    foundFiles.clear();
                    boolean case2flag = true;
                    System.out.println("Write path where we will search for files: ");
                    String chosenPath = reader.readLine();
                    while (!(new File(chosenPath).isDirectory())&&case2flag) {
                        //source = Path.of("C:\\");
                        System.out.println("Entered file is not a directory! Enter directory again or enter \"1\" for exit:");
                        chosenPath = reader.readLine();
                        if (chosenPath.equals("1")){
                            case2flag=false;
                            chosenPath=target.toString();
                            System.out.println("Chosen path:"+chosenPath);
                        }
                    }

                    long start = System.currentTimeMillis();
                    System.out.println("Searching...");

                    Files.walkFileTree(Paths.get(chosenPath), searchFileVisitor);
                    foundFiles = searchFileVisitor.getFoundFiles();

                    long finish = System.currentTimeMillis();
                    long elapsed = finish - start;
                    System.out.println("Searching time, ms: " + elapsed + "-> " + String.format("%.2f", (float) elapsed / 60000) + " min");
                    if (foundFiles.size() <= 0) {
                        System.out.println("Files not found!");
                    } else {
                        System.out.println("Amount of found files: " + foundFiles.size());
                    }
                    break;

                case "3":
                    flag = true;
                    while (flag) {
                        filesOut();
                        if (files.size() == 0) {
                            flag = false;
                            System.out.println("Files not found!");
                        } else {
                            System.out.println("\nCopy and enter path to remove file from directory or enter \"1\" for exit:");
                            s = reader.readLine();
                            s=s.trim();
                            if (s.equals("1")) {
                                flag = false;
                            } else {
                                Path newPath = Path.of(s);
                                if (target.equals(newPath.getParent())&&!deleteForever){
                                    System.out.println("\nFailed to delete! Target and source path cannot be same. Press any key to continue.");
                                    String temp3 = reader.readLine();
                                    break;
                                }
                                try {
                                    boolean success = newPath.toFile().renameTo(new File(target.toString().trim() + "\\" + newPath.getFileName().toString()));
                                    if (success) {
                                        if (!deleteForever){
                                            System.out.println("File |" + newPath.getFileName() + "| deleted!");
                                        } else if (deleteForever){
                                            System.out.println("\n\nAre you sure? File will be deleted forever. \nEnter \"y\" to delete or another key to stop.");
                                            s = reader.readLine();
                                            if (s.equals("y")){
                                                newPath.toFile().delete(); //deleting chosen file
                                                System.out.println("File |" + newPath.getFileName() + "| deleted forever!");
                                            } else {
                                                break;
                                            }
                                        }
                                        foundFiles.remove(newPath); //deleting path from list
                                        files.remove(newPath); //deleting file from list
                                        Thread.sleep(1000);
                                    } else {
                                        System.out.println("Failed to delete! Press any key to continue.");
                                        String temp3 = reader.readLine();
                                    }

                                } catch (NumberFormatException e) {
                                    System.out.println("Please enter only number of file");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    break;

                case "4":
                    System.out.println("\nEnter target path for moving files or enter \"1\" for exit:");
                    boolean innerFlag = true;
                    s = reader.readLine();
                    target = Path.of(s);
                    if (Files.exists(target)) {
                        System.out.println("Target path changed!");
                    } else {
                        while (innerFlag) {
                            System.out.println("Re-enter target path or enter \"1\" for exit:");
                            s = reader.readLine();
                            if (s.equals("1")) innerFlag=false;
                            target = Path.of(s);
                        }
                    }
                    break;

                case "5":
                    info();
                    break;

                case "6":
                    boolean temp4 = deleteForever;
                    deleteForever=!temp4;
                    break;

                case "7":
                    menuFlag = false;
                    System.out.println("Exit...");
                    System.out.println("\nApplication made by Matvey Penkov, 30.03.2022 \n(c)Skylandinn");
                    Thread.sleep(1500);
                    break;

                default:
                    System.out.println("Action is not chosen!");
                    break;
            }
        }
    }

    public static void info() {
        System.out.println("Welcome to console file search & delete app!\n");
        System.out.println("Files will be not deleted, they will be moved to " + target.toString() + " <- tap");
        System.out.println("You can delete all files after work in target path manually");
        System.out.println("Target path is: \"" + target.toString() + "\", filename search: \"" + searchFileVisitor.getPartOfName() + "\"");
    }


    public static void filesOut() throws IOException, URISyntaxException {
        files.clear();

        for (Path file : foundFiles) {
            files.put(file, file.getFileName().toString());
        }

        LinkedHashMap<Path, String> sortedMap = files.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        char[] arr2 = new char[200];
        Arrays.fill(arr2, '_');
        System.out.println(new String(arr2));
        System.out.println("\nList of files: \n");
        for (var pair : sortedMap.entrySet()) {
            String value = pair.getValue();

            StringBuilder str = new StringBuilder(value);
            char[] arr = new char[70];
            Arrays.fill(arr, '_');

            str.append(arr);
            if (str.length() > 70) {
                str.setLength(70);
                str.trimToSize();
            }

            Path key = pair.getKey().toRealPath();
            if (Files.exists(key)) {
                System.out.print(str + " #Path: " + key + "\r\n"); // file://
            }
            /*
            try {
                String temp = key.toRealPath().toString().replaceAll("\\\\","/").replaceAll(" ","%20");//
                URI uri2 = new URI("file:///" + temp);
                URLEncoder.encode(String.valueOf(uri2), StandardCharsets.UTF_16);
                System.out.println(str + " #Path:"+uri2 );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {

            }*/
        }
    }


    static class SearchFileVisitor extends SimpleFileVisitor<Path> {
        private String partOfName;
        private String partOfContent;
        private int minSize;
        private int maxSize;
        private List<Path> foundFiles = new ArrayList<>();

        public List<Path> getFoundFiles() {
            return foundFiles;
        }

        public String getPartOfName() {
            return partOfName;
        }

        public void setPartOfName(String partOfName) {
            this.partOfName = partOfName;
        }

        public String getPartOfContent() {
            return partOfContent;
        }

        public void setPartOfContent(String partOfContent) {
            this.partOfContent = partOfContent;
        }

        public int getMinSize() {
            return minSize;
        }

        public void setMinSize(int minSize) {
            this.minSize = minSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            // check if file name contains search string
            if (partOfName != null && !file.getFileName().toString().contains(partOfName))
                return FileVisitResult.CONTINUE;

            // read file content
            byte[] content = Files.readAllBytes(file);

            //check size of file
            if ((maxSize > 0 && content.length > maxSize) || (minSize > 0 && content.length < minSize))
                return FileVisitResult.CONTINUE;

            // check contents of file
            if (partOfContent != null && !partOfContent.isEmpty()) {
                String contentString = new String(content);
                if (!contentString.contains(partOfContent)) return FileVisitResult.CONTINUE;
            }

            // if all checks are passed, add file to result list
            foundFiles.add(file);
            return super.visitFile(file, attrs);
        }
    }
}

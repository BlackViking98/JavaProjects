import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Searcher extends JFrame {
    public static Map<Path, String> files = new HashMap<>();
    public static List<Path> foundFiles;
    public static Path target = Path.of("C:\\Users\\Public\\Music");
    public static SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
    JButton button1;
    JLabel label1, label2, label3, label4;
    JTextField text1, text2;
    eHandler handler = new eHandler();
    public String searchingPath;
    public static String finalPathFile;
    public static String finalPath;
    public String currentStatus;

    public Searcher(String s) {
        super(s);
        setSize(350, 150);
        setLayout(new FlowLayout());
        button1 = new JButton("Search");
        label1 = new JLabel("Enter music path");
        label2 = new JLabel("Enter path for saving songs list");
        Font font = new Font(null, Font.ITALIC, 10);
        label3 = new JLabel("It may take more than 5 minutes, if amount is more than 1000 songs");
        label3.setFont(font);
        label3.setForeground(Color.RED);
        label4 = new JLabel(currentStatus);
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        Container container = this.getContentPane();
        container.add(label1, BorderLayout.WEST);
        container.add(text1, BorderLayout.EAST);
        container.add(label2, BorderLayout.WEST);
        container.add(text2, BorderLayout.EAST);
        container.add(button1, BorderLayout.SOUTH);
        container.add(label4, BorderLayout.SOUTH);
        container.add(label3, BorderLayout.SOUTH);
        button1.addActionListener(handler);
    }

    public class eHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label4.setText("Searching...");


            if (e.getSource() == button1) {

                long start = System.currentTimeMillis();

                searchingPath = text1.getText();
                finalPath = text2.getText();
                finalPathFile = text2.getText().concat("\\SongsList.txt");

                if (!(new File(searchingPath).isDirectory()) || !(new File(finalPath).isDirectory())) {
                    label4.setText("Entered path is not a directory! Enter directory again.");
                } else {
                    searchFileVisitor.setPartOfName(".mp3");
                    try {
                        Files.walkFileTree(target, searchFileVisitor);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    foundFiles = searchFileVisitor.getFoundFiles();
                    try {
                        Files.walkFileTree(Paths.get(searchingPath), searchFileVisitor);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    foundFiles = searchFileVisitor.getFoundFiles();
                    try {
                        if (filesOut()) {
                            long finish = System.currentTimeMillis();
                            long elapsed = (finish - start) / 1000;
                            if (elapsed < 1) {
                                label4.setText("Done in less than 1 s");
                            } else {
                                label4.setText("Done in " + elapsed + " s");
                            }
                        } else {
                            label4.setText("File already exists!");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }


    public static boolean filesOut() throws IOException, URISyntaxException {
        files.clear();

        for (Path file : foundFiles) {
            files.put(file, file.getFileName().toString());
        }

        LinkedHashMap<Path, String> sortedMap = files.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        FileWriter fileWriter = null;
        File file = null;
        try {
            file = new File(finalPathFile); //создаем файл
            if (file.createNewFile()) {
                System.out.println("File created");

            } else {
                System.out.println("File already exists");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        String list = "";
        for (var pair : sortedMap.entrySet()) {
            list = list.concat(pair.getValue()).concat("\n");
        }

        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(list);
            fileWriter.write("\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
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






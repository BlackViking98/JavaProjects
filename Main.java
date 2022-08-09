import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Searcher searcher = new Searcher("MP3 Searcher App");
        searcher.setVisible(true);
        searcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searcher.setResizable(false);
        searcher.setLocationRelativeTo(null);
    }
}

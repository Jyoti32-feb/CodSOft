import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class WordCounterWithGUI extends JFrame {
    private JTextArea textArea;
    private JButton countButton;
    private JTextArea resultArea;

    public WordCounterWithGUI() {
        setTitle("Word Counter");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane textScrollPane = new JScrollPane(textArea);
        add(textScrollPane, BorderLayout.CENTER);

        countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                int totalWords = countWords(text);
                Map<String, Integer> wordFrequencyMap = getWordFrequencyMap(text);

                String result = "Total Words: " + totalWords + "\n";
                result += "Unique Words: " + wordFrequencyMap.size() + "\n";
                result += "Word Frequencies:\n";
                for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
                    result += entry.getKey() + ": " + entry.getValue() + "\n";
                }

                resultArea.setText(result);
            }
        });

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(countButton);

        add(resultScrollPane, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
    }

    private int countWords(String text) {
        String[] words = text.split("\\W+"); // Split by non-word characters (punctuation, space, etc.)
        return words.length;
    }

    private Map<String, Integer> getWordFrequencyMap(String text) {
        String[] words = text.split("\\W+");
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty() && !isCommonWord(word)) {
                wordFrequencyMap.put(word.toLowerCase(), wordFrequencyMap.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        return wordFrequencyMap;
    }

    private boolean isCommonWord(String word) {
        // You can add common words to ignore here
        Set<String> commonWords = new HashSet<>(Arrays.asList("the", "and", "a", "an", "in", "of", "to", "for", "on"));
        return commonWords.contains(word.toLowerCase());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WordCounterWithGUI wordCounter = new WordCounterWithGUI();
                wordCounter.setVisible(true);
            }
        });
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Interface extends JFrame {

    private JTextField titleField;
    private JTextField subtitleField; // Add this line
    private JTextArea textArea;
    String path;

    public Interface() {
        // Set the title of the window
        setTitle("Single Panel Interface");

        // Set the default close operation to exit the application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a layout manager for the frame
        setLayout(new BorderLayout());

        // Create a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY); // Set background color

        // Set a layout for the main panel
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add horizontal and vertical gap
        buttonPanel.setBackground(Color.GRAY);

        // Create buttons and add them to buttonPanel
        JButton button1 = new JButton("Load HTML");
        JButton button2 = new JButton("Save HTML");
        JButton button3 = new JButton("Create New");

        // Set font size for buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFullHTML();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFullHTML();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                titleField.setText("");
                subtitleField.setText("");
            }
        });

        // Add buttonPanel to mainPanel with doubled space at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH; // Align buttons to the top
        gbc.insets = new Insets(40, 20, 12, 20); // Double the top inset
        mainPanel.add(buttonPanel, gbc);

        // Create a text field for the title with placeholder text
        titleField = new JTextField("Title");
        titleField.setPreferredSize(new Dimension(0, 60)); // Double the height to 60 pixels
        titleField.setHorizontalAlignment(JTextField.LEFT);
        titleField.setForeground(Color.GRAY); // Set text color to grey
        titleField.setFont(new Font("Arial", Font.BOLD, 16)); // Set font to bold and size to 16px

        // Create a compound border for titleField
        titleField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Thin black border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding inside the text field
        ));

        // Add a focus listener to handle placeholder text behavior for titleField
        titleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleField.getText().equals("Title")) {
                    titleField.setText("");
                    titleField.setForeground(Color.BLACK); // Change text color to black
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (titleField.getText().isEmpty()) {
                    titleField.setText("Title");
                    titleField.setForeground(Color.GRAY); // Change text color to grey
                }
            }
        });

        // Add titleField below buttonPanel
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(20, 20, 5, 20); // Adjust the bottom inset to 5 pixels
        mainPanel.add(titleField, gbc);

        // Create a text field for the subtitle with placeholder text
        subtitleField = new JTextField("Subtitle");
        subtitleField.setPreferredSize(new Dimension(0, 30)); // Height of 30 pixels
        subtitleField.setHorizontalAlignment(JTextField.LEFT);
        subtitleField.setForeground(Color.GRAY); // Set text color to grey
        subtitleField.setFont(new Font("Arial", Font.PLAIN, 10)); // Set font size to 10px

        // Create a compound border for subtitleField
        subtitleField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Thin black border
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding inside the text field
        ));

        // Add a focus listener to handle placeholder text behavior for subtitleField
        subtitleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (subtitleField.getText().equals("Subtitle")) {
                    subtitleField.setText("");
                    subtitleField.setForeground(Color.BLACK); // Change text color to black
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (subtitleField.getText().isEmpty()) {
                    subtitleField.setText("Subtitle");
                    subtitleField.setForeground(Color.GRAY); // Change text color to grey
                }
            }
        });

        // Add subtitleField below titleField
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 20, 12, 20); // Adjust the insets
        mainPanel.add(subtitleField, gbc);

        // Create a text area for the main content
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font size to 16px
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Thin black border
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding inside the text area
        ));

        // Placeholder text for textArea
        String placeholder = "Content";
        textArea.setText(placeholder);
        textArea.setForeground(Color.GRAY); // Set initial text color to grey

        // Add focus listener to handle placeholder text behavior for textArea
        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK); // Change text color to black
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setText(placeholder);
                    textArea.setForeground(Color.GRAY); // Change text color to grey
                }
            }
        });

        // Create a JScrollPane for the text area without border and padding
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border from scroll pane

        // Add textArea below subtitleField with extra space at the bottom
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(12, 20, 24, 20); // Increase bottom inset
        mainPanel.add(scrollPane, gbc);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        // Set the size of the window to full screen
        setSize(800, 600);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Schedule a task to save textArea content to a file every second
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                saveTextAreaContentToFile();
            }
        }, 0, 300); // Run every 300ms
    }

    private String pathSelector() {
        JFrame frame = new JFrame("File Selector Example");
        path = "";

        JFileChooser fileChooser = new JFileChooser("/home/uron/proj/cms/");

        // Show open dialog; this method does not return until the dialog is closed
        int option = fileChooser.showOpenDialog(frame);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            path = filePath;
            JOptionPane.showMessageDialog(frame, "Selected File: " + filePath);
        } else {
            JOptionPane.showMessageDialog(frame, "File selection cancelled.");
        }

        return path;
    }

    private void saveFullHTML() {
        String saveFile = pathSelector();

        File createFile = new File(saveFile);

        String addedHtml = generateHTML(titleField.getText(), subtitleField.getText(), textArea.getText());


        try {
            createFile.createNewFile();
            String filePath = "boiler-plate.html";

            // Read entire file into a string
            String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Find index where to insert addedHtml
            int index = htmlContent.indexOf("<div id=\"main-content\" class=\"main\">")
                    + "<div id=\"main-content\" class=\"main\">".length();

            // Insert addedHtml at the identified index
            String modifiedHtml = new StringBuilder(htmlContent).insert(index, addedHtml).toString();

            // Write modified HTML back to the file
            Files.write(Paths.get(saveFile), modifiedHtml.getBytes(), StandardOpenOption.WRITE);

            System.out.println("Successfully saved file.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadFullHTML() {

        String file = pathSelector();
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String mainContentHtml = contentBuilder.toString();

        String title = "";
        String subtitle = "";
        StringBuilder paragraphs = new StringBuilder();

        // Extract content from h1
        int h1StartIndex = mainContentHtml.indexOf("<h1>");
        int h1EndIndex = mainContentHtml.indexOf("</h1>");
        if (h1StartIndex != -1 && h1EndIndex != -1) {
            title = mainContentHtml.substring(h1StartIndex + "<h1>".length(), h1EndIndex).trim();
        }

        // Extract content from h2
        int h2StartIndex = mainContentHtml.indexOf("<h2>");
        int h2EndIndex = mainContentHtml.indexOf("</h2>");
        if (h2StartIndex != -1 && h2EndIndex != -1) {
            subtitle = mainContentHtml.substring(h2StartIndex + "<h2>".length(), h2EndIndex).trim();
        }

        // Extract content from p elements, ignoring those inside <div id="banner-media">
        int pStartIndex = 0;
        while (true) {
            pStartIndex = mainContentHtml.indexOf("<p>", pStartIndex);
            if (pStartIndex == -1) {
                break; // No more <p> tags found
            }

            // Check if <p> tag is inside <div id="banner-media">
            int bannerMediaStartIndex = mainContentHtml.lastIndexOf("<div id=\"banner-media\">", pStartIndex);
            int bannerMediaEndIndex = mainContentHtml.indexOf("</div>", bannerMediaStartIndex);

            if (bannerMediaStartIndex != -1 && bannerMediaEndIndex != -1 && bannerMediaStartIndex < pStartIndex && pStartIndex < bannerMediaEndIndex) {
                // Skip this <p> tag as it is inside <div id="banner-media">
                pStartIndex += "<p>".length(); // Move to search after current <p> tag
                continue;
            }

            int pEndIndex = mainContentHtml.indexOf("</p>", pStartIndex);
            if (pEndIndex == -1) {
                break; // Malformed HTML - missing </p> tag
            }

            String paragraph = mainContentHtml.substring(pStartIndex + "<p>".length(), pEndIndex).trim();
            paragraphs.append(paragraph).append("\n"); // Append paragraph content with newline

            // Move to the next position after the current </p> tag
            pStartIndex = pEndIndex + "</p>".length();
        }

        // Set text of titleField, subtitleField, and textArea
        titleField.setForeground(Color.BLACK);
        titleField.setText(title);
        subtitleField.setText(subtitle);
        subtitleField.setForeground(Color.BLACK);
        textArea.setText(paragraphs.toString());
        textArea.setForeground(Color.BLACK);


    }

    private String generateHTML(String title, String subtitle, String text) {
        if (title.equals("Title")) {
            title = "";
        }
        if (subtitle.equals("Subtitle")) {
            subtitle = "";
        }

        String html = "";

        html = html + "<h1>" + title + "</h1>";
        html = html + "<h2>" + subtitle + "</h2>";

        if (text.equals("Content")) {
            text = "";
        }

        String[] textAreaSplit = text.split("\n");

        for (String textLine : textAreaSplit) {
            html = html + "<p>" + textLine + "</p>";
        }

        return html;
    }

    // Method to save textArea content to file
    private void saveTextAreaContentToFile() {
        try (FileWriter writer = new FileWriter("tmp_writer.html")) {
            String generatedHTML = generateHTML(titleField.getText(), subtitleField.getText(), textArea.getText());
            writer.write(generatedHTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of the Interface class
                Interface frame = new Interface();
                // Make the frame visible
                frame.setVisible(true);
            }
        });
    }
}

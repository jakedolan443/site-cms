import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Path to the Python script you want to run
        String pythonScriptPath = "server.py";
        String[] portNumber = {""}; // Array to hold port number

        // Command to run the Python script
        String[] command = {"python3", pythonScriptPath};

        try {
            // Create a process builder
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Redirect error stream to standard output
            processBuilder.redirectErrorStream(true);

            // Start the Python script process
            Process process = processBuilder.start();

            // Create a thread to read output from the Python script
            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line); // Output Python script output to console
                        if (line.startsWith("Listening on")) {
                            portNumber[0] = line.split(":")[2].trim(); // Update port number
                            System.out.println("Started listening on port " + portNumber[0]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Start the output reading thread
            outputThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create an instance of the Interface class
        Interface frame = new Interface();

        // Make the frame visible
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);

            // Get the size of the screen
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle screenBounds = ge.getMaximumWindowBounds();

            // Calculate the width for the left half of the screen
            int screenWidth = screenBounds.width;
            int halfWidth = screenWidth / 2;

            // Set the location of the frame to the left half of the screen
            frame.setLocation(screenBounds.x, screenBounds.y);

            // Set the size of the frame to occupy the left half of the screen
            frame.setSize(halfWidth, screenBounds.height);
        });
    }
}

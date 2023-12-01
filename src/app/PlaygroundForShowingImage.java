package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class PlaygroundForShowingImage extends JFrame {

    private final JLabel imageLabel;
    private final JLabel textLabel;

    public PlaygroundForShowingImage() {
        setTitle("Recipe Image Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // URL of the image (replace with your own recipe image URL)
        String imageUrl = "https://edamam-product-images.s3.amazonaws.com/web-img/c5d/c5d4a486b92c53bb3e0a9ef42b5f8f30.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFgaCXVzLWVhc3QtMSJHMEUCIQCvithZKVIJLU7NqW4fzP3hqsbW6Z2jsJ4mHLsMNjO1XwIgQg8xBrA3dCHkMQlDZv7yVvycHvJSX8HDOcPzkqtqsTkqwgUIsP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgwxODcwMTcxNTA5ODYiDNpSwumaeY%2BVVqW7WCqWBd31gfSBqpRyHGXIjS3G0RSnDuNL%2BbVfvJUm7y6toJupXEs8OnWV3mapkgOZXWqe5bFTL5NLE4agfnQSf%2BUcA8x9CGp%2F6%2BemNLSYEohDRiKZ0rA46EYxaGL1QoQu0b0nfJvbEMgzqZMHA38HxxFkWGORwFzMGdoWHhmHx436wN51mJbMlZy%2FtVwVx5SEbHAPYSoeh6%2BbOa0ht3O6g36jI486ffwz13P1hYaGQzCVIXNmyeTtLTREOiOT5BShygXoIZ5Zr88AHN%2FAeVMwuaObtTUZADI%2FeKyx14cX3w4nTAlsKVRSSxhsAX76rSo7fIIctBopizpzO8Wp%2BlP8xxbgLLbxSCBvJAoBjqCqEk5zbwF6HIyTWbB2HQNBPZlNoSOohJurav3zY9ILgjkPJPkd%2FCK4EksVpw2Rgk%2BVHsthKAHfO%2B1OFrkMlQPHj8bRr44xgEhuPEP8JRxWa1iPjHrQHg7xYIUD4IIiAjp%2BpFky5csMFiG846iKPHcnSygbWwmoBsGRdj4sVUtntnESbYEn%2F2c%2BhXlJlglsBmzTJzMS90aSULe6Cm9a75y53hZ1xS%2ByHs2x1sPfGuzO4veNT%2FT7bJwLkewBZPuq6OV5qg5GE0IMixfIMVapNvQjVubH%2F%2BVBR%2B5Rhpe9RHbgTXg4it9%2BgqBse9o%2Fz3S6X6g3d9ANhczVQUpVjNAAbk9Vy4cqUgn3wjg8PEqiwZH%2FGcep1URQE7M6OpMg0nufxCZvz8D46ZMJGRChfit9eOSWrEOcXRBknx%2B2lS0WOrDLwkDWcQ2e90NJDParn8OghInFF8J3aHPbjwXCG4g1xHLFCmQwkBIpeRQ7OVAOKLc%2Be0pHYPnBwvwau4wFMiSGvdEqZ%2FsFkvFGOHq4whCXMPfBlKsGOrEBPc3%2BjVGgXObV7ZJY3EJkaBRJP5JU5ib%2BBLulGPL25W8MAuhh1g%2FXwr7c%2BIbQN0ag46r1A5RALw%2B0w930vZFnRXPBfxSENr8SafnoAzX4fJhO02H54yCVQTS94PCXw0n23Pwj%2B7he35WYCtiLfz7y%2Fi52i8BbdC6YgGnTaUSDN2fBd6hzCk7ApIdz3KKMVBxECiA%2Fu%2BdZnSfWrqam9sPfA7YVkpY%2FjX%2F2%2Fxq%2B6hIi8Zrr&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231127T232539Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFGHZVIAFI%2F20231127%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=1099b192f4e537143604e4b616514b20bdc615d4088e369673d6619a8e4cae75";

        // Create a JPanel to hold the image and text labels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a JLabel to hold the image
        imageLabel = createImageLabel();

        // Create a JLabel for text
        textLabel = createTextLabel("Your Recipe Name Goes Here");

        // Add the image and text labels to the panel
        panel.add(imageLabel);
        panel.add(textLabel);

        // Add the panel to the frame
        getContentPane().add(panel);

        loadAndDisplayImage(imageUrl);

        // Set the preferred size of the imageLabel to make the image larger
        imageLabel.setPreferredSize(new Dimension(800, 600));

        pack();  // Pack the frame to take the preferred size into account
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createImageLabel() {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JLabel createTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        return label;
    }

    private void loadAndDisplayImage(String imageUrl) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    // Download the image from the URL
                    URL url = new URL(imageUrl);
                    Image image = ImageIO.read(url);

                    // Resize the image to fit the JLabel (optional)
                    Image scaledImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);

                    // Set the image to the JLabel on the EDT
                    SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(scaledImage)));
                } catch (IOException e) {
                    e.printStackTrace();
                    // Set an error message if the image fails to load
                    imageLabel.setText("Failed to load image");
                }
                return null;
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlaygroundForShowingImage());
    }
}

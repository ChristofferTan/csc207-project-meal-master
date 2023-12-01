
package app;
import javax.swing.*;



public class PlaygroundForShowDialog {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Popup Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton showPopupButton = new JButton("Show Popup");
        showPopupButton.addActionListener(e -> showPopup(frame));

        frame.getContentPane().add(showPopupButton);

        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showPopup(JFrame parentFrame) {
        JOptionPane.showMessageDialog(parentFrame, "Christod sucks", "Popup Title", JOptionPane.INFORMATION_MESSAGE);
    }
}

package ui.swing;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ApplicationFrame extends JFrame {
    
    private final ActionListener[] listeners;
    private final SwingImageViewerPanel imageViewer;
    private int index = 0;

    public ApplicationFrame(ActionListener[] listeners, SwingImageViewerPanel imageViewer) {
        super("Image Viewer");
        this.listeners = listeners;
        this.imageViewer = imageViewer;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.createComponents();
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createComponents() {
        this.add(imageViewer);
        this.add(createToolbar(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));
        return panel;
    }

    private JButton createButton(String title) {
        JButton button = new JButton(title);
        button.addActionListener(listeners[index++]);
        return button;
    }
    
    
}

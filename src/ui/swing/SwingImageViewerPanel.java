package ui.swing;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Image;
import ui.ImageViewer;

public class SwingImageViewerPanel extends JPanel implements ImageViewer {

    private Image image;
    private int offset;
    private int initialX;
    
    private BufferedImage currentBufferedImage;
    private BufferedImage nextBufferedImage;
    private BufferedImage prevBufferedImage;

    public SwingImageViewerPanel() {
        super();
        this.hookEvents();
    }

    @Override
    public void paint(Graphics graphics) {
        if (image == null) return;
        linkBufferedImages();
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(currentBufferedImage, offset, 0, null);
        if (offset == 0) return;
        if (offset < 0) graphics.drawImage(nextBufferedImage, currentBufferedImage.getWidth() + offset, 0, null);
        else graphics.drawImage(prevBufferedImage, offset - currentBufferedImage.getWidth(), 0, null);
    }
    
    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        refresh();
    }

    @Override
    public void refresh() {
        repaint();
    }

    private void hookEvents() {
        this.hookMouseListener();
        this.hookMouseMotionListener();
    }

    private void hookMouseListener() {
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("released" + e.getX());
                if (offset > currentBufferedImage.getWidth() / 2)
                    image = image.getPrev();
                if (offset < -currentBufferedImage.getWidth() / 2)
                    image = image.getNext();
                offset = 0;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void hookMouseMotionListener() {
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                offset = e.getX() - initialX;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }
    
    private void linkBufferedImages() {
        try {
            this.currentBufferedImage = ImageIO.read(new ByteArrayInputStream(image.getBitMap().getByteArray()));
            this.nextBufferedImage = ImageIO.read(new ByteArrayInputStream(image.getNext().getBitMap().getByteArray()));
            this.prevBufferedImage = ImageIO.read(new ByteArrayInputStream(image.getPrev().getBitMap().getByteArray()));
        } catch (IOException ex) {
        }
    }
    
}

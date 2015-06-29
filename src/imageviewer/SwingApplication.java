package imageviewer;

import controller.NextImageCommand;
import controller.PrevImageCommand;
import java.awt.event.ActionListener;
import model.Bitmap;
import model.Image;
import model.ProxyImage;
import model.RealImage;
import persistance.ImageLoader;
import ui.ImageViewer;
import ui.swing.ApplicationFrame;
import ui.swing.SwingImageViewerPanel;

public class SwingApplication {

    public static void main(String[] args) {
        
        new SwingApplication().execute();
    }

    private void execute() {
        Image[] images = linkImages(createImages());
        ImageViewer viewer = createImageViewer(images[0]);
        createApplicationFrame(createCommands(viewer),(SwingImageViewerPanel) viewer);
    }
    
    private Image[] createImages() {
        Image[] images = new Image[8];
        for (int i = 0; i < images.length; i++) {
            images[i] = createImage(i);
        }
        return images;
    }

    private Image createImage(final int index) {
        final String ROOT = "images/";
        final String[] images = {"Chrysanthemum.jpg", "Desert.jpg", "Hydrangeas.jpg", "Jellyfish.jpg", "Koala.jpg", "Lighthouse.jpg", "Penguins.jpg", "Tulips.jpg"};
        return new ProxyImage(new ImageLoader() {

            @Override
            public Image load() {
                return new RealImage(new Bitmap(ROOT + images[index]));
            }
        });
    }
    
    private Image[] linkImages(Image[] images) {
        for (int i = 0; i < images.length; i++) {
            Image image = images[i];
            Image next = images[(i + 1) % images.length];
            Image prev = images[(i + images.length - 1) % images.length];
            image.setNext(next);
            image.setPrev(prev);
        }
        return images;    
    }

    private ImageViewer createImageViewer(Image image) {
        SwingImageViewerPanel viewer = new SwingImageViewerPanel();
        viewer.setImage(image);
        return viewer;
    }

    private ApplicationFrame createApplicationFrame(ActionListener[] listeners, SwingImageViewerPanel viewer) {
        return new ApplicationFrame(listeners, viewer);
    }

    private ActionListener[] createCommands(ImageViewer viewer) {
        return new ActionListener[] {
            new PrevImageCommand(viewer),
            new NextImageCommand(viewer)
        };
    }

}

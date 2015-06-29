package ui.console;

import model.Image;
import ui.ImageViewer;

public class ConsoleImageViewer implements ImageViewer {

    private Image image;
    
    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void refresh() {
        System.out.print(getImage().getBitMap().getByteArray().length);
    }
    
}

package controller;

import java.awt.event.ActionEvent;
import ui.ImageViewer;

public class PrevImageCommand implements Command {

    private final ImageViewer viewer;

    public PrevImageCommand(ImageViewer viewer) {
        this.viewer = viewer;
    }
    
    @Override
    public void execute() {
        this.viewer.setImage(this.viewer.getImage().getPrev());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        execute();
    }
    
}

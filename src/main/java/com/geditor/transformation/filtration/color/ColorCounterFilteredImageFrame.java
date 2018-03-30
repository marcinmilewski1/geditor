package com.geditor.transformation.filtration.color;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorCounterFilteredImageFrame extends JFrame{

    public ColorCounterFilteredImageFrame(BufferedImage bufferedImage) throws HeadlessException {
        super("Color filtered");
        Container container = getContentPane();
//        container.setLayout();
        container.add(new ImagePanel(bufferedImage));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
    }


    private class ImagePanel extends JPanel{
        private BufferedImage bufferedImage;

        public ImagePanel(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
            setSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bufferedImage, 0, 0, null);
        }
    }
}


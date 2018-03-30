package com.geditor.ui.color;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorImagePanel extends JPanel {
    private static final int HEIGHT = 200;
    private static final int WIDTH = 200;
    private BufferedImage image;
    private Graphics2D graphics2D;
    private Graphics2D currentGraphics;

    public ColorImagePanel(RGBStructure rgbStructure) {
        setDoubleBuffered(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) image.getGraphics();
        draw(rgbStructure);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentGraphics = (Graphics2D) g;
        g.drawImage(image, 0, 0, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void draw(RGBStructure rgbStructure) {
        graphics2D.setColor(new Color(rgbStructure.r, rgbStructure.g, rgbStructure.b));
        graphics2D.fillRect(0, 0, HEIGHT, WIDTH);
        repaint();
    }
}

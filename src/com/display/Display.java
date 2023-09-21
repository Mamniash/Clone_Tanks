package com.display;

import com.io.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;

public abstract class Display {

    private static boolean created = false;
    private static JFrame window;
    private static Canvas contend;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;

    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height, String title, int _clearColor, int numBuffers) {

        if(created)
            return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contend = new Canvas();

        Dimension size = new Dimension(width, height);
        contend.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(contend);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        contend.createBufferStrategy(numBuffers);
        bufferStrategy = contend.getBufferStrategy();

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy() {
        if (!created)
            return;

        window.dispose();
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void addInputListener(Input inputListener) {
        window.add(inputListener);
    }
}

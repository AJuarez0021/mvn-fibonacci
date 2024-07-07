package com.work.fibonacci;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author linux
 */
public class FibonacciGraphics extends JPanel {

    private final List<Integer> fibonacciSeries;

    public FibonacciGraphics(int max) {
        this.fibonacciSeries = generateFibonacci(max);
    }

    public FibonacciGraphics() {
        this(30);
    }

    public List<Integer> getFibonacciSeries() {
        return fibonacciSeries;
    }

    private List<Integer> generateFibonacci(int max) {
        List<Integer> series = new ArrayList<>();
        series.add(0);
        series.add(1);
        for (int i = 2; i < max; i++) {
            series.add(series.get(i - 1) + series.get(i - 2));
        }
        return series;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / fibonacciSeries.size();
        int max = fibonacciSeries.stream().max(Integer::compareTo).orElse(1);

        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0, 0, width, height);

        g2d.setColor(Color.BLUE);
        for (int i = 0; i < fibonacciSeries.size(); i++) {
            int value = fibonacciSeries.get(i);
            int barHeight = (int) ((double) value / max * (height - 50));
            g2d.fillRect(i * barWidth, height - barHeight, barWidth - 2, barHeight);
            g2d.drawString(String.valueOf(value), i * barWidth, height - barHeight - 5);
        }
    }

    public void createWindow(int width, int height) {
        JFrame frame = new JFrame("Fibonacci");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.add(new FibonacciGraphics());
        frame.setVisible(true);
        setLocationCenter(frame);
    }

    private void setLocationCenter(JFrame frame) {
        setLocationMove(frame, 0, 0);
    }

    private void setLocationMove(JFrame frame, int moveWidth, int moveHeight) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frameSize.width = frameSize.width > screenSize.width ? screenSize.width : frameSize.width;
        frameSize.height = frameSize.height > screenSize.height ? screenSize.height : frameSize.height;
        frame.setLocation((screenSize.width - frameSize.width) / 2 + moveWidth, (screenSize.height - frameSize.height) / 2 + moveHeight);
    }
}

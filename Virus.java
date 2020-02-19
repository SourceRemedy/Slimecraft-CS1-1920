/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 734260
 */
public class Virus extends Sprite{
    
    private static final Color COLOR = Color.GREEN;
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int SPEED = 0;
    
    public Virus (int x, int y) {
        super(SPEED, x, y, WIDTH, HEIGHT, COLOR);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.drawOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
    
}

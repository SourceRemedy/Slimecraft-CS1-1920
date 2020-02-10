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
public class Food extends Sprite{
    
     private static final Color COLOR = Color.GREEN;
     
     public Food(int x, int y) {
         super(0, x, y, 7, 8, COLOR);
     }
    
     @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author 734260
 */
public class Slime extends Sprite {
    //static constants
    private static final int WIDTH = 10;
    private static final int HEIGHT = 15;
    private int strength;
    private int health;
    
    
    public Slime(int speed, int x, int y, Color color) {
        super(speed, x, y, WIDTH, HEIGHT, color);
        this.strength = (int) (Math.random() * 20);
        this.health = 50;
    }
    
     public void eat(Food food) {
        if (super.collide(food)) {
            super.setHeight(super.getHeight() + 5);
            super.setWidth(super.getWidth() + 5);
            food.die();
        }
    }
     
     public void infect(Virus virus) {
        if (super.collide(virus)) {
//            this.health -= this.health - 5;
//            if (this.health == 0) {
                this.die();
//            }
            virus.die();
        }
    }
     
    public void death(Slime this) {
        if (this.health == 0) {
            this.die();
        }
    }
     
    public void didWin(Slime other) {
        this.strength += this.strength - other.strength;
        super.grow(1.2);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    public int getStrength() {
        return strength;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
    
    
    
}

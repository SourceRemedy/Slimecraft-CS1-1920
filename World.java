/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.Timer;

/**
 *
 * @author 734260
 */
public class World extends JPanel implements MouseListener{
    private ArrayList<Blob> blobs = new ArrayList<>();    
    private ArrayList<Glob> globs = new ArrayList<>();    
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();   
    private ArrayList<Slime> slimes = new ArrayList<>(); 
    private ArrayList<Virus> viruses = new ArrayList<>();
    
    Timer timer;
    int frames = 0;
    
    public World() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/12);
        super.setSize(800, 600);
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 800 / 2);
            int y = (int) (Math.random() * 600);
            Blob blob = new Blob(x,y);
            sprites.add(blob);
            slimes.add(blob);
            blobs.add(blob);
            
        }
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 800 / 2 + 800 / 2);
            int y = (int) (Math.random() * 600);
            Glob glob = new Glob(x,y);
            sprites.add(glob);    
            slimes.add(glob);    
            globs.add(glob);    
        }
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Food food = new Food(x,y);
            foods.add(food);
            sprites.add(food);
        }  
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Virus virus = new Virus(x,y);
            viruses.add(virus);
            sprites.add(virus);
        }  
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        frames++;
        
        for (Slime slime : slimes) {
            for (Food food : foods) {
                slime.eat(food);
            }            
        }
        
        for (Slime slime : slimes) {
            for (Virus virus : viruses) {
                slime.infect(virus);
            }
        }
        
        ArrayList<Blob> newBlobs = new ArrayList<>();
        ArrayList<Glob> newGlobs = new ArrayList<>();
        
        for (Blob blob : blobs) {
            for (Glob glob : globs) {
                blob.fight(glob);
            }
            for (Blob otherBlob : blobs) {
                if (blob == otherBlob) continue;
                if (blob.collide(otherBlob) && Math.random() < 0.03)
                    newBlobs.add(blob.reproduce(otherBlob));
            }
        }
        for (Glob glob : globs) {
            for (Glob otherGlob : globs) {
                if (glob == otherGlob) continue;
                if (glob.collide(otherGlob) && Math.random() < 0.03)
                    newGlobs.add(glob.reproduce(otherGlob));
            }
        }
        for (Sprite sprite : sprites) {
            sprite.draw(g);
            sprite.update();
            sprite.collideWorldBounds(800,600);
        }        
        
        takeOutTheTrash();
        addNewSlimes(newBlobs, newGlobs);
    }
    
    private void addNewSlimes(ArrayList<Blob> newBlobs, ArrayList<Glob> newGlobs) {
        blobs.addAll(newBlobs);
        globs.addAll(newGlobs);
        sprites.addAll(newBlobs);
        sprites.addAll(newGlobs);
        slimes.addAll(newBlobs);
        slimes.addAll(newGlobs);
    }
    
    private void takeOutTheTrash() {
        ArrayList<Sprite> trash = new ArrayList<>();
        for (Sprite sprite : sprites) {
            if (!sprite.isAlive())
                trash.add(sprite);
        }
        sprites.removeAll(trash);
        trash.clear();
        for (Food food : foods) {
            if (!food.isAlive())
                trash.add(food);
        }
        foods.removeAll(trash);
        trash.clear();
        for (Blob blob : blobs) {
            if (!blob.isAlive())
                trash.add(blob);
        }
        blobs.removeAll(trash);
        trash.clear();
        for (Glob glob : globs) {
            if (!glob.isAlive())
                trash.add(glob);
        }
        globs.removeAll(trash);
        trash.clear();        
    }
    
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
    
    
    @Override
    public void mouseExited(MouseEvent event) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent event) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {
        
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        System.out.printf("Mouse Click at (%d, %d)",event.getX(), event.getY());
        Blob blob = new Blob(event.getX(), event.getY());
        blobs.add(blob);
        slimes.add(blob);
        sprites.add(blob);
    }
}

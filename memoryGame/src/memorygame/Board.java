package memorygame;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mor Aroesti
 */
public class Board extends JPanel {

    Cell[][] matrix;
    int size;
    
    
    protected void paintComponent(Graphics g) {

      super.paintComponent(g);
      Image img = null;
        try {
            img = ImageIO.read(new File("images/mainImage.png"));
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(img != null){
            g.drawImage(img, 0, 0, null);
        }
          
    }

    public Board(int size) {

        super(new GridLayout(size, size));
        this.size = size;
    
        matrix = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new Cell();
                
                matrix[i][j].card.setActionCommand(i + "#" + j);
                this.add(matrix[i][j].card);
            }
        }

        Random random = new Random();
        int topNumber = size * size / 2;
        for (int times = 0; times < 2; times++) {
            for (int num = 1; num <= topNumber; num++) {
                int i, j;
                do {
                    i = random.nextInt(size);
                    j = random.nextInt(size);
                } while (matrix[i][j].value != 0);
                matrix[i][j].value = num;
                
            }
        }
    }

    public void updateButtonsLisenters(GameForm g) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j].card.addActionListener(g);
            }
        }
    }
}
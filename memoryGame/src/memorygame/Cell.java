package memorygame;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mor Aroesti
 */
public class Cell  {

    public int value;
    public boolean found;
    public boolean stat;
    public JButton card;
   
    

    public Cell() {
        
        
        
        card = new JButton(new ImageIcon("images/back.png"));
        
        
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                  showValue();
                }
            }
        });
    }
    
    public void showValue() {
        card.setIcon(null);
        card.setIcon(new ImageIcon("images/" + value +".png"));
        
        
    }
    
    public void hideValue(){
        
        card.setIcon(null);
        card.setIcon(new ImageIcon("images/back.png"));
    }
}
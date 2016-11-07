/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Mor Aroesti
 */
public class HighScore extends JFrame implements ActionListener{

    JLabel label;
    
    public HighScore(){
        this.setSize(1200, 1000);
        this.setVisible(true);
        String scores = "<html>";
        scores += "טבלת שיאים";
        scores += "<br>";
        for (User user : Database.GetInstance().GetUsers()) {
            scores += user.name;
            scores += " : " + user.score;
            scores += "<br>";
        }
        scores += "</html>";
        label = new JLabel(scores, SwingConstants.CENTER);
        
        this.add(label);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

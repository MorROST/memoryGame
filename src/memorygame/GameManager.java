package memorygame;

import java.awt.Color;
import java.awt.Font;
;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Mor Aroesti
 */


public class GameManager implements ActionListener{

    JFrame mainJFr;
    JTextField nameLab;
    JButton easy;
    JButton mid;
    JButton hard;
    JButton highScore;
    GameForm gameForm;
    String userName;
    
    public GameManager(){
        
    }
    
    public void restart() {
        try {
            gameForm.EndGame();
            Start();
        } catch (IOException ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void Start() throws IOException {
        mainJFr = new JFrame("משחק זיכרון");

        ////////////Main backgroung image upload////////////
        mainJFr.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/mainImage.png")))));
        mainJFr.setLayout(new FlowLayout(FlowLayout.LEADING, 950, 25));
        GridBagConstraints a = new GridBagConstraints();
        a.gridwidth = GridBagConstraints.REMAINDER;

        ////////////JTextFiled for name setup////////////
        nameLab = new JTextField("הקלד את שמך ובחר רמה");
        userName=nameLab.getText();
        nameLab.setFont(new Font("Gisha", 250, 30));
        nameLab.setForeground(Color.yellow);
        nameLab.setBackground(new Color(0, 46, 111));
        mainJFr.add(nameLab);

        ////////////Add JButton////////////
        JPanel namePanel = new JPanel();
        easy = new JButton("קל");
        mid = new JButton("בינוני");
        hard = new JButton("קשה");
        
        highScore = new JButton("טבלת שיאים");
        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 HighScore scores = new HighScore();
                 
            }
        });
        
        namePanel.add(easy);        
        namePanel.add(mid);
        namePanel.add(hard);
        namePanel.add(highScore);
        
        namePanel.setBackground(new Color(0, 46, 111));

        mainJFr.add(namePanel);

        mainJFr.pack();
        mainJFr.setVisible(true);
        mainJFr.setResizable(false);
        mainJFr.setDefaultCloseOperation(EXIT_ON_CLOSE);

        easy.addActionListener(this);
        mid.addActionListener(this);
        hard.addActionListener(this);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        mainJFr.dispose();
        String name = nameLab.getText();
        if (ae.getSource() == easy) {
            if(userName != "הקלד את שמך ובחר רמה" )
            {
            gameForm = new GameForm(2, name,this);
            }

        } else if (ae.getSource() == mid) {
            gameForm = new GameForm(4, name, this);
        } else if (ae.getSource() == hard) {
            gameForm = new GameForm(6, name, this);
        }

    }
}

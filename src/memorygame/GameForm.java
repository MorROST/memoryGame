package memorygame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mor Aroesti
 */
public class GameForm extends JFrame implements ActionListener {

    Board myBoard;
    int clickNumber = 1;
    Cell first, second;
    int numnerOfUpCards = 0;
    int size;
    int score = 0;
    JPanel scorePanel = new JPanel ();
    JLabel scoreLab= new JLabel("");
    int userId;
    GameManager manager;   
   
    public GameForm(int size, String name, GameManager manager) {
        super("משחק זיכרון");
        userId = Database.GetInstance().CreateUser(name);
        
        this.manager = manager;
        this.size = size;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        myBoard = new Board(size);
        
        this.add(myBoard, BorderLayout.CENTER);
        JLabel welcomeLab = new JLabel("שלום לך " + name + "!!");
        welcomeLab.setFont(new Font("Gisha", 45 , 45));
        welcomeLab.setForeground(Color.yellow);
        welcomeLab.setOpaque(true);
        welcomeLab.setBackground(new Color(0, 46, 111));
        
        this.add(welcomeLab,BorderLayout.NORTH);
        
        scorePanel.add(scoreLab);
        scorePanel.setBackground(new Color(0, 46, 111));
        scoreLab.setForeground(Color.red);
        
        this.add(scorePanel,BorderLayout.SOUTH );
        
        myBoard.updateButtonsLisenters(this);
        JButton back = new JButton("חזרה לתפריט ראשי");
        scorePanel.add(back);
        
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==back){
                        
                    manager.restart();
                  
                }
            }
        });
        
    
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i, j;
        String buttonPosition = e.getActionCommand();
        String[] index = buttonPosition.split("#");
        i = Integer.parseInt(index[0]);
        j = Integer.parseInt(index[1]);
        
        switch (clickNumber) {
            case 1:
                first = myBoard.matrix[i][j];
                first.showValue();
                clickNumber++;
                break;
            case 2:
                second = myBoard.matrix[i][j];
                second.showValue();
                if(first == second){
                    return;
                }   clickNumber++;
                if (first.value == second.value) {
                    
                    first.card.setVisible(false);
                    second.card.setVisible(false);
                    
                    numnerOfUpCards++;
                    //score+=5;
                    Database.GetInstance().increaseScore(userId);
                    scoreLab.setText("Your score: " + Database.GetInstance().getScore(userId));
                   
                    if (numnerOfUpCards == this.size * this.size / 2) {
                        
                        Object[] options = {"OK"};
                        int result = JOptionPane.showConfirmDialog(null,
                                "כל הכבוד!! סיימת ! הנקודות שלך הם :"+ + Database.GetInstance().getScore(userId) +"\n" + "האם אתה רוצה משחק חדש??", 
                                "Game Ended",                                
                                JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION)
                        {
                            manager.restart();
                        }
                        else
                        {
                            System.exit(0);
                        }
                        
                    }
                }
                else if(first.value != second.value){
                    Database.GetInstance().decreaseScore(userId);
                    scoreLab.setText("Your score: " + Database.GetInstance().getScore(userId));
                    
                }
                break;
            case 3:
                clickNumber = 2;
                first.hideValue();
                first = myBoard.matrix[i][j];
                first.showValue();
                second.hideValue();
                second = null;
                break;
            default:
                break;
        }
    }
    
    public void EndGame(){
        this.dispose();
    }
}
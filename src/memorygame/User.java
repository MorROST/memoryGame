/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

/**
 *
 * @author Mor Aroesti
 */
public class User {
    public String name;
    public int score;
    public static int seq = 0;
    public int id;
    public User(){
        seq = seq + 1;
        id = seq;
    }
}

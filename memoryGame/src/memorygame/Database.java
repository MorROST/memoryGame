/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.util.ArrayList;

/**
 *
 * @author Mor Aroesti
 */
public final class Database {
    
    private static Database db = null;
    
    private static ArrayList<User> users = new ArrayList<User>();
    
    private Database(){
    }
    
    public static Database GetInstance(){
        if (db == null){
            db = new Database();
        }
            
        return db;
    }
    
    public ArrayList<User> GetUsers(){
        return users;
    }
    
    public int CreateUser(String name){
        User user = new User();
        user.name = name;
        users.add(user);
        
        return user.id;
    }
    
    public void increaseScore(int userId){
        for (User user : users) {
            if(user.id == userId){
                user.score += 5;
            }
        }
    }
    
    public void decreaseScore(int userId){
        for (User user : users) {
            if(user.id == userId){
                user.score -= 5;
            }
        }
    }
    
    public int getScore(int userId){
        for (User user : users) {
            if(user.id == userId){
                return user.score;
            }
        }
        
        return 0;
    }
}

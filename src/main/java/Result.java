/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 *
 * @author Мария
 */
public class Result {
    
    private String name;
    private int points;
    
    public Result(String n, int p){
        this.name=n;
        this.points=p;
    }
    
    public void setName(String s){
        this.name=s;
    }
    public void setPoints(int p){
        this.points=p;
    }
    
    public String getName(){
        return this.name;
    }
    public int getPoints(){
        return this.points;
    }
    
}

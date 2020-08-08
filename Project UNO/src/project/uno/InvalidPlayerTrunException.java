/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.uno;

public class InvalidPlayerTrunException extends Exception {
    String playerid;
    public InvalidPlayerTrunException(String message,String pid) {
        super(message);
        playerid = pid;
    }
    public String getPid(){
        return playerid;
    }
}

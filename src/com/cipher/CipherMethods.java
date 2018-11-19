/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipher;

import java.io.IOException;

/**
 *
 * @author ziad
 */

public class CipherMethods {
 // private char peek = ' ';
  
    
    
    static StringBuilder myText;
    static int str ;

    /**
     *
     * @param strenght
     * @param Mystring
     * @throws IOException
     */
    protected static void CipherEncryption(int strenght ,StringBuilder Mystring) throws IOException {
    
    str=strenght;    
    myText= Mystring;
    String EncryptedText= setEncryption();
    
     //JOptionPane.showMessageDialog(null, EncryptedText); //test the string 
     
    }
    
    
    /**
     *
     * @param strenght
     * @param Mystring
     * @throws java.io.IOException
     */
    protected static void CipherDecryption(int strenght ,StringBuilder Mystring) throws IOException{
        
        str=strenght;    
        myText= Mystring;
        
        String DecryptedText= setDecryption();
        
       //JOptionPane.showMessageDialog(null, DecryptedText); //test the string 
    }
    
    
    protected static String setEncryption(){
     
        char[][] ray = new char[str][myText.length()];
        for (int i =0;i<str;i++){
            for (int j=0;j< myText.length();j++){
                ray[i][j]='.';
                
            }
        } 

        int row=0;
        boolean Direction = false;
        
        for (int i =0;i<myText.length();i++){
            if (Direction==false){
                ray[row][i] = myText.charAt(i);
                row++;
                if (row == str){
                    Direction=true;
                    row--;
                }
            }
            else if(Direction == true){
                row--;
                ray[row][i] = myText.charAt(i);
                if(row==0){
                    Direction = false;
                    row=1;
                }
            }
        }
        
        String EncryptedT =new String();
        for (int i =0;i<str;i++){
            for (int j=0;j< myText.length();j++){
                EncryptedT+=ray[i][j];
                
            }
        }        
        EncryptedT = EncryptedT.replaceAll("\\.", "");
        
        ComCipher.write(EncryptedT);
        return EncryptedT;
    }

    public static String setDecryption() {
        
        
                char[][] ray = new char[str][myText.length()];
        for (int i =0;i<str;i++){
            for (int j=0;j< myText.length();j++){
                ray[i][j]='.';
                
            }
        } 
        
        
                int row=0;
        boolean Direction = false;
        
        for (int i =0;i<myText.length();i++){
            if (Direction==false){
                ray[row][i] = myText.charAt(i);
                row++;
                if (row == str){
                    Direction=true;
                    row--;
                }
            }
            else if(Direction == true){
                row--;
                ray[row][i] = myText.charAt(i);
                if(row==0){
                    Direction = false;
                    row=1;
                }
            }
            
        }
            
            int order =0;
            for (int i = 0; i < str; i++) {
                for (int j = 0; j < myText.length(); j++) {
                 String temp = Character.toString(ray[i][j]);
                 if(temp.matches("\\.")){
                 }else{
                     ray[i][j] =  myText.charAt(order);
                     //JOptionPane.showMessageDialog(null, ray[i][j]);
                     order++;
                 }
                 
                 
                }
                
            }
       // JOptionPane.showMessageDialog(null, ray[2][2]);
       
       
        String DecryptedT ="";
        Direction = false;
        row =0;
        
        for (int i =0;i<myText.length();i++){
            if (Direction==false){
                DecryptedT+=ray[row][i];
                row++;
                if (row == str){
                    Direction=true;
                    row--;
                }
            }
            else if(Direction == true){
                row--;
                DecryptedT+=ray[row][i];
                if (row == 0){
                    Direction=false;
                    row = 1;
                }
                }
        }        
          ComCipher.write(DecryptedT);
        return DecryptedT;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipher;

/**
 *
 * @author ziad
 */
public class StringToBinary {

    StringToBinary() {
        
    }

    
    public String StringToBinary(String S){
       
        int[] bn =getascii(S) ;
        return getbinary(bn);
      
         
        
    }

    public int[] getascii(String S) {
      char[] characters = S.toCharArray();
      int [] ascii = new int [S.length()];
      for(int i =0;i<S.length();i++){
          ascii[i]=characters[i];
      } 
      
      return ascii;
    }

    private String getbinary(int[] bn) {
        String BineryText = null ; 
       for(int i =0;i<bn.length;i++){
          if(i==0){
          BineryText=eighthbit(Integer.toBinaryString(bn[i]));
          }else
              BineryText+=eighthbit(Integer.toBinaryString(bn[i]));
              
       }
       return BineryText;
    }
    
    public String getbinaryMethod2(byte [] bn) {
        String Binary = new String ();    
        StringBuilder sb = new StringBuilder("00000000");
    for(int i =0;i<bn.length;i++){ 
     for (int bit = 0; bit < 8; bit++) {
         if (((bn[i] >> bit) & 1) > 0) {
             sb.setCharAt(7 - bit, '1');
         }
     }
     if(i==0){
          Binary=sb.toString();
          }else{
          Binary+=sb.toString();
     }
        sb = new StringBuilder("00000000");      
    }
    
     return Binary;
    }
    
    

    private String eighthbit(String toBinaryString ) {
        String Conv;
        Conv = toBinaryString;
           int z = Conv.length();
           if(z<8){
           for(int j =0 ;j<(8-z);j++){
               Conv = Conv.substring(0,j)+"0"+Conv.substring(j, Conv.length());
           }
           
           }
           return Conv;
    }
    
}

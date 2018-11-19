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
public class BinaryToString {
    
    public String getascii(String S) {
      int counter =0,Let;
      char c ;
      StringBuilder LettterB = new StringBuilder();
      String unsT=new String();
      for(int i =0;i<S.length();i++){
         if (counter == 8 ){
             Let=Integer.parseInt(LettterB.toString(),2);
             c=(char)Let;
             unsT+=c;
             counter=0;
             LettterB=new StringBuilder();
         } 
         LettterB.insert(counter, S.charAt(i));
         counter++;
      }
             Let=Integer.parseInt(LettterB.toString(),2);
             c=(char)Let;
             unsT+=c;
      
      
      return unsT;
    }

    int[] getinteger(String T) {
        T=T.replace("\n", "");
    int[] getint = new int[T.length()];
    String temp= new String();
    int z ;
    int k=0;
        for (int i=0;i<T.length();i++){
            if (T.charAt(i)!=' '){
                temp+=T.charAt(i);
            }else{
                z = Integer.parseInt(temp);
                getint[k]=z;
                temp = "";
                k++;
            } 
        }
        if (!"".equals(temp)){
        z=Integer.valueOf(temp);               
        getint[k]=z;
        }k++;
        int [] res = new int[k];
        System.arraycopy(getint, 0, res, 0, k);
        
      
     return res;
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipher;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JOptionPane;

/**
 *
 * @author ziad
 */
public class StreamCipher {
    double Period;
    int[] Taps = {4,22};
    String TextBin,test,initial,key,cipherText,DecipherT,StringTogo ;
    

public StreamCipher(StringBuilder T,String Init,boolean B,byte[] arr) throws IOException{
    if (CheckString(Init)){
       this.initial=Init; 
    }
    double len = Taps[Taps.length-1]+1;
    this.Period= power(02,len)-1;
   // String s = new String();
    this.key=GenerateKey();
    StringTogo=this.key.substring(0,Math.min(this.key.length(), (arr.length-1)*8));
    //SymCipherFrame scf = new SymCipherFrame();
    //scf.setVisible(true);
    //scf.puttext(this.key.substring(0,Math.min(this.key.length(), (arr.length-1)*8)));
    StringToBinary stb = new StringToBinary();
    
    if (B){
         
        this.TextBin= T.toString();//stb.getbinaryMethod2(arr);
        this.DecipherT=getDeCipher();  
        byte[]data = new BigInteger(DecipherT,2).toByteArray();
        byte[]dataminuszero = new byte[data.length-1];
        for (int i=1;i<data.length;i++){
            dataminuszero[i-1]=data[i];
        }
        ComCipher.writeImage(dataminuszero);
 
    }else{
        this.TextBin=stb.getbinaryMethod2(arr);
        ComCipher.write2(TextBin);
        this.cipherText=getCipher();
        ComCipher.write(cipherText); 
    }    
}    
    
    
    
static boolean CheckString(String s ){
    int len = s.length();
    for (int i=0;i< len ;i++){
        if((s.charAt(i)=='1')||(s.charAt(i)=='0')){
    }else{
            JOptionPane.showMessageDialog(null, "Key is not binary ");
        System.exit(0);
        }
    }
      return true;   
}

    private String GenerateKey() {
        char[] a =initial.toCharArray();
        StringBuilder GenerakKey= new StringBuilder();
        
	char keychar = a[a.length-1];
        GenerakKey.insert(0, keychar);
	char c;	       
        int x,tapo,result;
        for (int t = 0; t < Period; t++){
            result =Integer.parseInt(String.valueOf(a[Taps[0]]));
             for(int i =1;i<Taps.length;i++){
              tapo =   Taps[i];
              c= a[tapo];
              x=  Integer.parseInt(String.valueOf(c));
              result^=x;
             }
             
             for (int i = a.length-1; i > 0; i--)
		                a[i] = a[i-1];
             if (result==1)
             a[0]='1'; 
             else
             a[0]='0';
             
            StringBuilder insert = GenerakKey.insert(t+1, a[a.length-1]);
        }
        return GenerakKey.toString();
    }

    private String getCipher() {
     StringBuilder CipherT = new StringBuilder();
     int x,y,j=0,result,keycounter=0;
    for(int i = 0;i<TextBin.length();i++){
       x=Integer.parseInt(String.valueOf(TextBin.charAt(i))); 
       if (keycounter>15){
           keycounter=0;
       }
       y=Integer.parseInt(String.valueOf(key.charAt(keycounter))); 
       result=x^y;
       if(result==1){
            CipherT.insert(j,'1');
       }else
            CipherT.insert(j,'0');
       j++;
       keycounter++;
        
    }
     
     
     return CipherT.toString();
    }
    
    
        private String getDeCipher() {
     StringBuilder CipherT = new StringBuilder();
     int x,y,j=0,result,keycounter=0,r;
    for(int i = 0;i<TextBin.length();i++){
       x=Integer.parseInt(String.valueOf(TextBin.charAt(i))); 
       if (keycounter>15){
           keycounter=0;
       }
       y=Integer.parseInt(String.valueOf(key.charAt(keycounter))); 
       result=x^y;
       if(result==1){
            CipherT.insert(j,'1');
       }else
            CipherT.insert(j,'0');
       j++;
       keycounter++;
        
    }
     
     
     return CipherT.toString();
    }
    
}

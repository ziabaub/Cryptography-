
package com.cipher;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;


public class Rsa {
 File file ;   
 BigInteger P,N,Q,Fi,Euler,D,H;
 String HashCode,decipherToGo,HashToGo10,CipherTogo,CipherTogo16,Euler16,N16,decipherToGo16,Eulertogo;
 static Set<BigInteger> s = new  HashSet<BigInteger>();
  Rsa(String P, String Q, String H0,String HC,String e,boolean b) throws InterruptedException, IOException{
      
      decipherToGo16=new String("0");
      this.P=new BigInteger(P);
      this.Q= new BigInteger(Q);
      if (this.P.compareTo(this.Q)!=0){
      this.HashCode=HC;
      H = new BigInteger(HC,16);
      this.HashToGo10 = H.toString();
      if (((isPrime(this.P))&&(isPrime(this.Q)))&&(((this.P.compareTo(new BigInteger("2")))==1)&&((this.Q.compareTo(new BigInteger("2")))==1))){
        Fi=((this.P.subtract(new BigInteger("1"))).multiply((this.Q.subtract(new BigInteger("1")))));
        this.D=new BigInteger(H0);
        this.Euler = new BigInteger(e);
        this.N= this.P.multiply(this.Q);
        this.Fi=((this.P).subtract(new BigInteger("1"))).multiply((this.Q).subtract(new BigInteger("1")));//*(this.Q-1)   
        if ((this.Euler.equals(new BigInteger("0")))&&(this.D.compareTo(new BigInteger("0"))==1)){
           this.Euler = D.modInverse(Fi); 
        }else if ((this.Euler.compareTo(new BigInteger("0"))==1)&&(this.D.equals(new BigInteger("0"))))
           this.D = Euler.modInverse(Fi);
         
        if ((D.compareTo(N.subtract(new BigInteger("2")))==-1)&&(D.compareTo(new BigInteger("1"))==1)){
            if (H.compareTo(N)==-1){
             if ((this.Euler.compareTo(new BigInteger("0"))==1)||(this.D.compareTo(new BigInteger("0"))==1)){            
                Eulertogo=Euler.toString();
                Euler16= Euler.toString(16);
                N16= N.toString(16);


        if (b){//decription 
                     file = new File("/home/ziad/workspace/NetBeans/Com.Cipher/ziad");
                     if (file==null)
                         JOptionPane.showMessageDialog(null, "File Npt Found "); 
                     else{

                        this.CipherTogo=(ComCipher.GetMeString2(file)).toString();   
                        BigInteger Sign = new BigInteger(this.CipherTogo); 
                        CipherTogo16= Sign.toString(16);
                        BigInteger h = Sign.modPow(Euler, N);
                        decipherToGo=h.toString();
                        decipherToGo16 = h.toString(16);
                    }
                }
                else{//encryption 
                    BigInteger Sign ;
                    Sign = H.modPow(D,N);
                    if (Sign.compareTo(N)!=-1){
                        JOptionPane.showMessageDialog(null, "Something Wrong with Sign ");           
                    }
                    CipherTogo16= Sign.toString(16);
                    this.CipherTogo= Sign.toString();
                    ComCipher.write(CipherTogo);
                    String data = "E -> ("+Eulertogo+")"+"\n"+"N -> ("+N.toString()+")";
                    ComCipher.write2(data);
              
      }  
      }else{
          JOptionPane.showMessageDialog(null, "Not Possible to be K an E empty ");        
      }
      }else{       
          JOptionPane.showMessageDialog(null, "H is Higher then N by teulor it hase to be H < P");
      }
      }else{
          JOptionPane.showMessageDialog(null, "D is Not Correct It hase to be "+N.toString()+"-2");
      }
      }else{
          JOptionPane.showMessageDialog(null, "P or Q has to be >1 and primitive ");
      }
      }else{
          JOptionPane.showMessageDialog(null, "P and Q it cant be equal  ");        
      }
  }
  
  
     static int ModInverse(int a,int prime){
         a=a%prime;
         for (int x=1;x<=prime ;x++){
             if ((a*x) % prime == 1)
                 return x;
         }
         return -1;
     }  
     
     public static  boolean isPrime(BigInteger b) {
        return b.isProbablePrime(1); 
    } 


     
     

private BigInteger getElementG() throws InterruptedException {

     BigInteger Eul=N;
     byte[] by = N.toByteArray();
     int len = (by.length-1)*8-1;
     Random random = new Random();
     while(Eul.compareTo(N.subtract(new BigInteger("2")))==1){
     Eul=BigInteger.probablePrime(len, random);
     }
     if (!(isPrime(Eul))){
         JOptionPane.showMessageDialog(null, "Problem with Generating E ");
     }
     
     String S = Eul.toString();

     
     return Eul;
      
        
    }  
}


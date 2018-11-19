/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipher;

//import static com.sun.org.apache.xalan.internal.lib.ExsltMath.sqrt;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 *
 * @author ziad
 */
public class elgamalCipher {		
	String p ,g,k,x,CipherTogo,DecipherTogo,MainTogo,CharTogo,CharTogo2;
	int[] M ,ContentInteger; 
        static Set<Integer> s = new  HashSet<Integer>();
        
         elgamalCipher(){
            
        }

     elgamalCipher(StringBuilder text, String p, String x, String k,boolean b ,byte[] arr) throws InterruptedException, IOException, Exception {
      this.p =p; 
      this.x =x; 
      this.k =k; 
      if (isPrime(Integer.valueOf(this.p))) {
         if (!(Integer.parseInt(p)<256)){
             if ((!(Integer.parseInt(x)>=Integer.parseInt(p)-1))&&(!(Integer.parseInt(x)<=1))){
                 if ((!(Integer.parseInt(k)>=Integer.parseInt(p)-1))&&(!(Integer.parseInt(x)<=1))){
          this.g=getElementG();
      
      if (b==true){
          MainTogo = new String(arr);
          BinaryToString bts = new BinaryToString();
          String temp,s = new String();
          for (int i =0 ;i<text.length();i++){
             if (text.charAt(i)!=',')
              s+= text.charAt(i);
             else {
              s+=',';   
              break; }  
          }//temp =new String();
          temp=text.toString();
          temp=temp.replace(s,"");
          this.M = bts.getinteger(temp);
          int [] DecipherText;
          DecipherText = goDecipher();
          byte[] FinalDecipher ;
          FinalDecipher=convertIntegersToBytes(DecipherText);
          DecipherText = GetUnsignByte(FinalDecipher);
          FinalDecipher=convertIntegersToBytes(DecipherText);
          ComCipher.writeImage(FinalDecipher);
          DecipherTogo=Arrays.toString(FinalDecipher);
          char[] textchar = new char[M.length];
          for (int i=0;i<M.length;i++)
          textchar[i]=(char)M[i];
          CharTogo2= new String(textchar);
          CharTogo=new String(FinalDecipher);
          
          
      }else{
          StringToBinary str = new StringToBinary();
          MainTogo = new String(arr); 
          this.M =GetUnsignByte(arr);
          gocipher();
          char[] textchar = new char[ContentInteger.length];
          for (int i=0;i<ContentInteger.length;i++)
              textchar[i]=(char)ContentInteger[i];
          CharTogo= new String(textchar);
          
      }
      }else{
          JOptionPane.showMessageDialog(null, "K has to be minimum P-1 and >1");}
        }else{
            JOptionPane.showMessageDialog(null, "X has to be minimum P-1 and >1");}
         }else{
               JOptionPane.showMessageDialog(null, "P has to be minimum 256 ");
         }
          } else{
    	     JOptionPane.showMessageDialog(null,"P is not a  prime number ");
            }
    }
  
    public int power (int x, int y , int p ){
        int res =1; 
        
        x=x%p; // update x if >=p
        while  (y>0){
            //if y is a odd number 
            if (y%2!=0){
                res=(res*x)%p;
            }
            y=y>>1; //y=y/2;
            x=(x*x)%p;
                
        }       
        
        return res;
    }

    public static void findprimefactors (int n ){
        while(n%2 ==0){
            s.add(2);
            n=n/2;
        }
        for (int i=3;i<=n;i=i+2){
            
            while(n%i==0){
                s.add(i);
                n=n/i;
            }
        }
        if(n>2){
            s.add(n);
        }
    }
     
    
   public static boolean isPrime(int n){
      for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
      
   

    public static String findprimitive(int n ){
        
        if (isPrime(n)==false){
            return String.valueOf(-1);
        }
        int [] roots = new int[n-1];
        int counter =0;
        int phi =n-1;
        findprimefactors(phi);
        
        for(long r=2;r<=phi;r++){
            boolean flag = false ;
            Iterator<Integer> iterator = s.iterator();
            BigInteger rbi = BigInteger.valueOf(r);
           
            for(;iterator.hasNext(); ){
                long sl=iterator.next();
             if(rbi.modPow(BigInteger.valueOf(phi/sl), BigInteger.valueOf(n)).longValue()==1){
                flag =true;
                break;
             }  
                
            }
         if (flag== false){
           roots[counter]=(int)r;
           counter++;
         }
         //return String.valueOf((int)r);
        } if (roots.length!=0){
         int [] res = new int[counter];
        System.arraycopy(roots, 0, res, 0, counter);
            String gkey = "{ ";
            for (int i =0;i<res.length-1;i++){
               gkey+=String.valueOf(res[i])+","; 
            }gkey+=String.valueOf(res[res.length-1])+" }";
           return gkey;
        }

        //if no primitive root found         
        return String.valueOf(-1);
    }

    private void gocipher() {
     String CipherT = new String();
     BigInteger gi = new BigInteger(this.g);
     BigInteger pi = new BigInteger(this.p);
     BigInteger y = gi.pow(Integer.parseInt(x)).mod(pi);
     ContentInteger =new int[M.length];
     
     int[] mess =M ;
     
     //BigInteger message = new BigInteger(String.valueOf(mess));
     BigInteger message ;
    
     BigInteger a = gi.pow(Integer.parseInt( k)).mod(pi);
     //JOptionPane.showMessageDialog(null, a);
     for(int i =0 ; i< mess.length;i++){
         message = new BigInteger(String.valueOf(mess[i]));
         BigInteger b = y.pow(Integer.parseInt(k)).multiply(message).mod(pi);
         ContentInteger[i]=b.intValue();
         CipherT+=String.valueOf(a.intValue()) +","+b.toString()+" "; 
     }
     CipherTogo=CipherT;
     ComCipher.write(CipherT);
}

     private int[]  goDecipher(){
    int[] result = new int[M.length];
    String DecipherT = new String();
     BigInteger gi = new BigInteger(this.g);
     BigInteger pi = new BigInteger(this.p);
     BigInteger y = gi.pow(Integer.parseInt(x)).mod(pi);
     
     int[] mess =M ;
    
     BigInteger a = gi.pow(Integer.parseInt( k)).mod(pi);
     BigInteger po = pi.subtract(new BigInteger("1")).subtract(new BigInteger(x));
     BigInteger m1 = a.pow(po.intValue()).mod(pi);
     BigInteger b;
     int z;
     for(int i =0 ; i< mess.length;i++){
         b = new BigInteger(String.valueOf(mess[i]));
         BigInteger res = m1.multiply(b).mod(pi);
         z=res.intValue();
         result[i]=z;
     }
     return result;
     }

    private String getElementG() throws InterruptedException {
     GkeyFrame G = new GkeyFrame();
     G.setVisible(true);
 
     String S = findprimitive(Integer.valueOf(this.p));
     G.jTextArea1.append(S);
     
      
     String Getmyg=G.nationel; 
     while (Getmyg==null){
       Thread.sleep(ThreadLocalRandom.current().nextInt(1000,5000));
       Getmyg=G.nationel;
     }
     
     return Getmyg;
      
        
    }
    
    
    private int[] GetsignByte(byte[] bi) {
        int i;
        byte[] b = bi;
        byte[] result= new byte[bi.length];
        for (int j =0;j<bi.length;j++){
        for(i=0;i<256;i++) {
 }
        }
            return null;
  
    }
    
    
    private int[] GetUnsignByte(byte[] bi) {
        int [] result = new int[bi.length];      
        for(int i=0;i<bi.length;i++){
		if (bi[i] < 0) {
		    result[i] = 256 + bi[i];
		} else {
		    result[i] = bi[i];
		}
                }
              return result;
	    }
	
  
    
     public static byte[] convertIntegersToBytes (int[] integers) 
              throws Exception // Just for simplicity!
    {
        int[] data = integers;
        byte [] result = new byte[integers.length];
        int k=0;

        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length*4);        
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);

        byte[] array = byteBuffer.array();
        
        for(int i=3;i<array.length;i+=4){
            result[k++]=array[i];
           }

        return result;
    }
    
     
}

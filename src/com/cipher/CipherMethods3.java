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
public class CipherMethods3 {
    static char[][] mat ,test,back,test1 ;
    static int[] indexo;
    String Data;
    static int c,c1;
        public CipherMethods3(int[]In,StringBuilder data){
            mat = new char[4][4];
            test = new char [4][4];
            back=new char[4][4];
            test1=new char[4][4];
            test[0][0]='*'; 
            test[1][3]='*'; 
            test[2][2]='*'; 
            test[3][1]='*'; 
            this.Data=data.toString();
            indexo=In;
            
            for(int i=0;i<4;i++){ 
                  for(int j=0 ; j<4;j++){
                      mat[i][j] = '\0';
                   }
                   }
            
            for(int i=0;i<4;i++){ 
                  for(int j=0 ; j<4;j++){
                       back[i][j] ='\0';
                   }
                   }
        }
        protected static void swapRows(char[][] m) {
	    for (int  i = 0, k = m.length - 1; i < k; ++i, --k) {
	        char[] x = m[i];
	        m[i] = m[k];
	        m[k] = x;
	    }
	}

	protected static void rotateByNinetyToLeft(char[][] m) {
	    transpose(m);
	    swapRows(m);
	}

	private static void transpose(char[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
                char x = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = x;
            }
        }
    }

	public static void rotateByNinetyToRight(char[][] m) {
	    swapRows(m);
	    transpose(m);
	}
        
        public StringBuilder cipherthetext(int ind){
            int peek=Data.length() ;
            StringBuilder CipherCode = new StringBuilder();
             c =0;  c1=0;     
            while(c<peek){
            for (int k =0;k<4;k++){    
              for(int i =0;i<4 ;i++ ){
                for(int j=0;j<4;j++){
                  if ((c < 0) || (c >= Data.length())) {
                  } else{
                      if(test[i][j]=='*'){
                       mat[i][j]= Data.charAt(c++);
                      }    
                  }
                       
                       
           
                }
              }
              rotateByNinetyToRight(test);
            } 
              if (ind==3)
                for(int i=0;i<4;i++){ 
                   for(int j=0 ; j<4;j++){ 
                       if(mat[i][j]!='\0')
                      CipherCode.insert(c1++,mat[i][j] );
                   }
                }
              
              
              for(int i=0;i<4;i++){ 
                  for(int j=0 ; j<4;j++){
                      mat[i][j] ='\0';
                   }
                   }
              
            }
         
           ComCipher.write(CipherCode.toString());
            return CipherCode;
            
        }

 public StringBuilder dcipherthetext(int ind1,StringBuilder Text ){
         
    StringBuilder CipherCode1 = new StringBuilder();  

    String text = Text.toString();
     
     int is=0,len=0,backload=0;
     int count =0;
           while (len<text.length()){
               if (ind1==3)
                   backload =text.length()-len;
                   if( backload < 16 ){
                       backclean(backload);
                       CipherCode1 = describe2(CipherCode1,count);
                       ComCipher.write(CipherCode1.toString());
                       return CipherCode1;
                   }
               for (int k=0;k<4;k++){    
                for(int i=0;i<4;i++){
                    for(int j = 0;j<4;j++){
                        if (test[i][j]=='*'){
                        CipherCode1.insert(count++,Data.charAt(is) );
                        len++;
                        }
                        is++;
                        }
                    }rotateByNinetyToRight(test);
                    is=0;
               }
          
           }
           
          ComCipher.write(CipherCode1.toString());
            return CipherCode1;
            
            
            
 }

    private void backclean(int backload ) {
       int valueofelement =16-backload;
       int countero=0;
       for (int i=0;i<4;i++){ //loop rotate   
            rotateByNinetyToLeft(test);
                 for (int k=3;k>=0;k--){
                     for(int l =3;l>=0;l--){
                         if((test[k][l]=='*')&&(countero<valueofelement)){
                             test1[k][l]='.';
                             countero++;
                         }
                 }
                   
                 }
             
            }
       
    }

    public StringBuilder describe2(StringBuilder ciphertext, int counter ) {
    
        int is=0;
        int count=counter;
        StringBuilder s = ciphertext ;
        for (int k=0;k<4;k++){    
                for(int i=0;i<4;i++){
                    for(int j = 0;j<4;j++){
                      if(test1[i][j]=='.'){
                          back[i][j]='\0';
                        continue;  
                      }else   
                        if ((test[i][j]=='*'))
                        {
                            s.insert(counter++,Data.charAt(is+count));
                        }
                      is++;
                    }
                   
               }
                is=0; 
                rotateByNinetyToRight(test);
        }
        

 
        return s;
}
}



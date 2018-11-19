    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.cipher;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.GroupLayout.Group;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;    
import java.io.FileOutputStream;
import static java.lang.Integer.parseInt;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

    /**
     *
     * @author ziad
     */
    public class ComCipher extends Application implements EventHandler<ActionEvent> {
         
        int ind,ind1,ind2,ind3,ind4,ind5,ind6,ind7,indexo[];
        boolean b;
        Stage ElGamal,sym,STMethod2,RSA;
        ToggleButton b00,b01,b02,b03,b10,b11,b12,b13,b20,b21,b22,b23,b30,b31,b32,b33;
        Button Decr,Encr,BtnBrowser,done;
        Label LbLocation,lbStrenght,RSAatt;
        String FileLocation,key,nationel;  
        File FSource;
        StringBuilder MyText,MyTextWithAllContent;
        BufferedImage im = null;
        Slider slstrength;
        RadioButton r1,r2,r3,r4,r5,r6;
        Group Method2;
        TextField keyso,P,K,X,Pi,Q,H0,N,F,E,D,Ei;
        TextArea DeCipherT,CipherT,symt;
        elgamalCipher  gamal;
        TableView<Item> T1,T2,T3,T4,T5;
        Item i1,i2,i3,i4,i5;
        Rsa R;
        byte[] array;
       // ObservableList<Item> data =FXCollections.observableArrayList();   
        @Override
        public void start(Stage primaryStage) {
           
           Pane p = new Pane();
           

           ImageView img = new ImageView("FileP/Main.jpg");
           Label lbImage = new Label();
           lbImage.setGraphic(img);
           
           
           
           LbLocation = new Label("File !");
        
           BtnBrowser = new Button("Browse"); 
           BtnBrowser.setOnAction((ActionEvent event) -> {
               JFileChooser chooser = new JFileChooser();
               chooser.showOpenDialog(null);
               FSource = chooser.getSelectedFile();
               String s = FSource.getName();
               FileLocation = new String();
               FileLocation = FSource.getAbsolutePath();
               LbLocation.setText(s);
           });
           
           HBox HbLocation = new HBox(10); 
           HbLocation.getChildren().addAll(BtnBrowser,LbLocation);
           HbLocation.setLayoutY(242);
           
            
            Label lbType = new Label("Strength");
            lbStrenght = new Label("3");
            
            HBox hbStrenght = new HBox(10);
            hbStrenght.setLayoutY(290);
            hbStrenght.setLayoutX(230);
            hbStrenght.getChildren().addAll(lbType,lbStrenght);
            
            slstrength = new Slider(1, 6, 3);
            slstrength.setLayoutY(315);
            slstrength.setLayoutX(173);
            slstrength.setValue(3);
            slstrength.setShowTickLabels(true);
            slstrength.setOnMouseReleased((MouseEvent event) -> {
                lbStrenght.setText((int)slstrength.getValue()+"");
           });
            
            
             r1 = new RadioButton();
             r2 = new RadioButton();
             r3 = new RadioButton();
             r4 = new RadioButton();
             r5 = new RadioButton();
             r6 = new RadioButton();
             
             
            ToggleGroup tb = new ToggleGroup();
            tb.getToggles().addAll(r1,r2,r3,r4,r5,r6);
            
            Label lbP = new Label("P");
            Label lbK = new Label("X");
            Label lbX = new Label("K");
            
            P = new TextField();
            K = new TextField();
            X = new TextField();

            P.setPrefWidth(36);
            K.setPrefWidth(36);
            X.setPrefWidth(36);
            
            Label lbH0 = new Label("D");
            Label lbPi = new Label("Pi");
            Label lbQ = new Label("Q");
            Label lbEi = new Label("Ei");
            
            Pi = new TextField();
            Q = new TextField();
            H0 = new TextField();
            Ei = new TextField();

            Pi.setPrefWidth(280);
            Q.setPrefWidth(280);
            H0.setPrefWidth(280);
            Ei.setPrefWidth(280);
            Ei.setText("0");
            H0.setText("0");
            //Ei.setText("3528341431848833432232984356786696789");
            //H0.setText("22433255369185775437");
            //Q.setText("11858041638789695267");
            //Pi.setText("15640288098315529097");
            
            
            
            Label lb1 = new Label("Railway Fence");
            Label lb2 = new Label("Turning Lattice");
            Label lb3 = new Label("Vigenera");
            Label lb4 = new Label("Stream Cipher");
            Label lb5 = new Label("El Gamal Cipher");
            Label lb6 = new Label("RSA Cipher       ");
            
            HBox hb1 = new HBox(15);
            HBox hb2 = new HBox(15);
            HBox hb3 = new HBox(15);
            HBox hb4 = new HBox(15);
            HBox hb5 = new HBox(15);
            HBox hb5Content = new HBox(4);
            HBox hb6 = new HBox(15);
            HBox hb6Content1 = new HBox(4);//////////////////////////////////////////
            HBox hb6Content2 = new HBox(4);
            HBox hb6Content3 = new HBox(4);
            HBox hb6Content4 = new HBox(4);
            
            hb1.getChildren().addAll(r1,lb1);
            hb2.getChildren().addAll(r2,lb2);
            hb3.getChildren().addAll(r3,lb3);
            hb4.getChildren().addAll(r4,lb4);
            hb5Content.getChildren().addAll(lbP,P,lbX,K,lbK,X);
            hb5.getChildren().addAll(r5,lb5,hb5Content);
            hb6Content1.getChildren().addAll(lbPi,Pi);
            hb6Content2.getChildren().addAll(lbQ,Q);
            hb6Content3.getChildren().addAll(lbH0,H0);
            hb6Content4.getChildren().addAll(lbEi,Ei);
            hb6.getChildren().addAll(r6,lb6);
            
           VBox vbchoosentype =new VBox(3);
           vbchoosentype.getChildren().addAll(hb1,hb2,hb3,hb4,hb5,hb6,hb6Content1,hb6Content2,hb6Content3,hb6Content4);
           vbchoosentype.setLayoutY(277);
           vbchoosentype.setLayoutX(10);
           
           Decr = new Button("Decryption");
           Encr = new Button("Encryption");
           keyso = new TextField(null);
           keyso.setPrefWidth(115);
           HBox EDButtons = new HBox(10);
           EDButtons.getChildren().addAll(Decr,Encr,keyso);
           EDButtons.setLayoutX(5);
           EDButtons.setLayoutY(540);
           
           Decr.setOnAction(this);
           Encr.setOnAction(this);
           p.getChildren().addAll(hbStrenght,slstrength);
           p.getChildren().add(EDButtons);
           p.getChildren().add(vbchoosentype);
           p.getChildren().add(lbImage); 
           p.getChildren().add(HbLocation);

           Scene s = new Scene(p,580,325);
           s.setFill(Color.WHITESMOKE);

           primaryStage.setTitle("Ciphero");
           primaryStage.setScene(s);
           primaryStage.show();
           primaryStage.setMinHeight(600);
           primaryStage.setMaxWidth(325);
  //*****************************************************************************         
           
           ElGamal = new Stage();
           Pane ElGP= new Pane();
           Scene ElGScene =new Scene(ElGP);
           ElGamal.setScene(ElGScene);
           ElGamal.setMinWidth(700);
           ElGamal.setMaxWidth(700);
           ElGamal.setMinHeight(700);
           ElGamal.setMaxWidth(700);
           
           
           
           
           VBox  ElGVBox2  =new VBox(10);
           ElGVBox2.setLayoutY(30);
           ElGVBox2.setLayoutX(10);
           VBox  ElGVBox =new VBox(10);
           ElGVBox.setLayoutY(350);
           ElGVBox.setLayoutX(10);
           
         
           
           Label LDeCipherT = new Label("Plain Text");
           DeCipherT= new TextArea();
           DeCipherT.setPrefSize(680,250);
           DeCipherT.setWrapText(true);
           
           
           Button ConvertMain = new Button("To Char");
           ElGVBox2.getChildren().addAll(ConvertMain,LDeCipherT,DeCipherT);
           
           
           Button Convert = new Button("To Char");
           
            
           Label LCipherT = new Label("Cipher Text / DeCipher Text");
           CipherT= new TextArea();
           CipherT.setPrefSize(680,250);
           CipherT.setWrapText(true);
           
           Convert.setOnAction((ActionEvent t) -> {
               CipherT.setText(gamal.CharTogo);
           });
           
           ConvertMain.setOnAction((ActionEvent t) -> {
               DeCipherT.setText(gamal.CharTogo2);
           });            ObservableList<Item> data =
           FXCollections.observableArrayList(
            new Item("A", "Z")
        );   
           
           
           
           
           
           
           ElGVBox.getChildren().addAll(Convert,LCipherT,CipherT);
           
           ElGP.getChildren().addAll(ElGVBox,ElGVBox2);
           
//***************************************************************************
           
//*****************************************************************************         
           
           RSA = new Stage();
           Pane RSAP= new Pane();
           Scene RSAScene =new Scene(RSAP);
           RSA.setTitle("RSA Info");
           RSA.setScene(RSAScene);
           RSA.setMinWidth(500);
           RSA.setMaxWidth(500);
           RSA.setMinHeight(800);
           RSA.setMaxHeight(800);

           final Label HSAKeyL = new Label("Key");
           final Label HSARecievDL = new Label("Recieved Data");
           final Label HSAPublicKL = new Label("Public Keys");
           final Label HSALetterFromEdsL = new Label("Letter From RDS");
           final Label HSAFinalL = new Label("Result");
           
           HSAKeyL.setFont(new Font("Arial",20));
           HSARecievDL.setFont(new Font("Arial",20));
           HSAPublicKL.setFont(new Font("Arial",20));
           HSALetterFromEdsL.setFont(new Font("Arial",20));
           HSAFinalL.setFont(new Font("Arial",20));
           
           
           T1=new TableView<>();
           T2=new TableView<>();
           T3=new TableView<>();
           T4=new TableView<>();
           T5=new TableView<>();

            
           
           T1.setMaxHeight(90);
           T2.setMaxHeight(100);
           T3.setMaxHeight(90);
           T4.setMaxHeight(90);
           T5.setMaxHeight(100);
           
           
           T1.setEditable(true);
           T2.setEditable(true);
           T3.setEditable(true);
           T4.setEditable(true);
           T5.setEditable(true);
           
           T1.setPrefWidth(470);
            TableColumn<Item,String>  PQCo = new TableColumn<>("Element");
                   PQCo.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Item,String>  ValuePQCo = new TableColumn<>("Value");
                   ValuePQCo.setCellValueFactory(new PropertyValueFactory<>("surname"));
                   ValuePQCo.setPrefWidth(390);
                   
            TableColumn<Item,String>  DHSignCo = new TableColumn<>("Element");
                   DHSignCo.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Item,String>  ValueDHSCo = new TableColumn<>("Value");
                   ValueDHSCo.setCellValueFactory(new PropertyValueFactory<>("surname"));
                    ValueDHSCo.setPrefWidth(390); 
                    
            TableColumn<Item,String>  ENCo = new TableColumn<>("Element");
                   ENCo.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Item,String>  ValueENCo = new TableColumn<>("Value");
                   ValueENCo.setCellValueFactory(new PropertyValueFactory<>("surname"));
                   ValueENCo.setPrefWidth(390);  
                   
            TableColumn<Item,String>  Sign2Co = new TableColumn<>("Element");
                   Sign2Co.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Item,String>  ValueSign2Co = new TableColumn<>("Value");
                   ValueSign2Co.setCellValueFactory(new PropertyValueFactory<>("surname"));
                   ValueSign2Co.setPrefWidth(390);  
                   
            TableColumn<Item,String>  hHResCheckCo = new TableColumn<>("Element");
                   hHResCheckCo.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Item,String>  ValuehHResCo = new TableColumn<>("Value");
                   ValuehHResCo.setCellValueFactory(new PropertyValueFactory<>("surname"));                           
                   ValuehHResCo.setPrefWidth(390);  
           
           
           T1.getColumns().setAll(PQCo,ValuePQCo);
           T2.getColumns().addAll(DHSignCo,ValueDHSCo);
           T3.getColumns().addAll(ENCo,ValueENCo);
           T4.getColumns().addAll(Sign2Co,ValueSign2Co);
           T5.getColumns().addAll(hHResCheckCo,ValuehHResCo);
           

           
           VBox Table1 = new VBox();
           VBox Table2 = new VBox();
           VBox Table3 = new VBox();
           VBox Table4 = new VBox();
           VBox Table5 = new VBox();
           
           Table1.setSpacing(5);
           Table2.setSpacing(5);
           Table3.setSpacing(5);
           Table4.setSpacing(5);
           Table5.setSpacing(5);
           
           Table1.setPadding(new javafx.geometry.Insets(10, 0, 0, 10));
           Table2.setPadding(new javafx.geometry.Insets(10, 0, 0, 10));
           Table3.setPadding(new javafx.geometry.Insets(10, 0, 0, 10));
           Table4.setPadding(new javafx.geometry.Insets(10, 0, 0, 10));
           Table5.setPadding(new javafx.geometry.Insets(10, 0, 0, 10));
           
           Table1.getChildren().addAll(HSAKeyL,T1);
           Table2.getChildren().addAll(HSARecievDL,T2);
           Table3.getChildren().addAll(HSAPublicKL,T3);
           Table4.getChildren().addAll(HSALetterFromEdsL,T4);
           Table5.getChildren().addAll(HSAFinalL,T5);
           
           VBox MainVb = new VBox(1);
           Button bb = new  Button("To Hex");
////           
             bb.setOnAction((ActionEvent t)->{
//                 T1.getItems().add(new Item("ss", "sds"));
                 PutData(2);
             });
           MainVb.getChildren().addAll(Table1,Table2,Table3,Table4,Table5,bb);
   
          
           RSAP.getChildren().addAll(MainVb);

           
           
//***************************************************************************
           
          
           sym = new Stage();
           Pane symP= new Pane();
           Scene symScene =new Scene(symP);
           sym.setScene(symScene);
           sym.setMinWidth(580);
           sym.setMaxWidth(580);
           sym.setMinHeight(300);
           sym.setMaxHeight(300);
           
           
           symt=new TextArea();
           symt.setWrapText(true);
           symt.setLayoutY(20);
           symt.setLayoutX(20);
           symt.prefHeight(80);
           symt.prefWidth(160);
           symP.getChildren().add(symt);
           //sym.show();

        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            launch(args);
        }

    @Override //action for the too buttons dec and enc ;
    public void handle(ActionEvent event1) {
        MyText = null ;
        MyTextWithAllContent = null ;
        if (FSource == null){
         JOptionPane.showMessageDialog(null, "Pick A File!!");
         System.exit(0);
        }else{// read From file and get extension
 //************************************************************************************
                 try {
        File file =FSource;
 
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); 
            }
        } catch (IOException ex) {
            Logger.getLogger(ComCipher.class.getName()).log(Level.SEVERE, null, ex);
        }    array = bos.toByteArray();      
                 MyText= GetMeString(FSource);
                 MyTextWithAllContent= GetMeString2(FSource);
            
            } catch (IOException ex) {
                Logger.getLogger(ComCipher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 //************************************************************************************
        
                 
        
        
        
        if (event1.getSource()== Decr ){
            try {
                if (r1.isSelected()){
                   CipherMethods.CipherDecryption(Integer.parseInt(lbStrenght.getText()),MyText);
                } else if(r2.isSelected()){
                         
                         CipherMethods3 ci= new CipherMethods3( indexo,MyText);
                         StringBuilder s1= ci.dcipherthetext(parseInt(lbStrenght.getText()),MyText);
                          //   LbLocation.setText(s1.toString());
                } 
                  else if(r3.isSelected()){
                                key= toUpperCase(keyso.getText());
                                 if(key==null){
                                     System.exit(0);
                                 } 
                                   else
                                       CipherMethods2.decrypt(MyText, key);
                           }
                             else if (r4.isSelected()){     
                                      if (keyso.getText().length()!=23){
                                          JOptionPane.showMessageDialog(null , "the Initial has to be 23 binary number");
                                      }else{
                                              String res, Initial = keyso.getText();
                                              res = new String();
                                              for(int i = Initial.length()-1;i>=0;i--){
                                              res+=Initial.charAt(i);
                                           }
                                       StreamCipher strDes=new StreamCipher(MyTextWithAllContent,res,true,array); 
                                      }
                             }
                                   else if (r5.isSelected()){
                                          gamal=new elgamalCipher(MyTextWithAllContent,P.getText(),X.getText(),K.getText(),true,array);
                                          ElGamal.show();
                                          DeCipherT.setText(gamal.MainTogo);
                                          CipherT.setText(gamal.DecipherTogo);
                                 }else if (r6.isSelected()){
                                    // String hashin ="cba54c437b066da131714f0d5b89c916db03ac7f"; 
                                    String hashin = encryptThisString(MyTextWithAllContent.toString());
                                   R = new Rsa(Pi.getText(),Q.getText(),H0.getText(),hashin,Ei.getText(),true);
                                    this.b=true;
                                    PutData(1);
                                    RSA.show();
                                 }
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ComCipher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
               Logger.getLogger(ComCipher.class.getName()).log(Level.SEVERE, null, ex);
           }
         
            
            
      }
        else if (event1.getSource()==Encr){
            try {
                if (r1.isSelected())
                CipherMethods.CipherEncryption(Integer.parseInt(lbStrenght.getText()),MyText);
                     else if(r2.isSelected()){
                                CipherMethods3 ci= new CipherMethods3( indexo,MyText);
                              StringBuilder s= ci.cipherthetext(parseInt(lbStrenght.getText()));
                               
                     }
                           else if(r3.isSelected()){
                          
                               key= toUpperCase(keyso.getText());
                               if(key==null){
                                   System.exit(0);
                               }else
                               CipherMethods2.encrypt(MyText, key);
                           
                           }
                                  else if (r4.isSelected()){     
                                      if (keyso.getText().length()!=23){
                                          JOptionPane.showMessageDialog(null , "the Initial has to be 23 binary number");
                                      }else{
                                           String res, Initial = keyso.getText();
                                           res = new String();
                                           for(int i = Initial.length()-1;i>=0;i--){
                                           res+=Initial.charAt(i);
                                         }    
                                          
                                      StreamCipher str=new StreamCipher(MyTextWithAllContent,res,false,array);    
                                       sym.show();
                                       symt.setText(str.StringTogo);
                                      }
                                  }
                                        else if (r5.isSelected()){
                                         gamal=new elgamalCipher(MyTextWithAllContent,P.getText(),X.getText(),K.getText(),false,array);
                                         ElGamal.show();
                                         DeCipherT.setText(gamal.MainTogo);
                                         CipherT.setText(gamal.CipherTogo);
                                        }
                                        else if (r6.isSelected()){
                                         //   String hashin ="cba54c437b066da131714f0d5b89c916db03ac7f";
                                        String hashin = encryptThisString(MyTextWithAllContent.toString()); 
                                        R = new Rsa(Pi.getText(),Q.getText(),H0.getText(),hashin,Ei.getText(),false);
                                        if ("0".equals(Ei.getText()))
                                        Ei.setText(R.Eulertogo);
                                        else if ("0".equals(H0.getText()))
                                        H0.setText(R.D.toString());
                                        this.b=false;
                                        PutData(1);
                                        RSA.show();

                                 }
                
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ComCipher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {       
               Logger.getLogger(ComCipher.class.getName()).log(Level.SEVERE, null, ex);
           }       
      }
    }

    
    protected static StringBuilder GetMeString(File file) throws FileNotFoundException, IOException{
    
        FileInputStream fis = new FileInputStream(file);
     BufferedInputStream bis = new BufferedInputStream(fis);
     int k = 0,i;
     StringBuilder Text = new StringBuilder();
     while((i = bis.read())!=-1){
       if ( ((i>=65)&&(i<=90)) || ((i>=97)&&(i<=122)) ){
         Text.insert(k, (char)i);
         k++;  
       } 
         
     }
     return Text;
    }
    
    
    
    static public void write(String S ){    
           BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = S;

			fw = new FileWriter("ziad");
			bw = new BufferedWriter(fw);
			bw.write(content);

			//System.out.println("Done");

		} catch (IOException e) {
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
			}

		}

	}

    
        static public void write2(String S ){    
           BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = S;

			fw = new FileWriter("data");
			bw = new BufferedWriter(fw);
			bw.write(content);

			//System.out.println("Done");

		} catch (IOException e) {
		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
			}

		}

	}
//*****************************************************************************
  static public void writeImage (byte[] ByteArray) throws FileNotFoundException, IOException{ 
      // ByteArray = bos.toByteArray();
 
        //below is the different part
        File someFile = new File("ziado");
            try (FileOutputStream fos = new FileOutputStream(someFile)) {
                fos.write(ByteArray);
                fos.flush();
                fos.close();
            }
    

	}



//*****************************************************************************
    protected static StringBuilder GetMeString2(File file) throws FileNotFoundException, IOException {

     FileInputStream fis = new FileInputStream(file);
     BufferedInputStream bis = new BufferedInputStream(fis);
     int k = 0,i;
     StringBuilder Text = new StringBuilder();
     while((i = bis.read())!=-1){
         Text.insert(k, (char)i);
         k++;         
     }
     return Text;
    }
    
    
    public static String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 

  /*  private void createStage() {
        STMethod2 = new Stage();
        FlowPane Pmethod2 = new FlowPane();
        String s;
       
        int Matrixsize =parseInt(lbStrenght.getText());  
        if (Matrixsize<=1)
            System.exit(0);
       
            b00= new ToggleButton("1"); 
            b00.setPrefHeight(50);
            b00.setPrefWidth(50);
            
            b01=new ToggleButton("2"); 
            b01.setPrefHeight(50);
            b01.setPrefWidth(50);
            
            b02=new ToggleButton("3"); 
            b02.setPrefHeight(50);
            b02.setPrefWidth(50);
            
            b03=new ToggleButton("1"); 
            b03.setPrefHeight(50);
            b03.setPrefWidth(50);
            
            b10=new ToggleButton("3"); 
            b10.setPrefHeight(50);
            b10.setPrefWidth(50);
            
            b11=new ToggleButton("4"); 
            b11.setPrefHeight(50);
            b11.setPrefWidth(50);
            
            b12=new ToggleButton("4"); 
            b12.setPrefHeight(50);
            b12.setPrefWidth(50);
            
            b13=new ToggleButton("2"); 
            b13.setPrefHeight(50);
            b13.setPrefWidth(50);
            
            b20=new ToggleButton("2"); 
            b20.setPrefHeight(50);
            b20.setPrefWidth(50);
            
            b21=new ToggleButton("4"); 
            b21.setPrefHeight(50);
            b21.setPrefWidth(50);
            
            b22=new ToggleButton("4"); 
            b22.setPrefHeight(50);
            b22.setPrefWidth(50);
            
            b23=new ToggleButton("3"); 
            b23.setPrefHeight(50);
            b23.setPrefWidth(50);
            
            b30=new ToggleButton("1"); 
            b30.setPrefHeight(50);
            b30.setPrefWidth(50);
            
            b31=new ToggleButton("3"); 
            b31.setPrefHeight(50);
            b31.setPrefWidth(50);
            
            b32=new ToggleButton("2"); 
            b32.setPrefHeight(50);
            b32.setPrefWidth(50);
            
            b33=new ToggleButton("1"); 
            b33.setPrefHeight(50);
            b33.setPrefWidth(50);
            Pmethod2.getChildren().addAll(b00,b01,b02,b03,b10,b11,b12,b13,b20,b21,b22,b23,b30,b31,b32,b33);
            Pmethod2.setAlignment(Pos.CENTER);
            Pmethod2.setPadding(new Insets(15));
            //Pmethod2.setPadding(new Insets(150, 150, 150, 150));
           ToggleGroup tg1 = new ToggleGroup();
           ToggleGroup tg2 = new ToggleGroup();
           ToggleGroup tg3 = new ToggleGroup();
           ToggleGroup tg4 = new ToggleGroup();
           
           tg1.getToggles().addAll(b00,b03,b33,b30);
           tg2.getToggles().addAll(b01,b13,b32,b20);
           tg3.getToggles().addAll(b02,b23,b31,b10);
           tg4.getToggles().addAll(b11,b22,b12,b21);
           
        done = new Button("Done");
        Pmethod2.getChildren().add(done);
        
        Scene sMethod2 = new Scene(Pmethod2,260,260);
        STMethod2.setMinHeight(260);
        STMethod2.setMinWidth(235);
        STMethod2.setMaxHeight(260);
        STMethod2.setMaxWidth(235);
        STMethod2.setScene(sMethod2);
        STMethod2.show();
        
    }

    public int[] getindex() {
        int i=0;
        int[] In =new int[9];
        if(b00.isSelected()){
            In[i++]=0;
            In[i++]=0;
        }
        if (b01.isSelected()){
            In[i++]=0;
            In[i++]=1;
        }
        if (b02.isSelected()){
            In[i++]=0;
            In[i++]=2;
        }
        if (b03.isSelected()){
        
            In[i++]=0;
            In[i++]=3;
        }
        if (b10.isSelected()){
            In[i++]=1;
            In[i++]=0;
        }
        if (b11.isSelected()){
            In[i++]=1;
            In[i++]=1;
        }
        if (b12.isSelected()){
            In[i++]=1;
            In[i++]=2;
        }
        if (b13.isSelected()){
            In[i++]=1;
            In[i++]=3;
        }
        if (b20.isSelected()){
            In[i++]=2;
            In[i++]=0;
        }
        if (b21.isSelected()){
            In[i++]=2;
            In[i++]=1;
        }
        if (b22.isSelected()){
            In[i++]=2;
            In[i++]=2;
        }
        if (b23.isSelected()){
            In[i++]=2;
            In[i++]=3;
        }
        if (b30.isSelected()){
            In[i++]=3;
            In[i++]=0;
        }
        if (b31.isSelected()){
            In[i++]=3;
            In[i++]=1;
        }
        if (b32.isSelected()){
            In[i++]=3;
            In[i++]=2;
        }
        if (b33.isSelected()){
            In[i++]=3;
            In[i++]=3;
        }
                       
        ind=In[0];
        ind1=In[1];
        ind2=In[2];
        ind3=In[3];
        ind4=In[4];
        ind5=In[5];
        ind6=In[6];
        ind7=In[7];
        
       
        
        return In;
    }*/

    private void PutData(int i) {
           T1.getItems().clear();
           T2.getItems().clear();
           T3.getItems().clear();
           T4.getItems().clear();
           T5.getItems().clear();
     
           if (i==1){
                i1= new Item("H",R.HashToGo10);
                i2= new Item ("Sign",R.CipherTogo);
                i3= new Item("E",R.Euler.toString());
                i4= new Item("N",R.N.toString());
                i5= new Item("H",R.decipherToGo);
                }else {
                i1= new Item("H",R.HashCode);
                i2= new Item ("Sign",R.CipherTogo16);
                i3= new Item("E",R.Euler16);
                i4= new Item("N",R.N16);
                i5= new Item("H",R.decipherToGo16);
           }
           
           
           
           if (this.b) { //decipher
               

           T1.getItems().add(new Item("P",Pi.getText()));
           T1.getItems().add(new Item("Q",Q.getText()));        
           T2.getItems().add(new Item("Privar K",R.D.toString()));
           T2.getItems().add(i1);
           T2.getItems().add(i2);
           
           T3.getItems().add(i3);
           T3.getItems().add(i4);
           
           T4.getItems().add(i2);
           
           T5.getItems().add(i1);
           T5.getItems().add(i5);
           if (i1.getSurname().equals(i5.getSurname())){
               T5.getItems().add(new Item("Check","Signature True"));
           }else
               T5.getItems().add(new Item("Check","Signature False!!!"));
           
          
       }else{
           T1.getItems().add(new Item("P",Pi.getText()));
           T1.getItems().add(new Item("Q",Q.getText()));
           
           T2.getItems().add(new Item("Privar K",R.D.toString()));
           T2.getItems().add(i1);
           T2.getItems().add(i2);
           
           T3.getItems().add(i3);
           T3.getItems().add(i4);
           
           
       }

    }
       
       
    }

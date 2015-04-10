/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myassembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 * @author dkosyakov
 */


public class MyAssembler{
    MyAssembler(){
    }
    /**
     * @param args the command line arguments
     */
    static String[] fileContents;
    static Language l;
    static Method[] dictionary;
    static final String platformsFile="platforms.txt";
    static String[] listOfPlatforms;
    static int curLine;
    
    static void readFile(File fileToRun)throws IOException{
        int countLines=0;
        BufferedReader brCount=new BufferedReader(new FileReader(fileToRun));
        while (brCount.readLine()!=null) countLines++;
        brCount.close();
        fileContents=new String[countLines];
        BufferedReader brRead=new BufferedReader(new FileReader(fileToRun));
        for (int i=0;i<countLines;i++) fileContents[i]=brRead.readLine();
        brRead.close();
    }
    
    static void runProgram() throws Exception {
        String[] currentLine;
        for (curLine=0;curLine<fileContents.length;curLine++){
            currentLine=fileContents[curLine].trim().split(" +", 2);
            System.out.println(currentLine[0]+"   "+currentLine[1]);
            for (Method m:dictionary){
                    if (m.getName().equals(currentLine[0])){
                        Object[] args=currentLine[1].split(" *, *");
                        m.invoke(l,args);
                }
            }        
        }
    }
            
    public static void main(String[] args) {
        try{
            int countLines=0;
            BufferedReader brCount=new BufferedReader(new FileReader(platformsFile));
            while (brCount.readLine()!=null) countLines++;
            brCount.close();
            listOfPlatforms=new String[countLines];
            BufferedReader brRead=new BufferedReader(new FileReader(platformsFile));
            for (int i=0;i<countLines;i++) listOfPlatforms[i]=brRead.readLine();
            brRead.close();
        }catch(IOException e){System.out.println(e);}
        System.out.println("Type your platform number, then press <Enter>:");
        int i=0;
        for(String s:listOfPlatforms){
            System.out.println(i++ + " for "+s);
        }
        try{
            for(i=-1;(!((i<listOfPlatforms.length)&&(i>=0)));){
                byte[] b=new byte[1];
                int n=System.in.read(b);
                i=Character.getNumericValue(b[0]);
            }
        } catch(IOException e){System.out.println(e);}
        String selectedPlatform=listOfPlatforms[i];        
        try{
            Class platformClass=Class.forName(selectedPlatform);
            Platform a = (Platform)platformClass.newInstance();
            l=new Language(a);
            dictionary=l.getClass().getMethods();
        }catch(Exception e){System.out.println(e);}
    }
}

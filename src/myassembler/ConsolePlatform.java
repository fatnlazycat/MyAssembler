/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myassembler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author dkosyakov
 */
public class ConsolePlatform implements Platform{
    
    @Override
    public void inputHandler(String receiver){
        System.out.println("input (integers only):");
        byte[] b=new byte[100];
        try{
            int n=System.in.read(b);
            String result=new String(b).trim();
            int input=Integer.parseInt(result);
            MyAssembler.l.regsAndVars.put(receiver, input);
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void outputHandler(String sender){
        System.out.println(MyAssembler.l.regsAndVars.get(sender));
    }
    
    @Override
    public void main(String args[]){
        System.out.println("Please enter the filename to run:");
        new Thread(()-> {
            try{
                byte[] b=new byte[256];
                while (!MyAssembler.initialized){};
                System.in.read();
                    /*предварительно вызываем System.in.read, чтобы система обработала нажатие Enter
                    после первого ввода в главном модуле и была готова воспринимать последующий ввод*/
                int n=System.in.read(b);
                String fileToOpen=new String(b).trim();
                MyAssembler.readFile(new File(fileToOpen));
                MyAssembler.runProgram();
            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}

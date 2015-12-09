/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myassembler;

import java.util.*;
import static java.lang.Character.isLetter;

/**
 *
 * @author dkosyakov
 */
public class Language {
    Map<String, Integer> regsAndVars=new HashMap<>();
    Platform pl;
    Language(Platform currentPlatform){
        pl=currentPlatform;
        pl.main(new String[0]);
        String[] reg={"AX","BX","CX","DX"};
        for(String a:reg){
            regsAndVars.put(a, 0);
        }        
    }

    //commands description. Add your new commands here as methods
    public void MOV(String from, String to){
        int toSet=0;
        if (from.matches("[a-zA-Z][a-zA-Z_0-9]+")) {toSet=regsAndVars.get(from);}
        if (from.matches("-?\\d+")) {toSet=Integer.parseInt(from);}
        regsAndVars.put(to,toSet);
    }
    
    public void ADD(String result, String adder){
        int toAdd=0;
        if (adder.matches("[a-zA-Z][a-zA-Z_0-9]+")) {toAdd=regsAndVars.get(adder);}
        if (adder.matches("-?\\d+")) {toAdd=Integer.parseInt(adder);}
        regsAndVars.put(result,regsAndVars.get(result)+toAdd);
    }
    
    public void SUB(String result, String deduction){
        int toSub=0;
        if (deduction.matches("[a-zA-Z][a-zA-Z_0-9]+")) {toSub=regsAndVars.get(deduction);}
        if (deduction.matches("-?\\d+")) {toSub=Integer.parseInt(deduction);}
        regsAndVars.put(result,regsAndVars.get(result)-toSub);
    }
    
    public void INC(String arg){
        regsAndVars.put(arg, regsAndVars.get(arg)+1);
    }
    
    public void DEC(String arg){
        regsAndVars.put(arg, regsAndVars.get(arg)-1);
    }
    
    public void IN(String receiver){
        pl.inputHandler(receiver);
    }
    
    public void OUT(String sender){
        pl.outputHandler(sender);
    }
    
    public void DW(String name){
        if (isLetter(name.charAt(0))) regsAndVars.put(name, 0);
    }
}

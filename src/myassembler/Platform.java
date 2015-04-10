/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myassembler;

/**
 *
 * @author dkosyakov
 */
public interface Platform {
    public void main(String args[]);
    public void inputHandler(String receiver);
    public void outputHandler(String sender);
}

/*This program only uses functions for 3 switches.
  I'll make it work for more switches in the future :) */

import java.util.*;

public class Logic2excel{
  String sprache;
  String function;
  String ausgabe;
  String not="=NOT(";
  String and="=AND(";
  String or ="=OR(" ;
  
  public void Eingabe(){
    Scanner sc=new Scanner(System.in);
    System.out.println("Please choose a language: ");
    sprache=sc.next();
    while (sprache.equals("deutsch")==false||sprache.equals("english")==false){
    System.out.println("Please choose a language: ");
    }
    if (sprache.equalsIgnoreCase("deutsch")) {
    String not="=NICHT(";
    String and="=UND(";
    String or ="=ODER("; 
    } 
    System.out.println("Please name the function");
    function=sc.next();
    ausgabe=ausgabe.replaceAll("!",not)+ausgabe.replaceAll("^",and)+ausgabe.replaceAll("v",or)+;
    int notcount=function.length()-function.replaceAll("!","").length();
    int andcount=function.length()-function.replaceAll("^","").length();
    int orcount=function.length()-function.replaceAll("v","").length();
    ausgabe=not.repeat(notcount)+and.repeat(andcount)+or.repeat(orcount);
    
    }
    public static void main(String args [] ){
    Logic2excel l2e;
    l2e=new logic2excel;
    l2e.Eingabe();
    l2e.Verarbeitung();
    l2e.Ausgabe();
    }
    }

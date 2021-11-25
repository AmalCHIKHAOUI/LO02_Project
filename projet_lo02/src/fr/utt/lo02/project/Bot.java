package fr.utt.lo02.project;

import java.util.*;
public class Bot extends Joueur {
    private int numBot;
    private Strategie strateg;
    
    public Bot(int numJoueur ) {
    	super(numJoueur);
    	this.numBot=numJoueur;
    	}
    
    private Strategie getstrateg()
    {
    	return this.strateg;
    }
    
    public void setstrateg(int strateg)
    {
    	Strategie strategie;
    	if (strateg==1) {
    		strategie=new Prudent(); }
    	else 
    	{
    		strategie=new Aggressif();
    	}
    	this.strateg=strategie;
    }
    
   public void chooseRole() {
	   Random r = new Random();
	   int choixRole = r.nextInt(2);
	   
	   if(choixRole == 0) {
		   this.role = "witch";
		   
	   }else {
		   this.role = "villager";
	   }
   }
   public Partie jouer(Partie partie, int nbJoueurTot)
   {
	   Partie parti = strateg.jouer(partie, nbJoueurTot, this.numJoueur);
	   return parti;
   }  
   
   public Partie isAccused(Partie partie, Joueur joueurAccusant)
   {
	   Partie parti = strateg.isAccused(partie, joueurAccusant,this.numBot);
	   return parti;
   } 
   
   
    private int getNumBot() {
        // Automatically generated method. Please do not modify this code.
        return this.numBot;
    }

    private void setNumBot(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numBot = value;
    }

    public void effetWitch() {
    	
    }
    
    public void effetHunt() {
    	
    }

}
package fr.utt.lo02.project;

import java.util.*;
public class Prudent implements Strategie {
	public Partie jouer(Partie partie, int nbJoueurTot, int numBot) {
		Random r = new Random();
    	// Choisir un joueur a accuser
		int numJoueurAccuse = -1 ;
		boolean check=false;
		while (check==false)
		{
			numJoueurAccuse = r.nextInt(nbJoueurTot);	
			if (numJoueurAccuse != (numBot - 1) && (partie.joueur.get(numJoueurAccuse).isElimine() == false) && (partie.joueur.get(numJoueurAccuse).isRevealed() == false))
			{
				check=true;
			}
		}
		
		// accuser le joueur choisi
		Joueur joueurAccuse = partie.joueur.get(numJoueurAccuse);
		System.out.println("-------------------------------------------------------------");
		System.out.println(" J'accuse le joueur numero : " + (numJoueurAccuse + 1));
		System.out.println("-------------------------------------------------------------");
		joueurAccuse.isAccused(partie,partie.joueur.get(numBot - 1));
		if(joueurAccuse.elimine == true) {
			partie.joueur.get(numBot - 1).point++;
			partie.turn = numBot;
		}
		else {
			partie.turn = numJoueurAccuse+1;
		}
		
		return partie;
		
    }
    
    public Partie isAccused(Partie partie, Joueur joueurAccusant, int numBot){
    	Joueur bot  = partie.joueur.get(numBot -1 );
    	// Verifier si on a une carte rumeur
    	boolean aCartes= bot.main.isEmpty();
    	
    	// Si oui, choisir une carte rumeur, si non, reveler son identite
    	if (!aCartes)
    	{ //on choisit la premiere carte de la liste 
    		System.out.println("-------------------------------------------------------------");
    		System.out.println(" Je revele une carte rumeur \n " + bot.main.get(0) );
    		System.out.println("-------------------------------------------------------------");
    		partie.effetWitch(bot.main.get(0), joueurAccusant.getNumJoueur(), numBot);
    		bot.carteRevelee.add(bot.main.get(0)); // supprimer la carte jouée après l'avoir activé
    		bot.main.remove(0);
    	}
    	else 
    	{
    		
    		bot.setRevealed(true);
			System.out.println("-------------------------------------------------------------");
			System.out.println("Mon rôle est " + bot.getRole());
			System.out.println("-------------------------------------------------------------");
			if(bot.getRole().equalsIgnoreCase("witch")) {
				bot.setElimine(true);
				joueurAccusant.point++;
				partie.turn = joueurAccusant.numJoueur;
			}
			else {
				partie.turn = numBot;
			}
    	}
    	
    	return partie;
    }
}

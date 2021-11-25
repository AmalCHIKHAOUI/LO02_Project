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
			if (numJoueurAccuse != (numBot - 1))
			{
				check=true;
			}
		}
		
		// accuser le joueur choisi
		Joueur joueurAccuse = partie.joueur.get(numJoueurAccuse);
		System.out.println("-------------------------------------------------------------");
		System.out.println(" J'accuse le joueur numero: " + (numJoueurAccuse + 1));
		System.out.println("-------------------------------------------------------------");
		joueurAccuse.isAccused(partie,partie.joueur.get(numBot - 1));
		
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
    		bot.effetWitch(partie, bot.main.get(0), joueurAccusant.getNumJoueur(), numBot);
    		bot.carteRevelee.add(bot.main.get(0)); // supprimer la carte jouée après l'avoir activé
    		bot.main.remove(0);
    	}
    	else 
    	{
    		
    		bot.setRevealed(true);
			System.out.println("-------------------------------------------------------------");
			System.out.println("Mon rôle est est " + bot.getRole());
			System.out.println("-------------------------------------------------------------");
			if(bot.getRole().equalsIgnoreCase("witch")) {
				bot.setElimine(true);
			}
    	}
    	
    	return partie;
    }
}

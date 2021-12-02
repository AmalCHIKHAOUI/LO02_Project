package fr.utt.lo02.project;

import java.util.Random;

public class Aggressif implements Strategie {
    public Partie jouer(Partie partie, int nbJoueurTot, int numBot) {
    	// Rechercher un hunt agressif (on l'utilise sur un joueur)
    	int position =-1;
    	int i=0;
    	for (CarteRumeur carterumeur: partie.joueur.get(numBot -1).main)
    	{
    		if (carterumeur.isHuntAgressive())
    		{
    			position=i;
    		}
    	}
       	// Si on a un hunt agressif,l'utiliser sur un joueur, sinon, agresser un joueur au hasard
    	Random r = new Random();
    	int numJoueurAccuse = -1 ;
		boolean check=false;
    	if (position != -1) {
	    	// Choisir un joueur sur lequel utiliser le hunt
			while (check==false)
			{
				numJoueurAccuse = r.nextInt(nbJoueurTot);	
				if (numJoueurAccuse != (numBot - 1)  && (partie.joueur.get(numJoueurAccuse).isElimine() == false))
				{
					check=true;
				}
			}
			partie.effetHunt(partie.joueur.get(numBot-1).main.get(position), numJoueurAccuse + 1);
		}
    	 //agresser un joueur par hasard 
    	else 
    	{
    		while (check==false)
			{
				numJoueurAccuse = r.nextInt(nbJoueurTot);	
				if (numJoueurAccuse != (numBot - 1)  && (partie.joueur.get(numJoueurAccuse).isElimine() == false) && (partie.joueur.get(numJoueurAccuse).isRevealed() == false))
				{
					check=true;
				}
			}
    		System.out.println("-------------------------------------------------------------");
    		System.out.println(" J'accuse le joueur numero: " + (numJoueurAccuse + 1));
    		System.out.println("-------------------------------------------------------------");
    		partie.joueur.get(numJoueurAccuse).isAccused(partie, partie.joueur.get(numBot -1));
    		if(partie.joueur.get(numJoueurAccuse).elimine == true) {
    			partie.joueur.get(numBot - 1).point++;
    			partie.turn = numBot;
    		}
    		else {
    			partie.turn = numJoueurAccuse+1;
    		}
    		
    	}
		return partie;
		
    }
    	
    
    
    public Partie isAccused(Partie partie, Joueur joueurAccusant, int numBot){
    	
    	// Verifier si on a une carte rumeur
    	
    	Joueur bot= partie.joueur.get(numBot -1);
    	
    	boolean aCartes= bot.main.isEmpty();
    	if (aCartes==false)
    	{
    		// rechercher un witch agressif
    		int position =-1;
        	int i=0;
        	for (CarteRumeur carterumeur: partie.joueur.get(numBot -1).main)
        	{
        		if (carterumeur.isWitchAgressive())
        		{
        			position=i;
        		}
        	}
    		//si pas de which agressif ,jouer un witch au hasard
        	if (position==-1) {
	        	System.out.println("-------------------------------------------------------------");
	    		System.out.println(" Je revele une carte rumeur \n " + bot.main.get(0) );
	    		System.out.println("-------------------------------------------------------------");
	    		partie.effetWitch(bot.main.get(0), joueurAccusant.getNumJoueur(), numBot);
	    		bot.carteRevelee.add(bot.main.get(0)); // supprimer la carte jouée après l'avoir activé
	    		bot.main.remove(0);
        	}
        	else 
        	{
        		System.out.println("-------------------------------------------------------------");
	    		System.out.println(" Je revele une carte rumeur \n " + bot.main.get(0) );
	    		System.out.println("-------------------------------------------------------------");
	    		partie.effetWitch(bot.main.get(position), joueurAccusant.getNumJoueur(), numBot);
	    		bot.carteRevelee.add(bot.main.get(position)); // supprimer la carte jouée après l'avoir activé
	    		bot.main.remove(0);
        	}
    		
    	}else {
    		//reveler son identite
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

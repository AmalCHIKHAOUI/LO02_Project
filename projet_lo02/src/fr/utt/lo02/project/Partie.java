package fr.utt.lo02.project;

import java.util.*;


public class Partie {
    protected Partie objetUnique;
    protected int round;
    protected int turn;
    protected int nbJoueur;
    protected int nbOrdi;
    protected int gagnant;
    
    private int nbJoueurElimine;
    private int nbJoueurRevele;


    // constante utilis�e pour savoir si un joueur est witch ou villager
    protected final String w="witch"; 
    protected final String v="villager";

    public ArrayList<Joueur> joueur = new ArrayList<Joueur> ();
    public static Scanner scanner = new Scanner(System.in);
	protected JeuCartes jeu;

    


    
    public Partie(int nbJoueur, int nbOrdi, JeuCartes jeu) {
    	this.nbJoueur = nbJoueur;
    	this.nbOrdi = nbOrdi;
    	this.round = 1;
    	this.turn = 1;
    	this.gagnant = 0;
    	this.jeu = jeu;
    	this.nbJoueurElimine = 0;
    	this.nbJoueurRevele = 0;


    }
    public Partie getObjetUnique() {
        // Automatically generated method. Please do not modify this code.
        return this.objetUnique;
    }

    public void setObjetUnique(Partie value) {
        // Automatically generated method. Please do not modify this code.
        this.objetUnique = value;
    }


    
    public void initListeJoueur(int nbJoueur, int nbOrdi) {
    	for (int i = 1; i <= nbJoueur+nbOrdi; i++) {
    		if (i<=nbJoueur) {
    			this.joueur.add(new Joueur(i));
    		}
    		else {
    	    	
    			System.out.println("Tapez 1 pour prudent et tapez 2 pour agressive");
    			int strateg=scanner.nextInt();
    			Bot nouveauBot = (Bot) new Joueur(i);
    			nouveauBot.setstrateg(strateg);
    			this.joueur.add(nouveauBot);
    		}
    		
    	}	
    }
    
    


    public int getNbOrdi() {
        // Automatically generated method. Please do not modify this code.
        return this.nbOrdi;
    }

    public void setNbOrdi(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbOrdi = value;
    }


    public int getNbJoueur() {
        // Automatically generated method. Please do not modify this code.
        return this.nbJoueur;
    }

    public void setNbJoueur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbJoueur = value;
    }


    public int getGagnant() {
        // Automatically generated method. Please do not modify this code.
        return this.gagnant;
    }

    public void setGagnant(int value) {
        // Automatically generated method. Please do not modify this code.
        this.gagnant = value;
    }

    //public ArrayList<Round> round = new ArrayList<Round> ();

    public int getRound() {
    	return this.round;
    }
    public void setRound(int round) {
    	this.round = round;
    }
    public void setTurn(int turn) {
    	this.turn = turn;
    }

    public void getInstance() {
    }
    
    public void countJoueurElimine() {
    	System.out.println("----------------------------------- ");
    	System.out.println("Voici les joueurs non �limin�s: ");
    	this.nbJoueurElimine = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { 
			Joueur j = (Joueur)it.next();
			if (j.elimine == true) {
				this.nbJoueurElimine++;
			}
			else {
		    	System.out.println("Joueur n�"+j.numJoueur);
			}
			
		}
    	System.out.println("----------------------------------- ");

    }
    public void countJoueurRevele() {
    	System.out.println("----------------------------------- ");
    	System.out.println("Voici les joueurs non r�v�l�s : ");
    	this.nbJoueurRevele = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { 
			Joueur j = (Joueur)it.next();
			if (j.revealed == true) {
				this.nbJoueurRevele++;
			}
			else {
		    	System.out.println("Joueur n�"+j.numJoueur);
			}
			
		}
    	System.out.println("----------------------------------- ");

    }

    public int gagne() {
    	int gagne = 0;
		Iterator<Joueur> it =joueur.iterator();
		while (it.hasNext() && gagne == 0) {
			Joueur j = it.next();
			if( j.isVictoire() == true) {
				gagne = j.numJoueur;
			}
		}
		return gagne ;
    }

    public void debutJeu() {
    	System.out.println("--------------------------");
    	System.out.println("La partie commence !!!!!!");
    	System.out.println("--------------------------");

    }

    public void finJeu(int numJoueur) {
    	System.out.println("--------------------------");
    	System.out.println("Le joueur " + numJoueur + " a gagn�");
    	System.out.println("--------------------------");
    	System.out.println("Le jeu est termin� !!!!!!");
    	System.out.println("--------------------------");
    }
    public void debutRound(int nbJoueur) { // on distribue les cartes � tout les joueurs
    	
    	this.nbJoueurElimine = 0; // on reset le nb de joueurs �limin�s � 0
    	this.nbJoueurRevele = 0;
    	this.jeu.melanger(); // m�langer les cartes
    	int tempNbCarte = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { //choisir le r�le pour chaque joueur
			Joueur j = /*(Joueur)*/it.next();
			
			j.main.clear();// supprimer toutes les cartes de la main du joueur
			j.carteRevelee.clear(); // supprimer toutes les cartes fauss�es du joueur s�lectionn�
			
			j.elimine = false; // On remet chaque joueur en jeu 
			j.revealed = false;
			
			if(nbJoueur == 3) {
				
				for(int i=tempNbCarte;i<tempNbCarte+4;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 4;
			}
			if(nbJoueur == 4) {
				for(int i=tempNbCarte;i<tempNbCarte+3;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 3;
			}
			if(nbJoueur == 5) {
				for(int i=tempNbCarte;i<tempNbCarte+2;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 2;
			}
			if(nbJoueur == 6) {
				for(int i=tempNbCarte;i<tempNbCarte+2;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 2;
			}
		}
    	
    }
    
    public void finRound() {
    	System.out.println("Le round est termin�.");
    	System.out.println("--------------------------");
    	Joueur joueurRes = null;
    	Joueur j = null;
		Iterator<Joueur> it = this.joueur.iterator();
		while(it.hasNext()) {
			joueurRes = /*(Joueur)*/it.next();
			if(joueurRes.elimine == false && joueurRes.revealed == false) {
				j = joueurRes;
			}
		}
		
		System.out.println("Le joueur "+j.numJoueur+" est le dernier joueur non r�v�l� du round ");
    	System.out.println("--------------------------");

		if(j.role.equalsIgnoreCase(this.w)) {
			j.point += 2;
			System.out.println("Il est witch donc il gagne 2 points ");
	    	System.out.println("--------------------------");

		}
		else if(j.role.equalsIgnoreCase(this.v)) {
			j.point++;
			System.out.println("Il est villager donc il gagne 1 point ");
	    	System.out.println("--------------------------");


		}
		
		System.out.println("R�capitulatif des points de chaque joueur : ");
		Iterator<Joueur> it1 = this.joueur.iterator();
		while(it1.hasNext()) {
			joueurRes = /*(Joueur)*/it1.next();
			System.out.println("Joueur "+joueurRes.numJoueur+" : "+joueurRes.point+" point(s)");
		}
    	System.out.println("--------------------------");

		
		this.round++; // on incr�mente l'attribut round

    }
    
    public void effetWitch(CarteRumeur carte, int numJoueurAccusant, int numJoueurAccuse) {
		Joueur j = this.joueur.get(numJoueurAccuse-1); // joueur accus�
		Joueur j1 = this.joueur.get(numJoueurAccusant-1); // joueur accusant

    	if(carte.witch == Witch.ANGRYMOB || carte.witch == Witch.BROOMSTICK || carte.witch == Witch.WART || carte.witch == Witch.TOAD || carte.witch == Witch.BLACKCAT || carte.witch == Witch.PETNEWT) { //prendre le prochain tour
    		System.out.println("Vous prenez un autre tour");
    		System.out.println("------------------------");
    		this.turn = numJoueurAccuse;
    	}
    	else if(carte.witch == Witch.INQUISITION){ // reprendre carte r�v�l�e
    		if(j.estVide(j.main)==false) {
    			System.out.println("Vous reprenez une carte que vous avez r�v�l�");
        		System.out.println("------------------------");
        		j.main.add(j.carteRevelee.get(0));
        		j.carteRevelee.remove(0); // on enl�ve la carte des cartes r�v�l�es
    		}
    		else {
    			System.out.println("Vous n'avez r�v�l� aucune carte");
        		System.out.println("------------------------");
    		}
    		
    		this.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // prendre une carte de l'adversaire
    		System.out.println("Vous prenez une carte au hasard du joueur accusant");
    		System.out.println("------------------------");
    		j.main.add(j1.main.get(0));
    		j1.main.remove(0); // on enl�ve la carte de la main du joueur accusant
    		System.out.println("Voici votre main � pr�sent :");
    		System.out.println("------------------------");
    		System.out.println(j.main);
    		System.out.println("------------------------");
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL || carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
    		
    		this.turn = scanner.nextInt();
    		System.out.println("------------------------");
    		
    	}
    	else if(carte.witch == Witch.CAULDRON){ //Le joueur qui vous accuse jette une carte et vous prenez le prochain tour
    		j1.melanger();
    		j1.carteRevelee.add(j1.main.get(0));
    		j1.main.remove(0);
    		this.turn = numJoueurAccuse;
    		
    	}
    	
    }

    public void effetHunt(CarteRumeur carte, int numJoueur) {
		Joueur j = this.joueur.get(numJoueur-1);

    	if(carte.hunt == Hunt.ANGRYMOB){ // r�v�ler la carte d'un autre joueur
    		System.out.println("------------------------");
    		System.out.print("Entrer le num�ro du joueur dont vous voulez r�v�ler l'identit� :");
    		int numJoueurChoisi = scanner.nextInt();
    		System.out.println("------------------------");
    		
    		Joueur j1 = this.joueur.get(numJoueurChoisi-1);
    		System.out.println("Le joueur que vous avez choisi est ... un "+ j1.role);
    		System.out.println("------------------------");

    		j1.revealed = true; // le joueur a r�v�l� son identit�
    		if(j1.role.equalsIgnoreCase(this.w)) {
    			System.out.println("Vous gagnez 2 points et vous jouez le prochain tour");
        		System.out.println("------------------------");
    			j.point+=2;
    			this.turn = numJoueur;
    		}
    		else if(j1.role.equalsIgnoreCase(this.v)){
    			System.out.println("Vous perdez 2 points et le joueur choisi prend le prochain tour");
        		System.out.println("------------------------");
    			j.point-=2;
    			if(j.point < 0) { // on v�rifie que le score du joueur n'est pas n�gatif
    				j.point=0;
    			}
    			this.turn = j1.numJoueur; // on donne la main au joueur dont l'identit� a �t� r�v�l�
    		}
    		


    	}
    	else if(carte.hunt==Hunt.INQUISITION) {
			System.out.println("----------------------------------") ;
			System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;
    		System.out.println("Voici l'identit� du joueur que vous avez choisi (� regarder discr�tement) : "+this.joueur.get(this.turn-1).role) ;
    		System.out.println("----------------------------------") ;

    	}
    	else if(carte.hunt==Hunt.POINTEDHAT) {
    		System.out.println("----------------------------------") ;
			System.out.println("L'effet Hunt de cette carte vous permet donc de reprendre une de vos cartes r�v�l�es");
			if(j.estVide(j.carteRevelee)==true) {
				System.out.println("Vous n'avez pas encore r�v�l� de cartes ! ");
			}
			else {
				j.main.add(j.carteRevelee.get(0)); // on ins�re la carte r�v�l�e dans votre main
				j.carteRevelee.remove(0);
			}
			System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("Voici l'identit� du joueur que vous avez choisi (� regarder discr�tement) : "+this.joueur.get(this.turn-1).role) ;
    		System.out.println("----------------------------------") ;

    	}
    	else if(carte.hunt==Hunt.HOOKEDNOSE) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous avez la possibilit� de lui prendre une carte al�atoire") ;
    		Joueur j1 = this.joueur.get(this.turn-1);
    		if(j1.estVide(j1.main)==false) {
    			j.melanger(); // m�langer les cartes du joueur
    			j.main.add(j1.main.get(0)); // le joueur j prend une carte al�atoire � j1
    			j1.main.remove(0);
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous lui avez pris une carte avec succ�s !!!!") ;
        		System.out.println("----------------------------------") ;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a plus de carte dans sa main !!") ;
        		System.out.println("----------------------------------") ;
    		}

    	}
    	else if(carte.hunt == Hunt.BROOMSTICK || carte.hunt == Hunt.WART){ //choisir le prochain joueur
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;

    		
    	}
    	else if(carte.hunt == Hunt.DUCKINGSTOOL) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le num�ro du joueur que vous voulez choisir : ");
    		int numJ = scanner.nextInt();
    		System.out.println("----------------------------------") ;
    		Joueur j1 = this.joueur.get(numJ-1);
    		System.out.println("Joueur n�"+numJ+" : R�v�ler votre identit�(1) ou supprimer une carte(2) ? ");
    		if(scanner.nextInt()==1) {
        		System.out.println("----------------------------------") ;
        		System.out.println("Joueur n�"+numJ+" : vous �tes un "+j1.role);
        		j1.revealed = true;
        		if(j1.role.equalsIgnoreCase(this.w)) {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous gagnez 1 point et vous prenez le prochain tour") ;
            		j.point++;
        			this.turn=numJoueur;
        		}
        		else if(j1.role.equalsIgnoreCase(this.v)) {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous perdez 1 point et il prend le prochain tour") ;
            		j.point--;
        			this.turn=numJ;
        		}
        		System.out.println("----------------------------------") ;
        		System.out.println("Vos points : "+j.point);

    		}
    		else if(scanner.nextInt()==2) { // il supprime une carte
        		System.out.println("----------------------------------") ;
        		System.out.println(j1) ;
        		System.out.print("Entrez le num�ro de la carte que vous voulez supprimer : ") ;
        		int numCarte = scanner.nextInt();
    			j1.carteRevelee.add(j1.main.get(numCarte-1));
    			j1.main.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Carte supprim�e !!!") ;
        		System.out.println("----------------------------------") ;
        		System.out.println(j.main);

    		}
    		
    	}
    	else if(carte.hunt == Hunt.CAULDRON || carte.hunt == Hunt.TOAD) { 
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous devez r�v�ler votre identit� : vous �tes un "+j.role);
    		j.revealed = true;
    		if(j.role.equalsIgnoreCase(this.w)) {
    			this.turn++;
    			j.elimine = true;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
    		}
    	}
    	else if(carte.hunt == Hunt.EVILEYE) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le num�ro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    	}
    	else if(carte.hunt == Hunt.BLACKCAT) {
    		if(j.estVide(j.carteRevelee)==false) {
    			System.out.println("----------------------------------") ;
        		System.out.println("Voici les cartes supprim�es : ");
        		System.out.println(j.carteRevelee);
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous avez choisi n'a r�v�l� aucune carte ");
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous prenez le prochain tour");
    		
    		this.turn = numJoueur;
    		
    	}
    	else if(carte.hunt == Hunt.PETNEWT) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Choisir un joueur pour lui prendre une carte rumeur qu'il a r�v�l� : ");
    		int numJ = scanner.nextInt();
    		Joueur j1 = this.joueur.get(numJ-1);
    		
    		if(j1.estVide(j.carteRevelee)==false) {
    			System.out.println(j1.carteRevelee);
        		System.out.println("----------------------------------") ;
        		System.out.print("Choisir une carte qu'il a r�v�l� (entrez le num�ro de la carte : ");
        		int numCarte = scanner.nextInt();
        		j.main.add(j1.carteRevelee.get(numCarte-1)); // le joueur prend la carte r�v�l� d'un autre joueur
        		j1.carteRevelee.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Vous avez pris sa carte r�v�l�e avec succ�s !!! ");
        		System.out.println("----------------------------------") ;
        		System.out.println(j.main);
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a r�v�l� aucune carte ");
    		}
    		
    		
    		//choisir le prochain joueur
    		System.out.println("----------------------------------") ;
    		System.out.print("Choisissez le prochain joueur (num�ro) : ");
    		this.turn = scanner.nextInt();
    		

    		}

    	System.out.println("----------------------------------") ;
		System.out.println("Au tour du joueur n�"+ this.turn); // on affiche le num�ro du prochain joueur
    }
    
    
    
    public static void main(String[] args) {
    	System.out.println("---------------------------------");
    	System.out.println("Bienvenue dans le Jeu Witch Hunt");
    	System.out.println("---------------------------------");
    	
    	JeuCartes jeu = new JeuCartes();
    	System.out.print("Combien de joueurs physiques : ");
    	int nbJoueur = scanner.nextInt();
    	System.out.print("Combien de joueurs virtuels : ");
    	int nbOrdi = scanner.nextInt();
    	Partie partie = new Partie(nbJoueur, nbOrdi, jeu);
    	
    	int nbJoueurTot = nbJoueur + nbOrdi ;
    	
    	partie.debutJeu();
    	
    	
    	
    	partie.initListeJoueur(nbJoueur,nbOrdi); //initialiser la liste des joueurs
    	
    	Joueur joueurActuel = null;


    	
    	// ----------- boucle de la partie ---------------
    	while (partie.gagne() == 0) { 
    		
    		System.out.print("Qui commence ? "); // Choisir qui commence au d�but du jeu
        	partie.turn=scanner.nextInt();
        	
        	String test = scanner.nextLine();    	

        	
        	System.out.println("--------------- D�but du Round "+partie.round+" ------------------");
        	
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le r�le pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			System.out.println("Joueur n� "+j.getNumJoueur()+" - Choisir votre r�le : witch ou villager ?");
    			String role = scanner.nextLine();
    	    	System.out.println("---------------------------------");
        		
        		
        		j.setRole(role);
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		// ----------- boucle du round ---------------
    		while(partie.nbJoueurRevele < nbJoueurTot-1 && partie.gagne() == 0) { // on v�rifie qu'il reste assez de joueur pour jouer le round et on v�rifie si un joueur a atteint le nombre de points max
    			
    			joueurActuel = partie.joueur.get(partie.turn -1);
        		
    			System.out.println("Au tour du joueur n� "+joueurActuel.getNumJoueur());
    			
        		System.out.println(joueurActuel); //afficher le num�ro et les cartes du Joueur
        		
        		partie = joueurActuel.jouer(partie); // le joueur qui a la main joue
        		
        		partie.countJoueurRevele();
        		partie.countJoueurElimine();
        		
        		
        		/*partie.turn++;
        		
        		if(partie.turn>nbJoueurTot) { // retourner au joueur 1 apr�s le tour du dernier joueur
        			partie.turn = 1;
        		}
        		
        		*/
        		
    		}
    		
    		// Fin du round : on distribue les points au joueur restant non r�v�l�
    		
    		partie.finRound();
    		
    		
    		
    		
    	}
    	
    	partie.gagnant = partie.gagne();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    }

}

package fr.utt.lo02.project;

import java.util.*;

public class Joueur {
    protected int numJoueur;
    protected boolean accused;
    protected String role;
    protected boolean victoire;
    protected int point;
    protected boolean elimine;
    protected boolean revealed;
    public LinkedList<CarteRumeur> main = new LinkedList<CarteRumeur>();
    public LinkedList<CarteRumeur> carteRevelee = new LinkedList<CarteRumeur>();
    
    // constante utilisee pour savoir si un joueur est witch ou villager
    protected final String w="witch"; 
    protected final String v="villager";
    
    private static boolean check = false; // A  utiliser pour verifier des conditions d'entree pour l'utilisateur


    public static Scanner scanner = new Scanner(System.in);
    
    private final static int pointMax = 5;



    
    public Joueur(int numJoueur) {
    	this.numJoueur = numJoueur;
    	this.victoire = false;
    	this.elimine = false;
    	this.accused = false;
    	this.point = 0;
    	this.role = null;
    	this.revealed = false;
    	
    }
    
    public void setRevealed(boolean revealed) {
    	this.revealed = revealed;
    }
    
    public boolean isRevealed() {
    	return this.revealed;
    }
    public boolean isVictoire() {
    	if(this.point >= pointMax) {
    		return this.victoire = true;
    	}
    	else {
            return this.victoire = false;
    	}
    }

    public void setVictoire(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.victoire = value;
    }

    public void melanger(){
		Collections.shuffle(this.main);
	}

    
    public void setRole(String role) {
    	this.role = role;
    }
    public String getRole() {
    	return this.role;
    }
    public int getNumJoueur() {
    	return this.numJoueur;
    }

    
    public void ramasserCarte(CarteRumeur carte){
		this.main.add(carte);
	}
    public void retirerCarte(CarteRumeur carte, Partie partie) {
    	this.main.remove(carte);
    	this.carteRevelee.add(carte);
    }
    
    public void revelerCarteRumeur(CarteRumeur carte) {
    	System.out.println(carte);
    }
    public boolean estVide(LinkedList<CarteRumeur> carte) {
		int tailleTas = carte.size();
		if(tailleTas == 0) {
			return true;
		}
		else {
			return false;
		}
	}

    public void chooseRole() {
    	check = false;
		while(check == false) {
			System.out.println("Joueur "+this.numJoueur+" - Choisir votre role : witch ou villager ?");
			this.role = scanner.nextLine();
        	if(this.role.equalsIgnoreCase(w) || this.role.equalsIgnoreCase(v)) {
        		check = true;
        	}
        	else {
        		System.out.println("---------------------------------");
            	System.out.println("La saisie est incorrecte");
            	System.out.println("---------------------------------");
        	}
		}
    	System.out.println("---------------------------------");
    }
    public Partie isAccused(Partie partie, Joueur joueurAccusant) {
    	int action = 0;
    	check=false;
    	while(check == false) {
    		System.out.println("-------------------------------------------------------------");
        	System.out.print("Joueur "+this.numJoueur+" - Voulez-vous reveler votre role(1) ou reveler l'effet Witch d'une carte rumeur(2) ? ");
        	action = scanner.nextInt();
        	
    		if(action == 1 || (action == 2 && this.estVide(main)==false)) {
    			check = true;
    		}
    		else {
        		System.out.println("---------------------------------");
            	System.out.println("La saisie est incorrecte");
            	System.out.println("---------------------------------");
        	}
    	}
		if(action==1) {
			this.revealed = true;
			System.out.println("-------------------------------------------------------------");
			System.out.println("Le role du joueur "+ this.numJoueur +" est " + this.role);
			System.out.println("-------------------------------------------------------------");
			if(this.role.equalsIgnoreCase(this.w)) {
				this.elimine = true;
			}
				
		}
		else {
			System.out.println("-------------------------------------------------------------");
			System.out.println("Joueur "+this.numJoueur+" - Voici votre jeu : ");
			System.out.println(this.main);
			check = false;
			int numCarteRevelee = 0;
			while(check == false) {
				System.out.println("-------------------------------------------------------------");
				System.out.print("Entrez le numero de la carte que vous voulez reveler : ");
				numCarteRevelee = scanner.nextInt();
	        	
	    		if(numCarteRevelee > 0 && numCarteRevelee <= this.main.size()) {
	    			check = true;
	    		}
	    		else {
	        		System.out.println("---------------------------------");
	            	System.out.println("La saisie est incorrecte");
	            	System.out.println("---------------------------------");
	        	}
	    	}
			System.out.println("-------------------------------------------------------------");
			System.out.println("Voici la carte que vous avez revele avec son effet Witch? : ");
			System.out.println("-------------------------------------------------------------");
    		this.revelerCarteRumeur(this.main.get(numCarteRevelee-1));
			this.effetWitch(partie, this.main.get(numCarteRevelee-1), joueurAccusant.numJoueur, this.numJoueur);
			
			this.carteRevelee.add(this.main.get(numCarteRevelee-1)); // supprimer la carte jouee apres l'avoir active
    		this.main.remove(numCarteRevelee-1);
		}
		return partie;
    }



    public Partie jouer(Partie partie, int nbJoueurTot) {
    	
    	
    	int action = 0;
    	check = false;
    	
    	while(check == false) {
    		System.out.println("---------------------------------");
        	System.out.print("accuser(1) ou reveler l'effet Hunt? d'une carte rumeur (2) ? ");
    		action = scanner.nextInt();
    		if(action == 1 || (action == 2 && this.estVide(main)==false)) {
    			check = true;
    		}
    		else {
        		System.out.println("---------------------------------");
            	System.out.println("La saisie est incorrecte");
            	System.out.println("---------------------------------");
        	}
    	}
    	
		/*String accuser = "accuser";
		String reveler = "reveler";*/
    	
    	int numJoueurAcc = 0;
    	Joueur joueurAccused = null;
    	
		if(action == 1) {
			
	    	check = false;
	    	while(check == false) {
	    		System.out.println("---------------------------------");
				System.out.print("Entrez le numero du joueur que vous voulez accuser : ");
	    		numJoueurAcc = scanner.nextInt();
	    		joueurAccused = partie.joueur.get(numJoueurAcc-1);
	    		if(numJoueurAcc > 0 && numJoueurAcc <= nbJoueurTot && numJoueurAcc != this.numJoueur && this.revealed == false) {
	    			check = true;
	    		}
	    		else {
	    			System.out.println("---------------------------------");
	            	System.out.println("La saisie est incorrecte");
	            	System.out.println("---------------------------------");
	    		}
	    	}

	    	
    		partie = joueurAccused.isAccused(partie,this);
    		if(joueurAccused.elimine == true) {
    			this.point++;
    			partie.turn = this.numJoueur; // le joueur actuel prend la main
    		}
    		else {
    			partie.turn = numJoueurAcc; // le joueur accused prend la main
    		}
		}
		else if(action == 2) {
			int numCarteRevelee = 0;
    		check = false;
	    	while(check == false) {
	    		System.out.println("---------------------------------");
				System.out.print("Entrez le numero de la carte que vous voulez utiliser  : ");
	    		numCarteRevelee = scanner.nextInt();
	    		if(numCarteRevelee > 0 && numCarteRevelee <= this.main.size()) {
	    			check = true;
	    		}
	    		else {
	    			System.out.println("---------------------------------");
	            	System.out.println("La saisie est incorrecte");
	            	System.out.println("---------------------------------");
	    		}
	    	}
    		
    		CarteRumeur carteJouee = this.main.get(numCarteRevelee-1);
    		this.revelerCarteRumeur(carteJouee);
    		
    		this.effetHunt(partie, carteJouee,this.numJoueur); // activer effet hunt!
    		
    		this.carteRevelee.add(carteJouee); // supprimer la carte jouee apres l'avoir active
    		this.main.remove(numCarteRevelee-1);
    		
		}
		return partie;
    }
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n ---------------------------------------- \n");		
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " et possede ces cartes : \n");
		sb.append(this.main);
		sb.append("\n ---------------------------------------- \n");
		return sb.toString();
	}
    /*public static void main (String[] args) {
    	Joueur j=new Joueur(3);
    	CarteRumeur c1 = new CarteRumeur(Hunt.ANGRYMOB,Witch.ANGRYMOB);
    	CarteRumeur c2 = new CarteRumeur(Hunt.INQUISITION,Witch.INQUISITION);
    	j.main.add(c1);
    	j.main.add(c2);
    	System.out.println(j.main);


    	
    }*/
    public void effetWitch(Partie partie ,CarteRumeur carte, int numJoueurAccusant, int numJoueurAccuse) {
		Joueur j = partie.joueur.get(numJoueurAccuse-1); // joueur accuse
		Joueur j1 = partie.joueur.get(numJoueurAccusant-1); // joueur accusant

    	if(carte.witch == Witch.ANGRYMOB || carte.witch == Witch.BROOMSTICK || carte.witch == Witch.WART || carte.witch == Witch.TOAD || carte.witch == Witch.BLACKCAT || carte.witch == Witch.PETNEWT) { //prendre le prochain tour
    		System.out.println("Vous prenez un autre tour");
    		System.out.println("------------------------");
    		partie.turn = numJoueurAccuse;
    	}
    	else if(carte.witch == Witch.INQUISITION){
    		if(j.main.size()>1) {
    			int numCarte = 0;
        		check = false;
        		while(check==false) {
        			System.out.print("Entrez le numero de la carte que vous voulez supprimer :  ");
        			numCarte = scanner.nextInt();
            		System.out.println("------------------------");
            		
            		if(numCarte > 0 && numCarte <= j.main.size()) {
            			check=true;
            		}
            		else {
            			System.out.println("---------------------------------");
                    	System.out.println("La saisie est incorrecte");
                    	System.out.println("---------------------------------");
            		}
        		}
        		j.carteRevelee.add(j.main.get(numCarte-1));
			    j.main.remove(numCarte-1);
			    System.out.println("Carte supprimee !!!") ;
			    System.out.println("----------------------------------") ;
			    System.out.println(j.main);
    		}
    		else {
    			System.out.println("Vous n'avez plus de cartes ! Aucune carte n'a ete supprimee.  ");
        		System.out.println("------------------------");
    		}
    		System.out.println("Vous prenez un autre tour");
    		System.out.println("------------------------");
    		partie.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // reprendre carte revelee
    		if(j.estVide(j.main)==false) {
    			System.out.println("Vous reprenez une carte que vous avez revele");
        		System.out.println("------------------------");
        		j.main.add(j.carteRevelee.get(0));
        		j.carteRevelee.remove(0); // on enleve la carte des cartes revelees
        		System.out.println("Voici votre main actuelle : ");
        		System.out.println(j.main);
        		System.out.println("------------------------");
        		
    		}
    		else {
    			System.out.println("Vous n'avez revele aucune carte");
        		System.out.println("------------------------");
    		}
    		
    		partie.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.HOOKEDNOSE){ // prendre une carte de l'adversaire
    		
    		if(j1.estVide(j1.main) == false && j1.main.size() > 1 ) { // le joueur accuse peut prendre une carte si la main de l'adversaire n'est pas vide 
    			System.out.println("Vous prenez une carte au hasard du joueur accusant");
        		System.out.println("------------------------");
    			j.main.add(j1.main.get(0));
        		j1.main.remove(0); // on enleve la carte de la main du joueur accusant
        		System.out.println("Voici votre main actuelle :");
        		System.out.println("------------------------");
        		System.out.println(j.main);
        		System.out.println("------------------------");
    		}
    		else {
    			System.out.println("Le joueur accusant n'a plus de carte rumeur. Vous ne lui prenez rien");
        		System.out.println("------------------------");
    		}
    		
    		partie.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL || carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		check = false;
    		while(check==false) {
    			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
        		partie.turn = scanner.nextInt();
        		if(partie.turn != numJoueurAccuse && partie.turn > 0 && partie.turn <= (partie.nbOrdi + partie.nbJoueur) && partie.joueur.get(partie.turn-1).elimine==false) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		
    		System.out.println("------------------------");
    		
    	}
    	else if(carte.witch == Witch.CAULDRON){ //Le joueur qui vous accuse jette une carte et vous prenez le prochain tour
    		System.out.println("Le joueur accusant doit jeter une carte");
    		System.out.println("------------------------");
    		if(j1.estVide(j1.main)==false) {
    			j1.melanger();
        		j1.carteRevelee.add(j1.main.get(0));
        		j1.main.remove(0);
        		System.out.println("Voici la main du joueur accuse apres avoir jete une carte : ");
        		System.out.println(j1.main);
        		System.out.println("------------------------");
    		}
    		else {
        		System.out.println("Malheureusement le joueur accusant n'a plus de cartes rumeurs. Vous venez d'utiliser inutilement votre carte !");
        		System.out.println("------------------------");

    		}
    		System.out.println("Vous prenez le tour suivant");
    		System.out.println("------------------------");
    		partie.turn = numJoueurAccuse;
    		
    	}
    	System.out.println("----------------------------------") ;
	    System.out.println("Au tour du joueur "+ partie.turn); // on affiche le numero du prochain joueur
    	
    }
    
    
    public void effetHunt(Partie partie, CarteRumeur carte, int numJoueur) {
    	     Joueur j = partie.joueur.get(numJoueur-1);
    	     int nbJoueurTot = partie.nbJoueur + partie.nbOrdi;

    	     if(carte.hunt == Hunt.ANGRYMOB){ // reveler la carte d'un autre joueur
    	       int numJoueurChoisi = 0;
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("------------------------");
    	           System.out.print("Entrer le numero du joueur dont vous voulez reveler l'identite :");
    	           numJoueurChoisi = scanner.nextInt();
    	           if(numJoueurChoisi > 0 && numJoueurChoisi <= nbJoueurTot && partie.joueur.get(numJoueurChoisi-1).revealed==false) {
    	              check=true;
    	           }
    	           else {
    	             System.out.println("---------------------------------");
    	                 System.out.println("La saisie est incorrecte");
    	                 System.out.println("---------------------------------");
    	           }
    	       }
    	       
    	       Joueur j1 = partie.joueur.get(numJoueurChoisi-1);
    	       System.out.println("------------------------");
    	       System.out.println("Le joueur que vous avez choisi est ... un "+ j1.role);

    	       j1.revealed = true; // le joueur a revele son identite
    	       if(j1.role.equalsIgnoreCase(w)) {
    	           System.out.println("------------------------");
    	           System.out.println("Vous gagnez 2 points et vous jouez le prochain tour");
    	           j1.elimine=true;
    	           j.point+=2;
    	           partie.turn = numJoueur;
    	       }
    	       else if(j1.role.equalsIgnoreCase(v)){
    	           System.out.println("------------------------");
    	           System.out.println("Vous perdez 2 points et le joueur choisi prend le prochain tour");
    	           j.point-=2;
    	           if(j.point < 0) { // on verifie que le score du joueur n'est pas negatif
    	        	   j.point=0;
    	           }
    	           partie.turn = j1.numJoueur; // on donne la main au joueur dont l'identite a ete revele
    	       }

    	     }
    	     else if(carte.hunt==Hunt.INQUISITION) {
    	     
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    	           partie.turn = scanner.nextInt();
    	           if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	        	   check=true;
    	           }
    	           else {
    	               System.out.println("---------------------------------");
    	               System.out.println("La saisie est incorrecte");
    	               System.out.println("---------------------------------");
    	           }
    	       }
    	       System.out.println("----------------------------------") ;
    	       System.out.println("Voici l'identite du joueur que vous avez choisi (A regarder discretement) : "+partie.joueur.get(partie.turn-1).role) ;

    	     }
    	     else if(carte.hunt==Hunt.POINTEDHAT) {
		         System.out.println("----------------------------------") ;
	    	     System.out.println("L'effet Hunt de cette carte vous permet donc de reprendre une de vos cartes revelees");
	    	     if(j.estVide(j.carteRevelee)==true) {
	    	         System.out.println("----------------------------------") ;
	    	         System.out.println("Vous n'avez pas encore revele de cartes ! ");
	    	     }
	    	     else {
	    	         j.main.add(j.carteRevelee.get(0)); // on insere la carte revelee dans votre main
	    	         j.carteRevelee.remove(0);
	    	     }
	    	       
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    	           partie.turn = scanner.nextInt();
    	           if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	               check=true;
    	           }
    	           else {
    	        	     System.out.println("---------------------------------");
		                 System.out.println("La saisie est incorrecte");
		                 System.out.println("---------------------------------");
    	           }
    	       }
    	       

    	     }
    	     else if(carte.hunt==Hunt.HOOKEDNOSE) {
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    	           partie.turn = scanner.nextInt();
    	           if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	               check=true;
    	           }
    	           else {
    	               System.out.println("---------------------------------");
    	               System.out.println("La saisie est incorrecte");
    	               System.out.println("---------------------------------");
    	           }
    	       }
    	       System.out.println("----------------------------------") ;
    	       System.out.println("Vous avez la possibilite de lui prendre une carte aleatoire") ;
    	       Joueur j1 = partie.joueur.get(partie.turn-1);
    	       if(j1.estVide(j1.main)==false) {
    	           j.melanger(); // melanger les cartes du joueur
    	           j.main.add(j1.main.get(0)); // le joueur j prend une carte aleatoire a  j1
    	           j1.main.remove(0);
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Vous lui avez pris une carte avec succes !!!!") ;
    	       }
    	       else {
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Le joueur que vous avez choisi n'a plus de carte dans sa main !!") ;
    	       }

    	     }
    	     else if(carte.hunt == Hunt.BROOMSTICK || carte.hunt == Hunt.WART){ //choisir le prochain joueur
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    	           partie.turn = scanner.nextInt();
    	           if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	               check=true;
    	           }
    	           else {
    	               System.out.println("---------------------------------");
    	               System.out.println("La saisie est incorrecte");
    	               System.out.println("---------------------------------");
    	           }
    	       }

    	       
    	     }
    	     else if(carte.hunt == Hunt.DUCKINGSTOOL) {
    	       
    	       int numJ = 0;
    	       int action = 0;
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur que vous voulez choisir : ");
    	           numJ = scanner.nextInt();
    	           if(numJ > 0 && numJ <= nbJoueurTot && partie.joueur.get(numJ-1).elimine==false && numJ != numJoueur && (partie.joueur.get(numJ-1).revealed==false || partie.joueur.get(numJ-1).estVide(partie.joueur.get(numJ-1).main)==false)) {
    	               check=true;
    	           }
    	           else {
    	               System.out.println("---------------------------------");
    	               System.out.println("La saisie est incorrecte");
    	               System.out.println("---------------------------------");
    	           }
    	       }
    	       
    	       System.out.println("----------------------------------") ;
    	       Joueur j1 = partie.joueur.get(numJ-1);
    	       
    	       check = false;
    	       
    	       while(check==false) {
    	         System.out.print("Joueur "+numJ+" : Reveler votre identite(1) ou supprimer une carte(2) ? ");
    	           action = scanner.nextInt();
    	           if((action==1 && j1.revealed==false) || (action==2 && j1.estVide(j1.main)==false)) {
    	             check=true;
    	           }
    	           else {
    	               System.out.println("---------------------------------");
    	               System.out.println("La saisie est incorrecte");
    	               System.out.println("---------------------------------");
    	           }
    	       }
    	       if(action==1) {
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Joueur "+numJ+" : vous etes un "+j1.role);
    	           j1.revealed = true;
    	           if(j1.role.equalsIgnoreCase(w)) {
    	               System.out.println("----------------------------------") ;
    	               System.out.println("Vous gagnez 1 point et vous prenez le prochain tour") ;
    	               j.point++;
    	               partie.turn=numJoueur;
    	           }
    	           else if(j1.role.equalsIgnoreCase(v)) {
    	               System.out.println("----------------------------------") ;
    	               System.out.println("Vous perdez 1 point et il prend le prochain tour") ;
    	               j.point--;
    	               if(j.point < 0) { // on verifie que le score du joueur n'est pas negatif
    	            	   j.point=0;
    	               }
    	             partie.turn=numJ;
    	           }

    	       }
    	       else if(action==2) { // il supprime une carte
    	           int numCarte = 0;
    	           System.out.println("----------------------------------") ;
    	           System.out.println(j1) ;
    	           
    	           check = false;
    	           
    	           while(check==false) {
    	               System.out.println("----------------------------------") ;
    	               System.out.print("Joueur "+numJ+" : Entrez le numero de la carte que vous voulez supprimer : ") ;
    	               numCarte = scanner.nextInt();
    	               if(numCarte > 0 && numCarte <= j1.main.size()) {
    	                 check=true;
    	               }
    	               else {
    	                   System.out.println("---------------------------------");
    	                   System.out.println("La saisie est incorrecte");
    	                   System.out.println("---------------------------------");
    	               }
    	           }
				   j1.carteRevelee.add(j1.main.get(numCarte-1));
				   j1.main.remove(numCarte-1);
				   System.out.println("----------------------------------") ;
				   System.out.println("Carte supprimee !!!") ;
				   System.out.println(j1.main);

    	       }
    	       
    	     }
    	     else if(carte.hunt == Hunt.CAULDRON || carte.hunt == Hunt.TOAD) { 
    	       System.out.println("----------------------------------") ;
    	       System.out.println("Vous devez reveler votre identite : vous etes un "+j.role);
    	       j.revealed = true;
    	       if(j.role.equalsIgnoreCase(w)) {
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Vous etes witch, vous etes donc elimines du round !! Le joueur suivant joue le prochain tour. ");
    	           partie.turn++;
    	           j.elimine = true;
    	           if(partie.turn>nbJoueurTot) { // retourner au joueur 1 apres le tour du dernier joueur
    	             partie.turn = 1;
    	           }
    	         
    	       }
    	       else {
    	         check = false;
    	           
    	           while(check==false) {
    	               System.out.println("----------------------------------") ;
    	               System.out.print("Vous etes villager, entrez le numero du joueur qui joue le prohain tour : ");
    	               partie.turn = scanner.nextInt();
    	               if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	                   check=true;
    	               }
    	               else {
    	                   System.out.println("---------------------------------");
    	                   System.out.println("La saisie est incorrecte");
    	                   System.out.println("---------------------------------");
    	               }
    	           }
    	       }
    	     }
    	     else if(carte.hunt == Hunt.EVILEYE) {
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    	           partie.turn = scanner.nextInt();
    	           if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	               check=true;
    	           }
    	           else {
	                   System.out.println("---------------------------------");
	                   System.out.println("La saisie est incorrecte");
	                   System.out.println("---------------------------------");
	               }
    	       }
    	     }
    	     else if(carte.hunt == Hunt.BLACKCAT) {
    	       if(j.estVide(j.carteRevelee)==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Voici les cartes supprimees : ");
    	           System.out.println(j.carteRevelee);
    	       }
    	       else {
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Vous n'avez revelee aucune carte ");
    	       }
    	       System.out.println("----------------------------------") ;
    	       System.out.println("Vous prenez le prochain tour");
    	       
    	       partie.turn = numJoueur;
    	       
    	     }
    	     else if(carte.hunt == Hunt.PETNEWT) {
    	       
    	       int numJ = 0;
    	       
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Choisir un joueur pour lui prendre une carte rumeur qu'il a revele : ");
    	           numJ = scanner.nextInt();
    	           if(numJ > 0 && numJ <= nbJoueurTot && partie.joueur.get(numJ-1).elimine==false && numJ != numJoueur) {
    	               check=true;
    	           }
    	           else {
	                   System.out.println("---------------------------------");
	                   System.out.println("La saisie est incorrecte");
	                   System.out.println("---------------------------------");
	               }
    	       }
    	       
    	       Joueur j1 = partie.joueur.get(numJ-1);
    	       
    	       if(j1.estVide(j.carteRevelee)==false) { // si le joueur choisi a revele une ou plusieurs cartes
    	    	   System.out.println("----------------------------------") ;
    	           System.out.println("Voici les cartes revelees du joueur que vous avez choisi : ");
    	           System.out.println(j1.carteRevelee);
    	           
    	           int numCarte = 0;
    	           check = false;
        	       
        	       while(check==false) {
        	    	   System.out.println("----------------------------------") ;
        	           System.out.print("Choisir une carte qu'il a revele (entrez le numero de la carte) : ");
        	           numCarte = scanner.nextInt();
        	           if(numCarte > 0 && numCarte <= j1.carteRevelee.size()) {
        	               check=true;
        	           }
        	           else {
    	                   System.out.println("---------------------------------");
    	                   System.out.println("La saisie est incorrecte");
    	                   System.out.println("---------------------------------");
    	               }
        	       }
    	           
    	           j.main.add(j1.carteRevelee.get(numCarte-1)); // le joueur prend la carte revele d'un autre joueur
    	           j1.carteRevelee.remove(numCarte-1);
    	           
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Vous avez pris sa carte revelee avec succes !!! ");
    	           System.out.println("----------------------------------") ;
    	           System.out.println(j.main);
    	       }
    	       else {
    	           System.out.println("----------------------------------") ;
    	           System.out.println("Le joueur que vous avez choisi n'a revele aucune carte !!! Vous venez d'utiliser une carte inutilement. ");
    	       }
    	       
    	       
    	       //choisir le prochain joueur
    	       check = false;
    	       
    	       while(check==false) {
    	           System.out.println("----------------------------------") ;
    	           System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    	           partie.turn = scanner.nextInt();
    	           if(partie.turn > 0 && partie.turn <= nbJoueurTot && partie.joueur.get(partie.turn-1).elimine==false && partie.turn != numJoueur) {
    	               check=true;
    	           }
    	           else {
	                   System.out.println("---------------------------------");
	                   System.out.println("La saisie est incorrecte");
	                   System.out.println("---------------------------------");
	               }
    	       }
    	       
    	     }

    	     System.out.println("----------------------------------") ;
    	     System.out.println("Au tour du joueur "+ partie.turn); // on affiche le numero du prochain joueur
   }
    	   
   public boolean isAccused()
   {
	   return accused;
   }
	public void setAccused(boolean accused) {
		this.accused = accused;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isElimine() {
		return elimine;
	}

	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

}

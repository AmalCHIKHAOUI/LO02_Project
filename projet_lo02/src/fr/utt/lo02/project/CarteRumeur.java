package fr.utt.lo02.project;


public class CarteRumeur {
	protected Hunt hunt;
    protected Witch witch;
    
    public CarteRumeur(Hunt hunt, Witch witch) {
    	this.hunt = hunt;
    	this.witch = witch;
    }
    

    

    public boolean  isWitchAgressive() {
    	boolean agressivew = false;
    	if(this.witch == Witch.ANGRYMOB || this.witch == Witch.INQUISITION) {
    		agressivew = true;
    	}
    	return agressivew;
    	
    }
    
    public boolean isHuntAgressive(){
    	boolean agressiveh = false;
    	if (this.hunt == Hunt.ANGRYMOB || this.hunt== Hunt.HOOKEDNOSE || this.hunt == Hunt.DUCKINGSTOOL || this.hunt== Hunt.PETNEWT)
    	{
    		agressiveh=true;
    	}
    	return agressiveh;
    }
    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("\n-----------------\n");
    	sb.append("\n Carte : ");
    	sb.append(this.witch);
    	sb.append("\n\n Witch? : ");
    	
    	if(this.witch == Witch.ANGRYMOB) {
    		sb.append(" Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.INQUISITION) {
    		sb.append(" Jeter une carte de votre main et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.POINTEDHAT) {
    		sb.append(" Reprendre une carte revelee dans votre main et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.HOOKEDNOSE) {
    		sb.append(" Prendre une carte du joueur qui vous accuse et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.BROOMSTICK) {
    		sb.append(" Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.WART) {
    		sb.append(" Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.DUCKINGSTOOL) {
    		sb.append(" Choisir le prochain joueur \n");
    	}
    	else if(this.witch == Witch.CAULDRON) {
    		sb.append(" Le joueur qui vous accuse jette une carte aleatoire et vous prenez le prochain tour \n");
    	}
    	else if(this.witch == Witch.EVILEYE) {
    		sb.append(" Choisir le prochain joueur \n");
    	}
    	else if(this.witch == Witch.TOAD) {
    		sb.append(" Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.BLACKCAT) {
    		sb.append(" Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.PETNEWT) {
    		sb.append(" Prendre le prochain tour \n");
    	}
    	sb.append("\n Hunt! : ");
    	if(this.hunt == Hunt.ANGRYMOB) {
    		sb.append(" Reveler l'identite d'un autre joueur : si witch, vous gagnez 2 pts et si villager, vous perdez 2 pts  \n");
    	}
    	else if(this.hunt == Hunt.INQUISITION) {
    		sb.append(" Choisir le prochain joueur et regarder discretement son identite \n");
    	}
    	else if(this.hunt == Hunt.POINTEDHAT) {
    		sb.append(" Reprendre une carte revelee dans votre main et choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.HOOKEDNOSE) {
    		sb.append(" Choisir le prochain joueur et prendre une de ses cartes aleatoirement  \n");
    	}
    	else if(this.hunt == Hunt.BROOMSTICK) {
    		sb.append(" Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.WART) {
    		sb.append(" Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.DUCKINGSTOOL) {
    		sb.append(" Choisir un joueur et il doit reveler son identite ou supprimer une carte : tu gagnes 1 pt et tu prend le prochain tour si Witch, tu perds 1 pt et il prend le prochain tour si Villager, il prend le prochain tour s'il supprime une carte \n");
    	}
    	else if(this.hunt == Hunt.CAULDRON) {
    		sb.append(" Reveler ton identite : le joueur suivant prend la main si Witch, choisissez le prochain joueur si Villager \n");
    	}
    	else if(this.hunt == Hunt.EVILEYE) {
    		sb.append(" Choisir le prochain joueur et il devra, si possible, accuser un autre joueur \n");
    	}
    	else if(this.hunt == Hunt.TOAD) {
    		sb.append(" Reveler votre identite \n");
    	}
    	else if(this.hunt == Hunt.BLACKCAT) {
    		sb.append(" Voir une carte supprimee et prendre le prochain tour \n");
    	}
    	else if(this.hunt == Hunt.PETNEWT) {
    		sb.append(" Prendre une carte revelee d'un autre joueur et choisir le prochain joueur \n");
    	}
    	return sb.toString();
    }
    

}

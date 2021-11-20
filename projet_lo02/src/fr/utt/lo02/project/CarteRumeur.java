package fr.utt.lo02.project;


public class CarteRumeur {
	protected Hunt hunt;
    protected Witch witch;
    
    public CarteRumeur(Hunt hunt, Witch witch) {
    	this.hunt = hunt;
    	this.witch = witch;
    }
    

    

    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("\n-----------------\n");
    	sb.append("\n Witch? : ");
    	sb.append(this.witch);
    	if(this.witch == Witch.ANGRYMOB) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.INQUISITION) {
    		sb.append("\n Jeter une carte de votre main et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.POINTEDHAT) {
    		sb.append("\n Reprendre une carte révélée dans votre main et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.HOOKEDNOSE) {
    		sb.append("\n Prendre une carte du joueur qui vous accuse et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.BROOMSTICK) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.WART) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.DUCKINGSTOOL) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.witch == Witch.CAULDRON) {
    		sb.append("\n Le joueur qui vous accuse jette une carte aléatoire et vous prenez le prochain tour \n");
    	}
    	else if(this.witch == Witch.EVILEYE) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.witch == Witch.TOAD) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.BLACKCAT) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.PETNEWT) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	sb.append("\n-----------------\n");
    	sb.append("\n Hunt! : ");
    	sb.append(this.hunt);
    	if(this.hunt == Hunt.ANGRYMOB) {
    		sb.append("\n Révéler l'identité d'un autre joueur  \n");
    	}
    	else if(this.hunt == Hunt.INQUISITION) {
    		sb.append("\n Choisir le prochain et regarder son identité \n");
    	}
    	else if(this.hunt == Hunt.POINTEDHAT) {
    		sb.append("\n Reprendre une carte révélée dans votre main et choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.HOOKEDNOSE) {
    		sb.append("\n Coisir le prochain joueur et prendre une de ses cartes  \n");
    	}
    	else if(this.hunt == Hunt.BROOMSTICK) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.WART) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.DUCKINGSTOOL) {
    		sb.append("\n Choisir un joueur et il doit révéler son identité ou supprimer une carte : tu gagnes 1 pt et tu prend le prochain tour si Witch, tu perds 1 pt et il prend le prochain tour si Villager, il prend le prochain tour s'il supprime une carte \n");
    	}
    	else if(this.hunt == Hunt.CAULDRON) {
    		sb.append("\n Révéler ton identité : le joueur suivant prend la main si Witch, choisissez le prochain joueur si Villager \n");
    	}
    	else if(this.hunt == Hunt.EVILEYE) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.TOAD) {
    		sb.append("\n Révéler votre identité \n");
    	}
    	else if(this.hunt == Hunt.BLACKCAT) {
    		sb.append("\n Voir une carte supprimée et prendre le prochain tour \n");
    	}
    	else if(this.hunt == Hunt.PETNEWT) {
    		sb.append("\n Prendre une carte révélée d'un autre joueur et choisir le prochain joueur \n");
    	}
    	return sb.toString();
    }
    

}

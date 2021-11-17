package fr.utt.lo02.project;


public class CarteRumeur {
	private Hunt hunt;
    private Witch witch;
    
    public CarteRumeur(Hunt hunt, Witch witch) {
    	this.hunt = hunt;
    	this.witch = witch;
    }
    public void effetWitch() {
    }

    public void effetHunt() {
    }

    public void revelerCarteRumeur() {
    }

    
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("\n-----------------\n");
    	sb.append("\n Witch? : ");
    	sb.append(this.witch);
    	if(this.witch == Witch.AngryMob) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.Inquisition) {
    		sb.append("\n Jeter une carte de votre main et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.PointedHat) {
    		sb.append("\n Reprendre une carte révélée dans votre main et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.HookedNose) {
    		sb.append("\n Prendre une carte du joueur qui vous accuse et prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.Broomstick) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.Wart) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.DuckingStool) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.witch == Witch.Cauldron) {
    		sb.append("\n Le joueur qui vous accuse jette une carte aléatoire et vous prenez le prochain tour \n");
    	}
    	else if(this.witch == Witch.EvilEye) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.witch == Witch.Toad) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.BlackCat) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	else if(this.witch == Witch.PetNewt) {
    		sb.append("\n Prendre le prochain tour \n");
    	}
    	sb.append("\n-----------------\n");
    	sb.append("\n Hunt! : ");
    	sb.append(this.hunt);
    	if(this.hunt == Hunt.AngryMob) {
    		sb.append("\n Révéler l'identité d'un autre joueur  \n");
    	}
    	else if(this.hunt == Hunt.Inquisition) {
    		sb.append("\n Choisir le prochain et regarder son identité \n");
    	}
    	else if(this.hunt == Hunt.PointedHat) {
    		sb.append("\n Reprendre une carte révélée dans votre main et choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.HookedNose) {
    		sb.append("\n Coisir le prochain joueur et prendre une de ses cartes  \n");
    	}
    	else if(this.hunt == Hunt.Broomstick) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.Wart) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.DuckingStool) {
    		sb.append("\n Choisir un joueur et il doit révéler son identité ou supprimer une carte : tu gagnes 1 pt et tu prend le prochain tour si Witch, tu perds 1 pt et il prend le prochain tour si Villager, il prend le prochain tour s'il supprime une carte \n");
    	}
    	else if(this.hunt == Hunt.Cauldron) {
    		sb.append("\n Révéler ton identité : le joueur suivant prend la main si Witch, choisissez le prochain joueur si Villager \n");
    	}
    	else if(this.hunt == Hunt.EvilEye) {
    		sb.append("\n Choisir le prochain joueur \n");
    	}
    	else if(this.hunt == Hunt.Toad) {
    		sb.append("\n Révéler votre identité \n");
    	}
    	else if(this.hunt == Hunt.BlackCat) {
    		sb.append("\n Voir une carte supprimée et prendre le prochain tour \n");
    	}
    	else if(this.hunt == Hunt.PetNewt) {
    		sb.append("\n Prendre une carte révélée d'un autre joueur et choisir le prochain joueur \n");
    	}
    	return sb.toString();
    }
    

}

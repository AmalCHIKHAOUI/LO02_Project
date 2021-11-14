package fr.utt.lo02.project;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private int numJoueur;

    private boolean victoire;

    private boolean isVictoire() {
        // Automatically generated method. Please do not modify this code.
        return this.victoire;
    }

    private void setVictoire(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.victoire = value;
    }

    private boolean elimine;

    private boolean isElimine() {
        // Automatically generated method. Please do not modify this code.
        return this.elimine;
    }

    private void setElimine(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.elimine = value;
    }

    private int point;

    private int getPoint() {
        // Automatically generated method. Please do not modify this code.
        return this.point;
    }

    private void setPoint(int value) {
        // Automatically generated method. Please do not modify this code.
        this.point = value;
    }

    private boolean accuse;

    private boolean isAccuse() {
        // Automatically generated method. Please do not modify this code.
        return this.accuse;
    }

    private void setAccuse(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accuse = value;
    }

    public CarteID carteID;

    public List<CarteRumeur> carteRumeur = new ArrayList<CarteRumeur> ();

    public void accuse() {
    }

    public void jouer() {
    }

}

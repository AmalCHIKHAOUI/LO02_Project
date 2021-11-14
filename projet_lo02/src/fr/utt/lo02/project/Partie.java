package fr.utt.lo02.project;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private Partie objetUnique;

    private Partie getObjetUnique() {
        // Automatically generated method. Please do not modify this code.
        return this.objetUnique;
    }

    private void setObjetUnique(Partie value) {
        // Automatically generated method. Please do not modify this code.
        this.objetUnique = value;
    }

    private int pointMax = 5;

    private int getPointMax() {
        // Automatically generated method. Please do not modify this code.
        return this.pointMax;
    }

    private void setPointMax(int value) {
        // Automatically generated method. Please do not modify this code.
        this.pointMax = value;
    }

    private int nbOrdi;

    private int getNbOrdi() {
        // Automatically generated method. Please do not modify this code.
        return this.nbOrdi;
    }

    private void setNbOrdi(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbOrdi = value;
    }

    private int nbJoueur;

    private int getNbJoueur() {
        // Automatically generated method. Please do not modify this code.
        return this.nbJoueur;
    }

    private void setNbJoueur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbJoueur = value;
    }

    private int gagnant;

    private int getGagnant() {
        // Automatically generated method. Please do not modify this code.
        return this.gagnant;
    }

    private void setGagnant(int value) {
        // Automatically generated method. Please do not modify this code.
        this.gagnant = value;
    }

    public List<Round> round = new ArrayList<Round> ();

    public List<Joueur> joueur = new ArrayList<Joueur> ();

    public Partie() {
    }

    public void getInstance() {
    }

    public void gagne() {
    }

    public void debutJeu() {
    }

    public void finJeu() {
    }

}

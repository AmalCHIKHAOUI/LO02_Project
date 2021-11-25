package fr.utt.lo02.project;


public interface Strategie {
    public Partie jouer(Partie partie, int nbJoueurTot, int numBot);
    public Partie isAccused(Partie partie, Joueur joueurAccusant,int numBot);

}

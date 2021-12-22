package fr.utt.lo02.project.controleur;
import fr.utt.lo02.project.modele.*;
import fr.utt.lo02.project.vue.*;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ControleurPartie {
	
	private Partie partie;
	private VuePartie vue;
	private int i;

	
	public ControleurPartie(Partie partie, VuePartie vue, JButton boutonJoueurRound, JSpinner spinnerJoueurRound, JButton boutonChoixRole, JSpinner spinnerChoixRole,
			JSpinner spinnerNumJoueurRole) {
		this.partie = partie;
		this.vue = vue;
		i = 1;

		boutonJoueurRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vue.setChoisirJoueur(false);
				vue.setJouer(false);
				vue.setChoixRole(true);
				//vue.setI(1);
				
				//indice utilisé pour assigner un role à chaque joueur
				i=1;
				
				partie.debutRound(partie.getNbJoueur()+partie.getNbOrdi());

				partie.setTurn((Integer)spinnerJoueurRound.getValue());
				
				
				
			}
		});
		
		boutonChoixRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on assigne un role a chaque joueur
				/*System.out.println("nb de joueurs : "+partie.joueur.size());
				
				partie.joueur.get(vue.getI()-1).setRole((String)spinnerChoixRole.getValue());
				
				System.out.println("Joueur "+vue.getI()+" role : "+partie.joueur.get(vue.getI()-1).getRole());
				
				if(vue.getI() < partie.joueur.size()) {
					vue.setI(vue.getI()+1);
				}
				else {
					vue.setChoisirJoueur(false);
					vue.setJouer(true);
					vue.setChoixRole(false);
				}*/
				partie.joueur.get((Integer)spinnerNumJoueurRole.getValue() - 1).setRole((String)spinnerChoixRole.getValue());
				
				System.out.println("Joueur "+(Integer)spinnerNumJoueurRole.getValue()+" role : "+partie.joueur.get((Integer)spinnerNumJoueurRole.getValue()-1).getRole());
				
				if(i<partie.joueur.size()-1) {
					i++;
				}
				else {
					vue.setChoisirJoueur(false);
					vue.setJouer(true);
					vue.setChoixRole(false);
				}
				
			}
		});
	}

}

package fr.utt.lo02.project.controleur;
import fr.utt.lo02.project.modele.*;
import fr.utt.lo02.project.vue.*;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class ControleurPartie {
	
	private Partie partie;
	private VuePartie vue;
	private int i;

	
	public ControleurPartie(Partie partie, VuePartie vue, JButton boutonJoueurRound, JSpinner spinnerJoueurRound, JButton boutonChoixRole, JSpinner spinnerChoixRole,
			JSpinner spinnerNumJoueurRole, JButton carte1, JButton carte2, JButton carte3, JButton carte4,JTextPane textEffet) {
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
		carte1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(carte1.getLabel() != "Vide") {
					textEffet.setText(partie.joueur.get(vue.getNumJoueurActuel()-1).main.get(0).toString());
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textEffet.setText("");

			}
		});
		carte2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(carte2.getLabel() != "Vide") {
					textEffet.setText(partie.joueur.get(vue.getNumJoueurActuel()-1).main.get(1).toString());
				}			}
			@Override
			public void mouseExited(MouseEvent e) {
				textEffet.setText("");

			}
		});
		carte3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(carte3.getLabel() != "Vide") {
					textEffet.setText(partie.joueur.get(vue.getNumJoueurActuel()-1).main.get(2).toString());
				}			}
			@Override
			public void mouseExited(MouseEvent e) {
				textEffet.setText("");

			}
		});
		carte4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(carte4.getLabel() != "Vide") {
					textEffet.setText(partie.joueur.get(vue.getNumJoueurActuel()-1).main.get(3).toString());
				}			}
			@Override
			public void mouseExited(MouseEvent e) {
				textEffet.setText("");

			}
		});
		carte1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vue.setJouer(false);
				CarteRumeur carteJouee = partie.joueur.get(partie.getTurn()-1).main.get(0);
				partie.effetHunt(carteJouee,partie.getTurn());
			}
		});
	}

}

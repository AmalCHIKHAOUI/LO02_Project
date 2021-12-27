package fr.utt.lo02.project.vue;
import fr.utt.lo02.project.modele.*;
import fr.utt.lo02.project.controleur.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.*;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerListModel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VuePartie extends JFrame implements Observer {

	private JPanel contentPane;
	
	private Partie partie;
	
	private JLabel labelNumJoueur;
	private JButton carte1;
	private JButton carte2;
	private JButton carte3;
	private JButton carte4;
	private JButton boutonAccuser;
	private JLabel labelAccuser;
	private JTextPane textEffet;
	private JLabel labelEffet;
	private JSpinner spinnerAccuser;	
	private JLabel labelAction;	
	private JTextPane textAction;		
	private JLabel labelScore;	
	private JTextPane textScore;		
	private JLabel labelNonRevele;		
	private JTextPane textNonRevele;	
	private JLabel labelNonElimine;	
	private JTextPane textNonElimine;
	private JLabel labelID;
	private JButton boutonCarteID;
	private JLabel labelCarteRumeur;
	private JCheckBox checkboxRevelerID;
	
	private JButton boutonChoisirJoueur;
	private JLabel labelChoisirJoueur;
	private JSpinner spinnerChoisirJoueur;
	
	private JLabel labelChoixRole;
	private JSpinner spinnerChoixRole;
	private JLabel labelNumJoueurRole;
	private JButton boutonChoixRole;
	private JSpinner spinnerNumJoueurRole;


	protected boolean accuser;
	protected boolean jouer;
	protected boolean choisirJoueur;
	protected boolean choixRole;
	protected int numJoueurActuel;
	protected int nbJoueurTot;
	
	protected int[] score = {0,0,0,0,0,0,0};
	
	//protected int i;
	
	protected ControleurPartie controleur;
	
	// --- Debut Getters et Setters ----
	public boolean isAccuser() {
		return accuser;
	}

	public void setAccuser(boolean accuser) {
		this.accuser = accuser;
	}

	
	
	public boolean isJouer() {
		return jouer;
	}

	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public boolean isChoisirJoueur() {
		return choisirJoueur;
	}

	public void setChoisirJoueur(boolean choisirJoueur) {
		this.choisirJoueur = choisirJoueur;
	}
	
	

	public boolean isChoixRole() {
		return choixRole;
	}

	public void setChoixRole(boolean choixRole) {
		this.choixRole = choixRole;
	}
	
	
	public int getNumJoueurActuel() {
		return numJoueurActuel;
	}

	public void setNumJoueurActuel(int numJoueurActuel) {
		this.numJoueurActuel = numJoueurActuel;
	}

	// --- Fin Getters et Setters ----

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partie partie = new Partie(0,0,null);
					VuePartie frame = new VuePartie(partie);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void update(Observable instanceObservable, Object arg1) {
		
		// on active/desactive les éléments graphiques necessaires selon les situations de jeu 
		
		// situation : choix du joueur qui commence le tour 
		boutonChoisirJoueur.setEnabled(this.isChoisirJoueur());
		labelChoisirJoueur.setEnabled(this.isChoisirJoueur());
		spinnerChoisirJoueur.setEnabled(this.isChoisirJoueur());
		
		// situation : choix du role de chaque joueur
		labelChoixRole.setEnabled(this.isChoixRole());
		spinnerChoixRole.setEnabled(this.isChoixRole());
		labelNumJoueurRole.setEnabled(this.isChoixRole());
		boutonChoixRole.setEnabled(this.isChoixRole());
		spinnerNumJoueurRole.setEnabled(this.isChoixRole());
		
		// situation : un joueur joue le tour
		labelNumJoueur.setEnabled(this.isJouer());
		carte1.setEnabled(this.isJouer());
		carte2.setEnabled(this.isJouer());
		carte3.setEnabled(this.isJouer());
		carte4.setEnabled(this.isJouer());
		boutonAccuser.setEnabled(this.isJouer());
		labelAccuser.setEnabled(this.isJouer());
		textEffet.setEnabled(this.isJouer());
		labelEffet.setEnabled(this.isJouer());
		spinnerAccuser.setEnabled(this.isJouer());	
		labelAction.setEnabled(this.isJouer());	
		textAction.setEnabled(this.isJouer());		
		labelScore.setEnabled(this.isJouer());	
		textScore.setEnabled(this.isJouer());		
		labelNonRevele.setEnabled(this.isJouer());		
		textNonRevele.setEnabled(this.isJouer());	
		labelNonElimine.setEnabled(this.isJouer());	
		textNonElimine.setEnabled(this.isJouer());
		labelID.setEnabled(this.isJouer());
		boutonCarteID.setEnabled(this.isJouer());
		labelCarteRumeur.setEnabled(this.isJouer());
		checkboxRevelerID.setEnabled(this.isJouer());
		
		
		
		if(instanceObservable instanceof Partie) {

			this.partie=((Partie)instanceObservable).getInstance();
			
			this.labelNumJoueur.setText("Joueur "+partie.getTurn());
			
			// MaJ du tableau des scores
			int iScore = 1;
			for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { 
				//this.joueur.add(it.next());
				//this.joueur.get(i).addObserver(this);
				Joueur j = it.next();
				score[iScore]=j.getPoint();
				iScore++;
			}
			switch(partie.joueur.size()) {
				case 3 : 
					textScore.setText("Joueur 1 : "+score[1]+" pts \n"+"Joueur 2 : "+score[2]+" pts \n"+"Joueur 3 : "+score[3]+" pts \n");
					break;
				case 4 : 
					textScore.setText("Joueur 1 : "+score[1]+" pts \n"+"Joueur 2 : "+score[2]+" pts \n"+"Joueur 3 : "+score[3]+" pts \n"+"Joueur 4 : "+score[4]+" pts \n");
					break;
				case 5 : 
					textScore.setText("Joueur 1 : "+score[1]+" pts \n"+"Joueur 2 : "+score[2]+" pts \n"+"Joueur 3 : "+score[3]+" pts \n"+"Joueur 4 : "+score[4]+" pts \n"+"Joueur 5 : "+score[5]+" pts \n");
					break;
				case 6 : 
					textScore.setText("Joueur 1 : "+score[1]+" pts \n"+"Joueur 2 : "+score[2]+" pts \n"+"Joueur 3 : "+score[3]+" pts \n"+"Joueur 4 : "+score[4]+" pts \n"+"Joueur 5 : "+score[5]+" pts \n"+"Joueur 6 : "+score[6]+" pts \n");
					break;

			}
			
	
		}
		if(instanceObservable instanceof Joueur) {
			
			this.numJoueurActuel = ((Joueur)instanceObservable).getNumJoueur();
			
			this.boutonCarteID.setLabel(partie.joueur.get(partie.getTurn()-1).getRole());
			
			switch(((Joueur)instanceObservable).main.size()) {
				case 1 : 
					this.carte1.setLabel(((Joueur)instanceObservable).main.get(0).witch.toString());
					this.carte2.setLabel("Vide");
					this.carte3.setLabel("Vide");
					this.carte4.setLabel("Vide");
					break;
				case 2 : 
					this.carte1.setLabel(((Joueur)instanceObservable).main.get(0).witch.toString());
					this.carte2.setLabel(((Joueur)instanceObservable).main.get(1).witch.toString());
					this.carte3.setLabel("Vide");
					this.carte4.setLabel("Vide");
					break;
				case 3 : 
					this.carte1.setLabel(((Joueur)instanceObservable).main.get(0).witch.toString());
					this.carte2.setLabel(((Joueur)instanceObservable).main.get(1).witch.toString());
					this.carte3.setLabel(((Joueur)instanceObservable).main.get(2).witch.toString());
					this.carte4.setLabel("Vide");
					break;
				case 4 : 
					this.carte1.setLabel(((Joueur)instanceObservable).main.get(0).witch.toString());
					this.carte2.setLabel(((Joueur)instanceObservable).main.get(1).witch.toString());
					this.carte3.setLabel(((Joueur)instanceObservable).main.get(2).witch.toString());
					this.carte4.setLabel(((Joueur)instanceObservable).main.get(3).witch.toString());
					break;

			}
			// activation des emplacements de carte nécessaires
			if(carte1.getLabel()=="Vide") {
				carte1.setEnabled(false);
			}
			if(carte2.getLabel()=="Vide") {
				carte2.setEnabled(false);
			}
			if(carte3.getLabel()=="Vide") {
				carte3.setEnabled(false);
			}
			if(carte4.getLabel()=="Vide") {
				carte4.setEnabled(false);
			}

		}
	}
	/**
	 * Create the frame.
	 */
	public VuePartie(Partie partie) {
		
		this.accuser = false;
		this.jouer = false;
		this.choisirJoueur = false;
		this.choixRole = false;
		this.numJoueurActuel = 0;
		
		this.partie = partie;
		this.partie.addObserver(this);
		
		//int i = 0;
		for(Iterator<Joueur> it = this.partie.joueur.iterator(); it.hasNext(); ) { 
			/*this.joueur.add(it.next());
			this.joueur.get(i).addObserver(this);*/
			Joueur j = it.next();
			j.addObserver(this);
		}
		
		for(Iterator<CarteRumeur> it = this.partie.jeu.tasCartes.iterator(); it.hasNext(); ) { 
			/*this.joueur.add(it.next());
			this.joueur.get(i).addObserver(this);*/
			CarteRumeur carte = it.next();
			carte.addObserver(this);
		}
		this.nbJoueurTot = this.partie.getNbJoueur()+this.partie.getNbOrdi();

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelNumJoueur = new JLabel("Joueur "+partie.getTurn());
		labelNumJoueur.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelNumJoueur.setEnabled(false);
		labelNumJoueur.setHorizontalAlignment(SwingConstants.LEFT);
		labelNumJoueur.setBounds(24, 135, 101, 19);
		contentPane.add(labelNumJoueur);
		
		carte1 = new JButton("Carte 1");
		carte1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		carte1.setEnabled(false);
		carte1.setBounds(21, 227, 104, 108);
		contentPane.add(carte1);
		
		carte2 = new JButton("Carte 2");
		carte2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		carte2.setEnabled(false);
		carte2.setBounds(135, 227, 105, 108);
		contentPane.add(carte2);
		
		carte3 = new JButton("Carte 3");
		carte3.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		carte3.setEnabled(false);
		carte3.setBounds(253, 227, 108, 108);
		contentPane.add(carte3);
		
		carte4 = new JButton("Carte 4");
		carte4.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		carte4.setEnabled(false);
		carte4.setBounds(371, 227, 103, 108);
		contentPane.add(carte4);
		
		boutonAccuser = new JButton("Accuser");
		boutonAccuser.setEnabled(false);
		
		boutonAccuser.setBounds(383, 163, 85, 21);
		contentPane.add(boutonAccuser);
		
		labelAccuser = new JLabel("Accuser le joueur : ");
		labelAccuser.setEnabled(false);
		labelAccuser.setHorizontalAlignment(SwingConstants.CENTER);
		labelAccuser.setBounds(227, 167, 102, 13);
		contentPane.add(labelAccuser);
		
		textEffet = new JTextPane();
		textEffet.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textEffet.setEnabled(false);
		textEffet.setBounds(24, 360, 487, 167);
		contentPane.add(textEffet);
		
		labelEffet = new JLabel("Effet Witch? et Hunt! :");
		labelEffet.setEnabled(false);
		labelEffet.setBounds(24, 345, 134, 13);
		contentPane.add(labelEffet);
		
		spinnerAccuser = new JSpinner();
		spinnerAccuser.setEnabled(false);
		spinnerAccuser.setModel(new SpinnerNumberModel(1, 1, nbJoueurTot, 1));
		spinnerAccuser.setBounds(331, 164, 30, 20);
		contentPane.add(spinnerAccuser);
		
		labelAction = new JLabel("Actions r\u00E9alis\u00E9es");
		labelAction.setEnabled(false);
		labelAction.setBounds(24, 537, 134, 13);
		contentPane.add(labelAction);
		
		textAction = new JTextPane();
		textAction.setEnabled(false);
		textAction.setBounds(24, 552, 457, 49);
		contentPane.add(textAction);
		
		labelScore = new JLabel("Tableau des scores :");
		labelScore.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelScore.setEnabled(false);
		labelScore.setBounds(24, 611, 134, 13);
		contentPane.add(labelScore);
		
		textScore = new JTextPane();
		textScore.setFont(new Font("Tahoma", Font.BOLD, 9));
		textScore.setEnabled(false);
		textScore.setBounds(24, 626, 134, 76);
		contentPane.add(textScore);
		
		labelNonRevele = new JLabel("Joueurs non r\u00E9v\u00E9l\u00E9s :");
		labelNonRevele.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelNonRevele.setEnabled(false);
		labelNonRevele.setBounds(179, 611, 134, 13);
		contentPane.add(labelNonRevele);
		
		textNonRevele = new JTextPane();
		textNonRevele.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textNonRevele.setEnabled(false);
		textNonRevele.setBounds(179, 626, 134, 76);
		contentPane.add(textNonRevele);
		
		labelNonElimine = new JLabel("Joueurs non \u00E9limin\u00E9s :");
		labelNonElimine.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelNonElimine.setEnabled(false);
		labelNonElimine.setBounds(331, 611, 155, 13);
		contentPane.add(labelNonElimine);
		
		textNonElimine = new JTextPane();
		textNonElimine.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textNonElimine.setEnabled(false);
		textNonElimine.setBounds(333, 626, 148, 76);
		contentPane.add(textNonElimine);
		
		labelID = new JLabel("Carte ID");
		labelID.setEnabled(false);
		labelID.setHorizontalAlignment(SwingConstants.CENTER);
		labelID.setBounds(118, 75, 83, 13);
		contentPane.add(labelID);
		
		boutonCarteID = new JButton("Carte ID");
		boutonCarteID.setEnabled(false);
		boutonCarteID.setBounds(118, 90, 83, 108);
		contentPane.add(boutonCarteID);
		
		labelCarteRumeur = new JLabel("Cartes rumeurs :");
		labelCarteRumeur.setEnabled(false);
		labelCarteRumeur.setBounds(24, 208, 101, 13);
		contentPane.add(labelCarteRumeur);
		
		checkboxRevelerID = new JCheckBox("R\u00E9v\u00E9ler ID ?");
		checkboxRevelerID.setEnabled(false);
		checkboxRevelerID.setBounds(236, 117, 155, 21);
		contentPane.add(checkboxRevelerID);
		
		boutonChoisirJoueur = new JButton("Valider");
		boutonChoisirJoueur.setBounds(198, 36, 85, 21);
		contentPane.add(boutonChoisirJoueur);
		
		labelChoisirJoueur = new JLabel("Qui commence le round :");
		labelChoisirJoueur.setBounds(10, 40, 148, 13);
		contentPane.add(labelChoisirJoueur);
		
		spinnerChoisirJoueur = new JSpinner();
		spinnerChoisirJoueur.setModel(new SpinnerNumberModel(1, 1, nbJoueurTot, 1));
		spinnerChoisirJoueur.setBounds(158, 37, 30, 20);
		contentPane.add(spinnerChoisirJoueur);
		
		
		labelChoixRole = new JLabel("Choisir votre r\u00F4le :");
		labelChoixRole.setEnabled(false);
		labelChoixRole.setBounds(318, 44, 133, 13);
		contentPane.add(labelChoixRole);
		
		spinnerChoixRole = new JSpinner();
		spinnerChoixRole.setEnabled(false);
		spinnerChoixRole.setModel(new SpinnerListModel(new String[] {"villager", "witch"}));
		spinnerChoixRole.setBounds(434, 41, 95, 20);
		contentPane.add(spinnerChoixRole);
		
		labelNumJoueurRole = new JLabel("Joueur");
		labelNumJoueurRole.setEnabled(false);
		labelNumJoueurRole.setHorizontalAlignment(SwingConstants.CENTER);
		labelNumJoueurRole.setBounds(371, 21, 80, 13);
		contentPane.add(labelNumJoueurRole);
		
		boutonChoixRole = new JButton("Choisir ce role");	
		boutonChoixRole.setEnabled(false);
		boutonChoixRole.setBounds(328, 75, 162, 21);
		contentPane.add(boutonChoixRole);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(306, 11, 2, 85);
		contentPane.add(separator);
		
		spinnerNumJoueurRole = new JSpinner();
		spinnerNumJoueurRole.setModel(new SpinnerNumberModel(1, 1, nbJoueurTot, 1));
		spinnerNumJoueurRole.setBounds(444, 18, 30, 20);
		contentPane.add(spinnerNumJoueurRole);
		
		
		
		new ControleurPartie(this.partie,this,boutonChoisirJoueur,spinnerChoisirJoueur,boutonChoixRole,spinnerChoixRole,spinnerNumJoueurRole,carte1,carte2,carte3,carte4,textEffet);
		
		
		
		

		/*btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				partie.setTurn((Integer)spinnerChooseJoueur.getValue());
				
				
			}
		});*/
		
		
		/*VueDebutRound vueRound = new VueDebutRound(this.partie,this.partie.getRound(),nbJoueurTot);
		vueRound.setVisible(true);*/
		
		
	}
}

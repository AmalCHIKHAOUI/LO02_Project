package fr.utt.lo02.project.vue;
import fr.utt.lo02.project.modele.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VueNbJoueur extends JFrame {

	private JPanel contentPane;
	private JeuCartes jeu=new JeuCartes();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueNbJoueur frame = new VueNbJoueur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VueNbJoueur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de joueurs physiques :");
		lblNewLabel.setBounds(10, 91, 167, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombreDeBots = new JLabel("Nombre de bots :");
		lblNombreDeBots.setBounds(20, 122, 138, 19);
		contentPane.add(lblNombreDeBots);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner.setBounds(223, 91, 30, 20);
		contentPane.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner_1.setBounds(223, 122, 30, 20);
		contentPane.add(spinner_1);
		
		JLabel lblNewLabel_1 = new JLabel("Le nombre de joueurs est incorrect. R\u00E9essayez !");
		lblNewLabel_1.setEnabled(true);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(75, 215, 276, 13);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nbJoueur = (Integer)spinner.getValue();
				int nbBot = (Integer)spinner_1.getValue();
				int nbJoueurTot = nbJoueur+nbBot;
				if(nbJoueurTot>=3 && nbJoueurTot<=6) {
					Partie partie = new Partie(nbJoueur, nbBot, jeu, true);
			    	partie.initListeJoueur(nbJoueur,nbBot); //initialiser la liste des joueurs

					VuePartie nextFrame = new VuePartie(partie);
					dispose();
					nextFrame.setVisible(true);
					
				}
				else {
					lblNewLabel_1.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(168, 185, 85, 21);
		contentPane.add(btnNewButton);
		
		
		
	}

}

package fr.utt.lo02.project.vue;

import fr.utt.lo02.project.modele.*;
import java.awt.BorderLayout; 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueDebutRound extends JFrame {

	private JPanel contentPane;
	/*private int nbJoueurTot;
	private int numRound;
	private Partie partie;*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partie partie = new Partie(0,0,null);
					VueDebutRound frame = new VueDebutRound(partie,0,0);
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
	public VueDebutRound(Partie partie, int numRound, int nbJoueurTot) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Valider");
		
		btnNewButton.setBounds(147, 161, 116, 39);
		contentPane.add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, nbJoueurTot, 1));
		spinner.setBounds(233, 68, 30, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel = new JLabel("Quel joueur commence le Round ?");
		lblNewLabel.setBounds(65, 68, 158, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("D\u00E9but du Round "+numRound);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(86, 26, 250, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("nombre de joueurs : "+partie.getNbJoueur());
		lblNewLabel_2.setBounds(86, 98, 163, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("nombre de bots : "+partie.getNbOrdi());
		lblNewLabel_2_1.setBounds(86, 118, 163, 13);
		contentPane.add(lblNewLabel_2_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partie.setTurn((Integer)spinner.getValue());
				dispose();
				
			}
		});
		/*this.partie = partie;
		this.nbJoueurTot = nbJoueurTot;
		this.numRound = numRound;*/
	}

}

package fr.utt.lo02.project;


public class Bot extends Joueur {
    private int numBot;
    private Strategie strateg;
    
    public Bot(int numJoueur ) {
    	super(numJoueur);
    	this.numBot=numJoueur;
    	}
    
    private Strategie getstrateg()
    {
    	return this.strateg;
    }
    
    public void setstrateg(int strateg)
    {
    	Strategie strategie;
    	if (strateg==1) {
    		strategie=new Prudent(); }
    	else 
    	{
    		strategie=new Aggressif ();
    	}
    	this.strateg=strategie;
    }
    
   public void jouer()
   {
	   strateg.jouer();
   }    
   
   
    private int getNumBot() {
        // Automatically generated method. Please do not modify this code.
        return this.numBot;
    }

    private void setNumBot(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numBot = value;
    }

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

    public int point;

    

}

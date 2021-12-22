package fr.utt.lo02.project.modele;


public enum Witch {
	
	ANGRYMOB("AngryMob"), INQUISITION("Inquisition"), POINTEDHAT("PointedHat"), HOOKEDNOSE("HookedNose"), BROOMSTICK("Broomstick"), WART("Wart"), DUCKINGSTOOL("DuckingStool"), CAULDRON("Cauldron"), EVILEYE("EvilEye"), TOAD("Toad"), BLACKCAT("BlackCat"), PETNEWT("PetNewt");
	
	private final String text;
	
	Witch(final String text){
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}

package TD1;

public class Complexe {

    /*Scanner scanner = new Scanner(System.in);
    System.out.println("Ecrire la partie reelle  ");
    int re = scanner.nextInt();
    System.out.println(re);
    Scanner scanner = new Scanner(System.in);
    System.out.println("Ecrire la partie imaginaire ");
    int im = scanner.nextInt();
    System.out.println(im);*/
	private double re;
	private double im;
	public Complexe (double re,double im) {
		this.re=re;
		this.im=im;
	}
	public static void main(String[] args)
	{
		Complexe Z= new Complexe (12,2);
		System.out.println(Z);
	}
	public String toString() {
		return this.re + "i" + this.im;
	}
	public void  addition (Complexe Z )
	{
		this.re=this.re+Z.re;
		this.im=this.im+Z.im;
	}
	public void multiplication(Complexe Z)
	{
		//this.re=this.re*Z.re;
	
		return new Complexe(re * Z.re - im * Z.im + re * Z.im + Z.re * im);
	}
	public void module (Complexe Z)
	{
		this.re=pow(this.re);
		this.im=pow(this.im);
		this.re=pow(this.re);
	}
	
}


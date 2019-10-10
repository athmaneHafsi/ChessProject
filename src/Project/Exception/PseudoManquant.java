package Project.Exception;

public class PseudoManquant extends Exception{
	private static String mess=new String("Les deux joueurs ont le meme pseudo!!");
	public PseudoManquant() {
		super(mess);
	}
}

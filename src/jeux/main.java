package jeux;
import java.util.InputMismatchException;
import java.util.Scanner;

import Project.Exception.PseudoManquant;


public class main {
	public static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	PseudoManquant q;
        Echiquier a =Echiquier.ech;
        Arbitre arb=new Arbitre();
        char c1;
        char c2;
        int l1;
        int l2;
        char[]ligne=new char[4];
        do{
        	try
        	{
        		q=null;
        		System.out.println("Saisissez le pseudo du joueur 1 : ");
        		String Joueur1=sc.nextLine();
        		System.out.println("\nSaisissez le pseudo du joueur 2 : ");
        		String Joueur2=sc.nextLine();
        		Arbitre.listjoueur[0] =new Joueur(Joueur1);
        		Arbitre.listjoueur[1] =new Joueur(Joueur2);
        	}catch (PseudoManquant e) {
        		System.out.println(e.getMessage());
        		q=e;
        	}
        }while(q!=null);
        a.afficher();
    
        while (!Arbitre.mat(Couleur.blanc) && !Arbitre.mat(Couleur.noir))
        {
        	try
        	{
        		System.out.print("Ligne :");
        		l1=sc.nextInt();
        		System.out.print("Colonne :");
        		c1=sc.next().charAt(0);
        		System.out.print("Ligne d'arrivée :");
        		l2=sc.nextInt();
        		System.out.print("Colonne d'arrivée :");
        		c2=sc.next().charAt(0);
        	
        		
        		try
                {
                   a.mouvement(l1, c1, l2, c2);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("les coordonnones saisie sont erronees veuillez recommencer");
                }
        	}catch (InputMismatchException e) {
				System.out.println("les coordonnes ont mal etes saisies");
        		e=null;
        	}
        
        }
        if (Arbitre.mat(Couleur.blanc))
        	System.out.println("BRAVO !!! VICTOIRE DES NOIRS");
        else if(Arbitre.mat(Couleur.noir))
        	System.out.println("BRAVO !!! VICTOIRE DES BLANCS");
        
       
    }

}

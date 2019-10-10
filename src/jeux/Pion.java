package jeux;
import java.util.ArrayList;

public class Pion extends Piece {

    public Pion(Case cases) {
        super(cases);
    }
    public Pion(Case cases,Couleur coul) {
        super(cases);
        couleur=coul;
    }
    public String toString() {
    	if (couleur==Couleur.blanc)
    		return "PB";
    	return "PN";
    }
    public Pion(Case cases,Couleur coul,int i) {
    	super(cases);
        couleur=coul;
        setI(i);
    }


    public boolean verification(Case c2) {
    	try{
    		if(couleur==Couleur.blanc)
    		{
    			if ((c2.getRange()==cases.getRange()+1 && c2.getColonne()==cases.getColonne() && c2.getPiece()==null) || (c2.getRange()==cases.getRange()+1 && (c2.getColonne()==cases.getColonne()+1 || c2.getColonne()==cases.getColonne()-1)&& c2.getPiece()!=null && (c2.getPiece().getCouleur() != Couleur.blanc)))
    				return true;

    			if (debut && c2.getRange()==cases.getRange()+2 && c2.getColonne()==cases.getColonne() && Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()).getPiece()==null && Echiquier.ech.gcase(cases.getRange()+2, cases.getColonne()).getPiece()==null  )
    				return true;
    		}
    		else
    		{
    			if ((c2.getRange()==cases.getRange()-1 && c2.getColonne()==cases.getColonne() && c2.getPiece()==null) || (c2.getRange()==cases.getRange()-1 && (c2.getColonne()==cases.getColonne()+1 || c2.getColonne()==cases.getColonne()-1)&& c2.getPiece()!=null && (c2.getPiece().getCouleur() != Couleur.noir)))
    				return true;
    			if (cases.getPiece().isDebut() && c2.getRange()==cases.getRange()-2 && c2.getColonne()==cases.getColonne() && Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()).getPiece()==null && Echiquier.ech.gcase(cases.getRange()-2, cases.getColonne()).getPiece()==null)
    				return true;
    		}
    	}catch(NullPointerException e){}
    	
        return false;
    }
    public ArrayList<Case> possibilite()
    {
    	ArrayList<Case>tab=new ArrayList<Case>();
    	if (cases.getPiece().couleur==Couleur.blanc)
       	{
       		if (verification(Echiquier.ech.gcase(cases.getRange()+1,cases.getColonne())))
           		tab.add(Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()));
           	if (cases.getPiece().debut && verification(Echiquier.ech.gcase(cases.getRange()+2,cases.getColonne())))
           		tab.add(Echiquier.ech.gcase(cases.getRange()+2, cases.getColonne()));
           	if ((verification( Echiquier.ech.gcase(cases.getRange()+1,cases.getColonne()+1)))&& cases.getColonne()!=7 )
           		tab.add(Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()+1));          		
            if ((verification( Echiquier.ech.gcase(cases.getRange()+1,cases.getColonne()-1)))&& cases.getColonne()!=0 )
           		tab.add(Echiquier.ech.gcase(cases.getRange()+1, cases.getColonne()-1));    		
       	}
       	else
       	{
       		if (verification(Echiquier.ech.gcase(cases.getRange()-1,cases.getColonne())) )
           		tab.add(Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()));
           	if (verification( Echiquier.ech.gcase(cases.getRange()-2,cases.getColonne())))
           		tab.add(Echiquier.ech.gcase(cases.getRange()-2, cases.getColonne())); 		
           	if ((verification( Echiquier.ech.gcase(cases.getRange()-1,cases.getColonne()+1)))&& cases.getColonne()!=7)
           		tab.add(Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()+1));
           	if ((verification( Echiquier.ech.gcase(cases.getRange()-1,cases.getColonne()-1)))&& cases.getColonne()!=0)
           		tab.add(Echiquier.ech.gcase(cases.getRange()-1, cases.getColonne()-1));
       	}
    	
		return tab;
    }
    public boolean attVerification(Case c2)
    {
    	if(couleur==Couleur.blanc)
    	{
    		if ((c2.getRange()==cases.getRange()+1 && ((c2.getColonne()==cases.getColonne()+1 && this.getCase().getColonne()!=7) ||( c2.getColonne()==cases.getColonne()-1 && this.getCase().getColonne()!=0)))&& (c2.getPiece()==null || c2.getPiece().getCouleur()!=couleur))
           		return true;       		
    	}
    	else
       	{
    		if ((c2.getRange()==cases.getRange()-1 && ((c2.getColonne()==cases.getColonne()+1 && this.getCase().getColonne()!=7) ||( c2.getColonne()==cases.getColonne()-1 && this.getCase().getColonne()!=0)))&& (c2.getPiece()==null || c2.getPiece().getCouleur()!=couleur))
    			return true; 
       	}
    	return false;
    }
    public  boolean promotion()
    {
    	if (couleur==Couleur.blanc)
    		return cases.getRange()==7;
    	else 
    		return cases.getRange()==0;
    }
    public static boolean promotion(Piece pion)
    {
    	if (pion.getCase().getRange()==7 && pion.getCouleur()==Couleur.blanc)
    		return true;
    	if (pion.getCase().getRange()==0 && pion.getCouleur()==Couleur.noir)
    		return true;
    	return false;
    }
    public String[] promoPoss()
	{
		ArrayList<String> promotion =new ArrayList<String>();
		int nbTour=0,nbFou=0,nbDame=0,nbCavalier=0,i=0;
		if (couleur==Couleur.blanc)
		{
			while(i<Echiquier.blanc.size() &&( nbTour<2 || nbFou<2 || nbCavalier<2 || nbDame<1))
			{
				if (Echiquier.blanc.get(i) instanceof Tour &&  nbTour<2)
					nbTour++;
				else if (Echiquier.blanc.get(i) instanceof Fou &&  nbFou<2)
					nbFou++;
				else if (Echiquier.blanc.get(i) instanceof Dame &&  nbDame<1)
					nbDame++;
				else if (Echiquier.blanc.get(i) instanceof Cavalier &&  nbCavalier<2)
					nbCavalier++;
				i++;
			}
		}
		else
		{
			while(i<Echiquier.noir.size() &&( nbTour<2 || nbFou<2 || nbCavalier<2 || nbDame<1))
			{
				if (Echiquier.noir.get(i) instanceof Tour &&  nbTour<2)
					nbTour++;
				else if (Echiquier.noir.get(i) instanceof Fou &&  nbFou<2)
					nbFou++;
				else if (Echiquier.noir.get(i) instanceof Dame &&  nbDame<1)
					nbDame++;
				else if (Echiquier.noir.get(i) instanceof Cavalier &&  nbCavalier<2)
					nbCavalier++;
				i++;
			}
		}
		if (nbTour<2)
			promotion.add("Tour");
		if (nbFou<2)
			promotion.add("Fou");	
		if (nbCavalier<2)
			promotion.add("Cavalier");
		if (nbDame<1)
			promotion.add("Dame");
		String[]promo=new String[promotion.size()];
		promotion.toArray(promo);
		return promo;
	}
    public void ressusciter(String choix)
	{
		Case casep=getCase();
		int i=getI();
		if (getCouleur()==Couleur.blanc)
		{
			switch (choix) {
			case "Tour":
				Echiquier.blanc.set(i, new Tour(casep,getCouleur(),i));
				casep.setPiece(Echiquier.blanc.get(i));
				
				break;
			case"Dame":
				Echiquier.blanc.set(i, new Dame(casep,getCouleur(),i));
				casep.setPiece(Echiquier.blanc.get(i));
				break;
			case"Fou":
				Echiquier.blanc.set(i, new Fou(casep,getCouleur(),i));
				casep.setPiece(Echiquier.blanc.get(i));
				break;
			case"Cavalier":
				Echiquier.blanc.set(i, new Cavalier(casep,getCouleur(),i));
				casep.setPiece(Echiquier.blanc.get(i));
				break;
			}
		}
		else
		{
			switch (choix) {
			case "Tour":
				Echiquier.noir.set(i, new Tour(casep,getCouleur(),i));
				casep.setPiece(Echiquier.noir.get(i));
				
				break;
			case"Dame":
				Echiquier.noir.set(i, new Dame(casep,getCouleur(),i));
				casep.setPiece(Echiquier.noir.get(i));
				break;
			case"Fou":
				Echiquier.noir.set(i, new Fou(casep,getCouleur(),i));
				casep.setPiece(Echiquier.noir.get(i));
				break;
			case"Cavalier":
				Echiquier.noir.set(i, new Cavalier(casep,getCouleur(),i));
				casep.setPiece(Echiquier.noir.get(i));
				break;
			}
		}
		Echiquier.ech.afficher();
		
	}

}

package jeux;

import java.util.concurrent.Exchanger;

import Project.Exception.MauvaisePiece;

public class Arbitre {
	public static Couleur coulTour=Couleur.blanc;
	public static Joueur listjoueur[]=new Joueur[2];
	public static boolean Tour(Piece a)
	{
		if(a.getCouleur()==coulTour)
			return true;
		else
			return false;	
	}
	public static void TourSuivant(Couleur coul)
	{
		if (coul==Couleur.blanc)
			coulTour=Couleur.noir;
		else
			coulTour=Couleur.blanc;
	}
    public static boolean Echec(Couleur cl)
    {
    	if (cl==Couleur.blanc)
    		return Echec(cl, Echiquier.blanc.get(0).getCase());
    	return Echec(cl, Echiquier.noir.get(0).getCase());
    	
    }
    public static boolean Echec(Couleur cl,Case crt)
    
    {
    	boolean ech=false;
    	if (cl==Couleur.blanc)
    	{
    		for (Piece i:Echiquier.noir)
    		{
    			if (i instanceof Pion && ((Pion)i).attVerification(crt))
    				ech=true;	
    			else if (!(i instanceof Roi) && (!(i instanceof Pion)))
    			{
    				if (i.verification(crt))
    					ech=true;
    			}
    		} 		
    	}
    	else if (cl==Couleur.noir)
    	{
    		for (Piece i:Echiquier.blanc)
    		{
    			if (i instanceof Pion && ((Pion)i).attVerification(crt))
    				ech=true;	
    			else if (!(i instanceof Roi) && (!(i instanceof Pion)))
    			{
    				if(i.verification(crt))
    					ech=true;			
    			}		
    		}
    	}
		return ech;   	
    }
    
    public static boolean mat(Couleur coul)
    {
    	Piece b=Echiquier.blanc.get(0);
    	Piece n=Echiquier.noir.get(0);
    	switch (coul) {
		case blanc:
			try {
				return (b.possibilite().isEmpty() && Echec(b.getCouleur(), b.getCase()));
			} catch (Exception e) {
	
				e.printStackTrace();
			}
		case noir:
			try {
				return (n.possibilite().isEmpty() && Echec(n.getCouleur(), n.getCase()));
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
    	return false;
    }
    public static boolean pat()
    {
    	Piece roib=Echiquier.blanc.get(0);
    	Piece roin=Echiquier.noir.get(0);
		if((!Echec(roin.getCouleur()) && roin.possibilite().isEmpty() && nbTete(roin.getCouleur())<3)||(!Echec(roib.getCouleur()) && roib.possibilite().isEmpty() && nbTete(roib.getCouleur())<3))
			return true;
		return false;
    }
    public static int nbTete(Couleur coul)
    {
    	int i=0;
    	if (coul==Couleur.blanc)
    	{
    		for(Piece h:Echiquier.blanc)
    		{
    			if(h instanceof Tour || h instanceof Dame || h instanceof Fou)
    				i++;
    		}
    	}
    	else
    	{
    		for(Piece h:Echiquier.noir)
    		{
    			if(h instanceof Tour || h instanceof Dame || h instanceof Fou)
    				i++;
    		}
    	}
    	return i;
    }
    public static boolean mouvEchec(Case c,Case arriver) //verifie si un mouvement cause la mise en echec du roi true si c'est le cas
    {
    	boolean g=false;
    	Piece a;
    	Piece b;
    	a=arriver.getPiece();
    	b=c.getPiece();
    	arriver.setPiece(c.getPiece());
    	c.setPiece(null);
		try{
			if (b.couleur==Couleur.noir && Echec(Couleur.noir, Echiquier.noir.get(0).getCase()))
				g=true;
			
			else if (b.couleur==Couleur.blanc && Echec(Couleur.blanc, Echiquier.blanc.get(0).getCase()))
	    		g=true;
		}catch (NullPointerException e){}
		arriver.setPiece(a);
		c.setPiece(b);
		a=null;b=null;
		return g;
    }
    public static boolean mouvProtect(Case c,Case arriver) //verifie si un mouvement cause la mise en echec du roi true si c'est le cas
    {
    	boolean g=true;
    	Piece a;
    	Piece b;
    	a=arriver.getPiece();
    	b=c.getPiece();
    	arriver.setPiece(c.getPiece());
    	c.setPiece(null);
		try{
			if (c.getPiece().couleur==Couleur.noir && Echec(Couleur.noir, Echiquier.noir.get(0).getCase()))
				g=false;
			else if (c.getPiece().couleur==Couleur.blanc && Echec(Couleur.blanc, Echiquier.blanc.get(0).getCase()))
	    		g=false;
		}catch (NullPointerException e){}
		arriver.setPiece(a);
		c.setPiece(b);
		a=null;b=null;
		return g;
    }

}

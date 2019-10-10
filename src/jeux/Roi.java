package jeux;
import java.util.ArrayList;

import Project.Exception.MauvaisePiece;
import Project.Exception.MouvementMauvais;

public class Roi extends Piece {

    public Roi(Case cases) {
        super(cases);
    }
    public String toString() {
    	if (couleur==Couleur.blanc)
    		return "RB";
    	return "RN";
    }
    public Roi(Case cases,Couleur coul,int i) {
    	super(cases);
        couleur=coul;
        setI(i);
    }
    
    public boolean verification(Case c2) {
    	try
    	{
    		if ((Math.abs(c2.getColonne()-cases.getColonne())==1 || Math.abs(c2.getColonne()-cases.getColonne())==0)&&(Math.abs(c2.getRange()-cases.getRange())==0 || Math.abs(c2.getRange()-cases.getRange())==1) && (c2.getPiece()==null || c2.getPiece().getCouleur()!=getCouleur())&&!Arbitre.Echec(getCouleur(), c2))
            {
    			if (getCouleur()==Couleur.blanc && !((Math.abs(c2.getColonne()-Echiquier.noir.get(0).getCase().getColonne())==1 || Math.abs(c2.getColonne()-Echiquier.noir.get(0).getCase().getColonne())==0)&&(Math.abs(c2.getRange()-Echiquier.noir.get(0).getCase().getRange())==0 || Math.abs(c2.getRange()-Echiquier.noir.get(0).getCase().getRange())==1)))
    				 return true;
    			if (getCouleur()==Couleur.noir && !((Math.abs(c2.getColonne()-Echiquier.blanc.get(0).getCase().getColonne())==1 || Math.abs(c2.getColonne()-Echiquier.blanc.get(0).getCase().getColonne())==0)&&(Math.abs(c2.getRange()-Echiquier.blanc.get(0).getCase().getRange())==0 || Math.abs(c2.getRange()-Echiquier.blanc.get(0).getCase().getRange())==1)))
    				return true;
            }
    	}catch(NullPointerException a){
    		return false;
    	}
    	return false;
    }
    
    public ArrayList<Case> possibilite()
    {
    	ArrayList<Case> tab =new ArrayList<Case>();
    	for(int i=-1;i<2;i++)
    	{
    		for(int j=-1;j<2;j++)
    		{
    			if ((i!=0 || j!=0) && verification(Echiquier.ech.gcase(cases.getRange()+i,cases.getColonne()+j)))
       				tab.add( Echiquier.ech.gcase(cases.getRange()+i,cases.getColonne()+j));
    		}
    	}
		return tab;
	}
    public ArrayList<Case> roquePoss(){
    	ArrayList<Case> tab=new ArrayList<Case>();
    	int i=2;
    	if (couleur==Couleur.blanc)
    	{
    		while(i<Echiquier.blanc.size() && !(Echiquier.blanc.get(i) instanceof Tour))
        			i++;
    		 if (Echiquier.blanc.get(1) instanceof Tour && Echiquier.blanc.get(1).debut && debut && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-1)) &&  Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-1).getPiece()==null && Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-2).getPiece()==null && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-2)))
     	    	tab.add( Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-2));
    		 if (Echiquier.blanc.get(i) instanceof Tour && Echiquier.blanc.get(i).debut && debut && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+1)) && Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+1).getPiece()==null && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+2)) && Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+2).getPiece()==null)
      	    	tab.add( Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+2));
    	}
    	else
    	{
    		while(i<Echiquier.noir.size()-1 && !(Echiquier.noir.get(i) instanceof Tour && Echiquier.noir.get(i).isDebut()))
        			i++;
    		 if (Echiquier.noir.get(Echiquier.noir.size()-1) instanceof Tour && Echiquier.noir.get(Echiquier.noir.size()-1).debut && debut && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+1)) &&  Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+1).getPiece()==null && Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+2).getPiece()==null && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+2)))
     	    	tab.add( Echiquier.ech.gcase(cases.getRange(),cases.getColonne()+2));
    		 if (Echiquier.noir.get(i) instanceof Tour && Echiquier.noir.get(i).debut && debut && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-1)) && Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-1).getPiece()==null && !Arbitre.Echec(couleur, Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-2)) && Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-2).getPiece()==null)
      	    	tab.add( Echiquier.ech.gcase(cases.getRange(),cases.getColonne()-2));
    	}
    	return tab;
    	
    }
  


}
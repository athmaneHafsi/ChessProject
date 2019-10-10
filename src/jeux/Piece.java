package jeux;
import java.util.ArrayList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.MauvaisePiece;
import Project.Exception.MouvementMauvais;

public abstract class Piece {

    protected Couleur couleur;

    protected boolean debut;


    protected Case cases;
    
    protected int i;

    public Piece(Case cases) {
        this.cases = cases;
        if (cases.getRange()==0 || cases.getRange()==1)
        {
            this.couleur=Couleur.blanc;
        }
        else
        {
            this.couleur=Couleur.noir;
        }
        debut=true;
    }
    public void setDebut(boolean dd)
    {
    	debut=dd;
    }

    public void mouvement(Case arrive) throws Exception {
    	if(!Arbitre.Tour(this))
    	{
    		throw new MauvaisePiece();
    	}
    	if(!verification(arrive) ||((!Arbitre.Echec(couleur) && Arbitre.mouvEchec(cases,arrive)) || (Arbitre.Echec(couleur) && !Arbitre.mouvProtect(cases, arrive))))
    	{
    		throw new MouvementMauvais();
    	}
    	else{
    		if (arrive.getPiece()!=null)
        	{
        		if (arrive.getPiece().getCouleur()==Couleur.noir)
        		{
        			for (int i=arrive.getPiece().getI()+1;i<Echiquier.ech.noir.size();i++)
        				Echiquier.ech.noir.get(i).setI(i-1);
        			Echiquier.noir.remove(arrive.getPiece().getI());
        		}
        		else
        		{
        			for (int i=arrive.getPiece().getI()+1;i<Echiquier.ech.blanc.size();i++)
        				Echiquier.ech.blanc.get(i).setI(i-1);
        			Echiquier.blanc.remove(arrive.getPiece().getI());
        		}
        	}
            cases.setPiece(null);
            cases=arrive;
            cases.setPiece(this);
            if (debut)
                debut=false;
            Arbitre.TourSuivant(couleur);
    	}	
    }

    public abstract ArrayList<Case> possibilite();
    public abstract boolean verification(Case c2);

    public Couleur getCouleur() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.couleur;
    }


    void setCouleur(Couleur value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.couleur = value;
    }


    public abstract String toString();


    boolean isDebut() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.debut;
    }
    public Case getCase()
    {
    	return this.cases;
    }
    public void setI(int i)
    {
    	this.i= i;
    }
    public int getI()
    {
    	return i;
    }


}

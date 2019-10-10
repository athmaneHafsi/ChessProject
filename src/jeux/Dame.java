package jeux;

import java.util.ArrayList;


public class Dame extends Piece {
    
    public Dame(Case cases) {
        super(cases);
    }
    public Dame(Case cases,Couleur coul) {
        super(cases);
        couleur=coul;
    }
    public Dame(Case cases,Couleur coul,int i) {
        super(cases);
        couleur=coul;
        setI(i);
    }

    
    public String toString() {
    	if (couleur==Couleur.blanc)
    		return "DB";
    	return "DN";
    }

    public ArrayList<Case> possibilite()
    {
    	ArrayList a=Fou.possibilite(cases);
    	a.addAll(Tour.possibilite(cases));
        return a;
    }

	
	public boolean verification(Case c2) {
		return Fou.verification(cases, c2) || Tour.verification(cases, c2);
	}

}

package jeux;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Case {
//  Attribute
    private int range;

    private int colonne;

    private Piece piece;

// Methods
    public Case(int range, int colonne) {
        this.range=range;
        this.colonne=colonne;
        if (this.range==1 || this.range==6)
        {
            this.piece= new Pion(this);
        }
        else if (this.range>1 && this.range<6)
        {
            this.piece=null;
        }
        else if(this.colonne==0 || this.colonne==7)
        {
            this.piece= new Tour(this); 
        }
        else if(this.colonne==1 || this.colonne==6)
        {
            this.piece= new Cavalier(this); 
        }
        else if(this.colonne==2 || this.colonne==5)
        {
            this.piece= new Fou(this); 
        }
        else if (this.colonne==3)
        {
            this.piece=new Dame (this);
        }
        else if (this.colonne==4)
        {
            this.piece=new Roi(this);
        }
    }

    public Case(int i, int j, Piece p) {
    	range=i;
    	colonne=j;
    	piece=null;
    	
    	
	}

    public Case (int i,int j,String p,Couleur coul,Boolean debut,int index)
    {
    	range=i;
    	colonne=j;
    	switch (p) {
		case "P":
			this.piece=new Pion(this, coul,index);
			
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "T":
			this.piece=new Tour(this, coul,index);
			
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "F":
			this.piece=new Fou(this, coul,index);
			
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "R":
			this.piece=new Roi(this, coul,index);
			
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "C":
			this.piece=new Cavalier(this, coul,index);
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		case "D":
			this.piece=new Dame(this, coul,index);
			
			piece.setDebut(debut);
			if (coul==Couleur.blanc)
				Echiquier.blanc.add(piece);
			else
				Echiquier.noir.add(piece);		
			break;
		}
    	
    }

	@objid ("b5353494-c538-4d67-a173-27c62ed8306a")
    public void afficher() {
        if (this.piece==null)
        {
            System.out.print("   ");
        }
        else
        {
        	 System.out.print(this.piece+" ");
        }
    }

    public int getColonne() {
        return this.colonne;
    }

    public int getRange() {
        return this.range;
    }

    public void setPiece(Piece value) {
        this.piece = value;
    }

      public Piece getPiece() {
        return this.piece;
    }
    public boolean equals(Case a)
    {
    	if (a.range==range && a.colonne==colonne)
    		return true;
		return false;
    }
    public String toString()
    {
    		return "case : "+this.range+" "+this.colonne;
    }
    public String information()
    {
    	if (piece==null)
    		return "null\n";
    	return piece.couleur+"\n"+piece.debut+"\n"+piece.i+"\n";
    }
}


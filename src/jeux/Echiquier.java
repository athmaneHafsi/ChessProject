package jeux;

import java.util.ArrayList;
import java.util.List;




public class Echiquier {
  
    public static final char[] lettre = {'A','B','C','D','E','F','G','H'};

    private List<Case> table = new ArrayList<Case> ();

    public static List<Piece> blanc = new ArrayList<Piece>();

    public static List<Piece> noir = new ArrayList<Piece>();

    public static Echiquier ech = new Echiquier();

    private Echiquier() {
        Case a;
        for (int i=0;i<8;i++)
        {
            for (int j=0;j<8;j++)
            {
                a= new Case (i,j);
                table.add(a);
                if (i<2)    {
                	if(a.getPiece() instanceof Roi){
                		blanc.add(blanc.get(j-1));
                		for(int k=j-1;k>0;k--)	{
                			blanc.set(k,blanc.get(k-1));}
                		blanc.set(0, a.getPiece());}
                	if (!(a.getPiece() instanceof Roi)){
                		blanc.add(a.getPiece());
                        a.getPiece().setI(i*8+j);} }
                else if (i>5){
                	if(a.getPiece() instanceof Roi){
                		noir.add(noir.get(j-1));
                		for(int k=j-1;k>0;k--){
                			noir.set(k,noir.get(k-1));}
                		noir.set(0, a.getPiece());
                	}
                	if (!(a.getPiece() instanceof Roi))
                	{
                		noir.add(a.getPiece());
                        a.getPiece().setI((i-6)*8+j);
                	}
                }
            }
        }
        a=null;
    }
    public List<Case> getTable()
    {return table;  }
    	
  
    public Echiquier(ArrayList<Case>tan)
    {	table=tan; }
    
   
  
    public void afficher() {
        int k=0;
        for (int w=0;w<8;w++)
        {
            for (int i=0;i<4;i++)
            {
                for(int j=0;j<8;j++)
                {
                    if (j==0 && i==1)
                    {
                        System.out.print(w+1+" ");
                    }
                    if (i==0)
                    {
                        if (j==0)
                            System.out.print("  ");
                        if (j==7)
                        {
                            System.out.println("------");
                        }
                        else
                        {
                            System.out.print("-----");
                        }
                    }
                    else if (i==1)
                    {
        
                        if (j==7)
                        {
                            System.out.print("| ");
                            table.get(k).afficher();
                            k++;
                            System.out.println("|");
                        }
                        else
                        {
                            System.out.print("| ");
                            table.get(k).afficher();
                            k++;
                        }
                    }
                    else if (i==2 && w==7)
                    {
                        if (j==0)
                            System.out.print("  ");
                        if (j==7)
                        {
                            System.out.println("------");
                        }
                        else
                        {
                            System.out.print("-----");
                        }
                    }
                    else if (i==3 && w==7)
                        {
                            System.out.print("    "+lettre[j]);
                        }
                }
            }
        }
        System.out.println("");
    }

 
    public void mouvement(int li, char col, int lib, char colb) {
	// mouvement normal
        		try {
					try {
						gcase(li-1, col).getPiece().mouvement(gcase(lib-1, colb));
					} catch (Exception e) {
					System.out.println(e.getMessage());
					}
				}catch (NullPointerException e) {System.out.println("cette case est vide");}

        			// promotion pion
        		if (gcase(lib-1, colb).getPiece() instanceof Pion && Pion.promotion(gcase(lib-1, colb).getPiece()))
        		{
        			System.out.println("votre Pion a le droit à une promotion choisissez parmi l'une de ces pieces :");
       				for (String i:((Pion)(gcase(lib-1, colb).getPiece())).promoPoss())
       				{
       					System.out.print(i+" ; ");
       				}
       				if(((Pion)(gcase(lib-1, colb).getPiece())).promoPoss().length==0)
       					System.out.println("vous n'avez pas perdu de piece la promotion est impossible");
       				else
       				{
       					System.out.print("\n choisir une piece ");
            			String choix =main.sc.nextLine();
            			((Pion)(gcase(lib-1, colb).getPiece())).ressusciter(choix);
            			System.out.println("");
        			}
       			}    
        	afficher();
    }

    
    public Case gcase(int li, char col) {
        int i=0;
        while (lettre[i]!=col)
        {
                i++;
        }
        try{
        	return table.get((li)*8+i);
        }catch (IndexOutOfBoundsException e) {
			System.out.println("la piece choisi n'est pas disponible !!");
		}
        return null;
    }

    public Case gcase(int li, int col) {
    	try{
    		return table.get((li)*8+col);
    	}catch(IndexOutOfBoundsException e){return null;}
        
    }

}

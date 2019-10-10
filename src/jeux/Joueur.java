package jeux;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Project.Exception.PseudoManquant;


public class Joueur {

    private String pseudo;

    private static int nbj=0;
    private static String pseudoIdem;
    private Couleur couleur;

    public Joueur(String pseudo1)throws PseudoManquant {
    	if (pseudo1.equals(pseudoIdem))
    	{
    		pseudoIdem=null;
    		nbj=0;
    		throw new PseudoManquant();
    	}
    	this.pseudo=pseudo1;
    	pseudoIdem=pseudo1;
    	if (nbj==0)
    		couleur=Couleur.blanc;
    	else
    		couleur=Couleur.noir;
       	nbj++;
       	if (nbj==2)
       	{
       		nbj=0;
       	}	
    }

    public Couleur getCouleur()
    {
    	return couleur;
    }
    public String getPseudo()
    {
    	return pseudo;
    }
    public String toString() {
        return this.pseudo+" | "+couleur;
    }
    public String information() {
        return this.pseudo+"\n";
    }

}

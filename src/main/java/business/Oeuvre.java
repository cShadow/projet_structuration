package business;

import java.util.ArrayList;
import java.util.Date;

public class Oeuvre {

	private String titre;
	private String auteur;
	private int nbpage;
	private Date datePublication;
	private Thematique thematique;
	private ArrayList<Formation> accesFormation;
	private ArrayList<Universite> accesUniversite;
	private ArrayList<Role> accesRole;
}

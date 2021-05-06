package business;
import java.util.Date;

public class Inscription {
	private int id_utilisateur;
	private int id ;
	private String nom_utilisateur;
	private String prenom_utilisateur;
	private String nom_formation;

	private String nom_université;
	private Date date_sortie;
	private Date date_entrer;


	public Inscription(int id_utilisateur, int id, String nom_utilisateur, String prenom_utilisateur,
			String nom_formation, String nom_université, Date date_sortie, Date date_entrer) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.id = id++;
		this.nom_utilisateur = nom_utilisateur;
		this.prenom_utilisateur = prenom_utilisateur;
		this.nom_formation = nom_formation;
		this.nom_université = nom_université;
		this.date_sortie = date_sortie;
		this.date_entrer = date_entrer;
	}




}

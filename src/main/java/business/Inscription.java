package business;
import java.util.Date;

public class Inscription {
	
	private int id ;
	private int idUtilisateur;
	private String nomUtilisateur;
	private String prenomUtilisateur;
	private String nomFormation;
	private String nomUniversite;
	private Date dateSortie;
	private Date dateEntree;


	public Inscription(int id, int idUtilisateur, String nomUtilisateur, String prenomUtilisateur,
			String nomFormation, String nomUniversite, Date dateSortie, Date dateEntree) {
		this.idUtilisateur = idUtilisateur;
		this.id = id++;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.nomFormation = nomFormation;
		this.nomUniversite = nomUniversite;
		this.dateSortie = dateSortie;
		this.dateEntree = dateEntree;
	}




}

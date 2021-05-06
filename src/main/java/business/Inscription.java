package business;
import java.util.Date;

public class Inscription {
	
	private int id ;
	private int idUtilisateur;
	private String nomUtilisateur;
	private String prenomUtilisateur;
	private String nomFormation;
	private String nomUniversité;
	private Date dateSortie;
	private Date dateEntrer;


	public Inscription(int id, int idUtilisateur, String nomUtilisateur, String prenomUtilisateur,
			String nomFormation, String nomUniversité, Date dateSortie, Date dateEntrer) {
		this.idUtilisateur = idUtilisateur;
		this.id = id++;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.nomFormation = nomFormation;
		this.nomUniversité = nomUniversité;
		this.dateSortie = dateSortie;
		this.dateEntrer = dateEntrer;
	}




}

package business;
import java.util.Date;

public class Inscription {
	
	private int id ;
	private int idUtilisateur;
	private String nomUtilisateur;
	private String prenomUtilisateur;
	private String nomFormation;
	private String nomUniversit�;
	private Date dateSortie;
	private Date dateEntrer;


	public Inscription(int id, int idUtilisateur, String nomUtilisateur, String prenomUtilisateur,
			String nomFormation, String nomUniversit�, Date dateSortie, Date dateEntrer) {
		this.idUtilisateur = idUtilisateur;
		this.id = id++;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.nomFormation = nomFormation;
		this.nomUniversit� = nomUniversit�;
		this.dateSortie = dateSortie;
		this.dateEntrer = dateEntrer;
	}




}

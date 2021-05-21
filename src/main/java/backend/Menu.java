package backend;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	public Menu() throws Exception {
		
		Scanner sc = new Scanner(System.in);
		Backend.createBackend();
		System.out.println("Bonjour, veuillez vous connectez en entrant vôtre identifiant");
		String pseudo = sc.next();
		System.out.println(Backend.getLoginGestion().login(pseudo));
		int choix = 1;
		while (choix != 7) {
			System.out.println("Bienvenue dans la librairie du cul ! ;)");
			System.out.println("");
			System.out.println("");
			System.out.println("");

			boolean check = true;
			while (check) {
				try {
					System.out.println("Que voulez-vous faire ? (de 1 à 7) :");
					choix = sc.nextInt();
					while((choix < 1) || (choix > 7)) {
						System.out.println("Merci de saisir un nombre entre  1 et 7. Nouvelle saisi : ");
						choix =  sc.nextInt();
					}
					check = false;
				} catch (InputMismatchException e) {
					System.out.println("Vous n'avez pas saisi un entier !! ");
					sc.nextLine();
				}
			}

			switch(choix) {
			case 1:
				Backend.getCommentaireGestion().test();
;				break;
			case 2:
				
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:
				System.out.println("Fin de la session avec la librairie du cul");
				break;
			}

			System.out.println("Appuyez sur une touche pour continuer");
			sc.nextLine();
			sc.nextLine(); 

			try {
				if(System.getProperty("os.name" ).startsWith("Windows" ))
					Runtime.getRuntime().exec("cls" );
				else
					Runtime.getRuntime().exec("clear" );
			} catch(Exception excpt) {
				for(int i=0;i<100;i++)
					System.out.println();
			}

		}

		System.out.println("Fin.");

		sc.close();

	}

}


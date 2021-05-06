package main;

import java.util.Arrays;

//import org.bson.Document;

//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;

import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.io.File;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws IOException {
		//MongoClient mongoClient = MongoClients.create();
		//MongoDatabase database = mongoClient.getDatabase("data");
		//MongoCollection<Document> collection = database.getCollection("data");
		importMissingFiles();
	}

	public static void importFileIfMissing(File importFile)
	{
		try {
			// We read the entire file into memory, 
			// then use top down parsing to get the information we want to feed to MongoDB.
			FileInputStream fileInputStream = new FileInputStream(importFile);
			byte[] fileData = new byte[(int)importFile.length()];
			fileInputStream.read(fileData);
			fileInputStream.close();
			String fileContent = new String(fileData, "UTF-8");

			String[] fieldPrefixes = new String[]{"Titre:", "Auteurs:", "Pages:", "Publication:", "Theme:", "Formations:", "Universites:", "Roles:", "Contenu:"};
			String[] fieldStrings = new String[9];

			// We fill in the fieldStrings.
			int at = 0;
			boolean fileIsValid = true;
			for (int fieldIndex = 0; fieldIndex < fieldPrefixes.length - 1; fieldIndex++)
			{
				String fieldPrefix = fieldPrefixes[fieldIndex];
				int atField = fileContent.indexOf(fieldPrefix, at);
				if (atField >= 0)
				{
					at = atField + fieldPrefix.length();

					int afterField = at + fileContent.indexOf('\n', at) + 1;
					if (afterField > at)
					{
						fieldStrings[fieldIndex] = fileContent.substring(at, afterField).trim();
						at = afterField;
					}
					else
					{
						fileIsValid = false;
						break;
					}
				}
				else
				{
					fileIsValid = false;
					break;
				}
			}

			System.out.println(fileIsValid);
			if (fileIsValid)
			{
				fieldStrings[fieldStrings.length] = fileContent.substring(at).trim();
				for (int fieldIndex = 0; fieldIndex < fieldStrings.length; fieldIndex++)
				{
					System.out.println(fieldStrings[fieldIndex]);
				}
				// TODO: look up whether the database doesn't already have an article
				// with the same title and date. If not, then upload that document to the database.

				/*
						// Insertion d'un document
						Document doc = new Document("name", "MongoDB")
								.append("type", "database")
								.append("count", 1)
								.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
								.append("info", new Document("x", 203).append("y", 102));
						collection.insertOne(doc);
				 */

				// https://mongodb.github.io/mongo-java-driver/4.2/apidocs/mongodb-driver-sync/com/mongodb/client/package-summary.html

				// TODO: Maybe use this to parse a date once you have extracted a date string ??
				//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				//Date date = formatter.parse(dateString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void importMissingFiles()
	{
		// Iterative BFS-like directory tree walking. 
		// We use a circular buffer of File instances to memorize the directories to explore.
		// We assume that at most 4096 directories need to be memorized at once.
		File[] foldersStack = new File[4096];
		int pushIndex = 0;
		int fetchIndex = 0;

		// TODO: set the actual import folder path here
		foldersStack[0] = new File("import");
		++pushIndex;
		if (foldersStack[0] != null)
		{
			while (fetchIndex != pushIndex)
			{
				File folder = foldersStack[fetchIndex];
				fetchIndex = (fetchIndex+1) % foldersStack.length;
				File[] entries = folder.listFiles();
				for(int entryIndex = 0; entryIndex < entries.length; ++entryIndex) 
				{
					File entry = entries[entryIndex];
					if (entry.isDirectory())
					{
						foldersStack[pushIndex] = entry;
						pushIndex = (pushIndex+1) % foldersStack.length;
						System.out.print("Directory: ");
					}
					else if (entry.isFile())
					{
						System.out.print("File: ");
						importFileIfMissing(entry);
					}
					System.out.println(entry.getName());
				}
			}
		}
		else
		{
			System.out.println("Import folder was not found");
		}

	}
}
package main;


import com.mongodb.*;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;


import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.io.File;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws IOException {

		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = client.getDatabase("data");
		//MongoCollection collection = database.getCollection("data");
        
		System.out.println("Congratulations, you have run the program !");

		MongoIterable<String> listNames = database.listCollectionNames();
        for (String name : listNames) {
			System.out.println(name);
        }
        // java Document toy = new Document(“name”, “yoyo”) .append(“ages”, new Document(“min”, 5)); ObjectId id = toys.insertOne(toy).getInsertedId().asObjectId().getValue();


		
		importMissingFiles();
	}

	public static void importFileIfMissing(File importFile)
	{

		Oeuvre oeuvre = new Oeuvre();

		try {
			// We read the entire file into memory, 
			// then use top down parsing to get the information we want to feed to MongoDB.
			FileInputStream fileInputStream = new FileInputStream(importFile);
			byte[] fileData = new byte[(int)importFile.length()];
			fileInputStream.read(fileData);
			fileInputStream.close();
			String fileContent = new String(fileData, "UTF-8");

			ArrayList<String> fieldPrefixes = new String[]{"Titre:", "Auteurs:", "Pages:", "Publication:", "Theme:", "Formations:", "Universites:", "Roles:", "Contenu:"};
			String[] fieldStrings = new String[9];


			// We fill in the fieldStrings.
			int at = 0;
			boolean fileIsValid = true;

			InputStream in = new FileInputStream(importFile);
			Reader reader = new InputStreamReader(in, "US-ASCII");
			int intch;
			String keyword = new String("");
			while ((intch = r.read()) != -1) {
				char ch = (char) intch;
				keyword += ch;

				if (fieldPrefixes.contains())



			
			}

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

		foldersStack[0] = new File("import");
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("The current working directory is " + currentDirectory);

		++pushIndex;
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
					//System.out.print("Directory: ");
				}
				else if (entry.isFile())
				{
					//System.out.print("File: ");
					importFileIfMissing(entry);
				}
				System.out.println(entry.getName());
			}
		}
	}
}
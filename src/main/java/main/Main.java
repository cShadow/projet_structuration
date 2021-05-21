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

import business.Oeuvre;

public class Main {
	public static void main(String[] args) throws IOException {

		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = client.getDatabase("data");
		MongoCollection collection = database.getCollection("data");

		MongoIterable<String> listNames = database.listCollectionNames();
		for (String name : listNames) {
			System.out.println(name);
		}

		importMissingFiles(collection);
	}

	public static void importFileIfMissing(File importFile, MongoCollection collection)
	{
		//System.out.println();
		//System.out.println("Parsing file "+ importFile.getPath());
		try {
			// We read the entire file into memory, 
			// then we use top down parsing to get the information we want to feed to MongoDB.
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

			for (int fieldIndex = 0; fieldIndex < fieldPrefixes.length - 1 && fileIsValid; fieldIndex++)
			{
				String fieldPrefix = fieldPrefixes[fieldIndex];
				int atField = fileContent.indexOf(fieldPrefix, at);
				//System.out.println("fieldPrefix: " + fieldPrefix + " at:" + at + " atField:" + atField);

				if (atField >= 0)
				{
					at = atField + fieldPrefix.length();
					int afterField = fileContent.indexOf('\n', at) + 1;
					if (afterField > at)
					{
						fieldStrings[fieldIndex] = fileContent.substring(at, afterField).trim();
						at = afterField;
					}
					else
						fileIsValid = false;
				}
				else
					fileIsValid = false;
			}

			int atField = fileContent.indexOf(fieldPrefixes[fieldPrefixes.length - 1], at);
			if (atField >= 0)
			{
				at = atField + fieldPrefixes[fieldPrefixes.length-1].length();
				fieldStrings[fieldPrefixes.length-1] = fileContent.substring(at).trim();
			}
			else
				fileIsValid = false;

			if (fileIsValid)
			{
				boolean shouldImport = collection.find(and(eq("Titre", fieldStrings[0]), 
						eq("Publication", fieldStrings[3]))).first() == null;
				if (shouldImport)
				{
					String[] authorsList = fieldStrings[1].split(",");
					String[] coursesList = fieldStrings[5].split(",");
					String[] uniList = fieldStrings[6].split(",");
					String[] rolesList = fieldStrings[7].split(",");
					for (int i = 0; i < authorsList.length; i++)
						authorsList[i] = authorsList[i].trim();
					for (int i = 0; i < coursesList.length; i++)
						coursesList[i] = coursesList[i].trim();
					for (int i = 0; i < uniList.length; i++)
						uniList[i] = uniList[i].trim();
					for (int i = 0; i < rolesList.length; i++)
						rolesList[i] = rolesList[i].trim();

					Document doc = new Document("Titre", fieldStrings[0]);
					doc.append("Auteurs", Arrays.asList(authorsList));
					doc.append("Pages", fieldStrings[2]);
					doc.append("Publication", fieldStrings[3]);  // AAAA-MM-JJ
					doc.append("Theme", fieldStrings[4]);
					doc.append("Formations", Arrays.asList(coursesList));
					doc.append("Universites", Arrays.asList(uniList));
					doc.append("Roles", Arrays.asList(rolesList));
					doc.append("Contenu", fieldStrings[8]);
					collection.insertOne(doc);
				}

				/*
				for (int fieldIndex = 0; fieldIndex < fieldStrings.length; fieldIndex++)
				{
					System.out.println(fieldIndex + fieldStrings[fieldIndex]);
				}
				 */
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void importMissingFiles(MongoCollection collection)
	{
		// Iterative BFS-like directory tree walking. 
		// We use a circular buffer of File instances to memorize the directories to explore.
		// We assume that at most 4096 directories need to be memorized at once.
		File[] foldersStack = new File[4096];
		int pushIndex = 0;
		int fetchIndex = 0;

		foldersStack[0] = new File("../import");
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
				}
				else if (entry.isFile())
				{
					importFileIfMissing(entry, collection);
					//break;
				}
			}
		}
	}
}
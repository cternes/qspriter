package de.slackspace.qspriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import de.slackspace.qspriter.reader.DirectoryReader;
import de.slackspace.qspriter.writer.JsonWriter;
import de.slackspace.qspriter.writer.SpriteWriter;
import de.slackspace.qspriter.writer.Writer;


public class QSpriter {

	public static void main(String[] args) {
		new QSpriter(args);
	}

	private static String JSON_OUTPUT_NAME = 	"sprites.json";
	private static String SPRITE_OUTPUT_NAME = 	"sprites.png";
	
	private InputHandler inputHandler = new InputHandler();
	private ImageProcessor imageProcessor = new ImageProcessor();
	private JsonWriter jsonWriter = new JsonWriter();

	public QSpriter(String[] args) {
		createSprites(args);
	}
	
	private BufferedImage createSprites(String[] args) {
		String directory = inputHandler.processInput(args);
		if(directory == null) {
			System.exit(0);
		}
		
		try {
			HashMap<String, List<Sprite>> rowData = new DirectoryReader().readImageFiles(directory);
			BufferedImage bigSprite = imageProcessor.assembleImages(rowData, jsonWriter);
			if(bigSprite != null) {
				writeDataToDisk(new SpriteWriter(), bigSprite, directory, SPRITE_OUTPUT_NAME);
				writeDataToDisk(jsonWriter, jsonWriter.getCombinedJsonObject(), directory, JSON_OUTPUT_NAME);
				
				System.out.println("Sprite and JSON data created");
			}
		} catch (IOException e) {
			System.err.println("Could not read the files from the directory: " + directory);
			System.err.println("Cause: " + e.getMessage());
			System.exit(-1);
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void writeDataToDisk(Writer writer, Object data, String directory, String filename) {
		File file = new File(directory + "/" + filename);
		try {
			FileOutputStream fop = new FileOutputStream(file);
			
			System.out.println("Writing file " + file.getAbsolutePath());
			writer.write(fop, data);
		} catch (IOException e) {
			System.err.println("Could not write the sprite to the file: " + file.getAbsolutePath());
			System.err.println("Cause: " + e.getMessage());
			System.exit(-1);
		}
	}

}

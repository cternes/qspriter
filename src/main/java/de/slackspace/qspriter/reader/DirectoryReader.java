package de.slackspace.qspriter.reader;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.slackspace.qspriter.Sprite;

public class DirectoryReader {

	public HashMap<String, List<Sprite>> readImageFiles(String directory) throws IOException {
		Path directoryPath = FileSystems.getDefault().getPath(directory);
		if(!directoryPath.toFile().isDirectory()) {
			throw new IllegalArgumentException("The given path (" + directory + ") is not a directory. Aborting...");
		}
		
		HashMap<String, List<Sprite>> rowData = new HashMap<String, List<Sprite>>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, CreateFileFilter())) {
			for (Path p : stream) {
				Sprite sprite = new Sprite(p);
				groupSpritesByName(rowData, sprite);	
			}
		} catch (IOException e) {
			throw e;
		}
		
		return rowData;
	}

	private Filter<Path> CreateFileFilter() {
		DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {

			@Override
			public boolean accept(Path entry) throws IOException {
				//ignore subdirectories
				if(entry.toFile().isDirectory()) {
					return false;
				}
				
				String filename = entry.getFileName().toString();
				
				//ignore sprites.png
				if(filename.equalsIgnoreCase("sprites.png")) {
					return false;
				}
				
				//accept png and jpg files
				if(filename.contains(".png") || filename.contains(".jpg")) {
					return true;
				}
				
				return false;
			}
			
		};
		
		return filter;
	}

	private void groupSpritesByName(HashMap<String, List<Sprite>> rowData, Sprite sprite) {
		List<Sprite> rowSprites = rowData.get(sprite.getName());
		if(rowSprites == null) {
			rowSprites = new ArrayList<Sprite>();
		}
		rowSprites.add(sprite);
		
		rowData.put(sprite.getName(), rowSprites);
	}
	
}

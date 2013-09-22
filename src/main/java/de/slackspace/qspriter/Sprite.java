package de.slackspace.qspriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class Sprite {

	private int number;
	private String name;
	private Path path;
	private BufferedImage image;
	
	public Sprite(Path path) throws IOException {
		this.setPath(path);
		this.setNumber(extractNumber(path.getFileName().toString()));
		this.setName(extractName(path.getFileName().toString()));
		readImage();
	}
	
	private void readImage() throws IOException {
		try {
			this.setImage(ImageIO.read(path.toFile()));
		} catch (IOException e) {
			throw new IOException("Could not read the image file " + path.toFile(), e.getCause());
		}
	}
	
	private String extractName(String filename) {
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher m = pattern.matcher(filename);
		if(m.find()) {
			return m.group();
		}
		return "";
	}

	private int extractNumber(String filename) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m = pattern.matcher(filename);
		if(m.find()) {
			return Integer.parseInt(m.group());
		}
		return 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
	
	public int getWidth() {
		return getImage() == null ? 0 : getImage().getWidth();
	}
	
	public int getHeight() {
		return getImage() == null ? 0 : getImage().getHeight();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
}

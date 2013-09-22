package de.slackspace.qspriter.writer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class SpriteWriter implements Writer<BufferedImage> {

	@Override
	public void write(OutputStream stream, BufferedImage data) throws IOException {
		ImageIO.write(data, "PNG", stream);
	}
}

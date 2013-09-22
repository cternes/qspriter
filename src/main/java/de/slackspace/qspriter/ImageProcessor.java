package de.slackspace.qspriter;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import de.slackspace.qspriter.writer.JsonWriter;

public class ImageProcessor {
	
	private int maxSpriteWidth = 2048;

	public BufferedImage assembleImages(HashMap<String, List<Sprite>> rowData, JsonWriter jsonWriter) {
		Dimension canvasDimension = calculateCanvasSize(rowData);
		return createSpriteAndJson(rowData, canvasDimension, jsonWriter);
	}
	
	private Dimension calculateCanvasSize(HashMap<String, List<Sprite>> rowData) {
		int maxWidth = 0;
		int totalHeight = 0;

		Set<String> keys = rowData.keySet();
		for (String key : keys) {
			List<Sprite> spriteList = rowData.get(key);
			Sprite firstSprite = spriteList.get(0);
			int width = firstSprite.getWidth() * spriteList.size();
			double rows = 1;

			if(width > 2000) {
				rows = Math.ceil(width / maxSpriteWidth);
				width = maxSpriteWidth;
			}

			maxWidth = Math.max(width, maxWidth);
			totalHeight += firstSprite.getHeight() * rows + 1;
		}

		return new Dimension(maxWidth, totalHeight);
	}
	
	private BufferedImage createSpriteAndJson(HashMap<String, List<Sprite>> rowData, Dimension dimension, JsonWriter jsonWriter) {
		BufferedImage bigSprite = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D paint = bigSprite.createGraphics();
		
		int curY = 0;
		Set<String> keys = rowData.keySet();
		for (String key : keys) {
			List<Sprite> spriteList = rowData.get(key);
			Sprite firstImage = spriteList.get(0);

			int imageWidth = firstImage.getWidth();
			int rowHeight = firstImage.getHeight();
			int rowWidth = Math.min(imageWidth * spriteList.size(), maxSpriteWidth);
			int cols = (int) Math.floor(rowWidth / imageWidth);
			double rows = Math.ceil(spriteList.size() / cols);

			jsonWriter.appendJsonObjectForRow(key, curY, cols, imageWidth, rowHeight, spriteList.size());

			drawImagesIntoSprite(paint, curY, spriteList, imageWidth, rowHeight, cols, rows);
			curY += Math.ceil(rowHeight); 
		}
		
		return bigSprite;
	}

	private void drawImagesIntoSprite(Graphics2D paint, int curY, List<Sprite> spriteList, int imageWidth, int rowHeight, int cols,
			double rows) {
		for(int i = 0; i < rows; i++) {
			for(int k = 0; k < cols; k++) {
				if(spriteList.get( k + i * cols) != null)  {
					paint.drawImage(spriteList.get( k + i * cols).getImage(), k * imageWidth, curY, null);
				}
			}
		}
	}
}

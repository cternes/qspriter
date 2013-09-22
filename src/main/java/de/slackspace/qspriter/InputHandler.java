package de.slackspace.qspriter;

public class InputHandler {

	public String processInput(String[] args) {
		if(checkInput(args)) {
			return extractDirectory(args);
		}
		
		showHelp();
		return null;
	}
	
	private String extractDirectory(String[] args) {
		return args[0];
	}

	private boolean checkInput(String[] args) {
		if(args.length == 0) {
			System.out.println("You must provide a directory as argument.");
			return false;
		}
		
		//just a warning, that we can only process the first argument
		if(args.length > 1) {
			System.out.println("The first argument will be used as directory. Other arguments will be ignored.");
			return true;
		}
		
		return true;
	}
	
	private void showHelp() {
		System.out.println("");
		System.out.println("Usage: java -jar qspriter.jar [DIRECTORY]");
		System.out.println("Generates a sprite from all images in the directory (. can be used as current directory).");
	}
	
	
}

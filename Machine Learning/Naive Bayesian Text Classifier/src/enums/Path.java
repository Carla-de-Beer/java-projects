package enums;

public enum Path {

	FOLDERPATH_A("./sourceFiles/Business"), 
	FOLDERPATH_B("./sourceFiles/Sport"), 
	FOLDERPATH_X("./sourceFiles/CategoryX/"), 
	FILEPATH_X("./sourceFiles/CategoryX/X-01.txt"), 
	RESULT_FILE("./sourceFiles/Results/results.txt");

	private final String text;

	private Path(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}

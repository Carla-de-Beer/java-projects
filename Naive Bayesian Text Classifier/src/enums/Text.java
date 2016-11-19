package enums;

public enum Text {

	LINES("-------------------------------------"), 
	DIRECTORY("./sourceFiles/"), 
	RESULT_CAT_A("Classification result: BUSINESS \nCertainty probablility: "), 
	RESULT_CAT_B("Classification result: SPORT \nCertainty probablility: "), 
	RESULT_EITHER("RESULT: There is an equal probability of the input text being of either category"),  
	CAT_A("Business"), 
	CAT_B("Sport"),
	PROB_A("p(A): "), 
	PROB_B("p(B): "), 
	CLASSIFICATION("Classification: Category "), 
	DS_STORE(".DS_Store"), 
	FILE_PATH("File path:\n"), 
	CORRECT("CORRECT RESULT"), 
	INCORRECT("INCORRECT RESULT"), 
	ACCURACY("CLASSIFICATION ACCURACY:"), 
	RUNS(" runs"), 
	PERCENT("%"), 
	PERCENTAGE("% correct"), 
	DICTIONARY_SIZE("Dictionary HashMap size: "), 
	RESULT_SIZE("Result HashMap size: ");

	private final String text;

	private Text(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
	
}

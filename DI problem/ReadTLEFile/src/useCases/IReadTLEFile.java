package useCases;
import java.io.IOException;

/**
 * Interface IReadTLEFile to define the interface for the reading a TLE file.
 */

public interface IReadTLEFile {

	public ReadTLEFileResult readFile()
			throws IOException, FileEmptyException,
			FileIncorrectFormatException, FileIncorrectDataException;

}

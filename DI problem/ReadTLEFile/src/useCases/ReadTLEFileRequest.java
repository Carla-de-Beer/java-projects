package useCases;
public class ReadTLEFileRequest {

    String filepath;
    
    public ReadTLEFileRequest(){    
    }

    public String getFilePath() {
        return filepath;
    }
    
    public void setFilePath(String filepath) {
        this.filepath = filepath;
    }
}
package MyNotePad;

public class MyNotePadModel {

	private String fileName;
	private String content;
	public MyNotePadModel() {
		super();
		this.fileName = "";
		this.content = "";
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}

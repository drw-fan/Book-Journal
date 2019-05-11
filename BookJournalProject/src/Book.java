
public class Book implements Comparable< Book >{
//	private String ISBN;  
	private String name;    
	private String author;
	private String dateRead;
	
	private String summary;
	private String comments;
	
	private boolean readInBC;

//////////////////////////////////////////////////////////////////////
	
	public Book(String pName, String pAuthor, String pDateRead, String pSummary, String pComments, boolean pReadInBC) {
		
		name = pName;
		author = pAuthor;
		dateRead = pDateRead;
		summary = pSummary;
		comments = pComments;
		readInBC = pReadInBC;

//		getISBN(name);   
//		ISBN = getISBN();
	}
/*
	public String getISBN() {
		
		
	}
*/	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDateRead() {
		return dateRead;
	}

	public void setDateRead(String dateRead) {
		this.dateRead = dateRead;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isReadInBC() {
		return readInBC;
	}

	public void setReadInBC(boolean readInBC) {
		this.readInBC = readInBC;
	}

	@Override
	public int compareTo(Book b) {
		return this.getName().compareTo(b.getName());
	}
}

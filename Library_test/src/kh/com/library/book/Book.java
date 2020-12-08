package kh.com.library.book;

public class Book {
// 필드부
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private int bookPrice;
	private String publisher;
	private String genre;
	
// 생성자부
	public Book() {}
	public Book(int bookNo, String bookName, String bookWriter, int bookPrice,
			String publisher, String genre) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookPrice = bookPrice;
		this.publisher = publisher;
		this.genre = genre;
	}
// 메서드부
	public int getBookNo() {		return bookNo;	}
	public void setBookNo(int bookNo) {		this.bookNo = bookNo;	}
	public String getBookName() {		return bookName; }
	public void setBookName(String bookName) {	this.bookName = bookName;}
	public String getBookWriter() {	return bookWriter;	}
	public void setBookWriter(String bookWriter) {	this.bookWriter = bookWriter;	}
	public int getBookPrice() {	return bookPrice;	}
	public void setBookPrice(int bookPrice) {	this.bookPrice = bookPrice;	}
	public String getPublisher() {	return publisher;	}
	public void setPublisher(String publisher) {	this.publisher = publisher;	}
	public String getGenre() {		return genre;	}
	public void setGenre(String genre) {		this.genre = genre;	}
	
	@Override
	public String toString() {
		return bookNo+" / "+bookName+" / "+bookWriter+" / "+bookPrice
				+" / "+publisher+" / "+genre;
	} 

}

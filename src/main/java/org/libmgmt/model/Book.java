package org.libmgmt.model;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "isbn", nullable = false, unique = true)
	private String isbn;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name = "totalCopies", nullable = false)
	private Integer totalCopies;

	@Column(name = "availableCopies", nullable = false)
	private Integer availableCopies;

	@Column(name = "price", nullable = false)
	private Integer price;

	public Book() {
	}

	public Book(Integer bookId) {
		this.id = bookId;
	}

	public Book(Integer id, String isbn, String title, String author, String publisher, Integer totalCopies,
			Integer price) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.totalCopies = totalCopies;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Integer getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", isbn='" + isbn + '\'' + ", title='" + title + '\'' + ", author='" + author
				+ '\'' + ", publisher='" + publisher + '\'' + ", qty=" + totalCopies + ", price=" + price + '}';
	}
}

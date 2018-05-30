package org.libmgmt.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Book_Borrower")
public class BookBorrower {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Book")
    private Book bookId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    public BookBorrower() {
    }

    public BookBorrower(Integer id, Book bookId, User userId, Date issueDate, Date returnDate, Date dueDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

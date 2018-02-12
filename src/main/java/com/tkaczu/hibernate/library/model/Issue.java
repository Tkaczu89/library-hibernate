package com.tkaczu.hibernate.library.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @GeneratedValue
    @Column(name = "issue_id")
    public Integer issueId;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "issue_no")
    public Integer issueNo;

    @Column(name = "issue_date")
    public Date issueDate;

    public Issue(Integer issueNo, Date issueDate) {
        this.issueNo = issueNo;
        this.issueDate = issueDate;
    }


    public Integer getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(Integer issueNo) {
        this.issueNo = issueNo;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}

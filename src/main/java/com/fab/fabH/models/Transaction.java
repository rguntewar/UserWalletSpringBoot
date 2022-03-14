package com.fab.fabH.models;

import javax.persistence.*;

@Entity
public class Transaction {

    public Transaction() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Integer userA;
    Integer userB;
    Integer openingBalance;
    Integer closingBalance;
    Integer debit;
    Integer credit;
//    Date rowcd;
//    Date rowud;


    public Integer getUserA() {
        return userA;
    }

    public void setUserA(Integer userA) {
        this.userA = userA;
    }

    public Integer getUserB() {
        return userB;
    }

    public void setUserB(Integer userB) {
        this.userB = userB;
    }

    public Integer getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Integer openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Integer getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Integer closingBalance) {
        this.closingBalance = closingBalance;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

//    @PrePersist
//    void set(){
//        this.rowcd=this.rowud= new Date();
//    }
//
//    @PreUpdate
//    void updateRowud(){
//        this.rowud = new Date();
//    }
}

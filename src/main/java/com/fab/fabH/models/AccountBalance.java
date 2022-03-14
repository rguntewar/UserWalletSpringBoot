package com.fab.fabH.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountBalance {
    @Id
    Integer id;
    Integer balanceAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}

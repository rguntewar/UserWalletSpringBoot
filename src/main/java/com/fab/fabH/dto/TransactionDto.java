package com.fab.fabH.dto;

import lombok.Data;

@Data
public class TransactionDto {
    Integer userA;
    Integer userB;
    Integer debit;
    Integer credit;
}

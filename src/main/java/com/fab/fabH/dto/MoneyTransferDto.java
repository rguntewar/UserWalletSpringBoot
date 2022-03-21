package com.fab.fabH.dto;

import lombok.Data;

@Data
public class MoneyTransferDto {
    String fromId;
    String toId;
    String amount;
}

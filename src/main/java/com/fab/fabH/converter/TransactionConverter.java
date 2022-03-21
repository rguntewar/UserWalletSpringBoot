package com.fab.fabH.converter;

import com.fab.fabH.dto.TransactionDto;
import com.fab.fabH.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionConverter {

    public TransactionDto entityToDto (Transaction obj){
        TransactionDto dto = new TransactionDto();
        dto.setUserA(obj.getUserA());
        dto.setUserB(obj.getUserB());
        dto.setCredit(obj.getCredit());
        dto.setDebit(obj.getDebit());
        return dto;
    }
    public List<TransactionDto> entityToDto (List<Transaction> list){
        List<TransactionDto> listDto = new ArrayList<>();
        for (Transaction obj:list){
            listDto.add(entityToDto(obj));
        }
        return listDto;
    }
}

package com.fab.fabH.service;

import com.fab.fabH.dto.AccountBalanceDto;
import com.fab.fabH.dto.AddMoneyDto;
import com.fab.fabH.models.AccountBalance;
import com.fab.fabH.repository.AccountBalanceRepository;
import com.fab.fabH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<AddMoneyDto> addMoney(AddMoneyDto obj){
        System.out.println("inside add money service");

        Optional<AccountBalance> userAccount =  accountBalanceRepository.findById(obj.getId());
        Integer amountToAdd = obj.getAmountToAdd();
        if(userAccount.isPresent()){
            AccountBalance userAccountBalance = userAccount.get();
            userAccountBalance.setBalanceAmount(userAccountBalance.getBalanceAmount() + amountToAdd);
            accountBalanceRepository.save(userAccountBalance);
            System.out.println(userAccountBalance.getBalanceAmount());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public AccountBalanceDto getBalance(AccountBalanceDto obj){
        Optional<AccountBalance> userBalance = accountBalanceRepository.findById(Integer.valueOf(obj.getId()));
        AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
        if(userBalance.isPresent()){
            accountBalanceDto.setAccountBalance(userBalance.get().getBalanceAmount());
            accountBalanceDto.setId(userBalance.get().getId().toString());
        }
        return accountBalanceDto;
    }
}


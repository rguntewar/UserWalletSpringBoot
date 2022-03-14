package com.fab.fabH.service;

import com.fab.fabH.models.AccountBalance;
import com.fab.fabH.models.UserDetail;
import com.fab.fabH.repository.AccountBalanceRepository;
import com.fab.fabH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<AccountBalance> addMoney(Map<String, String> obj){
        System.out.println("inside add money service");

        Optional<AccountBalance> userAccount =  accountBalanceRepository.findById(Integer.parseInt(obj.get("id")));
        Integer amountToAdd = Integer.valueOf(obj.get("amount"));
        if(userAccount.isPresent()){
            AccountBalance userAccountBalance = userAccount.get();
            userAccountBalance.setBalanceAmount(userAccountBalance.getBalanceAmount() + amountToAdd);
            accountBalanceRepository.save(userAccountBalance);
            System.out.println(userAccountBalance.getBalanceAmount());
            return new ResponseEntity<AccountBalance>(userAccountBalance, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public AccountBalance getBalance(Map<String,String> obj){
        Optional<AccountBalance> userBalance = accountBalanceRepository.findById(Integer.parseInt(obj.get("id")));
        if(userBalance.isPresent())
            return (userBalance.get());
        return new AccountBalance();
    }
}


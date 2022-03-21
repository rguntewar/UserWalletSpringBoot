package com.fab.fabH.service;

import com.fab.fabH.converter.TransactionConverter;
import com.fab.fabH.converter.UserDetailConverter;
import com.fab.fabH.dto.MoneyTransferDto;
import com.fab.fabH.dto.TransactionDto;
import com.fab.fabH.dto.UserDetailDto;
import com.fab.fabH.models.AccountBalance;
import com.fab.fabH.models.Transaction;
import com.fab.fabH.models.UserDetail;
import com.fab.fabH.repository.AccountBalanceRepository;
import com.fab.fabH.repository.TransactionRepository;
import com.fab.fabH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private TransactionRepository transactionRepository;

     @Autowired
     private AccountBalanceRepository accountBalanceRepository;

     @Autowired private UserDetailConverter userDetailConverter;

     @Autowired private TransactionConverter transactionConverter;
     @Transactional
     public void addNewUser(UserDetailDto obj){

          if(obj.getPhoneNumber().length() != 10) {
               System.out.println("Error in Phone Number");
               return;
          }
//          System.out.println("printing by phone numbers");
//          System.out.println((userRepository.findByPhoneNum()).size());


          UserDetail user = new UserDetail();
          user.setUsername(obj.getUsername());
          user.setPassword(DigestUtils.md5DigestAsHex(obj.getPassword().getBytes(StandardCharsets.UTF_8)));

          user.setPhoneNumber(Long.parseLong(obj.getPhoneNumber()));
          user.setAddress(obj.getAddress());
          user.setActive(true);
          userRepository.save(user);
          Integer id = user.getId();
          AccountBalance accountBalance = new AccountBalance();
          accountBalance.setBalanceAmount(0);
          accountBalance.setId(id);
          accountBalanceRepository.save(accountBalance);
     }

     public Iterable<UserDetailDto> getAllUsers(){
          System.out.println("Inside get all users service");
          return userDetailConverter.entityToDto(userRepository.findAll());
     }

     @Transactional
     public ResponseEntity<UserDetailDto> updateUser(Integer id, UserDetailDto updateDetails){

          Optional<UserDetail> originalU =  userRepository.findById(id);

          if(originalU.isPresent()){
               UserDetail originalUser = originalU.get();
               originalUser.setUsername(updateDetails.getUsername());
               userRepository.save(originalUser);
               return new ResponseEntity<>(updateDetails, HttpStatus.OK);
          }
          else{
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
     }

     @Transactional
     public String transferMoney (MoneyTransferDto obj){

          System.out.println("inside transfer money");

//        Optional<UserDetail> optionalUser1 =  userRepository.findById( Integer.parseInt(obj.get("fromId")));
//        Optional<UserDetail> optionalUser2 =  userRepository.findById( Integer.parseInt(obj.get("toId")));
          Optional<AccountBalance> optionalUser1AccountBalance = accountBalanceRepository.findById(Integer.parseInt(obj.getFromId()));
          Optional<AccountBalance> optionalUser2AccountBalance = accountBalanceRepository.findById(Integer.parseInt(obj.getToId()));

          if( optionalUser2AccountBalance.isPresent() && optionalUser1AccountBalance.isPresent()){    // optionalUser1.isPresent() && optionalUser2.isPresent() &&
//            UserDetail user1 = optionalUser1.get();
//            UserDetail user2 = optionalUser2.get();
               AccountBalance user1AccountBalance = optionalUser1AccountBalance.get();
               AccountBalance user2AccountBalance = optionalUser2AccountBalance.get();

               if(user1AccountBalance.getBalanceAmount()>=Integer.parseInt(obj.getAmount())) {
                    Transaction transaction1 = new Transaction();
                    Transaction transaction2 = new Transaction();

                    transaction1.setUserA(user1AccountBalance.getId());
                    transaction1.setUserB(user2AccountBalance.getId());
                    transaction2.setUserA(user2AccountBalance.getId());
                    transaction2.setUserB(user1AccountBalance.getId());

                    transaction1.setDebit(Integer.parseInt(obj.getAmount()));
                    transaction1.setCredit(0);
                    transaction2.setDebit(0);
                    transaction2.setCredit(Integer.parseInt(obj.getAmount()));

                    transaction1.setOpeningBalance(user1AccountBalance.getBalanceAmount());
                    transaction2.setOpeningBalance(user2AccountBalance.getBalanceAmount());

                    user1AccountBalance.setBalanceAmount(user1AccountBalance.getBalanceAmount()-Integer.parseInt(obj.getAmount()));
                    user2AccountBalance.setBalanceAmount(user2AccountBalance.getBalanceAmount()+Integer.parseInt(obj.getAmount()));

                    transaction1.setClosingBalance(user1AccountBalance.getBalanceAmount());
                    transaction2.setClosingBalance(user2AccountBalance.getBalanceAmount());
                    transactionRepository.save(transaction1);
                    transactionRepository.save(transaction2);
                    accountBalanceRepository.save(user1AccountBalance);
                    accountBalanceRepository.save(user2AccountBalance);

                    return "Money transferred ";
               }
               else {
                    System.out.println("not acc transfer");
                    return "Not enough balance!!";
               }

          }
          return "User does not exist!!";
     }


     public List<TransactionDto> getTransactions(Integer id){
          System.out.println("inside get transaction in user service");
          List<TransactionDto> transactionList ;
          transactionList = transactionConverter.entityToDto((List<Transaction>) transactionRepository.findByUserA(id));

          System.out.println("size of list =" + transactionList.size());
          return transactionList;
     }
}

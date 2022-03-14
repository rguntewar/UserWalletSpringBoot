package com.fab.fabH.service;

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

import java.util.*;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     @Autowired
     private TransactionRepository transactionRepository;

     @Autowired
     private AccountBalanceRepository accountBalanceRepository;

     public void addNewUser(Map<String, String> obj){
          UserDetail user = new UserDetail();
          user.setUsername(obj.get("username"));
          user.setPassword(obj.get("password"));
          user.setPhoneNumber(Long.parseLong(obj.get("phoneNumber")));
          user.setAddress(obj.get("address"));
          user.setActive(true);
          userRepository.save(user);
          Integer id = user.getId();
          AccountBalance accountBalance = new AccountBalance();
          accountBalance.setBalanceAmount(Integer.parseInt(obj.get("balance")));
          accountBalance.setId(id);
          accountBalanceRepository.save(accountBalance);
     }

     public Iterable<UserDetail> getAllUsers(){
          System.out.println("Inside get all users service");
          return userRepository.findAll();
     }


     public ResponseEntity<UserDetail> updateUser(Integer id, UserDetail updateDetails){

          Optional<UserDetail> originalU =  userRepository.findById(id);

          if(originalU.isPresent()){
               UserDetail originalUser = originalU.get();
               originalUser.setUsername(updateDetails.getUsername());
               return new ResponseEntity<>(userRepository.save(originalUser), HttpStatus.OK);
          }
          else{
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }

     }

     public String transferMoney (Map<String, String> obj){

          System.out.println("inside transfer money");

//        Optional<UserDetail> optionalUser1 =  userRepository.findById( Integer.parseInt(obj.get("fromId")));
//        Optional<UserDetail> optionalUser2 =  userRepository.findById( Integer.parseInt(obj.get("toId")));
          Optional<AccountBalance> optionalUser1AccountBalance = accountBalanceRepository.findById(Integer.parseInt(obj.get("fromId")));
          Optional<AccountBalance> optionalUser2AccountBalance = accountBalanceRepository.findById(Integer.parseInt(obj.get("toId")));

          if( optionalUser2AccountBalance.isPresent() && optionalUser1AccountBalance.isPresent()){    // optionalUser1.isPresent() && optionalUser2.isPresent() &&
//            UserDetail user1 = optionalUser1.get();
//            UserDetail user2 = optionalUser2.get();
               AccountBalance user1AccountBalance = optionalUser1AccountBalance.get();
               AccountBalance user2AccountBalance = optionalUser2AccountBalance.get();

               if(user1AccountBalance.getBalanceAmount()>=Integer.parseInt(obj.get("amount"))) {
                    Transaction transaction1 = new Transaction();
                    Transaction transaction2 = new Transaction();

                    transaction1.setUserA(user1AccountBalance.getId());
                    transaction1.setUserB(user2AccountBalance.getId());
                    transaction2.setUserA(user2AccountBalance.getId());
                    transaction2.setUserB(user1AccountBalance.getId());

                    transaction1.setDebit(Integer.parseInt(obj.get("amount")));
                    transaction1.setCredit(0);
                    transaction2.setDebit(0);
                    transaction2.setCredit(Integer.parseInt(obj.get("amount")));

                    transaction1.setOpeningBalance(user1AccountBalance.getBalanceAmount());
                    transaction2.setOpeningBalance(user2AccountBalance.getBalanceAmount());

                    user1AccountBalance.setBalanceAmount(user1AccountBalance.getBalanceAmount()-Integer.parseInt(obj.get("amount")));
                    user2AccountBalance.setBalanceAmount(user2AccountBalance.getBalanceAmount()+Integer.parseInt(obj.get("amount")));

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


     public List<Transaction> getTransactions(Map<String, String > obj){
          System.out.println("inside get transaction in user service");
          List<Transaction> transactionList ; //= new ArrayList<>();
//          List<Integer> ids = Arrays.asList(Integer.parseInt(obj.get("id")));

          transactionList = (List<Transaction>) transactionRepository.findByUserA(Integer.valueOf(obj.get("id")));
          System.out.println("size of list =" + transactionList.size());
          return transactionList;
     }
}

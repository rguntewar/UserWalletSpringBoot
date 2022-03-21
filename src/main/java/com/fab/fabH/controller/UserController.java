package com.fab.fabH.controller;

import com.fab.fabH.dto.*;
import com.fab.fabH.models.AccountBalance;
import com.fab.fabH.models.Transaction;
import com.fab.fabH.repository.UserRepository;
import com.fab.fabH.service.AccountBalanceService;
import com.fab.fabH.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

//import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;

@RestController 
@CrossOrigin(origins = "*")

public class UserController {
	@Autowired 
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountBalanceService accountBalanceService;


	@PostMapping(path="/add") 
	public String addNewUser (@RequestBody UserDetailDto obj ) {
		System.out.println("inside add new user controller");
		userService.addNewUser(obj);
		return "User added successfully";
	}
	
	@GetMapping(path="/all")
	public Iterable<UserDetailDto> getAllUsers() {
		System.out.println("inside get all users controller");
		return userService.getAllUsers();
	}

	@PutMapping(path = "/editUser/{id}")
	public ResponseEntity<UserDetailDto> updateUser(@PathVariable Integer id, @RequestBody UserDetailDto updateDetails){
		System.out.println("inside edit user controller");
		return userService.updateUser(id,updateDetails);
	}

	@PutMapping(path = "/addMoney")
	public ResponseEntity<AddMoneyDto> addMoney(@RequestBody AddMoneyDto obj){
		System.out.println("inside add money");
		return accountBalanceService.addMoney(obj);
	}

	@PutMapping(path = "/transferMoney")
	public String transferMoney(@RequestBody MoneyTransferDto obj){
		return userService.transferMoney(obj);
	}

	@GetMapping(path = "/getBalance")
	public AccountBalanceDto getBalance(@RequestBody AccountBalanceDto obj){
		return accountBalanceService.getBalance(obj);
	}

	@GetMapping(path = "/transactions/{id}")
	public List<TransactionDto> getAllTransactions(@PathVariable Integer id){
		return userService.getTransactions(id);
	}
}
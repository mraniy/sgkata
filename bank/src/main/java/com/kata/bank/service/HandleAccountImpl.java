package com.kata.bank.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.kata.bank.Exception.AmountTooBigForWithdrawalException;
import com.kata.bank.Exception.MinimumDepositException;
import com.kata.bank.Exception.NotEnoughMoneyException;
import com.kata.bank.bean.Account;
import com.kata.bank.bean.Operation;

public class HandleAccountImpl implements IHandleAccount {
	
	private static final BigDecimal MINIMUMDEPOSIT=  new BigDecimal(10);
	
	private static final BigDecimal MAXIMUMWITHDRAWAL= new BigDecimal(2500);

	public void saveMoney(Account account,BigDecimal amount) throws MinimumDepositException {
		//throw exception if the minimum deposit is not respected
		if(amount.compareTo(MINIMUMDEPOSIT) <0) {
			throw new MinimumDepositException("The minimum deposit is 10");
		}
		// if there is not a blocking issue we update the balance and we save the operation
		account.setBalance(account.getBalance().add(amount));
		saveOperation(account,amount, "saveMoney");
		
	}

	public void retrieveMoney(Account account,BigDecimal amount) throws AmountTooBigForWithdrawalException, NotEnoughMoneyException{
		BigDecimal substract= new BigDecimal(0);
		substract= account.getBalance().subtract(amount);
		// throw exception if there is not enough money in the account
		if(substract.compareTo(new BigDecimal(0)) <0 ) {
			throw new NotEnoughMoneyException("not  enough money");
		}
		// throw exception if we try to retrieve more than the max amount of withdrawal
		if(amount.compareTo(MAXIMUMWITHDRAWAL) > 0) {
			throw new AmountTooBigForWithdrawalException("2500 max by operation");
		} 
		
		account.setBalance(substract);
		saveOperation(account,amount, "retrieveMoney");

	}
	

	private void saveOperation(Account account,BigDecimal amount, String type) {
		Operation operation = new Operation(type, Calendar.getInstance().getTime(), amount, account.getBalance());
		account.getListOperations().add(operation);
	}

	public List<Operation> getOperations(Account account) {
		Collections.sort(account.getListOperations());
		return account.getListOperations();
	}

}

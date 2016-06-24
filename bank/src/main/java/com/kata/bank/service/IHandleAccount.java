package com.kata.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.kata.bank.Exception.AmountTooBigForWithdrawalException;
import com.kata.bank.Exception.MinimumDepositException;
import com.kata.bank.Exception.NotEnoughMoneyException;
import com.kata.bank.bean.Account;
import com.kata.bank.bean.Operation;

public interface IHandleAccount {
	/**
	 * save money for an account
	 * @param account
	 * @param amount
	 * @throws MinimumDepositException
	 */
	public void saveMoney(Account account, BigDecimal amount) throws MinimumDepositException ;
	
	/**
	 * retrieve money from an account
	 * @param account
	 * @param amount
	 * @throws AmountTooBigForWithdrawalException
	 * @throws NotEnoughMoneyException
	 */
	public void retrieveMoney(Account account,BigDecimal amount) throws AmountTooBigForWithdrawalException, NotEnoughMoneyException;
	
	/**
	 * retrieve operations from an account
	 * @param account
	 * @return
	 */
	public List<Operation> getOperations(Account account);

}

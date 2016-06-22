package com.kata.bank;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kata.bank.Exception.AmountTooBigForWithdrawalException;
import com.kata.bank.Exception.MinimumDepositException;
import com.kata.bank.Exception.NotEnoughMoneyException;
import com.kata.bank.bean.Account;




/**
 * Unit test for simple App.
 */
public class HandleAccountTest  
{
	IHandleAccount iHandleAccount= null;
	

    @Before
    public void setUp() {
    	iHandleAccount= new HandleAccountImpl();
    }
	// test if the operation of saving money works fine
	@Test
	public void testSaveMoney() throws Exception{
		Account account = new Account("user1", "last1", new BigDecimal(500));
		iHandleAccount.saveMoney(account, new BigDecimal(200));
		Assert.assertEquals(account.getBalance(), new BigDecimal(700));
	}
	
	
	// test if the operation of retrieving money works fine
	@Test
	public void testRetrieveMoney() throws Exception{
		Account account = new Account("user1", "last1", new BigDecimal(500));
		iHandleAccount.retrieveMoney(account, new BigDecimal(200));
		Assert.assertEquals(account.getBalance(), new BigDecimal(300));
	}
	
	// test the retrieval of operations works fine
	@Test
	public void testGetOperations() throws Exception{
		Account account = new Account("user1", "last1", new BigDecimal(0));
		iHandleAccount.saveMoney(account, new BigDecimal(200));
		iHandleAccount.saveMoney(account, new BigDecimal(600));
		iHandleAccount.saveMoney(account, new BigDecimal(800));
		iHandleAccount.retrieveMoney(account, new BigDecimal(300));
		Assert.assertEquals(account.getListOperations().size(), 4);
		Assert.assertEquals(account.getBalance(), new BigDecimal(1300));
	}
	
	
	// test if the the sum of the deposit is too small , that should throw an exception
	@Test(expected= MinimumDepositException.class)
	public void testMinimumDepositException() throws Exception{
		Account account = new Account("user1", "last1", new BigDecimal(500));
		iHandleAccount.saveMoney(account, new BigDecimal(2));
	}
	

	//test if there is not enough money on the account that should throw an exception
	@Test(expected=NotEnoughMoneyException.class)
	public void testNotEnoughMoneyOnYourAccount() throws Exception{
		Account account = new Account("user1", "last1", new BigDecimal(500));
		iHandleAccount.retrieveMoney(account, new BigDecimal(700));
	}
	
	// test if the amount is bigger than 2500 that should throw an exception
	@Test(expected=AmountTooBigForWithdrawalException.class)
	public void testAmountTooBigForWithdrawalException() throws Exception{
		Account account = new Account("user1", "last1", new BigDecimal(5000));
		iHandleAccount.retrieveMoney(account, new BigDecimal(3000));
	}
	


}

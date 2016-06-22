package com.kata.bank.demo;

import java.math.BigDecimal;

import com.kata.bank.HandleAccountImpl;
import com.kata.bank.IHandleAccount;
import com.kata.bank.Exception.AmountTooBigForWithdrawalException;
import com.kata.bank.Exception.MinimumDepositException;
import com.kata.bank.Exception.NotEnoughMoneyException;
import com.kata.bank.bean.Account;

public class IhmProcess {

	private IHandleAccount handleAccount;

	private Account account;

	private Ihm ihm;

	public IhmProcess(Account account, Ihm ihm) {
		super();
		handleAccount = new HandleAccountImpl();
		this.account = account;
		this.ihm = ihm;
	}

	public void handleSaveMoney(String amount) {

		try {
			handleAccount.saveMoney(account, new BigDecimal(amount));
			ihm.getLabelBalance().setText(
					new StringBuffer(" Balance: ").append(
							account.getBalance().toString()).toString());
			updateOperations();
		} catch (MinimumDepositException e1) {
			ihm.getLabelError().setText(e1.getMessage());
		} catch (NumberFormatException e2) {
			ihm.getLabelError().setText("you have to fill a number");
		}

	}

	public void handleRetrieveMoney(String amount) {
		try {
			handleAccount.retrieveMoney(account, new BigDecimal(amount));
			ihm.getLabelBalance().setText(
					new StringBuffer(" Balance: ").append(
							account.getBalance().toString()).toString());
			updateOperations();
		} catch (AmountTooBigForWithdrawalException e) {
			ihm.getLabelError().setText(e.getMessage());
		} catch (NotEnoughMoneyException e) {
			ihm.getLabelError().setText(e.getMessage());
		} catch (NumberFormatException e2) {
			ihm.getLabelError().setText("you have to fill a number");
		}
	}

	private void updateOperations() {
		OperationModele operationModele = new OperationModele(account.getListOperations());
		ihm.getTableOperations().setModel(operationModele);
		ihm.getConteneur().add(ihm.getTableOperations()); 
	}
	




}

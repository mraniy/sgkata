package com.kata.bank.demo;

import java.awt.Color;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.kata.bank.Exception.AmountTooBigForWithdrawalException;
import com.kata.bank.Exception.MinimumDepositException;
import com.kata.bank.Exception.NotEnoughMoneyException;
import com.kata.bank.bean.Account;
import com.kata.bank.service.HandleAccountImpl;
import com.kata.bank.service.IHandleAccount;

public class IhmProcess {

	private IHandleAccount handleAccount;

	private Account account;

	private Ihm ihm;
	
	private boolean firstExecution=false;

	public IhmProcess(Account account, Ihm ihm) {
		super();
		handleAccount = new HandleAccountImpl();
		this.account = account;
		this.ihm = ihm;
	}

	public void handleSaveMoney(String amount) {

		try {
			// calling the service saving the money
			handleAccount.saveMoney(account, new BigDecimal(amount));
			// a little success message
			showLabelMessage(ihm.getLabelMessage(), "Money Saved successfully", Color.GREEN);
			// updating the balance
			ihm.getLabelBalance().setText(
					new StringBuffer(" Balance: ").append(
							account.getBalance().toString()).toString());

		} catch (MinimumDepositException e1) {
			// the minimum déposit is not respected
			showLabelMessage(ihm.getLabelMessage(), e1.getMessage(), Color.RED);
		} catch (NumberFormatException e2) {
			// no number filled so , raise the exception
			showLabelMessage(ihm.getLabelMessage(), "you have to fill a number", Color.RED);
		}

	}

	public void handleRetrieveMoney(String amount) {
		try {
			// calling the service retrieve the money
			handleAccount.retrieveMoney(account, new BigDecimal(amount));
			// a little success message
			showLabelMessage(ihm.getLabelMessage(), "Money Retrieved successfully", Color.GREEN);
			//updating the balance
			ihm.getLabelBalance().setText(
					new StringBuffer(" Balance: ").append(
							account.getBalance().toString()).toString());
		

		} catch (AmountTooBigForWithdrawalException e) {
			// amount to big for withdrawal
			showLabelMessage(ihm.getLabelMessage(), e.getMessage(), Color.RED);
		} catch (NotEnoughMoneyException e) {
			// not enough money to be withdrawn
			showLabelMessage(ihm.getLabelMessage(), e.getMessage(), Color.RED);
		} catch (NumberFormatException e2) {
			// no number filled so , raise the exception
			showLabelMessage(ihm.getLabelMessage(),"you have to fill a number", Color.RED);
		}
	}
	
	public void handleShowOperations() {
		// calling the service handling the operation and updating the ihm model
		account.setListOperations(handleAccount.getOperations(account));
		// initializing the data of the table
		OperationModele operationModele = new OperationModele(account.getListOperations());
		ihm.getTableOperations().setModel(operationModele);
		//créating a scroll pane with the data if it's the first execution
		if(!firstExecution) {
			firstExecution=true;
			ihm.setScrollPane(new JScrollPane(ihm.getTableOperations()));
		}
		// adding the scroll
		ihm.getConteneur().add(ihm.getScrollPane());
	}
	
	private void showLabelMessage(JLabel labelMessage, String message, Color color) {
		
		labelMessage.setForeground(color);
		labelMessage.setText(message);

		
	}


	




}

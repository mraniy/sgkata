package com.kata.bank.demo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.kata.bank.bean.Account;

public class Ihm extends JFrame implements ActionListener {

	private IhmProcess ihmProcess;
	
	private OperationModele operationModele;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1651065932278819277L;

	private JTable tableOperations = null;
	private JLabel labelBalance = new JLabel();

	private JPanel conteneur = new JPanel();

	private JButton buttonSaveMoney = new JButton("save money");

	private JButton buttonRetrieveMoney = new JButton("retrieve money");

	private JLabel labelError = new JLabel();

	private JTextField textSaveMoney = new JTextField("");
	private JTextField textRetrieveMoney = new JTextField("");

	public JLabel getLabelBalance() {
		return labelBalance;
	}

	public void setLabelBalance(JLabel labelBalance) {
		this.labelBalance = labelBalance;
	}

	public JPanel getConteneur() {
		return conteneur;
	}

	public void setConteneur(JPanel conteneur) {
		this.conteneur = conteneur;
	}

	public JButton getButtonSaveMoney() {
		return buttonSaveMoney;
	}

	public void setButtonSaveMoney(JButton buttonSaveMoney) {
		this.buttonSaveMoney = buttonSaveMoney;
	}

	public JButton getButtonRetrieveMoney() {
		return buttonRetrieveMoney;
	}

	public void setButtonRetrieveMoney(JButton buttonRetrieveMoney) {
		this.buttonRetrieveMoney = buttonRetrieveMoney;
	}

	public JLabel getLabelError() {
		return labelError;
	}

	public void setLabelError(JLabel labelError) {
		this.labelError = labelError;
	}

	public JTextField getTextSaveMoney() {
		return textSaveMoney;
	}

	public void setTextSaveMoney(JTextField textSaveMoney) {
		this.textSaveMoney = textSaveMoney;
	}

	public JTextField getTextRetrieveMoney() {
		return textRetrieveMoney;
	}

	public void setTextRetrieveMoney(JTextField textRetrieveMoney) {
		this.textRetrieveMoney = textRetrieveMoney;
	}

	public Ihm(Account account) {
		ihmProcess = new IhmProcess(account, this);
		labelBalance.setText(new StringBuffer(" Balance: ").append(
				account.getBalance()).toString());
		setTitle(new StringBuffer("Hello ").append(account.getFirstname())
				.append(" ").append(account.getLastName())
				.append(" , this is you simple cash management application.")
				.toString());
		setSize(800, 400);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		conteneur.setLayout(new GridLayout(4, 2));
		conteneur.add(buttonSaveMoney);
		buttonSaveMoney.addActionListener(this);
		conteneur.add(textSaveMoney);
		buttonRetrieveMoney.addActionListener(this);
		conteneur.add(buttonRetrieveMoney);
		conteneur.add(textRetrieveMoney);
		conteneur.add(labelBalance);
		conteneur.add(labelError);
		setVisible(true);
		setContentPane(conteneur);
		tableOperations= new JTable(operationModele);
		conteneur.add(tableOperations);
	}

	public static void main(String[] args) {
		Account account = new Account("test", "test", new BigDecimal(0));
		Ihm ihm = new Ihm(account);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonSaveMoney) {
			ihmProcess.handleSaveMoney(textSaveMoney.getText());
		} else if (source == buttonRetrieveMoney) {
			ihmProcess.handleRetrieveMoney(textRetrieveMoney.getText());
		}

	}

	public JTable getTableOperations() {
		return tableOperations;
	}

	public void setTableOperations(JTable tableOperations) {
		this.tableOperations = tableOperations;
	}
	
	

}

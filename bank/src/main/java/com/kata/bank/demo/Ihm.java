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
	
	private JScrollPane scrollPane;

	/**
	 * 
	 */
	
	private static final String OPERATIONVISIBLE="show operations";
	
	private static final String OPERATIONINVISIBLE="hide operations";
	private static final long serialVersionUID = -1651065932278819277L;

	private JTable tableOperations = null;
	private JLabel labelBalance = new JLabel();

	private JPanel conteneur = new JPanel();

	private JButton buttonSaveMoney = new JButton("save money");

	private JButton buttonRetrieveMoney = new JButton("retrieve money");
	
	private JButton buttonShowOperations = new JButton(OPERATIONVISIBLE);

	private JLabel labelMessageUser = new JLabel();

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

	public JLabel getLabelMessage() {
		return labelMessageUser;
	}

	public void setLabelMessage(JLabel labelError) {
		this.labelMessageUser = labelError;
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
	public JTable getTableOperations() {
		return tableOperations;
	}

	public void setTableOperations(JTable tableOperations) {
		this.tableOperations = tableOperations;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	

	public Ihm(Account account) {
		tableOperations= new JTable(operationModele);
		// instanciation of the adapter ihm process (separation of concerns)
		ihmProcess = new IhmProcess(account, this);
		// set the text of the balance
		labelBalance.setText(new StringBuffer(" Balance: ").append(
				account.getBalance()).toString());
		// set the title of the frame
		setTitle(new StringBuffer("Hello ").append(account.getFirstname())
				.append(" ").append(account.getLastName())
				.append(" , this is you simple cash management application.")
				.toString());
		setSize(800, 400);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setting the layout and the listners
		conteneur.setLayout(new GridLayout(4, 2));
		conteneur.add(buttonSaveMoney);
		buttonSaveMoney.addActionListener(this);
		conteneur.add(textSaveMoney);
		buttonRetrieveMoney.addActionListener(this);
		buttonShowOperations.addActionListener(this);
		// adding elements into the container
		conteneur.add(buttonRetrieveMoney);
		conteneur.add(textRetrieveMoney);
		conteneur.add(labelBalance);
		conteneur.add(labelMessageUser);
		conteneur.add(buttonShowOperations);
		
		setVisible(true);
		setContentPane(conteneur);
		
		
	}
	
	

	public static void main(String[] args) {
		Account account = new Account("test", "test", new BigDecimal(0));
		Ihm ihm = new Ihm(account);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonSaveMoney) {
			// call handle save money in the adapter
			ihmProcess.handleSaveMoney(textSaveMoney.getText());
		} else if (source == buttonRetrieveMoney) {
			// call handle retrieve money
			ihmProcess.handleRetrieveMoney(textRetrieveMoney.getText());
		} else if(source==buttonShowOperations) {
			// toggling the tables of operations
			if(OPERATIONVISIBLE.equals(buttonShowOperations.getText())) {
				tableOperations.setVisible(true);
				// show operations
				ihmProcess.handleShowOperations();
				buttonShowOperations.setText(OPERATIONINVISIBLE);	
			} else if(OPERATIONINVISIBLE.equals(buttonShowOperations.getText())){
				tableOperations.setVisible(false);
				buttonShowOperations.setText(OPERATIONVISIBLE);	
			}
		}

	}


	

}

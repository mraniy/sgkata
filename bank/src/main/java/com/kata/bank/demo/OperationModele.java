package com.kata.bank.demo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.kata.bank.bean.Operation;
import com.kata.bank.utils.DateUtils;

public class OperationModele extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5570842817987831512L;


	private final String[] entetes = { "type", "date", "amount", "balance" };

	
	private List<Operation> operations;
	
	
	
	
	public OperationModele(List<Operation> operations) {
		super();
		this.operations = operations;
	}
	
	
	

	public String getColumnName(int column) {
	    return entetes[column];
	}

	public int getRowCount() {
	
		// TODO Auto-generated method stub
		return operations.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		case 0:

			return operations.get(rowIndex).getType();

		case 1:

			return  DateUtils.fromDateToString(operations.get(rowIndex).getDate(), "yyyy-MM-dd HH:mm") ;

		case 2:

			return operations.get(rowIndex).getAmount();

		case 3:

			return operations.get(rowIndex).getBalance();


		default:
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:return String.class;
			case 1:
				return String.class;
	
			case 2:
				return BigDecimal.class;
	
			case 3:
				return BigDecimal.class;
	
	
	
			default:
				return Object.class;
		}
	}
	
	public List<Operation> getoperations() {
		return operations;
	}

}

package com.deloitte.tms.pl.workflow.model.variable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Integer")
public class IntegerVariable extends Variable {
	@Column(name="INTEGER_VALUE_")
	private int integerValue;

	public IntegerVariable(){}
	
	public IntegerVariable(int value){
		this.integerValue=value;
	}
	@Override
	public Object getValue() {
		return integerValue;
	}

	public int getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(int integerValue) {
		this.integerValue = integerValue;
	}
	@Override
	public VariableType getType() {
		return VariableType.Integer;
	}
}

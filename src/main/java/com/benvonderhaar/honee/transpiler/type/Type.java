package com.benvonderhaar.honee.transpiler.type;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class Type extends AnyType implements Lexable {

	protected String name;

	public Type(String name) {
		this.name = name;
	}

	public static Class<? extends Type> getType(String type) throws HoneeException {
		if (type.equals("integer")) {
			return IntegerType.class;
		} else if (type.equals("boolean")) {
			return BooleanType.class;
		} else {
			throw new HoneeException("Unrecognized type: " + type);
		}
	}

	@Override
	public String getRegex() {
		return "^integer|boolean$";
	}
	
}

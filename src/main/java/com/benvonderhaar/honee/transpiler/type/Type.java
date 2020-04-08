package com.benvonderhaar.honee.transpiler.type;

import com.benvonderhaar.honee.transpiler.util.HoneeException;

public abstract class Type extends AnyType {

	public static Class<? extends Type> getType(String type) throws HoneeException {
		if (type.equals("integer")) {
			return IntegerType.class;
		} else if (type.equals("boolean")) {
			return BooleanType.class;
		} else {
			throw new HoneeException("Unrecognized type: " + type);
		}
	}
	
}

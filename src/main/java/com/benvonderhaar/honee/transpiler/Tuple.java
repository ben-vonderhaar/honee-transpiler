package com.benvonderhaar.honee.transpiler;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Tuple {

    private Object[] contents;

    public Tuple(Object... contents) {

        if (contents.length != getTupleTypes().size()) {
            System.out.println("Incorrect number of parameters for Tuple type: "
                    + this.getClass().getName() + " requires " + getTupleTypes().stream() + " parameters");
            System.exit(1);
        }

        this.contents = contents;
    }

    public Object getItem(int index) {

        return contents[index];
    }

    public Object getItemByClass(Class clazz) {

        List<Class> tupleTypes = getTupleTypes();

        if (0 > tupleTypes.indexOf(clazz)) {
            return null;
        }

        return contents[(tupleTypes.indexOf(clazz))];
    }

    public abstract List<Class> getTupleTypes();

    @Override
    public int hashCode() {

        return Objects.hashCode(Arrays.stream(contents)
            .map(Object::toString)
            .collect(Collectors.joining(" ")));
    }
}

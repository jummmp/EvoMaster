package org.evomaster.client.java.controller.api.dto.problem.rpc.schema.params;

import org.evomaster.client.java.controller.api.dto.problem.rpc.schema.types.StringType;

/**
 * string param
 */
public class StringParam extends NamedTypedValue<StringType, String> {

    public StringParam(String name) {
        super(name, new StringType());
    }

    @Override
    public Object newInstance() {
        return getValue();
    }
}

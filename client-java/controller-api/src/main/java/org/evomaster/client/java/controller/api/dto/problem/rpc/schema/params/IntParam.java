package org.evomaster.client.java.controller.api.dto.problem.rpc.schema.params;

import org.evomaster.client.java.controller.api.dto.problem.rpc.schema.types.PrimitiveOrWrapperType;

/**
 * int param
 */
public class IntParam extends PrimitiveOrWrapperParam<Integer> {

    public IntParam(String name, String type, String fullTypeName) {
        super(name, type, fullTypeName);
    }

    public IntParam(String name, PrimitiveOrWrapperType type) {
        super(name, type);
    }
}

package org.evomaster.core.search.gene

import org.evomaster.core.search.StructuralElement

abstract class CompositeFixedGene(
        name: String,
        children: List<out Gene>
) : CompositeGene(name, children.toMutableList()) {

    constructor(name: String, child: Gene) : this(name, mutableListOf(child))

    init {
        if(children.isEmpty() && !canBeChildless()){
            throw IllegalStateException("A fixed composite gene must have at least 1 internal gene")
        }
    }

    open fun canBeChildless() = false

    //TODO lock methods on children

    override fun addChild(child: StructuralElement) {
        throw IllegalStateException("BUG in EvoMaster: cannot modify children of fixed ${this.javaClass}")
    }

    //TODO delete
}
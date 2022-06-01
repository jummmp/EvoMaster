package org.evomaster.core.search.gene.regex

import org.evomaster.core.output.OutputFormat
import org.evomaster.core.search.gene.Gene
import org.evomaster.core.search.gene.GeneUtils
import org.evomaster.core.search.gene.SimpleGene
import org.evomaster.core.search.service.AdaptiveParameterControl
import org.evomaster.core.search.service.Randomness
import org.evomaster.core.search.service.mutator.MutationWeightControl
import org.evomaster.core.search.service.mutator.genemutation.AdditionalGeneMutationInfo
import org.evomaster.core.search.service.mutator.genemutation.SubsetGeneSelectionStrategy

/**
 * Immutable class
 */
class PatternCharacterBlockGene(
        name: String,
        val stringBlock: String
) : RxAtom, SimpleGene(name) {

    override fun isMutable(): Boolean {
        return false
    }


    override fun copyContent(): Gene {
        return PatternCharacterBlockGene(name, stringBlock)
    }

    override fun randomize(randomness: Randomness, tryToForceNewValue: Boolean, allGenes: List<Gene>) {
        throw IllegalStateException("Not supposed to mutate " + this.javaClass.simpleName)
    }

    override fun shallowMutate(randomness: Randomness, apc: AdaptiveParameterControl, mwc: MutationWeightControl, allGenes: List<Gene>, selectionStrategy: SubsetGeneSelectionStrategy, enableAdaptiveGeneMutation: Boolean, additionalGeneMutationInfo: AdditionalGeneMutationInfo?): Boolean {
        throw IllegalStateException("Not supposed to mutate " + this.javaClass.simpleName)
    }

    override fun getValueAsPrintableString(previousGenes: List<Gene>, mode: GeneUtils.EscapeMode?, targetFormat: OutputFormat?, extraCheck: Boolean): String {
        return stringBlock
    }

    override fun copyValueFrom(other: Gene) {
        if (other !is PatternCharacterBlockGene) {
            throw IllegalArgumentException("Invalid gene type ${other.javaClass}")
        }

        if(other.stringBlock != this.stringBlock) {
            //this should not happen
            throw IllegalStateException("Not supposed to copy value for " + this.javaClass.simpleName)
        }
    }

    override fun containsSameValueAs(other: Gene): Boolean {
        if (other !is PatternCharacterBlockGene) {
            throw IllegalArgumentException("Invalid gene type ${other.javaClass}")
        }
        return this.stringBlock == other.stringBlock
    }


    override fun bindValueBasedOn(gene: Gene): Boolean {
        // do nothing
        return true
    }
}
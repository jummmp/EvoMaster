package org.evomaster.core.problem.rpc

import org.evomaster.core.problem.api.service.ApiWsAction
import org.evomaster.core.problem.api.service.param.Param
import org.evomaster.core.problem.rpc.param.RPCInputParam
import org.evomaster.core.search.gene.Gene

/**
 * created by manzhang on 2021/11/25
 */
class RPCCallAction(
    val id: String,
    parameters: MutableList<Param>
) : ApiWsAction(parameters)  {

    override fun getName(): String {
        return id
    }

    override fun seeGenes(): List<out Gene> {
        // ignore genes in response here
        return parameters.filterIsInstance<RPCInputParam>().flatMap { it.seeGenes() }
    }

    override fun shouldCountForFitnessEvaluations(): Boolean {
        return true
    }

    override fun copyContent(): RPCCallAction {
        val p = parameters.asSequence().map(Param::copyContent).toMutableList()
        return RPCCallAction(id, p)
    }
}
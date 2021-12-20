package org.evomaster.e2etests.utils;

import io.swagger.models.auth.In;
import org.evomaster.core.Main;
import org.evomaster.core.problem.rpc.RPCCallResult;
import org.evomaster.core.problem.rpc.RPCIndividual;
import org.evomaster.core.problem.util.ParamUtil;
import org.evomaster.core.search.Solution;
import org.evomaster.core.search.gene.ArrayGene;
import org.evomaster.core.search.gene.CollectionGene;
import org.evomaster.core.search.gene.Gene;
import org.evomaster.core.search.gene.MapGene;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RPCTestBase extends WsTestBase{

    protected Solution<RPCIndividual> initAndRun(List<String> args){
        return (Solution<RPCIndividual>) Main.initAndRun(args.toArray(new String[0]));
    }

    public static void assertResponseContainCustomizedException(Solution<RPCIndividual> solution, String exceptionName, String content){
        boolean ok = solution.getIndividuals().stream().anyMatch(s->
                s.evaluatedActions().stream().anyMatch(e-> {
                    String body = ((RPCCallResult)e.getResult()).getCustomizedExceptionBody();
                    return body != null && body.contains(exceptionName) && body.contains(content);
                }));
        assertTrue(ok, "do not find any exception matched with "+exceptionName+ " "+ content);
    }

    public static void assertResponseContainException(Solution<RPCIndividual> solution, String exceptionName){
        boolean ok = solution.getIndividuals().stream().anyMatch(s->
                s.evaluatedActions().stream().anyMatch(e-> {
                    String code = ((RPCCallResult)e.getResult()).getExceptionCode();
                    return code != null && code.equals(exceptionName);
                }));
        assertTrue(ok);
    }


    public static void assertSizeInResponseForEndpoint(Solution<RPCIndividual> solution, String methodName, Integer min, Integer max){
        boolean ok = solution.getIndividuals().stream().anyMatch(s->
                s.getIndividual().seeActions().stream().anyMatch(a-> {
                    int size = getCollectionSize( a.getResponse().getGene());
                    return a.getName().equals(methodName) && (min == null || size >= min) && (max == null ||  size <= max);
                    }));

        assertTrue(ok);
    }

    private static int getCollectionSize(Gene gene){
        if (gene == null) return -1;
        Gene valueGene = ParamUtil.Companion.getValueGene(gene);
        if (!(valueGene instanceof CollectionGene)){
           return -1;
        }

        if (valueGene instanceof ArrayGene)
            return ((ArrayGene)valueGene).getAllElements().size();
        if (valueGene instanceof MapGene)
            return ((MapGene) valueGene).getAllElements().size();
        return -1;
    }
}

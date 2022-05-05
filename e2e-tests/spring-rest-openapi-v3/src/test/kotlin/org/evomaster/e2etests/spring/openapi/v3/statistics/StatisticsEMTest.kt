package org.evomaster.e2etests.spring.openapi.v3.statistics

import com.foo.rest.examples.spring.openapi.v3.statistics.StatisticsController
import org.evomaster.core.output.Termination
import org.evomaster.core.problem.rest.HttpVerb
import org.evomaster.core.search.service.Statistics
import org.evomaster.e2etests.spring.openapi.v3.SpringTestBase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.*

class StatisticsEMTest : SpringTestBase() {

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            initClass(StatisticsController())
        }
    }

    @Test
    fun testRunEM(){
        val terminations = Arrays.asList(Termination.FAULTS.suffix,
                Termination.SUCCESSES.suffix)

        runTestHandlingFlakyAndCompilation(
                "StatisticsEM",
                "org.foo.StatisticsEM",
                terminations,
                1000
        ){args: List<String> ->

            val solution = initAndRun(args)

            val data = solution.statistics as MutableList<Statistics.Pair>

            assertTrue(solution.individuals.size >= 1)
            assertHasAtLeastOne(solution, HttpVerb.GET, 200)
            assertHasAtLeastOne(solution, HttpVerb.GET, 500)

            assertEquals("1", data.find { p -> p.header.contains("errors5xx") }?.element)
            assertEquals("1", data.find { p -> p.header.contains("distinct500Faults")}?.element)
            assertEquals("1", data.find { p -> p.header.contains("failedOracleExpectations")}?.element)
            assertEquals("3", data.find { p -> p.header.contains("potentialFaults")}?.element)


            data.filter { p -> p.header.startsWith("bootTimeCovered")}.apply {
                assertEquals(3, size)
                forEach { p->
                    assertTrue(p.element.toInt() > 0, "${p.header} should be more than 0, but it is ${p.element.toInt()}")
                }
            }

            data.filter { p -> p.header.startsWith("searchTimeCovered")}.apply {
                assertEquals(3, size)
                forEach { p->
                    assertTrue(p.element.toInt() > 0, "${p.header} should be more than 0, but it is ${p.element.toInt()}")
                }
            }
        }
    }
}
package com.thinkaurelius.faunus.formats;

import com.thinkaurelius.faunus.formats.graphson.GraphSONInputFormat;
import com.thinkaurelius.faunus.formats.graphson.GraphSONOutputFormat;
import com.thinkaurelius.faunus.formats.noop.NoOpOutputFormat;
import junit.framework.TestCase;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;


/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class InverterTest extends TestCase {

    public void testInputInversion() {
        assertEquals(Inverter.invertInputFormat(SequenceFileInputFormat.class), SequenceFileOutputFormat.class);
        assertEquals(Inverter.invertInputFormat(GraphSONInputFormat.class), GraphSONOutputFormat.class);
        try {
            Inverter.invertInputFormat(TextInputFormat.class);
            assertFalse(true);
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }

    public void testOutputInversion() {
        assertEquals(Inverter.invertOutputFormat(SequenceFileOutputFormat.class), SequenceFileInputFormat.class);
        assertEquals(Inverter.invertOutputFormat(GraphSONOutputFormat.class), GraphSONInputFormat.class);
        try {
            Inverter.invertOutputFormat(NoOpOutputFormat.class);
            assertFalse(true);
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
}

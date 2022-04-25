package com.douma.runlengthencoding;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RunLengthEncodingTest extends TestCase
{
    public void test_to_happy_path()
    {
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        this.assertEquals("2A1B3C1D4E",runLengthEncoding.to("AABCCCDEEEE"));
        this.assertEquals("3A3B",runLengthEncoding.to("AAABBB"));
        this.assertEquals("3A3B3C",runLengthEncoding.to("AAABBBCCC"));
        this.assertEquals("3A3B3C1D",runLengthEncoding.to("AAABBBCCCD"));
        this.assertEquals("12W1B12W3B24W1B14W",
                runLengthEncoding.to("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW"));
    }

    public void test_from_happy_path()
    {
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        this.assertEquals("AABCCCDEEEE",runLengthEncoding.from("2A1B3C1D4E"));
        this.assertEquals("AAABBB",runLengthEncoding.from("3A3B"));
        this.assertEquals("AAABBBCCC",runLengthEncoding.from("3A3B3C"));
        this.assertEquals("AAABBBCCCD",runLengthEncoding.from("3A3B3C1D"));
        this.assertEquals("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW",
                runLengthEncoding.from("12W1B12W3B24W1B14W"));
    }
}


// 2nd File 
package com.douma.runlengthencoding;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
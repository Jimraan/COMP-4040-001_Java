package org.jibril;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

public class Main {
    public static void main(String[] args) {

        NameParser parser = Parboiled.createParser(NameParser.class);
        ParsingResult<?> result;
        String parseTreePrintOut;

        String[] inputs = {
                "Fatih Sen",
                "Bob E Mill",
                "Kenmore E. Lite",
                "Aslan The Bestest Most Magical Narnian Lion"};
        for (String name : inputs) {
            result = new BasicParseRunner(parser.FullName()).run(name);
            parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
            System.out.println(parseTreePrintOut);
        }
    }
}
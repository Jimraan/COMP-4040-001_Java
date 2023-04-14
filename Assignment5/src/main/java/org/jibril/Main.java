package org.jibril;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

public class Main {
    public static void main(String[] args) {

        AddressParser parser = Parboiled.createParser(AddressParser.class);
        ParsingResult<?> result;
        String parseTreePrintOut;

        String[] inputs = {
                "3609 Mynders Ave, Memphis, 38111, TN, United States of America",
                "240 Pryor St, Little Rock, 71934, AR, United States of America"
        };

        for (String address : inputs) {
            result = new BasicParseRunner(parser.Address()).run(address);
            parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
            System.out.println(parseTreePrintOut);
        }
    }
}
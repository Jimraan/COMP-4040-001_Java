package org.jibril;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

public class Main {
    public static void main(String[] args) {

        FormParser parser = Parboiled.createParser(FormParser.class);
        ParsingResult<?> result;
        String parseTreePrintOut;

        /*  The inputs below include newline characters to match the format given in the instructions:
            John Doe
            3609 Mynders Ave, Memphis, TN, United States of America
            (901) 345-9834

            The parser is very robust, accepting the following special cases and more:
            John: optional middle name, 4-digit address #, "Ave" street suffix, abbreviation for state/province, multi-word country name
            Mary: multiple middle names, 2-digit address #, "St" street suffix, street suffix ending with ".", full name for state/province, single-word country name
            Joshua: initial for middle name (no "."), 3-digit address #, no lowercase/uppercase restriction on street name
            Bilbo: initial middle name (with "."), multi-word address name, multi-word state/province name, dash in country name
        */

        String[] inputs = {
                "John Doe\n3609 Mynders Ave, Memphis, TN, United States of America\n(901) 345-9834",
                "Mary Jane Helen Smith\n82 Main St., Toronto, Ontario, Canada\n(416) 876-1234",
                "Joshua R. Clemens\n982 pryor st, Little Rock, AR, USA\n(501) 876-1234",
                "Bilbo B Baggett\n379 Prancing Pony Pkwy, The Shire, Eriador, Middle-earth\n(901) 678-3044"
        };

        for (String form : inputs) {
            result = new BasicParseRunner(parser.Form()).run(form);
            parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
            System.out.println(parseTreePrintOut);
        }
    }
}
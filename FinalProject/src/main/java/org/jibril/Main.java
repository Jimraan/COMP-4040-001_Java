package org.jibril;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // initial parboiled things
        FormParser parser = Parboiled.createParser(FormParser.class);
        ParsingResult<?> result;
        String parseTreePrintOut;

        // Parser features with input examples
        /*  The inputs below include newline characters to match the format given in the instructions:
            John Doe
            3609 Mynders Ave, Memphis, TN, United States of America
            (901) 345-9834

            The parser is very robust, accepting the following special cases and more:
            John: optional middle name, 4-digit address #, "Ave" street suffix,
                    abbreviation for state/province, multi-word country name

            Mary: multiple middle names, 2-digit address #, "St" street suffix,
                    street suffix ending with ".", full name for state/province,
                    single-word country name

            Joshua: initial for middle name (no "."), 3-digit address #,
                    no lowercase/uppercase restriction on street name

            Bilbo: initial middle name (with "."), multi-word address name,
                    multi-word state/province name, dash in country name
        */


        Scanner scnr = new Scanner(System.in);

        // Collect user information
        String name = scnr.nextLine().trim();
        String address = scnr.nextLine().trim();
        String phoneNum = scnr.nextLine().trim();

        // Concatenate pieces of info into a single string
        String form = "";

        // These will be used to tell if there were any matching errors
        boolean name_matched = new BasicParseRunner<>(parser.FullName()).run(name).matched;
        boolean add_matched = new BasicParseRunner<>(parser.Address()).run(address).matched;
        boolean num_matched = new BasicParseRunner<>(parser.PhoneNumber()).run(phoneNum).matched;

        // See which parts of input were correct; if correct then concatenate them to the form, else display error
        if (name_matched) {form += name;}
        else {System.out.println("Something was wrong with the name and it couldn't be processed :(");}
        if (add_matched) {form += name_matched ? "\n"+address : address;}
        else {System.out.println("Something was wrong with the address and it couldn't be processed :(");}
        if (num_matched) {form += add_matched || name_matched ? "\n"+phoneNum : phoneNum;}
        else {System.out.println("Something was wrong with the number and it couldn't be processed :(");}

        // Run the parser on the entire form
        result = new BasicParseRunner<>(parser.Form()).run(form);
        parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
        System.out.println(parseTreePrintOut);


        // Example inputs and loop for automatic testing
        /*
        String[] inputs = {
                "John Doe\n3609 Mynders Ave, Memphis, TN, United States of America\n(901) 345-9834",
                "Mary Jane Helen Smith\n82 Main St., Toronto, Ontario, Canada\n(416) 876-1234",
                "Joshua R. Clemens\n982 pryor st, Little Rock, AR, USA\n(501) 876-1234",
                "Bilbo B Baggett\n379 Prancing Pony Pkwy, The Shire, Eriador, Middle-earth\n(901) 678-3044",
                "John\n323 King Pkwy, Wondon, Eriador, Mars\n(238) 333-2371",
                "NaRyah Lasha Marie Lester\n2691 Campus Postal St., Memphis, TN, United States of America\n(256) 642-6244",
                "dsjafosdj\n32 Cedar Dr., Memphis, TN, USA\n432"
        };

        String[] inputs = {
          "NaRyah Lasha Marie Lester\n2691 Campus Postal St., Memphis, TN, United States of America\n(256) 642-6244",
          "dsjafosdj\n32 Cedar Dr., Memphis, TN, USA\n432"
        };

        for (String form : inputs) {
            try {
                result = new BasicParseRunner(parser.Form()).run(form);
                parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
                System.out.println(parseTreePrintOut);
            }
            catch (Exception e) {
                System.out.println("Something was wrong with your input :(");
            }
        }
        */
    }
}
package org.jibril;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree

public class AddressParser extends BaseParser<Object> {
    Rule Address() {
        return Sequence(
                StAddress(),
                Sep(),
                City(),
                Sep(),
                ZipCode(),
                Sep(),
                State(),
                Sep(),
                Country()
        );
    }

    Rule StAddress() {
        return Sequence(
                StreetNum(),
                Ch(' '),
                StreetName()
        );
    }

    Rule Sep() {
        return String(", ");
    }

    Rule StreetNum() {
        return OneOrMore(Num());
    }

    Rule Num() {
        return CharRange('0', '9');
    }

    Rule StreetName() {
        return Sequence(
                Ident(),
                Ch(' '),
                FirstOf(
                        StreetSuffix(),
                        Optional(Ident(), Ch(' '))
                )
        );
    }

    Rule StreetSuffix() {
        return FirstOf(
                IgnoreCase("Ave"),
                IgnoreCase("Pkwy"),
                IgnoreCase("Blvd"),
                IgnoreCase("Road"),
                IgnoreCase("Cv"),
                IgnoreCase("Pl"),
                IgnoreCase("St"),
                IgnoreCase("Ln"));
    }

    Rule City() {
        return Location();
    }

    Rule Country() {
        return Location();
    }

    Rule Ident() {
        return Sequence(
                Cap(),
                OneOrMore(Lower())
        );
    }

    Rule Cap() {
        return CharRange('A', 'Z');
    }

    Rule Lower() {
        return CharRange('a', 'z');
    }

    Rule ZipCode() {
        return Sequence(
                Num(), Num(), Num(), Num(), Num()
        );
    }

    Rule State() {
        return Sequence(
                Cap(), Cap()
        );
    }

    Rule Location() {
        return OneOrMore(
                FirstOf(
                    Ident(),
                    String("of")),
                    Optional(Ch(' '))
        );
    }

}

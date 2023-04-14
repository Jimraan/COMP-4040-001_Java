package org.jibril;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree

public class FullAddressParser extends BaseParser<Object> {
    public Rule Address() {
        return Sequence(
                StAddress(),
                Sep(),
                City(),
                Sep(),
                State_Province(),
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
                OneOrMore(LazyIdent(), Ch(' ')),
                StreetSuffix()
        );
    }

    Rule StreetSuffix() {
        return Sequence(
                FirstOf(
                    IgnoreCase("Ave"),
                    IgnoreCase("Pkwy"),
                    IgnoreCase("Blvd"),
                    IgnoreCase("Road"),
                    IgnoreCase("Cv"),
                    IgnoreCase("Pl"),
                    IgnoreCase("St"),
                    IgnoreCase("Ln")
                ),
                Optional(Ch('.'))
        );
    }

    Rule City() {
        return Location();
    }

    Rule Country() {
        return Location();
    }

    Rule LazyIdent() {
        return OneOrMore(Letter());
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

    Rule Letter() { return CharRange('A', 'z'); }

    Rule State_Province() {
        return FirstOf(
                State(),
                Location()
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
                    LazyIdent(),
                    String("of"),
                    Ch(' '),
                    Ch('-')
                )
        );
    }

}

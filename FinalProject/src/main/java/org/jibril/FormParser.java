package org.jibril;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree
public class FormParser extends BaseParser<Object> {
    Rule Form() {
        return Sequence(
                FullName(),
                NewLine(),
                Address(),
                NewLine(),
                PhoneNumber()
        );
    }

    Rule NewLine() {
        return Ch('\n');
    }

    /* Rules for names */
    public Rule FullName() {
        return Sequence(
                FirstName(),
                Space(),
                Optional(
                        Optional(
                                OneOrMore(MiddleNames())
                        )
                ),
                LastName()
        );
    }
    Rule FirstName() {
        return Name();
    }
    Rule MiddleNames() {
        return Sequence(
                Name(), Space()
        );
    }
    Rule LastName() {
        return Name();
    }
    Rule Space() {
        return Ch(' ');
    }
    Rule Name() {
        return Sequence(
                Cap(),
                (Optional(FirstOf(Ch('.'), OneOrMore(Lower()))))
        );
    }
    Rule Cap() {
        return CharRange('A', 'Z');
    }
    Rule Lower() {
        return CharRange('a', 'z');
    }

    Rule Letter() { return CharRange('A', 'z'); }

    /* Rules for addresses */
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
    Rule Ident() {
        return Sequence(
                Cap(),
                OneOrMore(Lower())
        );
    }
    Rule LazyIdent() {
        return OneOrMore(Letter());
    }
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
                        Ident(),
                        LazyIdent(),
                        String("of"),
                        Ch(' '),
                        Ch('-')
                )
        );
    }


    /* Rules for phone numbers */
    Rule PhoneNumber() {
        return Sequence(
                AreaCode(),
                Ch(' '),
                Prefix(),
                Dash(),
                Suffix()
        );
    }
    Rule AreaCode() {
        return Sequence(
                Ch('('),
                Num(),
                Num(),
                Num(),
                Ch(')')
        );
    }
    Rule Prefix() {
        return Sequence(
                Num(),
                Num(),
                Num()
        );
    }
    Rule Dash() {
        return Ch('-');
    }
    Rule Suffix() {
        return Sequence(
                Num(),
                Num(),
                Num(),
                Num()
        );
    }
}

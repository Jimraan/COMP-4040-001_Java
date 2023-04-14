package org.jibril;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
@BuildParseTree

public class FullNameParser extends BaseParser<Object> {

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
}

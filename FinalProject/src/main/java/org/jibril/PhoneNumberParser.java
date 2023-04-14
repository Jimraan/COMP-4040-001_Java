package org.jibril;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

@BuildParseTree
public class PhoneNumberParser extends BaseParser<Object> {
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

    Rule Num() {
        return CharRange('0', '9');
    }
}

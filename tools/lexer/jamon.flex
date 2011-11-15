package org.jamon.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.diagnostic.Logger;

/**
 * This class was generated using JFlex on the jamon.flex file. Do not modify it directly.
 */
%%
%{
    public JamonFlexLexer() {
		this((java.io.Reader)null);
    }
	private static final Logger LOGGER = Logger.getInstance(JamonFlexLexer.class.getSimpleName());
%}
%class JamonFlexLexer
%implements FlexLexer
%function advance
%type IElementType
%eof{	return;
%eof}
%unicode

NEWLINE = \r|\n|\r\n
WHITESPACE     = {NEWLINE} | [ \t\f]
PERCENT_TAG_OPEN = "<%"
PERCENT_TAG_END = "%>"
PERCENT_TAG_CLOSE = "</%"
TAG_NAME = [:letter:]+

%state PERCENT_TAG
%state PERCENT_TAG_CLOSE
%state TAG_CONTENT
%state TAG_EMIT
%state TAG_ARGS

%%
<YYINITIAL> {
    {PERCENT_TAG_OPEN} {TAG_NAME} ">"   { return JamonTokenTypes.JAMON_PERCENT_TAG; }
    {PERCENT_TAG_OPEN} {TAG_NAME}       { yybegin(PERCENT_TAG); yypushback(yylength()); }
    {PERCENT_TAG_OPEN} {WHITESPACE}     { yybegin(TAG_EMIT); yypushback(yylength()); }
    {PERCENT_TAG_CLOSE} {TAG_NAME} ">"  { return JamonTokenTypes.JAMON_PERCENT_TAG_CLOSE; }
}

<PERCENT_TAG> {
    {PERCENT_TAG_OPEN} {TAG_NAME} {WHITESPACE}          { return JamonTokenTypes.JAMON_PERCENT_TAG; }
    ">"                                                 { yybegin(YYINITIAL); return JamonTokenTypes.GENERIC_TAG_END; }
    {PERCENT_TAG_END}                                   { yybegin(YYINITIAL); return JamonTokenTypes.PERCENT_TAG_END; }
    .                                                 { return JamonTokenTypes.TAG_ARGS; }
}

<TAG_EMIT> {
    {PERCENT_TAG_OPEN}  { return JamonTokenTypes.PERCENT_TAG_START; }
    {PERCENT_TAG_END}   { yybegin(YYINITIAL); return JamonTokenTypes.PERCENT_TAG_END; }
    .                   { return JamonTokenTypes.OUTPUT; }
}

.|\n                { return JamonTokenTypes.BAD_CHARACTER; }

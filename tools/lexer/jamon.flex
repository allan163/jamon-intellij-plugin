package org.jamon.intellij.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

/**
 * This class was generated using JFlex on the jamon.flex file. Do not modify it directly.
 */
%%

%{
	public JamonLexer() {
		this((java.io.Reader)null);
	}
%}
%class JamonLexer
%implements FlexLexer
%function advance
%type IElementType
%eof{	return;
%eof}
%unicode

LETTER = [:letter:]
DIGIT = [0-9]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
JAMON_TAG_START_1 = "<%args>"
JAMON_TAG_CLOSE_1 = "</%args>"

%%
<YYINITIAL> {
    {WhiteSpace} { }
}
. { return JamonElementTypes.JAMON_ANYTHING; }
//{JAMON_TAG_START_1} { return JamonElementTypes.JAMON_ARGS_START; }
//{JAMON_TAG_CLOSE_1} {return JamonElementTypes.JAMON_ARGS_END; }

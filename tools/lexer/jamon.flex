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
WHITESPACE = [ \n\r\t\f]+
JAMON_TAG_START_1 = "<%"
JAMON_TAG_CLOSE_1 = "</%"
JAMON_TAG_END = ">"

%state ARGS



%%

<YYINITIAL> {JAMON_TAG_START_1} "args" {JAMON_TAG_END} { yybegin(ARGS); return JamonElementTypes.JAMON_ARGS_START; }
<ARGS> {JAMON_TAG_CLOSE_1} "args" {JAMON_TAG_END} { yybegin(YYINITIAL); return JamonElementTypes.JAMON_ARGS_END; }
<ARGS> [^\<]* {return JamonElementTypes.JAMON_ARGS_CONTENT; }
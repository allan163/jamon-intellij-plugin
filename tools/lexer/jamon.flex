package org.jamon.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.diagnostic.Logger;
import org.jamon.intellij.lang.element.JamonElementTypes;
import org.jamon.intellij.lang.element.JamonElement;

/**
 * This class was generated using JFlex on the jamon.flex file. Do not modify it directly.
 */
%%
%{
    public JamonLexer() {
		this((java.io.Reader)null);
    }
	private static Logger LOGGER = Logger.getInstance(JamonLexer.class.getSimpleName());
	private StringBuffer output = new StringBuffer();
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
WHITESPACE     = {LineTerminator} | [ \t\f]
TAG_START_PERCENT = "<%"
TAG_END_PERCENT = "%>"
TAG_CLOSE_PERCENT = "</%"

%state PERCENT_TAG
%state TAG_CONTENT
%state TAG_EMIT
%state TAG_ARGS

%%
<YYINITIAL> {
    {WHITESPACE} { }
}
//{TAG_START_PERCENT} { yybegin(PERCENT_TAG); return JamonTokenTypes.TAG_START_PERCENT;}
//<PERCENT_TAG> {
////    ">"             { yybegin(YYINITIAL);    return JamonTokenTypes.GENERIC_TAG_END; }
//    "args>"         { output.setLength(0); yybegin(TAG_CONTENT);  return JamonElementTypes.ARGS_START;    }
//    "import>"       { output.setLength(0); yybegin(TAG_CONTENT);  return JamonElementTypes.IMPORT_START;  }
//
//    {WHITESPACE}    { output.setLength(0); yybegin(TAG_EMIT); return JamonTokenTypes.WHITE_SPACE; }
//
//    <TAG_EMIT> {
//        {TAG_END_PERCENT}   { yybegin(YYINITIAL); return new JamonElement("EMIT").setRawContent(output.toString()); }
//        .                   { output.append( yytext() ); }
//    }
//
//    <TAG_CONTENT> {
//        {TAG_CLOSE_PERCENT} { yybegin(PERCENT_TAG); return new JamonElement("TAG_CLOSE_PERCENT").setRawContent(output.toString()); }
//        .                   { output.append( yytext() ); }
//    }
//    // todo: capture end percent tag
//}

// Bad things happened.
. {
//    LOGGER.warn("Illegal character <" + yytext() + ">");
    return JamonTokenTypes.BAD_CHARACTER;
    }

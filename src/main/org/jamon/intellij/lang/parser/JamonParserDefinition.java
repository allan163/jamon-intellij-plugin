package org.jamon.intellij.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.xml.util.XmlUtil;
import org.jamon.intellij.lang.JamonWrapperPsiElement;
import org.jamon.intellij.lang.element.JamonElementTypes;
import org.jamon.intellij.lang.file.JamonFile;
import org.jamon.intellij.lang.file.JamonFileElementType;
import org.jamon.intellij.lang.file.JamonFileType;
import org.jamon.intellij.lang.highlighter.JamonSyntaxHighlighter;
import org.jamon.intellij.lang.lexer.JamonLexer;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:28 PM
 */
public class JamonParserDefinition implements ParserDefinition {
    public static final IFileElementType JAMON_FILE =
            new JamonFileElementType(JamonFileType.JAMON_LANGUAGE);

    @NotNull
    public Lexer createLexer(Project project) {
        return new JamonLexer();
    }

    public PsiParser createParser(Project project) {
        return new JamonParser();
    }

    public IFileElementType getFileNodeType() {
        return JAMON_FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return TokenSet.create(JamonSyntaxHighlighter.WHITE_SPACE);
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return TokenSet.create(JamonSyntaxHighlighter.COMMENT);
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
//        final IElementType type = node.getElementType();

//        if (type == JamonElementTypes.JAMON_ARGS_START) {
//            return new JamonArgsImpl(node);
//        }

        return new JamonWrapperPsiElement(node);
    }

    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new JamonFile(fileViewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        final Lexer lexer = createLexer(left.getPsi().getProject());
        return XmlUtil.canStickTokensTogetherByLexerInXml(left, right, lexer, 0);
    }
}

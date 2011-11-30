package org.jamon.intellij.lang.element;

import com.intellij.lang.*;
import com.intellij.lang.html.HTMLParser;
import com.intellij.lexer.HtmlLexer;
import com.intellij.openapi.project.Project;
import com.intellij.peer.PeerFactory;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.templateLanguages.OuterLanguageElement;
import com.intellij.psi.templateLanguages.OuterLanguageElementImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILazyParseableElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:55 PM
 */
public interface JamonElementTypes {
    IElementType JAMON_ELEMENT = new JamonElement("JamonElement");
    IElementType OUTPUT_ELEMENT = new OutputElement("");
    ILazyParseableElementType OUTPUT = new ILazyParseableElementType("OUTPUT") {
        @NotNull
        @Override
        public Language getLanguage() {
            return StdLanguages.HTML;
        }

        @Override
        public ASTNode parseContents(ASTNode chameleon) {
            final PeerFactory factory = PeerFactory.getInstance();
            final PsiElement parentElement = chameleon.getTreeParent().getPsi();
            final Project project = JavaPsiFacade.getInstance(parentElement.getProject()).getProject();

            final PsiBuilder builder = factory.createBuilder(chameleon, new HtmlLexer(), getLanguage(), chameleon.getText(), project);
            final PsiParser parser = new HTMLParser();

            return parser.parse(this, builder).getFirstChildNode();
        }

        @Override
        public ASTNode createNode(CharSequence text) {
            return new OutputElementImpl(text);
        }
    };
}

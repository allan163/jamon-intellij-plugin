package org.jamon.intellij.proxy;

import org.jamon.api.ParsedTemplate;
import org.jamon.api.TemplateParser;
import org.jamon.intellij.proxy.exception.ProxyException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 7/31/11
 * Time: 1:02 AM
 */
public class TemplateProcessorHandler implements InvocationHandler, TemplateParser {
    public TemplateProcessorHandler() throws ProxyException {

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke called");
//        return method.invoke(proxy, args);
        return null;
    }

    public ParsedTemplate parseTemplate(String s) throws IOException {
        System.out.println("parseTemplate called");
        return null;
    }
}

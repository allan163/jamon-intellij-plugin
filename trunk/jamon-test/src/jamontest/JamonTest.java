package jamontest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: castral
 * Date: 7/28/11
 * Time: 9:21 PM
 */
public class JamonTest {
    public static void main(String[] args) throws IOException {
        JamonTestTemplate template = new JamonTestTemplate();
        template.render(new OutputStreamWriter(System.out), new Date(0), args);
    }
}

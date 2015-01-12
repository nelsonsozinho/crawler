package com.crawler.service.parser;

import com.crawler.service.model.Resource;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nelson.sozinho on 27/06/2014.
 */
public class AbstractHTMLTextParserTest {

    private Response response;
    private Document doc;
    private Resource resource;

    @Before
    public void prepare() {
        Response response = null;
        Document doc = null;

        try {
            response = Jsoup.connect("http://www.terra.com.br").execute();
            doc = response.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        resource = new Resource();
        resource.setText(doc.html());
        resource.setTitle("Test Title");
        resource.setUrl("http://www.terra.com.br");
    }

    @Test
    public void extratTitleFromTitleTag() {
        Parser parser = new HTMLTextParser(resource);
        List<String> elements = parser.extractContentFronTag("title", false);
        assertNotNull(elements);
        assertFalse(elements.isEmpty());
    }

    @Test
    public void withMetaTags() {
        Parser parser = new HTMLTextParser(resource);
        List<String> elements = parser.extractContentFronTag("meta", true);
        String element = elements.get(0);

        assertNotNull(elements);
        assertFalse(elements.isEmpty());
        assertTrue(element.contains("<") || element.contains(">"));
    }

    @Test
    public void withoutMetaTags() {
        Parser parser = new HTMLTextParser(resource);
        List<String> elements = parser.extractContentFronTag("body", false);
        String element = elements.get(0);

        assertNotNull(elements);
        assertFalse(elements.isEmpty());
        assertFalse(element.contains("<") || element.contains(">"));
    }

}

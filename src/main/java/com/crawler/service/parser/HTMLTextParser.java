package com.crawler.service.parser;

import com.crawler.service.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nelson.sozinho on 26/06/2014.
 */
public class HTMLTextParser implements Parser {

    private Resource resource;
    private Document document;

    public HTMLTextParser(Resource resource) {
        this.resource = resource;
        parserJson();
    }

    private void parserJson() {
        document = Jsoup.parse(resource.getText());
    }

    @Override
    public List<String> extractContentFronTag(String tag, boolean withTag) {
        List<String> out = new ArrayList<String>();
        Elements elements = this.document.getElementsByTag(tag);
        if(withTag) {
            for (Element element : elements) {
                out.add(element.toString());
            }
        }
        else {
            for (Element element : elements) {
                out.add(refineOut(element));
            }
        }
        return out;
    }

    private String refineOut(Element element) {
        String text = element.text();
        return text;
    }
}

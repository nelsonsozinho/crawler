package com.crawler.service.parser;

import java.util.List;

/**
 * Created by nelson.sozinho on 26/06/2014.
 */
public interface Parser {

    public List<String> extractContentFronTag(String tag, boolean withTag);

}

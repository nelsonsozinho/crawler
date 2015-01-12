package com.crawler.service.filter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.crawler.service.util.UrlUtils;

public class UrlUtilTest {

	@Test
	public void testGetDomain() {
		String url = "http://www.terra.com.br";
		String response = UrlUtils.getDomain(url);
		assertEquals(response, "terra");
	}
	
	@Test
	public void testGetDomainWithUrlWithoutDotBr() {
		String url = "http://www.cnn.com";
		String response = UrlUtils.getDomain(url);
		assertEquals(response, "cnn");
	}
	
	@Test
	public void testGetDomainWithServiceAccess() {
		String url = "http://www.cnn.com/test.do";
		String response = UrlUtils.getDomain(url);
		assertEquals(response, "cnn");
	}
	
	//@Test
	public void testGetDomainWithSubdomain() {
		String url = "http://webmail.fpf.br";
		String response = UrlUtils.getDomain(url);
		assertEquals(response, "webmail.fpf");
	}
	
	@Test
	public void testGetDomainWithoutWWW() {
		String url = "http://bbc.com";
		String response = UrlUtils.getDomain(url);
		assertEquals(response, "bbc");
	}

	@Test
	public void testGetDomainWithoutWWW2() {
		String url = "http://terra.com.br";
		String response = UrlUtils.getDomain(url);
		assertEquals(response, "terra");
	}
	
	@Test
	public void testGetSimpleUrlPath() {
		String url = "http://stackoverflow.com/questions/27745/getting-parts-of-a-url-regex/trr.php";
		String response = UrlUtils.getPath(url);
		assertEquals(response, "/questions/27745/getting-parts-of-a-url-regex/trr.php");
	}
	
	@Test
	public void testGetCompleteUrlPathFromYahooFinances() {
		String url = "http://finance.yahoo.com/blogs/breakout/3-sectors-to-play-in-the-coming-u-s--manufacturing-boom-143209688.html";
		String response = UrlUtils.getPath(url);
		assertEquals(response, "/blogs/breakout/3-sectors-to-play-in-the-coming-u-s--manufacturing-boom-143209688.html");
	}
	
	//@Test
	public void testGetCompleteUrlPathFromGoogleFinances() {
		String url = "https://www.google.com/finance?q=INDEXDJX%3A.DJI%2CINDEXSP%3A.INX%2CINDEXNASDAQ%3A.IXIC&ei=I0ioU9D3B_Tt6QHBpoHIDg";
		String response = UrlUtils.getPath(url);
		assertEquals(response, "/finance?q=INDEXDJX%3A.DJI%2CINDEXSP%3A.INX%2CINDEXNASDAQ%3A.IXIC&ei=I0ioU9D3B_Tt6QHBpoHIDg");
	}
	
}

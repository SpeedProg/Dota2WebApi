package tk.speedprog.dota2.webapi.aspects;

import java.io.InputStream;
import org.springframework.util.StopWatch;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.String;

public aspect ApiAspects {

/*	pointcut getStreamCall(String path):
		call(static * tk.speedprog.dota2.webapi.Dota2Api.getStreamFromApiUrl(String)) && args(path);

	InputStream around(String path) : getStreamCall(path) {
		StopWatch sw = new StopWatch();
		sw.start();
		InputStream ob = proceed(path);
		sw.stop();
		System.out.println("Getting the InputStream took "
				+ sw.getTotalTimeSeconds() + "s.");
		System.out.println("ApiUrl: " + path);
		return ob;
	}*/

/*	pointcut measureSaxParse(InputStream is, DefaultHandler dh) :
		call(public * javax.xml.parsers.SAXParser.parse(InputStream, DefaultHandler)) && args(is, dh);

	
	void around(InputStream is, DefaultHandler dh) : measureSaxParse(is, dh) {
		StopWatch parseWatch = new StopWatch();
		parseWatch.start();
		proceed(is, dh);
		parseWatch.stop();
		System.out.println("SaxParser.parse() took "
				+ parseWatch.getTotalTimeSeconds() + "s.");
	}
*/
/*	pointcut measureGetContent(String url) :
		call(static * tk.speedprog.dota2.webapi.Dota2Api.getContentFromApiUrl(String)) && args(url);

	String around(String url) : measureGetContent(url) {
		StopWatch sw = new StopWatch();
		sw.start();
		String s = proceed(url);
		sw.stop();
		System.out.println("Getting content of "+s.length()+" letters took: " + sw.getTotalTimeSeconds());
		return s;
	}*/
}

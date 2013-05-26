package de.waldmeisterundfreunde.frontiermanger.client;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gwt.core.client.EntryPoint;

import de.waldmeisterundfreunde.frontiermanger.parser.BlutrauschWarMapContentHandler;

public class Tasks implements EntryPoint {

	@Override
	public void onModuleLoad() {
		URL sourceURL;
		try {
			//prepare HTTP request
//			sourceURL = new URL("http://www.allianzdesblutes.de/tnt/wmap/");
//			HttpURLConnection sourceConnection = (HttpURLConnection) sourceURL.openConnection();
//			sourceConnection.setRequestProperty("Accept-Encoding", "gzip");
//			sourceConnection.connect();
//			InputStream resultingInputStream = null;
//			resultingInputStream = new GZIPInputStream(sourceConnection.getInputStream());
//			InputSource inputSource = new InputSource(resultingInputStream);
			
			FileReader fileReader = new FileReader("test.html");
			InputSource inputSource = new InputSource(fileReader);
			
			
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new BlutrauschWarMapContentHandler());
			reader.parse(inputSource);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//now the stream can be read directly,
		//and the data will be on the contentType received above.
	}

}

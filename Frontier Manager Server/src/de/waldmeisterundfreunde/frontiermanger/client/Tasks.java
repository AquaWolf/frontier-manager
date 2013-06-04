package de.waldmeisterundfreunde.frontiermanger.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.SAXParser;

import org.w3c.tidy.Tidy;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gwt.core.client.EntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import de.waldmeisterundfreunde.frontiermanger.parser.BlutrauschWarMapContentHandler;

public class Tasks extends HttpServlet {

	//	@Override
	//	public void onModuleLoad() {
	//		URL sourceURL;
	//		try {
	//			//prepare HTTP request
	////			sourceURL = new URL("http://www.allianzdesblutes.de/tnt/wmap/");
	////			HttpURLConnection sourceConnection = (HttpURLConnection) sourceURL.openConnection();
	////			sourceConnection.setRequestProperty("Accept-Encoding", "gzip");
	////			sourceConnection.connect();
	////			InputStream resultingInputStream = null;
	////			resultingInputStream = new GZIPInputStream(sourceConnection.getInputStream());
	////			InputSource inputSource = new InputSource(resultingInputStream);
	//			
	//			FileReader fileReader = new FileReader("test.html");
	//			InputSource inputSource = new InputSource(fileReader);
	//			
	//			
	//			XMLReader reader = XMLReaderFactory.createXMLReader();
	//			reader.setContentHandler(new BlutrauschWarMapContentHandler());
	//			reader.parse(inputSource);
	//		} catch (MalformedURLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (SAXException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//		//now the stream can be read directly,
	//		//and the data will be on the contentType received above.
	//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4059344303593461681L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {	
			FileReader fileReader = new FileReader("test.html");

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			Tidy tidy = new Tidy();		
			tidy.setMakeClean( true );   // Ohne Störungen
			tidy.setXmlTags( true );
			tidy.parse(fileReader,out);

			InputStream inputStream = new ByteArrayInputStream(out.toByteArray());
			InputSource inputSource = new InputSource(inputStream);

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
		//
	}

}

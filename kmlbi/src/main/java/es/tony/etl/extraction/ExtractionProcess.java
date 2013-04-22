package es.tony.etl.extraction;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import es.tony.configuration.ETLConf;
import es.tony.datewarehouseII.ETLProcess;

public class ExtractionProcess implements ETLProcess {

	private final static Logger log = Logger.getLogger(ExtractionProcess.class);
	double lat;
	double longi;

	public ExtractionProcess(double lat, double longi) {
		this.lat = lat;
		this.longi = longi;
	}

	public String doIt() {
		final WebClient webClient = new WebClient();
		String lat = String.valueOf(this.lat);
		String longi = String.valueOf(this.longi);
		String strFichero = null;
		HtmlPage page;
		try {
			Thread.sleep(30000); // We must be polite ;) !!
		} catch (InterruptedException e1) {
			log.error("Error sleep");
		}
		try {
			page = webClient
					.getPage("http://power.larc.nasa.gov/cgi-bin/cgiwrap/solar/timeseries.cgi"
							+ "?email=daily%40larc.nasa.gov&step=1&lat="
							+ lat
							+ "&lon= "
							+ longi
							+ "&ms=1&ds=1&ys="
							+ ETLConf.getMinYear()
							+ "&me=12&de=31&ye="
							+ ETLConf.getMaxYear()
							+ "&p=swv_dwn&"
							// "p=lwv_dwn&"
							// + "p=toa_dwn&" +
							// "p=avg_kt&p=clr_sky&"
							// + "p=clr_kt&" +
							// "p=PS&" +
							// "p=T2M&" +
							// "p=T2MN&"
							// + "p=T2MX&"
							// + "p=Q2M&"
							+ "p=RH2M&"
							+ "p=DFP2M&"
							+ "p=TSKIN&"
							+ "p=WS10M&"
							+ "p=RAIN&" + "submit=Submit&plot=swv_dwn");

			TextPage fichero;

			HtmlAnchor htmlLink = page.getAnchorByText("Download a text file");
			if (htmlLink != null) {
				fichero = (TextPage) htmlLink.click();
				WebResponse wr = fichero.getWebResponse();
				strFichero = wr.getContentAsString();
				webClient.closeAllWindows();
			}
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strFichero;
	}

}

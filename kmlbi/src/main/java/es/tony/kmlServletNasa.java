package es.tony;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import es.tony.businessLogic.KmlCreator;
import es.tony.businessLogic.MdxProcess;
import es.tony.domain.OlapData;
import es.tony.domain.RequestIF;
import es.tony.domain.RequestVO;

public class kmlServletNasa extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(kmlServletNasa.class);

	public kmlServletNasa() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestIF requestVal = new RequestVO();
		try {
			response.reset();
			MdxProcess.maxValue = -500;
			MdxProcess.minValue = 500;

			switch (Integer.parseInt(request.getParameter("q"))) {
			case 1:// Solar
				requestVal.setOperation("Solar_Radiation");
				break;
			case 2:// Dew Frost
				requestVal.setOperation("Dew_Frost");
				break;
			case 3:// Relative Humidity
				requestVal.setOperation("Relative_Humidity");
				break;
			case 4:// Wind Speed
				requestVal.setOperation("Wind_Speed");
				break;
			case 5:// Precipitation Average
				requestVal.setOperation("Precipitation");
				break;
			case 6:// Precipitation Average
				requestVal.setOperation("Temperature");
				break;
				
			default:
			
			}
			if (!request.getParameter("fYear").equals("-----"))
				requestVal.setAnyoFrom(Integer.parseInt(request
						.getParameter("fYear")));
			else {
				
				requestVal.setAnyoFrom(0);
			}

			if (!request.getParameter("tYear").equals("-----")) {
				
				requestVal.setAnyoTo(Integer.parseInt(request
						.getParameter("tYear")));
			} else {
				
				requestVal.setAnyoTo(0);
			}

			if (!request.getParameter("fMonth").equals("0"))
				requestVal.setMothFrom(Integer.parseInt(request
						.getParameter("fMonth")));
			else {
				
				requestVal.setMothFrom(0);
			}

			if (!request.getParameter("tMonth").equals("0"))
				requestVal.setMothTo(Integer.parseInt(request
						.getParameter("tMonth")));
			else {
				
				requestVal.setMothTo(0);
				
			}

			if (!request.getParameter("fDay").equals(""))
				requestVal.setFechaFrom(request.getParameter("fDay"));
			else {
				
				requestVal.setFechaFrom("");
			}

			if (!request.getParameter("tDay").equals(""))
				requestVal.setFechaTo(request.getParameter("tDay"));
			else {
				
				requestVal.setFechaTo("");
			}
			switch (Integer.parseInt(request.getParameter("aggr"))) {
			case 1:
				requestVal.setAggr("_Total");
				break;
			case 2:
				requestVal.setAggr("_Average");
				break;
			case 3:
				requestVal.setAggr("_Max");
				break;
			case 4:
				requestVal.setAggr("_Min");
				break;

			}

			ServletOutputStream sOutStream = response.getOutputStream();
			streamBinaryData(requestVal, sOutStream, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void streamBinaryData(RequestIF request,
			ServletOutputStream outstr, HttpServletResponse resp)
			throws IOException {
		String ErrorStr = null;

		try {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			MdxProcess mdxProcess = new MdxProcess(request);
			List<OlapData> resultados = mdxProcess.doIt();
			KmlCreator kmlCreator = new KmlCreator(resultados);
			File kmlFile = kmlCreator.doIt();

			int tam = (int) kmlFile.length();
			bis = new BufferedInputStream(new FileInputStream(kmlFile));

			try {
				// Asignamos el tipo de fichero zip
				resp.setContentType("application/vnd.google-earth.kml+xml"); // Cualquier
				// mime
				// type
				// Seteamos el tamaño de la respuesta
				resp.setContentLength(tam);

				bos = new BufferedOutputStream(outstr);

				// Bucle para leer de un fichero y escribir en el otro.
				byte[] array = new byte[1000];
				int leidos = bis.read(array);
				while (leidos > 0) {
					bos.write(array, 0, leidos);
					leidos = bis.read(array);
				}

			} catch (Exception e) {
				e.printStackTrace();
				ErrorStr = "Error Streaming the Data";
				outstr.print(ErrorStr);
			} finally {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
				if (outstr != null) {
					outstr.flush();
					outstr.close();
				}
			}

		} catch (Exception e) {
			System.out.println("Fichero no encontrado");
			resp.sendRedirect("fileNotFound.jsp");

		}

	}
}

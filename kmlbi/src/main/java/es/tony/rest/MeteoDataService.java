package es.tony.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import es.tony.businessLogic.MdxProcess;
import es.tony.domain.OlapData;
import es.tony.domain.RequestIF;
import es.tony.domain.RequestVO;


@Path("/json")
public class MeteoDataService {
	private final static Logger log = Logger.getLogger(MeteoDataService.class);
	@GET
	@Path("/{mode}/{aggr}/{operation}/{time}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OlapData> getOlapData1(@PathParam("aggr") String aggr,
			@PathParam("operation") String operation,
			@PathParam("time") String time) {
		MdxProcess.minValue = 500;
		MdxProcess.maxValue = -500;

		RequestIF request = new RequestVO();
		request.setOperation(translateMeasure(operation));
		request.setAnyoFrom(Integer.parseInt(time));
		request.setAnyoTo(0);
		request.setMothFrom(0);
		request.setMothTo(0);
		request.setFechaFrom("");
		request.setFechaTo("");
		request.setAggr(translateAggregation(aggr));

		MdxProcess mdxProcess = new MdxProcess(request);

		return mdxProcess.doIt();
	}

	@GET
	@Path("/{mode}/{aggr}/{operation}/{timeFrom}/{timeTo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OlapData> getOlapData2(@PathParam("mode") String mode,
			@PathParam("aggr") String aggr,
			@PathParam("operation") String operation,
			@PathParam("timeFrom") String timeFrom, @PathParam("timeTo") String timeTo) {

		// Test if mode is a number
		RequestIF request = new RequestVO();
		switch (Integer.parseInt(mode)) {
		case 1:// year:year
			request.setOperation(translateMeasure(operation));
			request.setAnyoFrom(Integer.parseInt(timeFrom));
			request.setAnyoTo(Integer.parseInt(timeTo));
			request.setMothFrom(0);
			request.setMothTo(0);
			request.setFechaFrom("");
			request.setFechaTo("");
			request.setAggr(translateAggregation(aggr));
			break;
		case 2:
			request.setOperation(translateMeasure(operation));
			request.setAnyoFrom(Integer.parseInt(timeFrom));
			request.setAnyoTo(0);
			request.setMothFrom(Integer.parseInt(timeTo));
			request.setMothTo(0);
			request.setFechaFrom("");
			request.setFechaTo("");
			request.setAggr(translateAggregation(aggr));
			break;
		case 3:
			request.setOperation(translateMeasure(operation));
			request.setAnyoFrom(0);
			request.setAnyoTo(0);
			request.setMothFrom(0);
			request.setMothTo(0);
			request.setFechaFrom(transformTime(timeFrom));
			request.setFechaTo(transformTime(timeTo));
			request.setAggr(translateAggregation(aggr));
			break;
		}

		MdxProcess mdxProcess = new MdxProcess(request);

		return mdxProcess.doIt();
	}

	private String transformTime(String time) {
		// mm.dd.yyyy -> mm/dd/yyyy
		String[] parts = time.split("\\.");
		return (parts[0] + "/" + parts[1] + "/" + parts[2]);
	}

	@GET
	@Path("/{mode}/{aggr}/{operation}/{timeFrom}/{timeTo}/{time}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OlapData> getOlapData3(@PathParam("mode") String mode,
			@PathParam("aggr") String aggr,
			@PathParam("operation") String operation,
			@PathParam("timeFrom") String timeFrom, @PathParam("timeTo") String timeTo,
			@PathParam("time") String time) {

		RequestIF request = new RequestVO();
		
		request.setOperation(translateMeasure(operation));
		request.setAnyoFrom(Integer.parseInt(timeFrom));
		request.setMothFrom(Integer.parseInt(timeTo));
		request.setMothTo(Integer.parseInt(time));
		request.setMothTo(0);
		request.setFechaFrom("");
		request.setFechaFrom("");
		request.setAggr(translateAggregation(aggr));

		MdxProcess mdxProcess = new MdxProcess(request);	

		return mdxProcess.doIt();
	}

	private String translateAggregation(String aggr) {

		if (aggr.equalsIgnoreCase("avg")) {
			return "_Average";
		} else if (aggr.equalsIgnoreCase("max")) {
			return "_Max";
		} else if (aggr.equalsIgnoreCase("min")) {
			return "_Min";
		} else if (aggr.equalsIgnoreCase("tot")) {
			return "_Total";
		} else {
			return "error";
		}
	}

	private String translateMeasure(String measure) {
		if (measure.equalsIgnoreCase("hsr")) {
			return "Solar_Radiation";
		} else if (measure.equalsIgnoreCase("pre")) {
			return "Precipitation";
		} else if (measure.equalsIgnoreCase("hum")) {
			return "Relative_Humidity";
		} else if (measure.equalsIgnoreCase("tem")) {
			return "Temperature";
		} else if (measure.equalsIgnoreCase("win")) {
			return "Wind_Speed";
		} else {
			return "error";
		}

	}

}

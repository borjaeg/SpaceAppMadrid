package es.tony.etl.extraction;

import es.tony.datewarehouseII.Command;
import es.tony.datewarehouseII.ETLProcess;
import es.tony.etl.transformation.TransformCommand;


public class ExtractCommand implements Command {

	ETLProcess etlP;

	public ExtractCommand(double lat, double longi) {
		this.etlP = new ExtractionProcess(lat,longi);
	}

	public void execute() {
		String respuesta = (String) etlP.doIt();
		if (respuesta != null)
			nextStep(respuesta);
		else
			System.out.println("Link no encontrado");
		
	}

	private void nextStep(String respuesta) {
		Command nextCommand = new TransformCommand(respuesta);
		nextCommand.execute();
	}

}

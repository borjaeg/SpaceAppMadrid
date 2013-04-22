package es.tony.etl.transformation;

import java.util.List;

import es.tony.auxiliar.Stage;
import es.tony.datewarehouseII.Command;
import es.tony.datewarehouseII.ETLProcess;
import es.tony.etl.load.LoadCommand;

public class TransformCommand implements Command {

	ETLProcess etlP;

	public TransformCommand(String datos) {
		this.etlP = new TransformProcess(datos);
	}

	public void execute() {
		List<Stage> datos = (List<Stage>) etlP.doIt();
		nextStep(datos);
	}

	private void nextStep(List<Stage> datos) {
		Command command = new LoadCommand(datos);
		command.execute();
	}

}

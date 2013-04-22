package es.tony.etl.load;

import java.util.List;

import es.tony.auxiliar.Stage;
import es.tony.datewarehouseII.Command;
import es.tony.datewarehouseII.ETLProcess;

public class LoadCommand implements Command {

	ETLProcess etlProcessSun;

	// ETLProcess etlProcessLoc;

	public LoadCommand(List<Stage> datos) {
		// etlProcessLoc = new LoadProcessLocation(datos);
		etlProcessSun = new LoadProcessMeteo(datos);

	}

	public void execute() {
		// etlProcessLoc.doIt();
		etlProcessSun.doIt();
	}

}

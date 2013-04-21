package es.tony.businessLogic;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.olap4j.Cell;
import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.Position;
import org.olap4j.PreparedOlapStatement;
import org.olap4j.layout.RectangularCellSetFormatter;

import es.tony.domain.OlapData;
import es.tony.domain.RequestIF;
import es.tony.mdx.MdxColumnDecorator;
import es.tony.mdx.MdxQuery;
import es.tony.mdx.MdxSlicerDecorator;
import es.tony.mdx.SimpleMdxQuery;
import es.tony.mdx.SimpleMdxRowsDecorator;

public class MdxProcess implements OlapProcess {

	private MdxQuery mdxQuery;

	Connection _connection;
	OlapConnection _olapConnection;
	private final static Logger log = Logger.getLogger(MdxProcess.class);
	RequestIF request;

	public static double minValue = 50;
	public static double maxValue = -50;
	public static String measure = "";

	public MdxProcess(RequestIF request) {
		try {
			Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
			_connection = DriverManager
					.getConnection("jdbc:mondrian:"
							+ "Jdbc='jdbc:postgresql://localhost:5432/MeteoDB';"
							+ "Catalog='file:/Users/b3j90/Documents/Tomcat/apache-tomcat-6.0.36/webapps/kmlbi/WEB-INF/classes/MeteoSchemaGeoHashing.xml';"
							+ "JdbcUser='postgres';"
							+ "JdbcPassword='password';"
							+ "JdbcDrivers=org.postgresql.Driver;");
			_olapConnection = _connection.unwrap(OlapConnection.class);
			this.request = request;
			mdxQuery = new MdxSlicerDecorator(new SimpleMdxRowsDecorator(
					new MdxColumnDecorator(new SimpleMdxQuery(),
							request.getAnyoFrom(), request.getAnyoTo(),
							request.getMonthFrom(), request.getMonthTo(),
							request.getFechaFrom(), request.getFechaTo())),
					request.getOperation(), request.getAggr());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<OlapData> doIt() {
		CellSet cellSet = null;
		OlapData olapData;
		List<OlapData> olapDataList = new ArrayList<OlapData>();
		this.measure = request.getOperation().replaceAll("_", " ");
		try {
			PreparedOlapStatement ps = _olapConnection
					.prepareOlapStatement(mdxQuery.buildMdxQuery());

			cellSet = ps.executeQuery();
		} catch (OlapException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Position rowPos : cellSet.getAxes().get(0)) {
			for (Position colPos : cellSet.getAxes().get(1)) {
				Cell cell = cellSet.getCell(rowPos, colPos);
				if (!cell.isEmpty()) {
					olapData = new OlapData();
					olapData.setSunRadiationAvg(Float.valueOf(cell.getValue()
							.toString()));
					if (this.maxValue < Float.valueOf(cell.getValue()
							.toString()))
						this.maxValue = Float.valueOf(cell.getValue()
								.toString());

					if (this.minValue > Float.valueOf(cell.getValue()
							.toString()))
						this.minValue = Float.valueOf(cell.getValue()
								.toString());

					if (request.getAnyoFrom() != 0) { // agregación año,
														// intervalo año a año,
						// o meses...
						if (request.getAnyoTo() != 0) { // intervarlo año a año
														// [year]:[year]
							// return generate(0);
							olapData.setFecha(rowPos.getMembers().get(1)
									.getName()
									+ "-01-01");
							olapData.setFechaSig(rowPos.getMembers().get(1)
									.getName()
									+ "-12-31");
						} else if (request.getMonthTo() != 0) { // intervalo mes
																// a mes

							olapData.setFecha(rowPos.getMembers().get(1)
									.getParentMember().getName()
									+ "-"
									+ getMesTotal(rowPos.getMembers().get(1)
											.getName()) + "-01");
							olapData.setFechaSig(rowPos.getMembers().get(1)
									.getParentMember().getName()
									+ "-"
									+ getMesTotal(rowPos.getMembers().get(1)
											.getName()) + "-31");
							// [year].[month]:[year]:[month]
						} else { // agregacion [year].[month] V [year]
							if (request.getMonthFrom() != 0) { // [year].[month]
								olapData.setFecha(rowPos.getMembers().get(1)
										.getParentMember().getName()
										+ "-"
										+ getMesTotal(rowPos.getMembers()
												.get(1).getName()) + "-01");
								olapData.setFechaSig(rowPos.getMembers().get(1)
										.getParentMember().getName()
										+ "-"
										+ getMesTotal(rowPos.getMembers()
												.get(1).getName()) + "-31");
								// return generate(2);
							} else {
								// [year]
								olapData.setFecha(rowPos.getMembers().get(1)
										.getName()
										+ "01-01");
								olapData.setFechaSig(rowPos.getMembers().get(1)
										.getName()
										+ "12-31");
							}
						}
					} else { // [day] V [day]:[day]
						if (request.getFechaTo() != null) { // [day] : [day]
							olapData.setFechaSig(rangoTemporalTotalSig(rowPos
									.getMembers().get(1).getParentMember()
									.getParentMember().getName(), rowPos
									.getMembers().get(1).getParentMember()
									.getName(), rowPos.getMembers().get(1)
									.getName()));

							olapData.setFecha(rangoTemporalTotal(rowPos
									.getMembers().get(1).getParentMember()
									.getParentMember().getName(), rowPos
									.getMembers().get(1).getParentMember()
									.getName(), rowPos.getMembers().get(1)
									.getName()));
						} else { // [day]

							olapData.setFecha(rangoTemporalTotal(rowPos
									.getMembers().get(1).getParentMember()
									.getParentMember().getName(), rowPos
									.getMembers().get(1).getParentMember()
									.getName(), rowPos.getMembers().get(1)
									.getName()));
							olapData.setFechaSig(rangoTemporalTotalSig(rowPos
									.getMembers().get(1).getParentMember()
									.getParentMember().getName(), rowPos
									.getMembers().get(1).getParentMember()
									.getName(), rowPos.getMembers().get(1)
									.getName()));

						}
					}

					olapData.setLatitude(Float.valueOf(colPos.getMembers()
							.get(0).getName()));
					olapData.setLongitude(Float.valueOf(colPos.getMembers()
							.get(1).getName()));
					olapDataList.add(olapData);

				} else {
					log.info("CAMPOS VACIOS");
				}

			}
		}
		return olapDataList;
	}

	private String rangoTemporalTotalSig(String anyo, String mes, String dia) {
		int diaInt = Integer.parseInt(dia);
		diaInt++;
		return anyo + "-" + getMesTotal(mes) + "-"
				+ getDiaTotal(String.valueOf(diaInt));

	}

	private String rangoTemporalTotal(String anyo, String mes, String dia) {
		return anyo + "-" + getMesTotal(mes) + "-" + getDiaTotal(dia);
	}

	private String getDiaTotal(String dia) {
		if (dia.length() < 2) {
			return "0" + dia;
		} else
			return dia;
	}

	private String getMesTotal(String mes) {
		if (mes.length() < 2) {
			return "0" + mes;
		} else
			return mes;
	}
}

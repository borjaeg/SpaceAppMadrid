package es.tony.mdx;

import org.apache.log4j.Logger;

import es.tony.kmlServletNasa;

public class MdxColumnDecorator extends MdxDecorator {

	private int yearFrom;
	private int yearTo;
	private int monthFrom;
	private int monthTo;
	private String fechaFrom;
	private String fechaTo;

	private final static Logger log = Logger
			.getLogger(MdxColumnDecorator.class);

	public MdxColumnDecorator(MdxQuery mdxCompleteQuery, int yearF, int yearT,
			int monthF, int monthT, String fecha1, String fecha2) {
		super(mdxCompleteQuery);
		this.yearFrom = yearF;
		this.yearTo = yearT;
		this.monthFrom = monthF;
		this.monthTo = monthT;
		this.fechaFrom = fecha1;
		this.fechaTo = fecha2;

	}

	public String buildMdxQuery() {
		return completeMdxQuery.buildMdxQuery() + addRowAxis();
	}

	private String parseData() {

		log.error("yearTO ->" + yearTo);
		log.error("monthTo->" + monthTo);
		// What kind of question is being done?
		if (yearFrom != 0) { // agregación año, intervalo año a año, o meses...
			if (yearTo != 0) { // intervarlo año a año [year]:[year]
				return generate(0);
			} else if (monthTo != 0) { // intervalo mes a mes
										// [year].[month]:[year]:[month]
				return generate(1);
			} else { // agregacion [year].[month] V [year]
				if (monthFrom != 0) // [year].[month]
					return generate(2);
				else
					// [year]
					return generate(3);
			}
		} else { // [day] V [day]:[day]
			if (fechaTo != null) { // [day] : [day]
				return generate(4);
			} else { // [day]
				generate(5);
			}
		}

		return null;

	}

	private String generate(int mode) {
		String query = "";
		switch (mode) {
		case 0: //
			query = " {[Time.Time Hierarchy].[" + String.valueOf(this.yearFrom)
					+ "]:[Time.Time Hierarchy].[" + String.valueOf(this.yearTo)
					+ "]} ";
			break;
		case 1: //
//			log.error("ERROR MONTHF" + monthFrom);
//			log.error("ERROR MONTHT" +monthTo);
			query = " {[Time.Time Hierarchy].[" + String.valueOf(this.yearFrom)
					+ "].[" + String.valueOf(this.monthFrom)
					+ "]:[Time.Time Hierarchy].[" + String.valueOf(this.yearFrom)
					+ "].[" + String.valueOf(this.monthTo) + "]} ";
			break;
		case 2:
			query = " {[Time.Time Hierarchy].[" + this.yearFrom + "].["
					+ this.monthFrom + "]}";
			break;
		case 3:
			query = "{[Time.Time Hierarchy].[" + this.yearFrom + "]} ";
			break;
		case 4: // [day]:[day]
			// reorder string format
			String[] parts = this.fechaFrom.split("/");
			String[] partsT = this.fechaTo.split("/");
			query = " {[Time.Time Hierarchy].[" + parts[2] + "].["
					+ getMes(parts[0]) + "].[" + getDia(parts[1])
					+ "]: [Time.Time Hierarchy].[" + partsT[2] + "].["
					+ getMes(partsT[0]) + "].[" + getDia(partsT[1]) + "]}";
			break;
		case 5:// [day]
			log.info("just one day");
			String[] parts3 = this.fechaFrom.split("/");
			query = " {[Time.Time Hierarchy].[" + getDia(parts3[2]) + "].["
					+ getMes(parts3[0]) + "].[" + getDia(parts3[1]) + "]}";
			break;
		default:
			log.error("Bad Chosen");

		}
		return query;

	}

	private String getMes(String mes) {
		if (mes.charAt(0) == '0')
			return mes.substring(1);
		else
			return mes;
	}

	private String getDia(String dia) {
		if (dia.charAt(0) == '0')
			return dia.substring(1);
		else
			return dia;
	}

	private String addRowAxis() {
		return parseData() + " ON COLUMNS,";
	}

}

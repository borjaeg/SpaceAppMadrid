package es.tony.mdx;

public class MdxRowDecorator extends MdxDecorator {

	private float longitudeFrom;
	private float latitudeFrom;
	private float latituteTo;
	private float longitudeTo;

	public MdxRowDecorator(MdxQuery mdxCompleteQuery, float latitudeFrom,
			float longitudeFrom, float latitudeTo, float longitudeTo) {
		super(mdxCompleteQuery);
		this.longitudeFrom = longitudeFrom;
		this.latitudeFrom = latitudeFrom;
		this.latituteTo = latitudeTo;
		this.longitudeTo = longitudeTo;
	}

	public String buildMdxQuery() {
		return completeMdxQuery.buildMdxQuery() + addRowAxis();
	}

	private String addRowAxis() {
		System.out.println(latLongFromTo());
		return "Crossjoin({" + latLongFromTo() + " ON ROWS "
				+ "FROM [SunRadiationCube] ";
	}

	private String latLongFromTo() {
		int numLat;
		int numLong;
		String query = "";
		if (this.latituteTo != 0 && this.longitudeTo != 0) {
			numLat = Math.round((latituteTo - latitudeFrom) * 10);
			System.out.println(numLat);
			numLong = Math.round((longitudeTo - longitudeFrom) * 10);
			String longi = null;
			for (int i = 0; i < numLong; i++) {
				// query += "[Longitude.Longitude hierarchy].[?],";
				longi = String.valueOf(longitudeFrom + (0.1f * i));
				if (longi.charAt(0) == '-') {
					longi = longi.substring(0, 4);
				} else {
					longi = longi.substring(0, 3);
				}
				if (longi.charAt(longi.length()-1) == '0') {
					longi = longi.substring(0, longi.length() - 2);
				}
				query += "[Longitude.Longitude hierarchy].[" + longi + "],";
			}
			query = query.substring(0, query.length() - 1);
			query += "},{";
			String lat;
			for (int i = 0; i < numLat; i++) {
				lat = String.valueOf(latitudeFrom + (0.1f * i));
				if (lat.charAt(0) == '-') {
					lat = lat.substring(0, 5);
				} else {
					lat = lat.substring(0, 4);
				}
				if (lat.charAt(lat.length()-1) == '0') {
					lat = lat.substring(0, lat.length() - 2);
				}
				query += "[Latitud.Latitude hierarchy].[" + lat + "],";
			}
			query = query.substring(0, query.length() - 1);
			query += "})";
			return query;
		} else {
			return "[Longitude.Longitude hierarchy].["
					+ String.valueOf(this.longitudeFrom)
					+ "]},{[Latitud.Latitude hierarchy].["
					+ String.valueOf(this.latitudeFrom) + "]})";
		}
	}
}

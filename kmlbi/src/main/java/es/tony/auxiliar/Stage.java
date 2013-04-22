package es.tony.auxiliar;

public class Stage implements StageIF {

	private String lat;
	private String longi;
	private String year;
	private String month;
	private String day;
	private String horizontalSolarRadiation;
	private String radiativeFlux;
	private String topInsolation;
	private String insolationClearness;
	private String clearSkyHorizontalSolarRadiation;
	private String clearSkyInsolationClearness;
	private String avgAtmosphericPreassure;
	private String AvgAirTemperature;
	private String MinAirTemperature;
	private String MaxAirTemperature;
	private String avgHumidityRadio;
	private String relativeHumidity;
	private String dewFrost;
	private String avgTemperature;
	private String windSpeed;
	private String precipitation;
	private String geoHash;
	private String elevation;
	private String climateZone;

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getLat()
	 */
	public String getLat() {
		return lat;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setLat(java.lang.String)
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getLongi()
	 */
	public String getLongi() {
		return longi;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setLongi(java.lang.String)
	 */
	public void setLongi(String longi) {
		this.longi = longi;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getYear()
	 */
	public String getYear() {
		return year;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setYear(java.lang.String)
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getMonth()
	 */
	public String getMonth() {
		return month;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setMonth(java.lang.String)
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getDay()
	 */
	public String getDay() {
		return day;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setDay(java.lang.String)
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getGeoHash()
	 */
	public String getGeoHash() {
		return geoHash;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setGeoHash(java.lang.String)
	 */
	public void setGeoHash(String geoHash) {
		this.geoHash = geoHash;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getElevation()
	 */
	public String getElevation() {
		return elevation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setElevation(java.lang.String)
	 */
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getClimateZone()
	 */
	public String getClimateZone() {
		return climateZone;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setClimateZone(java.lang.String)
	 */
	public void setClimateZone(String climateZone) {
		this.climateZone = climateZone;

	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getHorizontalSolarRadiation()
	 */
	public String getHorizontalSolarRadiation() {
		return horizontalSolarRadiation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setHorizontalSolarRadiation(java.lang.String)
	 */
	public void setHorizontalSolarRadiation(String horizontalSolarRadiation) {
		this.horizontalSolarRadiation = horizontalSolarRadiation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getRadiativeFlux()
	 */
	public String getRadiativeFlux() {
		return radiativeFlux;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setRadiativeFlux(java.lang.String)
	 */
	public void setRadiativeFlux(String radiativeFlux) {
		this.radiativeFlux = radiativeFlux;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getTopInsolation()
	 */
	public String getTopInsolation() {
		return topInsolation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setTopInsolation(java.lang.String)
	 */
	public void setTopInsolation(String topInsolation) {
		this.topInsolation = topInsolation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getInsolationClearness()
	 */
	public String getInsolationClearness() {
		return insolationClearness;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setInsolationClearness(java.lang.String)
	 */
	public void setInsolationClearness(String insolationClearness) {
		this.insolationClearness = insolationClearness;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getClearSkyHorizontalSolarRadiation()
	 */
	public String getClearSkyHorizontalSolarRadiation() {
		return clearSkyHorizontalSolarRadiation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setClearSkyHorizontalSolarRadiation(java.lang.String)
	 */
	public void setClearSkyHorizontalSolarRadiation(
			String clearSkyHorizontalSolarRadiation) {
		this.clearSkyHorizontalSolarRadiation = clearSkyHorizontalSolarRadiation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getClearSkyInsolationClearness()
	 */
	public String getClearSkyInsolationClearness() {
		return clearSkyInsolationClearness;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setClearSkyInsolationClearness(java.lang.String)
	 */
	public void setClearSkyInsolationClearness(
			String clearSkyInsolationClearness) {
		this.clearSkyInsolationClearness = clearSkyInsolationClearness;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getAvgAtmosphericPreassure()
	 */
	public String getAvgAtmosphericPreassure() {
		return avgAtmosphericPreassure;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setAvgAtmosphericPreassure(java.lang.String)
	 */
	public void setAvgAtmosphericPreassure(String avgAtmosphericPreassure) {
		this.avgAtmosphericPreassure = avgAtmosphericPreassure;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getAvgAirTemperature()
	 */
	public String getAvgAirTemperature() {
		return AvgAirTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setAvgAirTemperature(java.lang.String)
	 */
	public void setAvgAirTemperature(String avgAirTemperature) {
		AvgAirTemperature = avgAirTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getMinAirTemperature()
	 */
	public String getMinAirTemperature() {
		return MinAirTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setMinAirTemperature(java.lang.String)
	 */
	public void setMinAirTemperature(String minAirTemperature) {
		MinAirTemperature = minAirTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getMaxAirTemperature()
	 */
	public String getMaxAirTemperature() {
		return MaxAirTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setMaxAirTemperature(java.lang.String)
	 */
	public void setMaxAirTemperature(String maxAirTemperature) {
		MaxAirTemperature = maxAirTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getAvgHumidityRadio()
	 */
	public String getAvgHumidityRadio() {
		return avgHumidityRadio;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setAvgHumidityRadio(java.lang.String)
	 */
	public void setAvgHumidityRadio(String avgHumidityRadio) {
		this.avgHumidityRadio = avgHumidityRadio;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getRelativeHumidity()
	 */
	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setRelativeHumidity(java.lang.String)
	 */
	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getDewFrost()
	 */
	public String getDewFrost() {
		return dewFrost;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setDewFrost(java.lang.String)
	 */
	public void setDewFrost(String dewFrost) {
		this.dewFrost = dewFrost;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getAvgTemperature()
	 */
	public String getAvgTemperature() {
		return avgTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setAvgTemperature(java.lang.String)
	 */
	public void setAvgTemperature(String avgTemperature) {
		this.avgTemperature = avgTemperature;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getWindSpeed()
	 */
	public String getWindSpeed() {
		return windSpeed;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setWindSpeed(java.lang.String)
	 */
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#getPrecipitation()
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/* (non-Javadoc)
	 * @see auxiliar.StageIF#setPrecipitation(java.lang.String)
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

}

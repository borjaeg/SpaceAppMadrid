package es.tony.auxiliar;

public interface StageIF {

	public abstract String getLat();

	public abstract void setLat(String lat);

	public abstract String getLongi();

	public abstract void setLongi(String longi);

	public abstract String getYear();

	public abstract void setYear(String year);

	public abstract String getMonth();

	public abstract void setMonth(String month);

	public abstract String getDay();

	public abstract void setDay(String day);

	public abstract String getGeoHash();

	public abstract void setGeoHash(String geoHash);

	public abstract String getElevation();

	public abstract void setElevation(String elevation);

	public abstract String getClimateZone();

	public abstract void setClimateZone(String climateZone);

	public abstract String getHorizontalSolarRadiation();

	public abstract void setHorizontalSolarRadiation(
			String horizontalSolarRadiation);

	public abstract String getRadiativeFlux();

	public abstract void setRadiativeFlux(String radiativeFlux);

	public abstract String getTopInsolation();

	public abstract void setTopInsolation(String topInsolation);

	public abstract String getInsolationClearness();

	public abstract void setInsolationClearness(String insolationClearness);

	public abstract String getClearSkyHorizontalSolarRadiation();

	public abstract void setClearSkyHorizontalSolarRadiation(
			String clearSkyHorizontalSolarRadiation);

	public abstract String getClearSkyInsolationClearness();

	public abstract void setClearSkyInsolationClearness(
			String clearSkyInsolationClearness);

	public abstract String getAvgAtmosphericPreassure();

	public abstract void setAvgAtmosphericPreassure(
			String avgAtmosphericPreassure);

	public abstract String getAvgAirTemperature();

	public abstract void setAvgAirTemperature(String avgAirTemperature);

	public abstract String getMinAirTemperature();

	public abstract void setMinAirTemperature(String minAirTemperature);

	public abstract String getMaxAirTemperature();

	public abstract void setMaxAirTemperature(String maxAirTemperature);

	public abstract String getAvgHumidityRadio();

	public abstract void setAvgHumidityRadio(String avgHumidityRadio);

	public abstract String getRelativeHumidity();

	public abstract void setRelativeHumidity(String relativeHumidity);

	public abstract String getDewFrost();

	public abstract void setDewFrost(String dewFrost);

	public abstract String getAvgTemperature();

	public abstract void setAvgTemperature(String avgTemperature);

	public abstract String getWindSpeed();

	public abstract void setWindSpeed(String windSpeed);

	public abstract String getPrecipitation();

	public abstract void setPrecipitation(String precipitation);

}
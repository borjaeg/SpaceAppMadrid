Energy2People
=============

This project is solving the Renewable Energy Explorer challenge for the 2013 NASA International Space Apps Challenge.

Interactive Data visualization client for the selection of wealthy renewable resources areas. The application allows to the user the estimation of potential amount of energy in a specific location.


Renewable Energy FAQ
--------------------
### What is the purpose of Energy2People?
The aim of this website is to visualize several geophysical parameters collected by NASA that have a direct impact on renewable energies, and to explore suitable areas for the installation of power generation facilities.

### Which locations are available?
The data is limited to the Iberian Peninsula and have a spatial resolution of 1 degree (approximately 110 km). The data source has a worldwide coverage and additional countries may be added upon request.

### Which source data is available?
The collected data to be displayed on the map are: horizontal solar radiation, dew frost, relative humidity, wind speed, precipitation and temperature. The unitary temporal resolution is 1 day and you can aggregate data to produce monthly and yearly series as well using the input form.

### Why can´t I see data in some cases?
Not all the data are available for the complete temporal range. The following table shows the range for each geophysical parameter.
 * Horizontal solar radiation: Jul 1983 - present
 * Dew frost: Jan 1983 - present
 * Wind speed: Jan 1983 - present
 * Precipitation: Jan 1997 - Aug 2009
 * Relative humidity: Jan 1983 - present
 * Temperature: Jan 1983 - present

### Which energy parameters can I obtain?
Based on the source data a simple calculation is done to estimate the solar and wind energy at a given location proposed by the user. To achieve this the user gives the total surface of solar panels, the number of wind turbines and the type of facility to be installed. With these inputs the application calculates the energy generated per day, the number of homes that can be powered with this energy, the monetary value, the facilities cost and the time to recover the investment.


Dependencies
------------

### Production environment
 * [Apache Tomcat 1.6](http://tomcat.apache.org/)
 * [PostgreSQL 9.1](http://www.postgresql.org/)

### FrontEnd dependencies
 * [Google Earth API](https://developers.google.com/earth/)
 * [JQuery 1.7.1](http://jquery.com/)
 * [Bootstrap 2.3.1](http://twitter.github.io/bootstrap/)
 * [Amcharts 2.10.0](http://www.amcharts.com/)

### BackEnd dependencies
 * [Apache Maven 3.0.5](http://maven.apache.org/)
 * [JUnit 4.11](http://junit.org/)
 * [Apache Java Servlet 2.4](http://tomcat.apache.org/)
 * [JavaAPIforKml 2.2.0](https://code.google.com/p/javaapiforkml/)
 * [Apache log4j 1.2.17](http://logging.apache.org/log4j/1.2/)
 * [olap4j 1.0.1.539](http://www.olap4j.org/)
 * [mondrian 3.3.0.14701](http://mondrian.pentaho.com/)
 * [eigenbase 1.1.0.10924](http://www.eigenbase.org/)
 * [Json-simple 1.1](https://code.google.com/p/json-simple/)
 * [htmlunit 2.12](http://htmlunit.sourceforge.net/)
 * [ch.hsr.geohash](https://github.com/kungfoo/geohash-java)


Resources
---------
 * [GitHub MAIN Repo](https://github.com/borja3790/SpaceAppMadrid)
 * [GitHub Repo FrontEnd Code](https://github.com/rafinskipg/Energy2PeopleFront)
 * [FrontEnd Test Page](http://appsets.com/index.html)


Project Information
-------------------

### License
[Creative Commons BY-NC 3.0](http://creativecommons.org/licenses/by-nc/3.0/)


### Team
 * [Alberto Alcolea Ursua](https://github.com/albertoalcolea)
 * [Borja Espejo Garcia](https://github.com/borja3790)
 * [Rafael Pedrola](https://github.com/rafinskipg)
 * [Albert Zurita](https://github.com/albertzurita)
 * [Fernando Martin](https://github.com/fmartinp)
 * Enrique Perez Alonso
 * Pau Contreras de Luna
 * [Daniel Sanchez Seijo](https://github.com/Daniseijo)
 * [David Portilla Abellan](https://github.com/davidportilla)

 ### Rest API (json)

 * Aggregations ({aggr}):
 	* Average : avg
 	* Total   : tot
 	* Maximum : max
 	* Minimum : min

* Energy Parameters ({param}):
	* Horizontal Solar Radiation : hsr
	* Wind speed                 : win
	* Temperature                : tem
	* Relative Humidity          : hum
	* Dew & frost                : dew

* Time 
	* ([year_1]:[year_2]) -> https://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/{aggr}/{param}/year_1/year_2
	* ([year][month_1]:[year][month_2]) -> https://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/{aggr}/{param}/year/month_1/month_2
	* ([year_1][month_1][day_1]:[year_2][month_2][day_2]) -> https://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/{aggr}/{param}/(mm.dd.yyyy)_1/(mm.dd.yyyy)_2
	* ([year]) -> https://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/{aggr}/{param}/year

* Examples
	1. Horizontal Solar Radiation Average between 1995 and 2006 (year by year)
 	- http://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/avg/hsr/1995/2006

	2. Max. Speed wind between January and December in year 2007 (month by month)
	- http://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/max/win/2007/1/12

	3. Total Precipitation between 02.05.1995 and 07.22.2006 (day by day)
 	- http://abejamaya.cps.unizar.es/kmlbi/rest/v1/json/tot/pre/02.05.1995/07.22.2006


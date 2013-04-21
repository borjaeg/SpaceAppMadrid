package es.tony.mdx;

public class SimpleMdxRowsDecorator extends MdxDecorator {

	public SimpleMdxRowsDecorator(MdxQuery completeMdxQuery) {
		super(completeMdxQuery);
	}

	public String buildMdxQuery() {
		return completeMdxQuery.buildMdxQuery() + " Crossjoin({[Latitud.Latitude hierarchy].Children},{[Longitude.Longitude hierarchy].Children}) ON ROWS ";
	}

}

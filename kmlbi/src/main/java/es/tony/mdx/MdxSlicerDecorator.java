package es.tony.mdx;

public class MdxSlicerDecorator extends MdxDecorator {
	
	private String measure;
	private String slicer;

	public MdxSlicerDecorator(MdxQuery mdxCompleteQuery, String measure, String slicer) {
		super(mdxCompleteQuery);
		this.measure = measure;
		this.slicer = slicer;
	}

	public String buildMdxQuery() {
		return completeMdxQuery.buildMdxQuery() + addRowAxis();
	}

	private String addRowAxis() {
		return "FROM [MeteoCube] WHERE [Measures].["+ this.measure + this.slicer + "]";
	}

}

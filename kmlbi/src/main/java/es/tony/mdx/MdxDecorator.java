package es.tony.mdx;

public class MdxDecorator implements MdxQuery{
	
	protected MdxQuery completeMdxQuery;
	
	public MdxDecorator(MdxQuery completeMdxQuery){
		this.completeMdxQuery = completeMdxQuery;
	}

	public String buildMdxQuery() {
		return completeMdxQuery.buildMdxQuery();
	}

}

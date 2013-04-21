package es.tony.mdx;

import org.apache.log4j.Logger;

import es.tony.businessLogic.MdxProcess;

public class SimpleMdxQuery implements MdxQuery {

	private final static Logger log = Logger.getLogger(SimpleMdxQuery.class);
	
	public String buildMdxQuery() {
		return "SELECT ";
	}

}

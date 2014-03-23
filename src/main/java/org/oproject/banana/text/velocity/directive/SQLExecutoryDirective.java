/**
 * 
 */
package org.oproject.banana.text.velocity.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.oproject.banana.db.SqlExecutor;

import com.google.inject.Inject;

/**
 * SQLÖ´ÐÐºê
 * @author aohai.li
 */
public class SQLExecutoryDirective extends Directive {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(SQLExecutoryDirective.class);

	/**
	 * SQLÖ´ÐÐÆ÷
	 */
	@Inject
	private static SqlExecutor sqlExecutor;
	
	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getName()
	 */
	@Override
	public String getName() {
		return "executeSQL";
	}

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getType()
	 */
	@Override
	public int getType() {
		return LINE;
	}

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#render(org.apache.velocity.context.InternalContextAdapter, java.io.Writer, org.apache.velocity.runtime.parser.node.Node)
	 */
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException,
			MethodInvocationException {
		if(node.jjtGetChild(0) == null) {
			return false;
		}
		
		String sqlStatement = String.valueOf(node.jjtGetChild(0).value(context));
		if(log.isDebugEnabled()){
			log.debug("SQLºê²ÎÊý:" + sqlStatement);
		}

		String val = executeQuery(sqlStatement);
		
		if(log.isDebugEnabled()){
			log.debug("äÖÈ¾×Ö·û´®:" + val);
		}
		
		writer.write(val);
		return true;
	}

	protected String executeQuery(String sql){
		List<Map<String, Object>> retList = sqlExecutor.select(sql);
		
		if(retList.size() != 1){
			return "ER_LIST_SIZE";
		}
		
		Map<String, Object> retMap = retList.get(0);
		if(retMap.entrySet().size() != 1){
			return "ER_RESULT_SIZE";
		}
		
		return String.valueOf(retMap.entrySet().iterator().next().getValue());
	}
}

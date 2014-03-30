/**
 * 
 */
package org.oproject.banana.text.velocity.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;
import org.oproject.banana.db.SqlExecutor;

import com.google.inject.Inject;

/**
 * SQL执行宏
 * @author aohai.li
 */
public class SQLDeleteDirective extends Directive {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(SQLDeleteDirective.class);

	/**
	 * SQL执行器
	 */
	@Inject
	private static SqlExecutor sqlExecutor;
	
	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getName()
	 */
	@Override
	public String getName() {
		return "SQL_delete";
	}

	/* (non-Javadoc)
	 * @see org.apache.velocity.runtime.directive.Directive#getType()
	 */
	@Override
	public int getType() {
		return BLOCK;
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
		
		Node blockNode = node.jjtGetChild(0);
		
		if(!(blockNode instanceof ASTBlock)){
			throw new VelocityException("#SQL_delete 异常，参数类型必须是ASTBlock");
		}
		ASTBlock astBlock = (ASTBlock) blockNode;
		
		// 对语句块做一次render，这样让变量渲染后，能够以值的形式拼接到SQL语句块中
		StringWriter sqlStatementWriter = new StringWriter();
		astBlock.render(context, sqlStatementWriter);
		
		String sqlStatement = sqlStatementWriter.toString();

		if(log.isDebugEnabled()){
			log.debug("SQL宏参数:" + sqlStatement);
		}

		String val = executeQuery(sqlStatement);
		
		if(log.isDebugEnabled()){
			log.debug("渲染字符串:" + val);
		}
//		writer.write(val);
		return true;
	}

	protected String executeQuery(String sql){
		return String.valueOf(sqlExecutor.delete(sql));
	}
}

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
 * SQLִ�к�
 * @author aohai.li
 */
public class SQLDeleteDirective extends Directive {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(SQLDeleteDirective.class);

	/**
	 * SQLִ����
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
			throw new VelocityException("#SQL_delete �쳣���������ͱ�����ASTBlock");
		}
		ASTBlock astBlock = (ASTBlock) blockNode;
		
		// ��������һ��render�������ñ�����Ⱦ���ܹ���ֵ����ʽƴ�ӵ�SQL������
		StringWriter sqlStatementWriter = new StringWriter();
		astBlock.render(context, sqlStatementWriter);
		
		String sqlStatement = sqlStatementWriter.toString();

		if(log.isDebugEnabled()){
			log.debug("SQL�����:" + sqlStatement);
		}

		String val = executeQuery(sqlStatement);
		
		if(log.isDebugEnabled()){
			log.debug("��Ⱦ�ַ���:" + val);
		}
//		writer.write(val);
		return true;
	}

	protected String executeQuery(String sql){
		return String.valueOf(sqlExecutor.delete(sql));
	}
}

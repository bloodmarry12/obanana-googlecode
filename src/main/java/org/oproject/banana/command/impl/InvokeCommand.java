/**
 * 
 */
package org.oproject.banana.command.impl;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.oproject.banana.command.Command;
import org.oproject.banana.util.FileTool;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * ִ��
 * @author aohai.li
 */
public class InvokeCommand implements Command {
	
	/**
	 * ��־����
	 */
	private static final Logger logger = Logger.getLogger(InvokeCommand.class);
	
	/** �ű����� */
    private static final String SCRIPT_TYPE = "groovy";
	
    private static final ScriptEngineManager factory = new ScriptEngineManager();
	
    @Inject
    private static Injector injector;
    
    static{
    	factory.registerEngineName(SCRIPT_TYPE, new GroovyScriptEngineFactory());
    }
    
	/**
	 * @param filePath
	 * @param context
	 */
	public void invoke(String filePath, Map<String, Object> context){
		String fileContent = FileTool.readFileContent(filePath);
		try{
            ScriptEngine engine = factory.getEngineByName(SCRIPT_TYPE);
            engine.put("logger", logger);
            engine.put("context", context);
            engine.put("injector", injector);
            engine.eval(fileContent);
        }catch (RuntimeException re){
            logger.warn("ִ��ʧ��", re);
        } catch (ScriptException e) {
            logger.warn("ִ��ʧ��", e);
        }
	}
}

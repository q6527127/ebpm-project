package com.ebpm.common.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.WrappingTemplateModel;

/**
 * 自定义freeMark标签处理类
 * @author xiaodi
 */
@Component
public class FreemarkCustomLabelUtil implements TemplateDirectiveModel{
	/**
		environment ： 是环境变量，在这里我们可以拿到 通过  environment.getOut  拿到 Write 。
		map ：这里我们可以等到参数，这里的参数是以 键值对的形式存在的。
		templateModel ： 是所以数据类型的顶级接口，我们  可以通过  templateMole[i]  通过这个i变量来返回我们指定的第几个参数的返回值。
		templateBody ： 是标签开始和结束的 内容 ，通过这个对象的 render（）方法我可以接着执行 自定义标签里的 其他标签（freemarker内置标签或者我们自定义标签）
	 */
	@SuppressWarnings("all")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
		  	Writer out = env.getOut();
	        out.write("Akishimo num=" + params.get("num"));
	        List<String> list = new ArrayList<String>();
	        list.add("555555");
	        list.add("444444");
	        list.add("333333");
	        loopVars[0] = WrappingTemplateModel.getDefaultObjectWrapper().wrap(list);
	        body.render(env.getOut());
//	        if (body != null) {
//	            body.render(env.getOut());
//	        }else{
//	            throw new RuntimeException("标签内部至少要加一个空格");
//	        }
	  }

}

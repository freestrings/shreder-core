package com.freestrings.shreder;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.commons.io.IOUtils;

import com.freestrings.shreder.SpringRestApiProcessor;

public class SpringRequestMappingDocumentProcessorTestBase {

	String run(String path) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		String[] options = new String[] { "-implicit:none" };
		InputStream in = getClass().getClassLoader().getResource(path + ".java").openStream();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(new File(System.getProperty("java.io.tmpdir"))));
		JavaFileObject file = new JavaSourceFromString(path, IOUtils.toString(in));
		Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
		StringWriter sw = new StringWriter();
		CompilationTask task = compiler.getTask(sw, fileManager, null, Arrays.asList(options), null, compilationUnits);
		task.setProcessors(Collections.singleton(new SpringRestApiProcessor()));
		task.call();
		String json = sw.toString().substring(sw.toString().indexOf("{"));
		System.out.println(json);
		return json;
	}

	class JavaSourceFromString extends SimpleJavaFileObject {
		final String code;

		JavaSourceFromString(String name, String code) {
			super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			this.code = code;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return code;
		}
	}
}

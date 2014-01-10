package com.freestrings.shreder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic.Kind;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SupportedAnnotationTypes({ "org.springframework.web.bind.annotation.RequestMapping" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SRDocProcessor extends SpringRestApiProcessor {

	private FileWriter writer;

	@Override
	protected void beforeProcess(int count) {
		super.beforeProcess(count);
		try {
			writer = new FileWriter(new File("./result.sd"));
			writer.append("{\"version\": \"Shreder 1.0\", \"created\": " + System.currentTimeMillis() + ",");
			writer.write("\"apis\": [");
		} catch (IOException e) {
		}
	}

	@Override
	protected void afterProcess() {
		super.afterProcess();
		try {
			writer.append("] }");
			writer.flush();
		} catch (IOException e) {
		}
	}

	@Override
	protected void process(Map<String, Object> ret, boolean isLast) {
		processingEnv.getMessager().printMessage(Kind.NOTE, ret.get("patterns") != null ? ret.get("patterns").toString() : "?");

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
			String result = mapper.defaultPrettyPrintingWriter().writeValueAsString(ret);
			writer.append(result);
			if (!isLast) {
				writer.append(", ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

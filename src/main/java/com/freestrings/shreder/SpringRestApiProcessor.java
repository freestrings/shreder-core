package com.freestrings.shreder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic.Kind;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author freestrings
 * 
 */
@SupportedAnnotationTypes({ "org.springframework.web.bind.annotation.RequestMapping" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SpringRestApiProcessor extends AbstractProcessor {

	class TypeVisitorAdapter implements TypeVisitor<Void, DocElement> {

		public Void visit(TypeMirror t) {
			return null;
		}

		public Void visit(TypeMirror t, DocElement p) {
			return null;
		}

		public Void visitArray(ArrayType t, DocElement p) {
			return null;
		}

		public Void visitDeclared(DeclaredType t, DocElement p) {
			return null;
		}

		public Void visitError(ErrorType t, DocElement p) {
			return null;
		}

		public Void visitExecutable(ExecutableType t, DocElement p) {
			return null;
		}

		public Void visitNoType(NoType t, DocElement p) {
			return null;
		}

		public Void visitNull(NullType t, DocElement p) {
			return null;
		}

		public Void visitPrimitive(PrimitiveType t, DocElement p) {
			return null;
		}

		public Void visitTypeVariable(TypeVariable t, DocElement p) {
			return null;
		}

		public Void visitUnknown(TypeMirror t, DocElement p) {
			return null;
		}

		public Void visitWildcard(WildcardType t, DocElement p) {
			return null;
		}

	}

	class AnnotationScanner implements AnnotationValueVisitor<Void, Map<String, Object>> {

		private String name;

		public AnnotationScanner(String key) {
			this.name = key;
		}

		public Void visit(AnnotationValue av) {
			return null;
		}

		public Void visit(AnnotationValue av, Map<String, Object> p) {
			return null;
		}

		public Void visitAnnotation(AnnotationMirror a, Map<String, Object> p) {
			Map<String, Object> values = new HashMap<String, Object>();
			for (Entry<? extends ExecutableElement, ? extends AnnotationValue> e : a.getElementValues().entrySet()) {
				AnnotationValue value = (AnnotationValue) e.getValue();
				if (Collection.class.isAssignableFrom(value.getValue().getClass())) {
					value.accept(this, p);
				} else {
					values.put(e.getKey().getSimpleName().toString(), e.getValue().toString());
				}
			}
			p.put(a.getAnnotationType().asElement().getSimpleName().toString(), values);
			return null;
		}

		public Void visitArray(List<? extends AnnotationValue> vals, Map<String, Object> p) {
			for (int i = 0; i < vals.size(); i++) {
				Map<String, Object> values = new HashMap<String, Object>();
				AnnotationValue av = vals.get(i);
				av.accept(new AnnotationScanner(String.valueOf(i)), values);
				p.put(String.valueOf(i), values);
			}
			return null;
		}

		public Void visitBoolean(boolean b, Map<String, Object> p) {
			visitValue(b, p);
			return null;
		}

		public Void visitByte(byte b, Map<String, Object> p) {
			visitValue(b, p);
			return null;
		}

		public Void visitChar(char c, Map<String, Object> p) {
			visitValue(c, p);
			return null;
		}

		public Void visitDouble(double d, Map<String, Object> p) {
			visitValue(d, p);
			return null;
		}

		public Void visitEnumConstant(VariableElement c, Map<String, Object> p) {
			visitValue(c, p);
			return null;
		}

		public Void visitFloat(float f, Map<String, Object> p) {
			visitValue(f, p);
			return null;
		}

		public Void visitInt(int i, Map<String, Object> p) {
			visitValue(i, p);
			return null;
		}

		public Void visitLong(long i, Map<String, Object> p) {
			visitValue(i, p);
			return null;
		}

		public Void visitShort(short s, Map<String, Object> p) {
			visitValue(s, p);
			return null;
		}

		public Void visitString(String s, Map<String, Object> p) {
			visitValue(s, p);
			return null;
		}

		public Void visitType(TypeMirror t, Map<String, Object> p) {
			return null;
		}

		public Void visitUnknown(AnnotationValue av, Map<String, Object> p) {
			return null;
		}

		public Void visitValue(Object value, Map<String, Object> p) {
			p.put(this.name, value.toString().trim());
			return null;
		}

	}

	class DocElement {
		DocElement parent;
		String name = null;
		String rawType = null;
		String comment = null;
		String type = null;
		Map<String, Map<String, Object>> annotations = null;
		Map<String, Map<String, Object>> optionalAnnotations = null;
		List<DocElement> members = null;

		DocElement(String name) {
			this.name = name;
		}

		public void setRawType(String rawType) {
			this.rawType = rawType;
			if (Types.ARRAY.equals(rawType)) {
				this.type = "array";
			} else {
				this.type = Types.toJsonType(rawType);
			}
		}

		public void setRawType(String rawType, TypeMirror t) {
			this.rawType = rawType;
			if (Types.ARRAY.equals(rawType) || isInstanceOf(t, Collection.class)) {
				this.type = "array";
			} else if (isInstanceOf(t, Map.class)) {
				this.type = "map";
			} else if(!Types.isPrimitive(rawType) && isInstanceOf(t, Number.class)) {
				this.type = "number";
			} else {
				this.type = Types.toJsonType(rawType);
			}
		}

		public void setType(Element e) {
			if (e.getKind() == ElementKind.ENUM) {
				this.type = "enum";
			}
		}

		public void setVisited() {
			this.type = "ref";
		}

		public void setComment(String comment) {
			if (comment == null || this.comment != null) {
				return;
			}
			comment = comment.trim();
			this.comment = comment;
		}

		public void addAnnotation(String name, Map<String, Object> annotationInfo) {
			if (this.annotations == null) {
				this.annotations = new HashMap<String, Map<String, Object>>();
			}
			this.annotations.put(name, annotationInfo);
		}

		public void addAnnotations(Map<String, Map<String, Object>> annotationInfos) {
			if (annotationInfos == null || annotationInfos.isEmpty()) {
				return;
			}
			if (this.annotations == null) {
				this.annotations = new HashMap<String, Map<String, Object>>();
			}
			this.annotations.putAll(annotationInfos);
		}

		public void setOptionalAnnotations(Map<String, Map<String, Object>> optionalAnnotations) {
			if (optionalAnnotations != null && optionalAnnotations.isEmpty()) {
				return;
			}
			if (this.optionalAnnotations != null) {
				this.optionalAnnotations.putAll(optionalAnnotations);
			} else {
				this.optionalAnnotations = optionalAnnotations;
			}
		}

		public void addMembers(DocElement e) {
			if (this.members == null) {
				this.members = new ArrayList<DocElement>();
			}
			e.parent = this;
			members.add(e);
		}

		/**
		 * TODO temporary fix, it will be removed
		 */
		public void purgeMember() {
			if (this.members == null) {
				return;
			}

			List<DocElement> newMembers = new ArrayList<DocElement>();
			for (DocElement e : this.members) {
				if (newMembers.indexOf(e) == -1) {
					newMembers.add(e);
				}
			}
			this.members = newMembers;
		}

		public String getName() {
			return name;
		}

		public String getRawType() {
			return rawType;
		}

		public String getComment() {
			return comment;
		}

		public Map<String, Map<String, Object>> getAnnotations() {
			return annotations;
		}

		public Map<String, Map<String, Object>> getOptionalAnnotations() {
			return optionalAnnotations;
		}

		public List<DocElement> getMembers() {
			return members;
		}

		public String getType() {
			return type;
		}

		@Override
		public String toString() {
			return "name " + name + ", " + "rawType " + rawType;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof DocElement) {
				DocElement de = ((DocElement) o);
				boolean eq = false;
				eq = this.name != null ? this.name.equals(de.name) : this.name == de.name;
				eq = eq && (this.rawType != null ? this.rawType.equals(de.rawType) : this.rawType == de.rawType);
				return eq;
			} else {
				return super.equals(o);
			}
		}

	}

	class NoResponseElement extends DocElement {

		NoResponseElement(String name) {
			super(name);
			this.type = "No @ResponseBody";
		}

	}

	boolean isMultipart(TypeMirror t) {
		return isMultipart(t.toString());
	}

	boolean isMultipart(String t) {
		return t.toString().startsWith("org.springframework.web.multipart");
	}

	class RequestMappingAnnotationScanner extends ElementScanner6<Map<String, Object>, Void> {

		private static final String PATTERNS = "patterns";
		private static final String PARAMS = "params";
		private static final String METHODS = "methods";
		private static final String HEADERS = "headers";
		private static final String RESPONSE = "response";
		private static final String REQUEST = "request";
		private static final String MULTIPART_REQUEST = "multipart-request";

		private Set<String> ignoredParameter = new HashSet<String>();

		RequestMappingAnnotationScanner() {
			ignoredParameter.add(HttpEntity.class.getName());
			ignoredParameter.add(MultipartFile.class.getName());
			ignoredParameter.add(Model.class.getName());
			ignoredParameter.add(BindingResult.class.getName());
			ignoredParameter.add(RedirectAttributes.class.getName());
		}

		@Override
		public Map<String, Object> visitExecutable(ExecutableElement e, Void p) {
			if (e.getEnclosingElement().getAnnotation(org.springframework.stereotype.Controller.class) == null) {
				return null;
			}
			Map<String, Object> ret = new HashMap<String, Object>();
			buildRequestHandleAnnotation(e, ret);

			String apiComment = processingEnv.getElementUtils().getDocComment(e);
			if (apiComment != null) {
				ret.put("comment", apiComment);
			}

			buildResponse(e, ret);
			buildRequest(e, ret);
			return ret;
		}

		private boolean isIgnore(TypeMirror t) {
			Iterator<String> iterator = ignoredParameter.iterator();
			while (iterator.hasNext()) {
				if (t.toString().startsWith(iterator.next())) {
					return true;
				}
			}
			return false;
		}

		private void buildRequest(ExecutableElement e, Map<String, Object> ret) {
			List<? extends VariableElement> parameters = e.getParameters();
			if (parameters.size() == 0) {
				return;
			}
			boolean multipart = false;
			DocElement request = new DocElement(null);
			for (VariableElement parameter : parameters) {
				DocElement de = getParamDocElement(parameter);
				ReturnTypeVisitor rv = new ReturnTypeVisitor();
				TypeMirror asType = parameter.asType();
				if (multipart == false) {
					multipart = isMultipart(asType);
				}
				if (isIgnore(asType) || !Types.isPrimitive(asType.toString()) && asType instanceof DeclaredType && rv.isIgnore((DeclaredType) asType)) {
					continue;
				}
				de.setRawType(asType.toString(), asType);
				request.addMembers(de);
				asType.accept(rv, de);
			}
			if (multipart == true) {
				ret.put(MULTIPART_REQUEST, true);
			}
			if (request.getMembers() != null && request.getMembers().size() > 0) {
				ret.put(REQUEST, request);
			}
		}

		private DocElement getParamDocElement(VariableElement parameter) {
			if (parameter.getAnnotation(RequestParam.class) != null) {
				RequestParam anno = parameter.getAnnotation(RequestParam.class);
				Map<String, Object> info = new HashMap<String, Object>();
				info.put("required", anno.required());
				String dv = anno.defaultValue();
				if (!ValueConstants.DEFAULT_NONE.equals(dv)) {
					info.put("defaultValue", dv);
				}
				DocElement de = new DocElement(anno.value().equals("") ? parameter.getSimpleName().toString() : anno.value());
				de.addAnnotation("RequestParam", info);
				de.setOptionalAnnotations(getOptionalParameterAnnotations(parameter, RequestParam.class));
				return de;
			}
			if (parameter.getAnnotation(PathVariable.class) != null) {
				String value = parameter.getAnnotation(PathVariable.class).value();
				String name = value.equals("") ? parameter.getSimpleName().toString() : value;
				DocElement de = new DocElement(name);
				de.addAnnotation("PathVariable", new HashMap<String, Object>());
				de.setOptionalAnnotations(getOptionalParameterAnnotations(parameter, PathVariable.class));
				return de;
			}
			DocElement de = new DocElement(parameter.getSimpleName().toString());
			if (parameter.getAnnotation(org.springframework.web.bind.annotation.RequestBody.class) != null) {
				de.addAnnotation("RequestBody", new HashMap<String, Object>());
			}
			de.setOptionalAnnotations(getOptionalParameterAnnotations(parameter, org.springframework.web.bind.annotation.RequestBody.class));
			return de;
		}

		private Map<String, Map<String, Object>> getOptionalParameterAnnotations(VariableElement parameter, final Class<?> annotation) {

			return getAnnotations(parameter, new AnnotationGatheringGuard() {

				public boolean canGather(AnnotationMirror am) {
					String name = am.getAnnotationType().asElement().getSimpleName().toString();

					if (annotation.getSimpleName().equals(name)) {
						return false;
					}
					return true;
				}

			});

		}

		private void buildResponse(ExecutableElement e, Map<String, Object> ret) {
			if (e.getAnnotation(ResponseBody.class) != null) {
				DocElement response = new DocElement(null);
				e.getReturnType().accept(new ReturnTypeVisitor(), response);
				ret.put(RESPONSE, response);
			} else {
				NoResponseElement response = new NoResponseElement(null);
				ret.put(RESPONSE, response);
			}
		}

		private void buildRequestHandleAnnotation(ExecutableElement e, Map<String, Object> ret) {

			RequestMapping classAnnotation = e.getEnclosingElement().getAnnotation(RequestMapping.class);
			RequestMapping methodAnnotation = e.getAnnotation(RequestMapping.class);

			Set<String> headers = new HashSet<String>();
			Set<String> methods = new HashSet<String>();
			Set<String> params = new HashSet<String>();
			Set<String> patterns = new HashSet<String>();

			if (classAnnotation != null) {
				headers.addAll(Arrays.asList(classAnnotation.headers()));
				RequestMethod[] method = classAnnotation.method();
				for (RequestMethod requestMethod : method) {
					methods.add(requestMethod.name());
				}
				params.addAll(Arrays.asList(classAnnotation.params()));
				for (String pattern : classAnnotation.value()) {
					String[] methodPatterns = methodAnnotation.value();
					if (methodPatterns == null || methodPatterns.length == 0) {
						patterns.add(pattern);
					} else if (pattern.endsWith("/*")) {
						pattern = pattern.substring(0, pattern.length() - 2);
					}
					for (String subPattern : methodAnnotation.value()) {
						subPattern = subPattern.charAt(0) == '/' ? subPattern.substring(1) : subPattern;
						patterns.add(pattern + "/" + subPattern);
					}
				}
			} else {
				patterns.addAll(Arrays.asList(methodAnnotation.value()));
			}

			headers.addAll(Arrays.asList(methodAnnotation.headers()));
			RequestMethod[] method = methodAnnotation.method();
			for (RequestMethod requestMethod : method) {
				methods.add(requestMethod.name());
			}
			params.addAll(Arrays.asList(methodAnnotation.params()));

			if (!headers.isEmpty()) {
				ret.put(HEADERS, headers);
			}
			if (methods.isEmpty()) {
				methods.add(RequestMethod.GET.name());
			}
			ret.put(METHODS, methods);

			if (!params.isEmpty()) {
				ret.put(PARAMS, params);
			}
			if (!patterns.isEmpty()) {
				ret.put(PATTERNS, patterns);
			}
		}

	}

	class FieldScanner extends ElementScanner6<Void, DocElement> {

		Map<String, VariableElement> names = new HashMap<String, VariableElement>();

		@Override
		public Void visitVariable(VariableElement e, DocElement p) {
			if (e.getKind() == ElementKind.FIELD) {
				names.put(e.getSimpleName().toString(), e);
			}
			return null;
		}

	}

	class ClassScanner extends ElementScanner6<Void, DocElement> {

		private List<? extends TypeMirror> generics;
		private Map<String, TypeMirror> genericMap = new HashMap<String, TypeMirror>();
		private Map<String, VariableElement> names;

		ClassScanner(List<? extends TypeMirror> generics, Map<String, VariableElement> names) {
			this.generics = generics;
			this.names = names;
		}

		@Override
		public Void visitTypeParameter(TypeParameterElement arg0, DocElement arg1) {
			return super.visitTypeParameter(arg0, arg1);
		}

		private String getName(ExecutableElement e) {
			List<? extends VariableElement> parameters = e.getParameters();
			String name = e.getSimpleName().toString();
			if (parameters == null || parameters.size() != 0) {
				name = null;
			} else if (name.startsWith("get")) {
				name = name.substring(3);
			} else if (name.startsWith("is")) {
				name = name.substring(2);
			} else {
				name = null;
			}
			return name != null && !name.isEmpty() ? name.substring(0, 1).toLowerCase() + name.substring(1) : null;
		}

		private Map<String, Map<String, Object>> getConstraints(VariableElement var) {
			return getAnnotations(var, new AnnotationGatheringGuard() {

				public boolean canGather(AnnotationMirror am) {
					return Types.isBuiltInConstraints(am.getAnnotationType().asElement().toString());
				}

			});

		}

		private Map<String, Map<String, Object>> getOptionalAnnotation(VariableElement var) {
			return getAnnotations(var, new AnnotationGatheringGuard() {

				public boolean canGather(AnnotationMirror am) {
					return !Types.isBuiltInConstraints(am.getAnnotationType().asElement().toString());
				}

			});

		}

		private Map<String, Map<String, Object>> getMethodOptionalAnnotation(ExecutableElement e) {
			return getAnnotations(e.getAnnotationMirrors(), new AnnotationGatheringGuard() {

				public boolean canGather(AnnotationMirror am) {
					return !Types.isBuiltInConstraints(am.getAnnotationType().asElement().toString());
				}

			}, e);

		}

		@Override
		public Void visitExecutable(ExecutableElement e, DocElement p) {
			String name = getName(e);
			if (e.getKind() == ElementKind.CONSTRUCTOR || isInstanceOf(e.getEnclosingElement().asType(), Collection.class) || name == null) {
				return null;
			}

			DocElement de = new DocElement(name);
			p.addMembers(de);

			if (names.containsKey(name)) {
				de.addAnnotations(getConstraints(names.get(name)));
				de.setOptionalAnnotations(getOptionalAnnotation(names.get(name)));
				de.setComment(processingEnv.getElementUtils().getDocComment(names.get(name)));
			}

			de.setOptionalAnnotations(getMethodOptionalAnnotation(e));

			if (e.getReturnType().getKind() == TypeKind.TYPEVAR) {
				_visitType(de, genericMap.get(e.getReturnType().toString()));
			} else {
				_visitType(de, e.getReturnType());
			}

			// if class comment exist, method comment ignored.
			de.setComment(processingEnv.getElementUtils().getDocComment(e));

			return super.visitExecutable(e, p);
		}

		@Override
		public Void visitVariable(VariableElement e, DocElement p) {
			if (e.getKind() == ElementKind.ENUM_CONSTANT && e.getEnclosingElement().getKind() == ElementKind.ENUM) {
				DocElement de = new DocElement(e.getSimpleName().toString());
				p.addMembers(de);
			}
			return super.visitVariable(e, p);
		}

		@Override
		public Void visitType(TypeElement e, DocElement p) {
			if (e.getKind() == ElementKind.CLASS || e.getKind() == ElementKind.INTERFACE) {
				List<? extends TypeParameterElement> typeParameters = e.getTypeParameters();
				for (int i = 0; i < generics.size(); i++) {
					DocElement temp = p;
					do {
						if (typeParameters.size() <= i) {
							return null;
						}
						if (!generics.get(i).toString().equals(temp.getRawType())) {
							genericMap.put(typeParameters.get(i).getSimpleName().toString(), generics.get(i));
						}
						temp = temp.parent;
					} while (temp != null);
				}

				if (e.getTypeParameters().size() == 0) {
					DocElement temp = p.parent;
					while (temp != null) {
						if (e.toString().equals(temp.getRawType())) {
							p.setVisited();
							return null;
						}
						temp = temp.parent;
					}
				}

				p.setComment(processingEnv.getElementUtils().getDocComment(e));
			}
			return super.visitType(e, p);
		}

	}

	class ReturnTypeVisitor extends TypeVisitorAdapter {

		Set<Class<?>> asyncTypes = new HashSet<Class<?>>();
		Set<Class<?>> wrapperTypes = new HashSet<Class<?>>();
		Set<Class<?>> ignores = new HashSet<Class<?>>();

		ReturnTypeVisitor() {
			asyncTypes.add(Callable.class);
			asyncTypes.add(WebAsyncTask.class);
			asyncTypes.add(DeferredResult.class);
			wrapperTypes.add(HttpEntity.class);
		}

		boolean isIgnore(DeclaredType t) {
			ArrayList<Class<?>> allows = new ArrayList<Class<?>>(asyncTypes);
			allows.add(Collection.class);
			allows.add(Map.class);
			allows.addAll(wrapperTypes);
			ArrayList<Class<?>> ignores = new ArrayList<Class<?>>(this.ignores);
			return isInstanceOf(t, ignores) || (!isInstanceOf(t, allows) && (t.toString().startsWith("java")));
		}

		@Override
		public Void visitArray(ArrayType t, DocElement p) {
			p.setRawType(Types.ARRAY);
			DocElement de = new DocElement(null);
			p.addMembers(de);
			_visitType(de, t.getComponentType());
			return super.visitArray(t, p);
		}

		@Override
		public Void visitDeclared(DeclaredType t, DocElement p) {

			if (isIgnore(t)) {
				p.setRawType(t.toString());
				return null;
			}

			Element element = t.asElement();

			ArrayList<Class<?>> allows = new ArrayList<Class<?>>(asyncTypes);
			allows.addAll(wrapperTypes);
			if (isInstanceOf(t, allows)) {
				// async-types are always ignored.
				if (t.getTypeArguments().size() > 0) {
					_visitType(p, t.getTypeArguments().get(0));
				}
			} else if (isInstanceOf(t, Map.class)) {
				// Subclasses of Map has no getters... may be,,
				p.setRawType(element.toString(), t);
				if (t.getTypeArguments().size() > 1) {
					DocElement key = new DocElement("$key");
					p.addMembers(key);
					_visitType(key, t.getTypeArguments().get(0));

					DocElement value = new DocElement("$value");
					p.addMembers(value);
					_visitType(value, t.getTypeArguments().get(1));
				}
				return null;
			} else if (isInstanceOf(t, Number.class)) {
				p.setRawType(element.toString(), t);
				return null;
			} else {
				p.setRawType(element.toString(), t);
				element.accept(new ClassScanner(t.getTypeArguments(), getFildScanner(t).names), p);
				TypeElement typeElement = (TypeElement) t.asElement();
				TypeMirror superclass = typeElement.getSuperclass();
				String superName = superclass.toString();
				if (superclass != null && !"java.lang.Object".equals(superName) && !Types.isPrimitive(superName)) {
					superclass.accept(new ReturnTypeVisitor(), p);
				}
				// TODO remove
				p.purgeMember();

				if (element.getKind() == ElementKind.ENUM) {
					p.setType(element);
				} else if (isInstanceOf(t, Collection.class)) {
					DocElement de = new DocElement(null);
					p.addMembers(de);
					if (t.getTypeArguments().size() > 0) {
						_visitType(de, t.getTypeArguments().get(0));
					}
				}
			}
			return null;
		}

		FieldScanner getFildScanner(DeclaredType t) {
			FieldScanner fns = new FieldScanner();
			try {
				t.asElement().accept(fns, null);
			} catch (Exception e) {
				processingEnv.getMessager().printMessage(Kind.WARNING, "IGNORED " + t);
			}
			return fns;
		}
	}

	boolean isInstanceOf(TypeMirror typeMirror, Class<?> type) {
		ArrayList<Class<?>> list = new ArrayList<Class<?>>();
		list.add(type);
		return isInstanceOf(typeMirror, list);
	}

	boolean isInstanceOf(TypeMirror typeMirror, List<Class<?>> types) {
		if (!(typeMirror instanceof DeclaredType)) {
			return false;
		}

		for (Class<?> type : types) {
			if (typeMirror.toString().startsWith(type.getName())) {
				return true;
			}
		}

		DeclaredType declaredType = (DeclaredType) typeMirror;
		TypeElement typeElement = (TypeElement) declaredType.asElement();
		for (TypeMirror iface : typeElement.getInterfaces()) {
			for (Class<?> type : types) {
				if (isInstanceOf(iface, type)) {
					return true;
				}
			}
		}
		return isInstanceOf(typeElement.getSuperclass(), types);
	}

	void _visitType(DocElement de, TypeMirror type) {
		if (type == null) {
			return;
		}
		de.setRawType(type.toString(), type);
		if (!Types.isPrimitive(type.toString())) {
			type.accept(new ReturnTypeVisitor(), de);
		}
	}

	Map<String, Map<String, Object>> getAnnotations(VariableElement parameter, AnnotationGatheringGuard guard) {
		List<? extends AnnotationMirror> annotationMirrors = parameter.getAnnotationMirrors();
		return getAnnotations(annotationMirrors, guard, parameter);
	}

	Map<String, Map<String, Object>> getAnnotations(List<? extends AnnotationMirror> annotationMirrors, AnnotationGatheringGuard guard,
			Element enclosing) {
		Map<String, Map<String, Object>> ret = new HashMap<String, Map<String, Object>>();
		for (AnnotationMirror am : annotationMirrors) {
			if (!guard.canGather(am)) {
				continue;
			}

			Map<String, Object> values = new HashMap<String, Object>();
			for (Entry<? extends ExecutableElement, ? extends AnnotationValue> e : am.getElementValues().entrySet()) {
				AnnotationValue value = (AnnotationValue) e.getValue();
				value.accept(new AnnotationScanner(e.getKey().getSimpleName().toString()), values);
			}
			ret.put(am.getAnnotationType().asElement().getSimpleName().toString(), values);
		}
		return ret;
	}

	private interface AnnotationGatheringGuard {
		boolean canGather(AnnotationMirror am);
	}

	@Override
	public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {
		if (roundEnv.processingOver()) {
			return false;
		}
		process(roundEnv);
		return true;
	}

	private void process(RoundEnvironment roundEnv) {
		int count = 0;
		for (Element e : roundEnv.getElementsAnnotatedWith(RequestMapping.class)) {
			if (e instanceof ExecutableElement) {
				count++;
			}
		}
		beforeProcess(count);
		int index = 0;
		for (Element e : roundEnv.getElementsAnnotatedWith(RequestMapping.class)) {
			if (e instanceof ExecutableElement) {
				Map<String, Object> ret = e.accept(new RequestMappingAnnotationScanner(), null);
				process(ret, ++index == count);
			}
		}
		afterProcess();
	}

	protected void beforeProcess(int count) {
		if (count == 0) {
			processingEnv.getMessager().printMessage(Kind.NOTE, "No RequestMappings");
			process(new HashMap<String, Object>(), true);
		}
	}

	protected void afterProcess() {
	}

	protected void process(Map<String, Object> ret, boolean isLast) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		try {
			processingEnv.getMessager().printMessage(Kind.NOTE, mapper.defaultPrettyPrintingWriter().writeValueAsString(ret));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}

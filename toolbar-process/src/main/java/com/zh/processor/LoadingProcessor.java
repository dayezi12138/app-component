package com.zh.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.zh.annatation.loading.LoadingPoint;
import com.zh.bean.Constant;
import com.zh.bean.PackingMethod;
import com.zh.fade.ILoading;
import com.zh.processor.utils.Const;
import com.zh.processor.utils.Logger;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * author : dayezi
 * data :2019/7/1
 * description:
 */
@AutoService(Processor.class)
public class LoadingProcessor extends AbstractProcessor {
    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;
    private Logger logger;
    private final String SPOT = ",";
    private final String PARAM_FLAG = "$S";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        logger = new Logger(processingEnv.getMessager());
        logger.info("======init======");
        typeUtils = processingEnvironment.getTypeUtils();
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (CollectionUtils.isNotEmpty(set)) {
            logger.info(">>> Found LoadingPoint, start... <<<");
            parseLoadingPoint(roundEnvironment);
            logger.info(">>> Found LoadingPoint, end... <<<");
        }
        return false;
    }

    private void parseLoadingPoint(RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(LoadingPoint.class);
        logger.info(">>> Found LoadingPoints, size is " + routeElements.size() + " <<<");
        if (CollectionUtils.isEmpty(routeElements)) return;
        List<PackingMethod> packingMethodList = new ArrayList<>();
        for (Element element : routeElements) {
            TypeMirror tm = element.asType();
            if (validLegal(tm)) {
                String param = "atlas.add($S)";
                PackingMethod packingMethod = new PackingMethod();
                packingMethod.setParam(param);
                TypeElement element1 = (TypeElement) element;
                packingMethod.setKey(element1.getQualifiedName().toString());
                packingMethodList.add(packingMethod);
            }
        }
        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                ClassName.get(List.class),
                ClassName.get(String.class)
        );
        ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "atlas").build();
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("loadInfo")
                .addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).addParameter(groupParamSpec);
        for (PackingMethod packingMethod : packingMethodList) {
            methodBuilder.addStatement(packingMethod.getParam(), packingMethod.getKey());
        }
        TypeSpec helloWorld1 = TypeSpec.classBuilder(LoadingPoint.class.getSimpleName() + Const.PROVIDER_SUFFIX)
                .addModifiers(Modifier.PUBLIC).addMethod(methodBuilder.build()).addSuperinterface(ClassName.get(ILoading.class))
                .build();
        try {
            JavaFile.builder(Constant.PACK_NAME_1, helloWorld1).build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(LoadingPoint.class);
        return annotations;
    }

    private boolean validLegal(TypeMirror tm) {
        TypeMirror type_Activity = elementUtils.getTypeElement(Const.ACTIVITY).asType();
        TypeMirror type_Fragment = elementUtils.getTypeElement(Const.FRAGMENT).asType();
        return typeUtils.isSubtype(tm, type_Activity) || typeUtils.isSubtype(tm, type_Fragment);
    }

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }
}

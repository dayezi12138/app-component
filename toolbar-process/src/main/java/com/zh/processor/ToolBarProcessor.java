package com.zh.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.zh.annatation.toolbar.OnMenuOnclick;
import com.zh.annatation.toolbar.OnNavigationClick;
import com.zh.annatation.toolbar.ToolbarLeft;
import com.zh.annatation.toolbar.ToolbarNavigation;
import com.zh.annatation.toolbar.ToolbarTitle;
import com.zh.bean.Constant;
import com.zh.bean.PackingMethod;
import com.zh.bean.ToolbarNavigationBean;
import com.zh.bean.ToolbarTitleBean;
import com.zh.fade.INavagitionClick;
import com.zh.fade.IOnMenuClick;
import com.zh.fade.IToolbarLeft;
import com.zh.fade.IToolbarNavigation;
import com.zh.fade.IToolbarTitle;
import com.zh.processor.utils.Const;
import com.zh.processor.utils.Logger;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * author : dayezi
 * data :2019/5/21
 * description:
 */
@AutoService(Processor.class)
public class ToolBarProcessor extends AbstractProcessor {
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
        logger.info("======" + this.getClass().getName() + " init======");
        typeUtils = processingEnvironment.getTypeUtils();
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (CollectionUtils.isNotEmpty(set)) {
            logger.info(">>> Found ToolbarNavigation, start... <<<");
            parseToolbarNavigation(roundEnvironment);
            logger.info(">>> Found ToolbarNavigation, end... <<<");

            logger.info(">>> Found ToolbarTitle, start... <<<");
            parseToolbarTitle(roundEnvironment);
            logger.info(">>> Found ToolbarTitle, end... <<<");

            logger.info(">>> Found ToolbarLeft, start... <<<");
            parseToolbarLeft(roundEnvironment);
            logger.info(">>> Found ToolbarLeft, end... <<<");

            logger.info(">>> Found OnMenuOnclick, start... <<<");
            paraseClick(OnMenuOnclick.class, IOnMenuClick.class, roundEnvironment);
            logger.info(">>> Found OnMenuOnclick, end... <<<");

            logger.info(">>> Found OnMenuOnclick, start... <<<");
            paraseClick(OnNavigationClick.class, INavagitionClick.class, roundEnvironment);
            logger.info(">>> Found OnMenuOnclick, end... <<<");
            return true;
        }
        return false;
    }

    private boolean validLegal(TypeMirror tm) {
        TypeMirror type_Activity = elementUtils.getTypeElement(Const.ACTIVITY).asType();
        TypeMirror type_Fragment = elementUtils.getTypeElement(Const.FRAGMENT).asType();
        return typeUtils.isSubtype(tm, type_Activity) || typeUtils.isSubtype(tm, type_Fragment);
    }

    private void paraseClick(Class clz, Class interfaceClz, RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(clz);
        logger.info(">>> Found " + clz.getSimpleName() + ", size is " + routeElements.size() + " <<<");
        if (CollectionUtils.isEmpty(routeElements)) return;
        try {
            List<PackingMethod> packingMethodList = new ArrayList<>();
            for (Element element : routeElements) {
                if (element instanceof ExecutableElement) {
                    ExecutableElement element1 = (ExecutableElement) element;
                    TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                    String param = "atlas.put($S,$S)";
                    PackingMethod packingMethod = new PackingMethod();
                    packingMethod.setParam(param);
                    packingMethod.setKey(enclosingElement.getQualifiedName().toString());
                    packingMethod.addParams(element1.getSimpleName().toString());
                    packingMethodList.add(packingMethod);
                }
            }
            ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(String.class),
                    ClassName.get(String.class)
            );
            ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "atlas").build();
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("loadInfo")
                    .addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).addParameter(groupParamSpec);
            for (PackingMethod packingMethod : packingMethodList) {
                methodBuilder.addStatement(packingMethod.getParam(), packingMethod.getKey(), packingMethod.getParamList().get(0));
            }
            TypeSpec helloWorld1 = TypeSpec.classBuilder(clz.getSimpleName() + Const.PROVIDER_SUFFIX)
                    .addModifiers(Modifier.PUBLIC).addMethod(methodBuilder.build()).addSuperinterface(ClassName.get(interfaceClz))
                    .build();
            JavaFile.builder(Constant.PACK_NAME, helloWorld1).build().writeTo(filer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseToolbarTitle(RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(ToolbarTitle.class);
        logger.info(">>> Found ToolbarTitles, size is " + routeElements.size() + " <<<");
        if (CollectionUtils.isEmpty(routeElements)) return;
        List<PackingMethod> packingMethodList = new ArrayList<>();
        for (Element element : routeElements) {
            TypeMirror tm = element.asType();
            if (validLegal(tm)) {
                ToolbarTitle toolbarTitle = element.getAnnotation(ToolbarTitle.class);
                StringBuilder builder = new StringBuilder("$T.build(")
                        .append(PARAM_FLAG)
                        .append(SPOT)
                        .append(toolbarTitle.textSize())
                        .append(SPOT)
                        .append(toolbarTitle.textColorId())
                        .append(SPOT)
                        .append(toolbarTitle.viewId())
                        .append(SPOT)
                        .append(toolbarTitle.backGroundColorId())
                        .append(SPOT)
                        .append(toolbarTitle.statusBarColorId())
                        .append(")");
                String param = "atlas.put($S," + builder.toString() + ")";
                PackingMethod packingMethod = new PackingMethod();
                packingMethod.setParam(param);
                TypeElement element1 = (TypeElement) element;
                packingMethod.setKey(element1.getQualifiedName().toString());
                packingMethod.addParams(toolbarTitle.title());
                packingMethodList.add(packingMethod);
            }
        }
        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(ToolbarTitleBean.class)
        );
        ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "atlas").build();
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("loadInfo")
                .addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).addParameter(groupParamSpec);
        for (PackingMethod packingMethod : packingMethodList) {
            methodBuilder.addStatement(packingMethod.getParam(), packingMethod.getKey(), ToolbarTitleBean.class, packingMethod.getParamList().get(0));
        }
        TypeSpec helloWorld1 = TypeSpec.classBuilder(ToolbarTitle.class.getSimpleName() + Const.PROVIDER_SUFFIX)
                .addModifiers(Modifier.PUBLIC).addMethod(methodBuilder.build()).addSuperinterface(ClassName.get(IToolbarTitle.class))
                .build();
        try {
            JavaFile.builder(Constant.PACK_NAME, helloWorld1).build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseToolbarLeft(RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(ToolbarLeft.class);
        logger.info(">>> Found ToolbarLefts, size is " + routeElements.size() + " <<<");
        if (CollectionUtils.isEmpty(routeElements)) return;
        List<PackingMethod> packingMethodList = new ArrayList<>();
        for (Element element : routeElements) {
            TypeMirror tm = element.asType();
            if (validLegal(tm)) {
                ToolbarLeft toolbarLeft = element.getAnnotation(ToolbarLeft.class);
                String param = "atlas.put($S," + toolbarLeft.menuId() + ")";
                TypeElement element1 = (TypeElement) element;
                PackingMethod packingMethod = new PackingMethod();
                packingMethod.setParam(param);
                packingMethod.setKey(element1.getQualifiedName().toString());
                packingMethodList.add(packingMethod);
            }
        }
        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(Integer.class)
        );
        ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "atlas").build();
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("loadInfo")
                .addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).addParameter(groupParamSpec);
        for (PackingMethod packingMethod : packingMethodList) {
            methodBuilder.addStatement(packingMethod.getParam(), packingMethod.getKey());
        }
        TypeSpec helloWorld1 = TypeSpec.classBuilder(ToolbarLeft.class.getSimpleName() + Const.PROVIDER_SUFFIX)
                .addModifiers(Modifier.PUBLIC).addMethod(methodBuilder.build()).addSuperinterface(ClassName.get(IToolbarLeft.class))
                .build();
        try {
            JavaFile.builder(Constant.PACK_NAME, helloWorld1).build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseToolbarNavigation(RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(ToolbarNavigation.class);
        logger.info(">>> Found ToolbarNavigations, size is " + routeElements.size() + " <<<");
        if (CollectionUtils.isEmpty(routeElements)) return;
        ClassName toolClassName = ClassName.get(ToolbarNavigationBean.class);
        List<PackingMethod> packingMethodList = new ArrayList<>();
        for (Element element : routeElements) {
            TypeMirror tm = element.asType();
            if (validLegal(tm)) {
                ToolbarNavigation toolbarNavigation = element.getAnnotation(ToolbarNavigation.class);
                StringBuilder builder = new StringBuilder("$T.build(")
                        .append(PARAM_FLAG)
                        .append(SPOT)
                        .append(toolbarNavigation.titleAppearanceId())
                        .append(SPOT)
                        .append(toolbarNavigation.titleColorId())
                        .append(SPOT)
                        .append(PARAM_FLAG)
                        .append(SPOT)
                        .append(toolbarNavigation.subTitleAppearanceId())
                        .append(SPOT)
                        .append(toolbarNavigation.subTitleColorId())
                        .append(SPOT)
                        .append(toolbarNavigation.iconId())
                        .append(SPOT)
                        .append(toolbarNavigation.visibleNavigation())
                        .append(")");
                String val = builder.toString();
                String param = "atlas.put($S," + val + ")";
                PackingMethod packingMethod = new PackingMethod();
                packingMethod.setParam(param);
                TypeElement element1 = (TypeElement) element;
                packingMethod.setKey(element1.getQualifiedName().toString());
                packingMethod.addParams(toolbarNavigation.title());
                packingMethod.addParams(toolbarNavigation.subTitle());
                packingMethodList.add(packingMethod);
            }
        }

        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(ToolbarNavigationBean.class)
        );
        ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "atlas").build();
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("loadInfo")
                .addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).addParameter(groupParamSpec);
        for (PackingMethod packingMethod : packingMethodList) {
            methodBuilder.addStatement(packingMethod.getParam(), packingMethod.getKey(), toolClassName, packingMethod.getParamList().get(0), packingMethod.getParamList().get(1));
        }
        TypeSpec helloWorld1 = TypeSpec.classBuilder(ToolbarNavigation.class.getSimpleName() + Const.PROVIDER_SUFFIX)
                .addModifiers(Modifier.PUBLIC).addMethod(methodBuilder.build()).addSuperinterface(ClassName.get(IToolbarNavigation.class))
                .build();
        try {
            JavaFile.builder(Constant.PACK_NAME, helloWorld1).build().writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(ToolbarNavigation.class);
        annotations.add(ToolbarTitle.class);
        annotations.add(ToolbarLeft.class);
        annotations.add(OnNavigationClick.class);
        annotations.add(OnMenuOnclick.class);
        return annotations;
    }
}

package com.example.sparkdata;

import javax.sound.midi.MetaEventListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class SparkInvocationHandlerFactory {

    private DataExtracterResolver resolver;
    private Map<String, TransformationSpider> spiderMap;


    public SparkInvocationHandler create(Class<? extends SparkRepository> sparkRepoInterface) {
        Class<?> modelClass = getModelClass(sparkRepoInterface);

        String pathToData = modelClass.getAnnotation(Source.class).value();
        Set<String> fieldNames = getFieldNames(modelClass);
        DataExtracter dataExtracter = resolver.resolve(pathToData);



        Map<Method, List<SparkTransformation>> transformationChain = new HashMap<>();

        Method[] methods = sparkRepoInterface.getMethods();
        for (Method method : methods) {
            TransformationSpider currentSpider = null;
            List<String> methodWords = WordsMatcher.toWordsByJavaConversion(method.getName());
            List<SparkTransformation> transformations = new ArrayList<>();
            while (methodWords.size() > 1) {
                String spiderName = WordsMatcher.findAndRemoveMatchingPiecesIfExists(spiderMap.keySet(), methodWords);
                if (!spiderName.isEmpty()) {
                    currentSpider = spiderMap.get(spiderName);
                }
                transformations.add(currentSpider.getTransformation(methods));
            }
        }


        SparkInvocationHandler.builder()
                .modelClass(modelClass)
                .pathToData(pathToData)
                .dataExtracter(dataExtracter)
                .transformationChain(transformationChain)
    }


    private Class<?> getModelClass(Class<? extends SparkRepository> repoInterface) {
        ParameterizedType genericInterface = (ParameterizedType)repoInterface.getGenericInterfaces()[0];
        Class<?> modelClass = (Class<?>)genericInterface.getActualTypeArguments()[0];
        return modelClass;
    }

    private Set<String> getFieldNames(Class<?> modelClass) {
        return Arrays.stream(modelClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Transient.class))
                .filter(f -> !Collection.class.isAssignableFrom(f.getType()))
                .map(Field::getName)
                .collect(Collectors.toSet());
    }
}

package com.example.unsafe_sparkdata;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class LazyCollectionInjectorPostFinalizer implements PostFinalizer {

    private final ConfigurableApplicationContext realContext;

    @SneakyThrows
    @Override
    public Object postFinalize(Object retVal) {
        if (Collection.class.isAssignableFrom(retVal.getClass())) {
            List models = (List) retVal;

            for (Object model : models) {
                Field idField = model.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                Long ownerId = idField.getLong(model);

                Field[] fields = model.getClass().getDeclaredFields();

                for (Field field : fields) {
                    if (List.class.isAssignableFrom(field.getType())) {
                        LazySparkList lazySparkList = realContext.getBean(LazySparkList.class);
                        lazySparkList.setOwnerId(ownerId);

                        String columnName = field.getAnnotation(ForiegnKey.class).value();
                        lazySparkList.setForeignKeyName(columnName);
                        Class<?> embeddedModelClass = getEmbeddedModel(field);
                        lazySparkList.setModelClass(embeddedModelClass);
                        String pathToData = embeddedModelClass.getAnnotation(Source.class).value();
                        lazySparkList.setPathToSource(pathToData);

                        field.setAccessible(true);
                        field.set(model, lazySparkList);
                    }
                }
            }

        }
        return retVal;
    }

    private Class<?> getEmbeddedModel(Field field) {
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        Class<?> embeddedModel = (Class<?>)genericType.getActualTypeArguments()[0];
        return embeddedModel;
    }
}

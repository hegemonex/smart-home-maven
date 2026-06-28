package com.solvd.smarthome.reflection;

import com.solvd.smarthome.annotations.DeviceInfo;
import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.district.house.devices.smartdevices.SmartLight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;

@DeviceInfo(
        label = "ReflectionDemo",
        version = "3.0",
        description = "Demonstrates Java Reflection and custom annotation processing",
        includeInReport = true
)
public class ReflectionDemo {

    private static final Logger logger = LogManager.getLogger(ReflectionDemo.class);

    public static void inspectFields(Class<?> clazz) {
        logger.info("── Fields of {} ──", clazz.getSimpleName());

        for (Field field : clazz.getDeclaredFields()) {
            logger.info("{} {} {}",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName());
        }
    }

    public static void inspectConstructors(Class<?> clazz) {
        logger.info("── Constructors of {} ──", clazz.getSimpleName());

        for (Constructor<?> ctor : clazz.getDeclaredConstructors()) {
            StringBuilder params = new StringBuilder();
            for (Class<?> param : ctor.getParameterTypes()) {
                if (params.length() > 0) params.append(", ");
                params.append(param.getSimpleName());
            }

            logger.info("{} {}({})",
                    Modifier.toString(ctor.getModifiers()),
                    clazz.getSimpleName(),
                    params);
        }
    }

    public static void inspectMethods(Class<?> clazz) {
        logger.info("── Methods of {} ──", clazz.getSimpleName());

        for (Method method : clazz.getDeclaredMethods()) {
            StringBuilder params = new StringBuilder();
            for (Class<?> param : method.getParameterTypes()) {
                if (params.length() > 0) params.append(", ");
                params.append(param.getSimpleName());
            }

            logger.info("{} {} {}({})",
                    Modifier.toString(method.getModifiers()),
                    method.getReturnType().getSimpleName(),
                    method.getName(),
                    params);
        }
    }

    public static SmartLight createSmartLightViaReflection() throws Exception {
        logger.info("Creating SmartLight via Reflection...");

        Class<?> clazz = Class.forName(
                "com.solvd.smarthome.district.house.devices.smartdevices.SmartLight");

        Constructor<?> constructor = clazz.getDeclaredConstructor(
                String.class,
                BigDecimal.class,
                LocalDate.class,
                String.class,
                String.class,
                int.class,
                boolean.class
        );

        Object instance = constructor.newInstance(
                "Reflection Light",
                new BigDecimal("59.99"),
                LocalDate.of(2025, 1, 15),
                "Cool White",
                "ReflectaBulb",
                70,
                true
        );

        logger.info("Created instance: {}", instance);
        return (SmartLight) instance;
    }

    public static void callMethodViaReflection(Device device, String methodName) throws Exception {
        logger.info("Calling '{}' on {}", methodName, device.getClass().getSimpleName());

        Method method = device.getClass().getMethod(methodName);
        Object result = method.invoke(device);

        logger.info("Return value: {}", result);
    }

    public static void readPrivateField(Device device, String fieldName) throws Exception {
        logger.info("Reading private field '{}'...", fieldName);

        Class<?> clazz = device.getClass();
        Field field = null;

        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        if (field == null) {
            logger.warn("Field not found: {}", fieldName);
            return;
        }

        field.setAccessible(true);
        Object value = field.get(device);

        logger.info("{} = {} (declared in {})",
                field.getName(),
                value,
                field.getDeclaringClass().getSimpleName());
    }

    public static void processDeviceInfoAnnotation(Class<?> clazz) {
        logger.info("Processing @DeviceInfo for {}", clazz.getSimpleName());

        if (clazz.isAnnotationPresent(DeviceInfo.class)) {
            DeviceInfo a = clazz.getAnnotation(DeviceInfo.class);
            logger.info("[CLASS] label='{}', version='{}', include={}, desc='{}'",
                    a.label(), a.version(), a.includeInReport(), a.description());
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeviceInfo.class)) {
                DeviceInfo a = method.getAnnotation(DeviceInfo.class);
                logger.info("[METHOD {}] label='{}', include={}",
                        method.getName(), a.label(), a.includeInReport());
            }
        }
    }

    public static void runAnnotatedMethods(Object target) throws Exception {
        logger.info("Running annotated methods for {}", target.getClass().getSimpleName());

        for (Method method : target.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeviceInfo.class)) {
                DeviceInfo info = method.getAnnotation(DeviceInfo.class);

                if (info.includeInReport()) {
                    logger.info("Invoking: {}", method.getName());
                    method.invoke(target);
                } else {
                    logger.debug("Skipping: {}", method.getName());
                }
            }
        }
    }
}
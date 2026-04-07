package com.solvd.smarthome.reflection;

import com.solvd.smarthome.annotations.DeviceInfo;
import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.district.house.devices.smartdevices.SmartLight;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;


@DeviceInfo(
        label       = "ReflectionDemo",
        version     = "3.0",
        description = "Demonstrates Java Reflection and custom annotation processing",
        includeInReport = true
)
public class ReflectionDemo {


    public static void inspectFields(Class<?> clazz) {
        System.out.println("\n── Fields of " + clazz.getSimpleName() + " ──");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            String modifiers = Modifier.toString(field.getModifiers());
            String type      = field.getType().getSimpleName();
            String name      = field.getName();

            System.out.println("  " + modifiers + " " + type + " " + name);
        }
    }

    public static void inspectConstructors(Class<?> clazz) {
        System.out.println("\n── Constructors of " + clazz.getSimpleName() + " ──");

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> ctor : constructors) {
            String modifiers = Modifier.toString(ctor.getModifiers());

            StringBuilder params = new StringBuilder();
            for (Class<?> param : ctor.getParameterTypes()) {
                if (params.length() > 0) params.append(", ");
                params.append(param.getSimpleName());
            }

            System.out.println("  " + modifiers + " " + clazz.getSimpleName()
                    + "(" + params + ")");
        }
    }

    public static void inspectMethods(Class<?> clazz) {
        System.out.println("\n── Methods of " + clazz.getSimpleName() + " ──");

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            String modifiers   = Modifier.toString(method.getModifiers());
            String returnType  = method.getReturnType().getSimpleName();
            String name        = method.getName();

            StringBuilder params = new StringBuilder();
            for (Class<?> param : method.getParameterTypes()) {
                if (params.length() > 0) params.append(", ");
                params.append(param.getSimpleName());
            }

            System.out.println("  " + modifiers + " " + returnType
                    + " " + name + "(" + params + ")");
        }
    }

    public static SmartLight createSmartLightViaReflection() throws Exception {
        System.out.println("\n── Creating SmartLight via Reflection ──");

        Class<?> smartLightClass = Class.forName(
                "com.solvd.smarthome.com.solvd.smarthome.district.house.devices.smartdevices.SmartLight");

       Constructor<?> constructor = smartLightClass.getDeclaredConstructor(
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

        System.out.println("  Created: " + instance);
        System.out.println("  Type   : " + instance.getClass().getSimpleName());

        return (SmartLight) instance;
    }

    public static void callMethodViaReflection(Device device, String methodName) throws Exception {
        System.out.println("\n── Calling '" + methodName + "' via Reflection on "
                + device.getClass().getSimpleName() + " ──");

        Method method = device.getClass().getMethod(methodName);

        Object returnValue = method.invoke(device);

        System.out.println("  Return value: " + returnValue);
    }

    public static void readPrivateField(Device device, String fieldName) throws Exception {
        System.out.println("\n── Reading private field '" + fieldName + "' via Reflection ──");

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
            System.out.println("  Field not found: " + fieldName);
            return;
        }

        field.setAccessible(true);
        Object value = field.get(device);
        System.out.println("  " + field.getName() + " = " + value
                + " (declared in " + field.getDeclaringClass().getSimpleName() + ")");
    }

    public static void processDeviceInfoAnnotation(Class<?> clazz) {
        System.out.println("\n── @DeviceInfo annotation report for " + clazz.getSimpleName() + " ──");

        if (clazz.isAnnotationPresent(DeviceInfo.class)) {
            DeviceInfo classAnnotation = clazz.getAnnotation(DeviceInfo.class);
            System.out.println("  [CLASS] label='"        + classAnnotation.label()          + "'"
                    + " version='"               + classAnnotation.version()         + "'"
                    + " includeInReport="        + classAnnotation.includeInReport()
                    + " description='"           + classAnnotation.description()     + "'");
        } else {
            System.out.println("  [CLASS] No @DeviceInfo annotation found.");
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeviceInfo.class)) {
                DeviceInfo methodAnnotation = method.getAnnotation(DeviceInfo.class);
                System.out.println("  [METHOD " + method.getName() + "()] "
                        + "label='"      + methodAnnotation.label()            + "'"
                        + " version='"   + methodAnnotation.version()          + "'"
                        + " report="     + methodAnnotation.includeInReport()
                        + " desc='"      + methodAnnotation.description()      + "'");
            }
        }
    }

    @DeviceInfo(
            label       = "SmartHomeDiagnostics",
            version     = "2.1",
            description = "Runs diagnostic checks on the smart home system",
            includeInReport = true
    )
    public static class SmartHomeDiagnostics {

        @DeviceInfo(
                label       = "runFullDiagnostic",
                version     = "2.1",
                description = "Runs a full system diagnostic — checks all devices",
                includeInReport = true
        )
        public void runFullDiagnostic() {
            System.out.println("Running full diagnostic...");
        }

        @DeviceInfo(
                label       = "runQuickCheck",
                version     = "1.5",
                description = "Quick 30-second health check",
                includeInReport = false
        )
        public void runQuickCheck() {
            System.out.println("Running quick check...");
        }

        public void internalUtil() {
            System.out.println("Internal utility — not annotated.");
        }
    }

    public static void runAnnotatedMethods(Object target) throws Exception {
        System.out.println("\n── Running @DeviceInfo(includeInReport=true) methods on "
                + target.getClass().getSimpleName() + " ──");

        for (Method method : target.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeviceInfo.class)) {
                DeviceInfo info = method.getAnnotation(DeviceInfo.class);
                if (info.includeInReport()) {
                    System.out.println("  → Invoking: " + method.getName()
                            + " [label='" + info.label() + "']");
                    method.invoke(target);
                } else {
                    System.out.println("  ✗ Skipping: " + method.getName()
                            + " (includeInReport=false)");
                }
            }
        }
    }
}
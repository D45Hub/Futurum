package com.futurumgame.testhelpers;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelper {

    private ReflectionHelper(){
    }

    /**
     * Searches all methods of the specified class and returns a boolean value indicating whether the defined methods are contained or not.
     * The methodnames should be in the same order as their corresponding arguments
     *
     * @param clazz the class that shall contain the methods
     * @param methodNames the names of the methods to be searched for
     * @param args the arguments of the methods to be searched for, each array corresponds to a specific method that reflects all types of the arguments the method has.
     *             They have to be in the same order as defined by the method that wil be searched for. If the method has no arguments an empty array should be passed on.
     * @return <b>true</b>rue if and only if the class contains the defined methods, <b>false</b> otherwise
     * @throws IllegalArgumentException if the methodNames and the args do not have the same length
     */
    public static  boolean hasMethods(Class<?> clazz, String[] methodNames, List<Class[]> args) {
        if (methodNames.length != args.size()) {
            throw new IllegalArgumentException();
        }
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methodNames.length; i++) {
            final String methodName = methodNames[i];
            final Class[] params = args.get(i);
            if (Arrays.stream(methods).anyMatch(m -> isMethod(m, methodName, params))) {
                continue;
            }
            return false;
        }
        return true;
    }

    private static boolean isMethod(Method method, String methodName, Class<?>[] args) {
        Class<?>[] params = method.getParameterTypes();
        if(! method.getName().equals(methodName)|| params.length != args.length){
            return false;
        }
        for (int i = 0; i < params.length; i++) {
            if(params[i].equals(args[i])){
                continue;
            }
            return false;
        }
        return true;
    }
}

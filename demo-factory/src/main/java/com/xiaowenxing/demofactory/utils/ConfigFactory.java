package com.xiaowenxing.demofactory.utils;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiFunction;

/**
 * TestConfig 配置文件的构建工厂类
 * <p>
 * 用于手动读取配置文件初始化 TestConfig 对象，只有在非IOC环境下你才会用到此类
 */
public class ConfigFactory {

    private ConfigFactory() {
    }

    /**
     * 配置文件地址
     */
    public static String configPath = "test.properties";

    /**
     * 根据configPath路径获取配置信息
     *
     * @return 一个SaTokenConfig对象
     */
    public static TestConfig createConfig() {
        return createConfig(configPath);
    }

    public static void main(String[] args) {
        TestConfig config = createConfig();
        System.out.println(config);
    }

    /**
     * 根据指定路径路径获取配置信息
     *
     * @param path 配置文件路径
     * @return 一个SaTokenConfig对象
     */
    public static TestConfig createConfig(String path) {
        Map<String, String> map = readPropToMap(path);
        if (map == null) {
            throw new RuntimeException("找不到配置文件：" + configPath, null);
        }
        return (TestConfig) initPropByMap(map, new TestConfig());
    }

    /**
     * 工具方法: 将指定路径的properties配置文件读取到Map中
     *
     * @param propertiesPath 配置文件地址
     * @return 一个Map
     */
    private static Map<String, String> readPropToMap(String propertiesPath) {
        Map<String, String> map = new HashMap<String, String>(16);
        try {
            InputStream is = ConfigFactory.class.getClassLoader().getResourceAsStream(propertiesPath);
            if (is == null) {
                return null;
            }
            Properties prop = new Properties();
            prop.load(is);
            for (String key : prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }
        } catch (IOException e) {
            throw new RuntimeException("配置文件(" + propertiesPath + ")加载失败", e);
        }
        return map;
    }

    /**
     * 工具方法: 将 Map 的值映射到一个 Model 上
     *
     * @param map 属性集合
     * @param obj 对象, 或类型
     * @return 返回实例化后的对象
     */
    private static Object initPropByMap(Map<String, String> map, Object obj) {

        if (map == null) {
            map = new HashMap<String, String>(16);
        }

        // 1、取出类型
        Class<?> cs = null;
        if (obj instanceof Class) {
            // 如果是一个类型，则将obj=null，以便完成静态属性反射赋值
            cs = (Class<?>) obj;
            obj = null;
        } else {
            // 如果是一个对象，则取出其类型
            cs = obj.getClass();
        }

        // 2、遍历类型属性，反射赋值
        for (Field field : cs.getDeclaredFields()) {
            String value = map.get(field.getName());
            if (value == null) {
                // 如果为空代表没有配置此项
                continue;
            }
            try {
                Object valueConvert = getValueByType(value, field.getType());
                field.setAccessible(true);
                field.set(obj, valueConvert);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException("属性赋值出错：" + field.getName(), e);
            }
        }
        return obj;
    }

    /**
     * 将指定值转化为指定类型
     *
     * @param <T> 泛型
     * @param obj 值
     * @param cs  类型
     * @return 转换后的值
     */
    public static <T> T getValueByType(Object obj, Class<T> cs) {
        // 如果 obj 为 null 或者本来就是 cs 类型
        if (obj == null || obj.getClass().equals(cs)) {
            return (T) obj;
        }
        // 开始转换
        String obj2 = String.valueOf(obj);
        Object obj3 = null;
        if (cs.equals(String.class)) {
            obj3 = obj2;
        } else if (cs.equals(int.class) || cs.equals(Integer.class)) {
            obj3 = Integer.valueOf(obj2);
        } else if (cs.equals(long.class) || cs.equals(Long.class)) {
            obj3 = Long.valueOf(obj2);
        } else if (cs.equals(short.class) || cs.equals(Short.class)) {
            obj3 = Short.valueOf(obj2);
        } else if (cs.equals(byte.class) || cs.equals(Byte.class)) {
            obj3 = Byte.valueOf(obj2);
        } else if (cs.equals(float.class) || cs.equals(Float.class)) {
            obj3 = Float.valueOf(obj2);
        } else if (cs.equals(double.class) || cs.equals(Double.class)) {
            obj3 = Double.valueOf(obj2);
        } else if (cs.equals(boolean.class) || cs.equals(Boolean.class)) {
            obj3 = Boolean.valueOf(obj2);
        } else {
            obj3 = (T) obj;
        }
        return (T) obj3;
    }

    public static BiFunction<String, String, Integer> testF = (k,v)-> {
        return 1;
    };


}

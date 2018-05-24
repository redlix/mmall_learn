package com.mmall.util;

import com.google.common.collect.Lists;
import com.mmall.pojo.TestPojo;
import com.mmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author redLi
 * @package com.mmall.util
 * @time 2018/03/14 21:47
 * @description:
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空bean转json错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下样式,即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));
        //忽略在json字符串存在，但是在java对象中不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String objToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    public static <T> String objToStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * 泛型反序列化
     *
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T stringToObj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * ...代表可传一个参数，也可传多个参数
     *
     * @param str
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T stringToObj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        TestPojo testPojo = new TestPojo();
        testPojo.setName("redli");
        testPojo.setId(666);

//        //{"name":"Geely","id":666}
        String json = "{\"name\":\"redli\",\"color\":\"blue\",\"id\":666}";
        TestPojo testPojoObject = JsonUtil.stringToObj(json, TestPojo.class);
        String testPojoJson = JsonUtil.objToString(testPojo);
        log.info("testPojoJson:{}", testPojoJson);
        log.info("end");

        User user = new User();
        user.setId(2);
        user.setEmail("redli@redli.xin");
        user.setCreateTime(new Date());
        String userJsonPretty = JsonUtil.objToStringPretty(user);
        log.info("userJson:{}", userJsonPretty);

        User u2 = new User();
        u2.setId(2);
        u2.setEmail("redli1@redli.xin");
        String user1Json = JsonUtil.objToString(u2);
        String user1JsonPretty = JsonUtil.objToStringPretty(u2);
        log.info("user1Json:{}", user1Json);
        log.info("user1JsonPretty:{}", user1JsonPretty);

        User user1 = JsonUtil.stringToObj(user1Json, User.class);

        List<User> userList = Lists.newArrayList();
        userList.add(user1);
        userList.add(u2);
        String userListStr = JsonUtil.objToStringPretty(userList);
        log.info("==================");
        log.info(userListStr);
        List<User> userListObj1 = JsonUtil.stringToObj(userListStr, new TypeReference<List<User>>() {
        });
        List<User> userListObj2 = JsonUtil.stringToObj(userListStr, List.class, User.class);
        System.out.println("end");
    }
}

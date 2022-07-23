package com.pn.config.redis;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class Md5KeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        String value = method.getName().concat(Arrays.stream(objects)
                .map(Object::toString)
                .collect(Collectors.joining(",")));
        return DigestUtils.md5Hex(value);
    }
}

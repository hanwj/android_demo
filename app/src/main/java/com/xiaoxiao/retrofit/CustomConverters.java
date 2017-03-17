package com.xiaoxiao.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by caixiaoxiao on 22/2/17.
 */
final public class CustomConverters extends Converter.Factory{
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (String.class == type){
            return RequestBodyConverter.INSTANCE;
        }
        return null;
    }

    static final class RequestBodyConverter implements Converter<String,RequestBody>{
        static final RequestBodyConverter INSTANCE = new RequestBodyConverter();
        @Override
        public RequestBody convert(String value) throws IOException {
           return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),value);
        }
    }
}

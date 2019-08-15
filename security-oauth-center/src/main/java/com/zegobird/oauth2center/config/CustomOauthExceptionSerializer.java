package com.zegobird.oauth2center.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/13 15:18
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    public CustomOauthExceptionSerializer(){
        super(CustomOauthException.class);
    }
    //重写序列化实现
    @Override
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("error",String.valueOf(e.getHttpErrorCode()));
        jsonGenerator.writeStringField("message","用户名或密码错误");//e.getMessage()
        jsonGenerator.writeStringField("path",request.getServletPath());
        jsonGenerator.writeStringField("timestamp",String.valueOf(new Date().getTime()));
        if(e.getAdditionalInformation()!=null){
            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }
}

package com.dress.collocation.springmvc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Description:返回值处理
 * User:Xue jiahao (xuejiahao@raycloud.com)
 * Date: 16/10/12
 * Time: 16:33
 * Version: 1.0
 **/
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public final static Charset UTF8 = Charset.forName("UTF-8");

    private SerializerFeature[] features = new SerializerFeature[0];

    protected SerializeFilter[] serialzeFilters;

    public FastJsonHttpMessageConverter(){
        serialzeFilters = new SerializeFilter[]{};
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(obj,serialzeFilters , features);
        byte[] bytes = text.getBytes(UTF8);
        out.write(bytes);
    }
}

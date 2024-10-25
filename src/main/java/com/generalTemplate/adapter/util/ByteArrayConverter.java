package com.generalTemplate.adapter.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteArrayConverter {

    public static String convertFromByteArray(byte[] content){
        Charset charset = StandardCharsets.US_ASCII;
        return charset.decode(ByteBuffer.wrap(content)).toString();
    }
    public static byte[] convertToByteArray(String content){
        Charset charset = StandardCharsets.US_ASCII;
        return charset.encode(content).array();
    }
}

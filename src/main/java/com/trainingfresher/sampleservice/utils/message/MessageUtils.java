package com.trainingfresher.sampleservice.utils.message;

import org.springframework.util.ObjectUtils;


/**
 * @author Nhat
 */
public class MessageUtils {

    public static String getMessage(String message) {

        if (ObjectUtils.isEmpty(message)) {
            return "";
        }
        return message;
    }

}

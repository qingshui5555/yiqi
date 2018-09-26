package com.health.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class i18n {

    public static Locale local=Locale.CHINA;

    public static String getMessage(String code, Object... args){
        String codedMessage = "";
        try{
            ResourceBundle rb = ResourceBundle.getBundle("i18n.messages");
            String temp = rb.getString(code);
            if (args.length > 0){
                Object[] argarr = args.clone();
                int i=0;
                for(Object obj:args){
                    String arg = obj.toString();
                    try{
                        arg =rb.getString(obj.toString());
                    }catch (Exception e1){
                         e1.printStackTrace();
                    }
                    argarr[i]=arg;
                    i++;
                }
                temp = MessageFormat.format(temp, argarr);
            }
            codedMessage =   temp;
        } catch (Exception ex){
            codedMessage = "";
        }

        return codedMessage;
    }

    public static String getText(String key){
        String codedText = "";
        try{
            ResourceBundle rb = ResourceBundle.getBundle("i18n.contents.dictionary");
            String temp = rb.getString(key);
            codedText = temp;
        } catch (Exception ex){
            codedText = "";
        }
        return codedText;
    }
}

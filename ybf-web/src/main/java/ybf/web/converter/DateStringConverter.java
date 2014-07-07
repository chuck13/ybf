package yb.snf.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 〈一句话功能简述〉<br> Converter
 * 〈功能详细描述〉实现dateTostring转换
 */
public class DateStringConverter extends DateConverterBase implements Converter<Date, String> {

    @Override
    public String convert(Date source) {
        if (source == null){
            return "";
        }
        return getDateFormat().format(source);
    }

}

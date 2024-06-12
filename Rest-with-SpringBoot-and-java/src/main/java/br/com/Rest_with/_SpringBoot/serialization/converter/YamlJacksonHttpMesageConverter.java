package br.com.Rest_with._SpringBoot.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJacksonHttpMesageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJacksonHttpMesageConverter() {
        super(
                new YAMLMapper()
                    .setSerializationInclusion(
                        JsonInclude
                                .Include.NON_NULL),
                                 MediaType.parseMediaType("application/x-yaml"));

    }
}

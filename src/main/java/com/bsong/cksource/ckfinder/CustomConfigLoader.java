package com.bsong.cksource.ckfinder;

import com.cksource.ckfinder.config.Config;
import com.cksource.ckfinder.config.loader.ConfigLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import javax.inject.Named;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Named
public class CustomConfigLoader implements ConfigLoader {
    @Override
    public Config loadConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Path configPath = Paths.get("/home/ckfinder.yml");

        return mapper.readValue(Files.newInputStream(configPath), Config.class);
    }
}
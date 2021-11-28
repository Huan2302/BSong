package com.bsong.cksource.ckfinder.listener;

import com.cksource.ckfinder.config.Config;
import com.cksource.ckfinder.event.GetConfigForRequestEvent;
import com.cksource.ckfinder.exception.InvalidRequestException;
import com.cksource.ckfinder.listener.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Named
public class PerRequestConfigListener implements Listener<GetConfigForRequestEvent> {
    private static final Logger logger = LoggerFactory.getLogger(PerRequestConfigListener.class);

    @Inject
    private HttpSession session;

    @Override
    public void onApplicationEvent(GetConfigForRequestEvent event) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            throw new InvalidRequestException("Username is not set");
        }

        // It is assumed that the username is safe to use as a part of the file system path.
        // You might need to perform some validation to avoid path traversal security issues.
        String userFolderPath = "/tmp/" + username;

        Path dirPath = Paths.get(userFolderPath);

        // If the directory does not exist, create one.
        if (!Files.exists(dirPath)) {
            logger.info(String.format("Private directory for user \"%s\" doesn't exist", username));

            try {
                Files.createDirectories(dirPath);
                logger.info(String.format("Created private directory for user \"%s\" under %s", username, dirPath));
            } catch (IOException e) {
                logger.error(String.format("Could not create private directory for user \"%s\" under %s", username, dirPath));
            }
        }

        // Update the configuration and use the path as the root of the backend named "default".
        Config.Backend backendConfig = event.getConfig().getBackendConfig("default");

        backendConfig.setRoot(userFolderPath);
    }
}
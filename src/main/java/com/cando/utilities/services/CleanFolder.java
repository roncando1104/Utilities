package com.cando.utilities.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CleanFolder {

  Logger LOGGER = LoggerFactory.getLogger(CleanFolder.class);

  static void clean(Path folder) {

    Function<Path, Stream<Path>> walk = p -> {
      try {
        return Files.walk(p);
      } catch (IOException e) {
        return Stream.empty();
      }};

    Consumer<Path> delete = p -> {
      try {
        Files.delete(p);
      } catch (IOException e) {
        LOGGER.info("Delete info: {}", e.getMessage());
      }
    };

    try (Stream<Path> paths = Files.list(folder)) {
      paths.flatMap(walk)
          .sorted(Comparator.reverseOrder())
          .forEach(delete);
    } catch (IOException e) {
      LOGGER.info("Files from {} has been deleted.", folder);
      LOGGER.info("Folder info: {}", e.getMessage());
    }
  }
}

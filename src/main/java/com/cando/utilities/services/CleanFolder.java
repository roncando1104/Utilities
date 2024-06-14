package com.cando.utilities.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public interface CleanFolder {
  static void clean(Path folder) throws IOException {

    Function<Path, Stream<Path>> walk = p -> {
      try { return Files.walk(p);
      } catch (IOException e) {
        return Stream.empty();
      }};

    Consumer<Path> delete = p -> {
      try {
        Files.delete(p);
      } catch (IOException e) {
      }
    };

    Files.list(folder)
        .flatMap(walk)
        .sorted(Comparator.reverseOrder())
        .forEach(delete);
  }
}

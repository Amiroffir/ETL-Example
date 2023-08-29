package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface FileParser {
 ParsedFile parseFile(File file);
}

 class JsonFileParser implements FileParser {
  @Override
  public ParsedFile parseFile(File file) {
   return null;
  }

 }

 class TextFileParser implements FileParser {


  @Override
  public ParsedFile parseFile(File file) {
   return null;
  }
 }
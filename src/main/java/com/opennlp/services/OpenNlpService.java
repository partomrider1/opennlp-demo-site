package com.opennlp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opennlp.core.OpenNLPLoader;

@Service
public class OpenNlpService {

  @Autowired
  private OpenNLPLoader openNLPLoader;

  public String[] detectSentences(String text) {
    return openNLPLoader.getSentenceDetector().sentDetect(text);
  }
}

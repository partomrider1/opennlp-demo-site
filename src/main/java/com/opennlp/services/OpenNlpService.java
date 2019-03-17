package com.opennlp.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opennlp.core.OpenNLPLoader;
import opennlp.tools.util.Span;

@Service
public class OpenNlpService {

  @Autowired
  private OpenNLPLoader openNLPLoader;

  public String[] detectSentences(String text) {
    return openNLPLoader.getSentenceDetector().sentDetect(text);
  }

  public String[] detectTokens(String text) {
    return openNLPLoader.getTokenizer().tokenize(text);
  }

  public String[] detectPOSTags(String text) {
    String [] tokens = this.detectTokens(text);
    String [] tags = openNLPLoader.getPosTaggerME().tag(tokens);
    String [] output= new String[tokens.length];
    for(int i=0; i<tokens.length; i++) {
      output[i] = tokens[i] + " : " + tags[i];
    }
    return output;
  }

  public String[] detectNER(String text) {
    String [] tokens = this.detectTokens(text);
    List<String> names = new ArrayList();
    Span[] spans = openNLPLoader.getPersonNameFinderME().find(tokens);
    for (Span s: spans) {
      names.add(tokens[s.getStart()]);
    }
    String[] ner = new String[names.size()];
    return names.toArray(ner);
  }
}

package com.opennlp.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

@Component
public class OpenNLPLoader {

  private Log logger = LogFactory.getLog(OpenNLPLoader.class);
  private TokenizerME tokenizer;
  private SentenceDetectorME sentenceDetector;
  private POSTaggerME posTaggerME;
  private NameFinderME personNameFinderME;
  private ChunkerME chunkerME;
  private ThreadLocal<Parser> parsers = null;

  @PostConstruct
  public void init() {
    try (InputStream inputStream1 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-chunker.bin");
        InputStream inputStream2 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-token.bin");
        InputStream inputStream3 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-sent.bin");
        InputStream inputStream4 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-pos-maxent.bin");
        InputStream inputStream5 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-ner-person.bin");
        InputStream inputStream6 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-parser-chunking.bin")) {
      tokenizer = new TokenizerME(new TokenizerModel(inputStream2));
      sentenceDetector = new SentenceDetectorME(new SentenceModel(inputStream3));
      posTaggerME = new POSTaggerME(new POSModel(inputStream4));
      personNameFinderME = new NameFinderME(new TokenNameFinderModel(inputStream5));
      chunkerME = new ChunkerME(new ChunkerModel(inputStream1));
      ParserModel PARSER_MODEL = new ParserModel(inputStream6);
      parsers = ThreadLocal.withInitial(() -> ParserFactory.create(PARSER_MODEL));
    } catch (IOException e) {
      logger.error("Failed to initialize OpenNLP parser : ", e);
    }
  }

  public TokenizerME getTokenizer() {
    return tokenizer;
  }

  public SentenceDetectorME getSentenceDetector() {
    return sentenceDetector;
  }

  public POSTaggerME getPosTaggerME() {
    return posTaggerME;
  }

  public NameFinderME getPersonNameFinderME() {
    return personNameFinderME;
  }

  public ChunkerME getChunkerME() {
    return chunkerME;
  }

  public List<String> getNounPhrases(String text) {
    Set<String> nounPhrases = new HashSet<>();
    try {
      Parse topParses[] = ParserTool.parseLine(text, parsers.get(), tokenizer, 500);
      for (Parse p : topParses) {
        getNounPhrases(p, nounPhrases);
      }
      return new ArrayList<>(nounPhrases);
    } catch (Exception e) {
      logger.error("Error while extracting noun phrases from text : " + text + " :: " , e);
    }finally {
      parsers.remove();
    }

    return null;
  }

  private Set<String> getNounPhrases(Parse p, Set<String> nounPhrases) {

    if (p.getType().equals("NP")) { //NP=noun phrase
      if (!p.getCoveredText().isEmpty() && p.getCoveredText().split(" ").length > 1 && p.getCoveredText().split(" ").length < 4) {
        nounPhrases.add(p.getCoveredText());
      }
    }
    for (Parse child : p.getChildren())
      getNounPhrases(child, nounPhrases);

    return nounPhrases;
  }
}

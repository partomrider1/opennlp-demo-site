package com.opennlp.core;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
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
  private final int NUM_OF_PARSERS = 50;

  @PostConstruct
  public void init() {
    try (InputStream inputStream1 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-parser-chunking.bin");
        InputStream inputStream2 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-token.bin");
        InputStream inputStream3 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-sent.bin");
        InputStream inputStream4 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-pos-maxent.bin");
        InputStream inputStream5 = OpenNLPLoader.class.getClassLoader().getResourceAsStream("en-ner-person.bin")) {
      ParserModel PARSER_MODEL = new ParserModel(inputStream1);
      tokenizer = new TokenizerME(new TokenizerModel(inputStream2));
      sentenceDetector = new SentenceDetectorME(new SentenceModel(inputStream3));
      posTaggerME = new POSTaggerME(new POSModel(inputStream4));
      personNameFinderME = new NameFinderME(new TokenNameFinderModel(inputStream5));
      ThreadLocal<Parser> parsers = new ThreadLocal<Parser>() {
        @Override
        public Parser initialValue() {
          return ParserFactory.create(PARSER_MODEL);
        }
      };
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
}

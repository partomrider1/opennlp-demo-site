// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  serverUrl: 'http://localhost:8081/api/nlp',
  placeholders: {
    'Sentence Detection': `//Loading sentence detector model\nInputStream inputStream = new FileInputStream("C:/OpenNLP_models/en-sent.bin");\nSentenceModel model = new SentenceModel(inputStream); 
\n//Instantiating the SentenceDetectorME class\nSentenceDetectorME detector = new SentenceDetectorME(model);\n\n//Detecting the sentence\nString sentences[] = detector.sentDetect(sentence); `,
    'Tokenization': `//Instantiating SimpleTokenizer classSimpleTokenizer\nsimpleTokenizer = SimpleTokenizer.INSTANCE;\n\n//Tokenizing the given sentence\nString tokens[] = simpleTokenizer.tokenize(sentence);`,
    'Name Entity Recognition': `//Loading the NER-person model\nInputStream inputStreamNameFinder = new FileInputStream(".../en-nerperson.bin");\nTokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
\n//Instantiating the NameFinderME class\nNameFinderME nameFinder = new NameFinderME(model);\n\n//Finding the names in the sentence\nSpan nameSpans[] = nameFinder.find(sentence);`,
    'Finding Parts Of Speech': `//Loading Parts of speech-maxent model\nInputStream inputStream = new FileInputStream("C:/OpenNLP_models/en-pos-maxent.bin");\nPOSModel model = new POSModel(inputStream);
\n//Instantiating POSTaggerME class\nPOSTaggerME tagger = new POSTaggerME(model);\n\n//Tokenizing the sentence using WhitespaceTokenizer class\nWhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
String[] tokens = whitespaceTokenizer.tokenize(sentence);\n\n//Generating tags\nString[] tags = tagger.tag(tokens);`,
    'Parsing The Sentences': `//Loading parser model\nInputStream inputStream = new FileInputStream(".../en-parserchunking.bin");\nParserModel model = new ParserModel(inputStream);\n\n//Creating a parser
Parser parser = ParserFactory.create(model);\n\n//Parsing the sentence\nParse topParses[] = ParserTool.parseLine(sentence, parser, 1);`,
    'Chunking Sentences': `//Tokenizing the sentence\nWhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;\nString[] tokens = whitespaceTokenizer.tokenize(sentence);\n
//Generating the POS tags\nFile file = new File("C:/OpenNLP_models/en-pos-maxent.bin");\nPOSModel model = new POSModelLoader().load(file);\n\n//Constructing the tagger\nPOSTaggerME tagger = new POSTaggerME(model);
\n//Generating tags from the tokens\nString[] tags = tagger.tag(tokens);\n\n//Loading the chunker model\nInputStream inputStream = new FileInputStream("C:/OpenNLP_models/en-chunker.bin");\nChunkerModel chunkerModel = new ChunkerModel(inputStream);
\n//Instantiate the ChunkerME class\nChunkerME chunkerME = new ChunkerME(chunkerModel);`
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.

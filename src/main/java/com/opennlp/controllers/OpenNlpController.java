package com.opennlp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.opennlp.domain.NLPRequest;
import com.opennlp.domain.NLPResponse;
import com.opennlp.services.OpenNlpService;

@RestController
@RequestMapping("/api/nlp")
public class OpenNlpController {

  @Autowired
  private OpenNlpService nlpService;

  @PostMapping("/sentences")
  @ResponseBody
  public NLPResponse detectSentences(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectSentences(request.getText()));
    return nlpResponse;
  }

  @PostMapping("/tokens")
  @ResponseBody
  public NLPResponse detectTokens(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectTokens(request.getText()));
    return nlpResponse;
  }

  @PostMapping("/POSTags")
  @ResponseBody
  public NLPResponse detectPOSTags(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectPOSTags(request.getText()));
    return nlpResponse;
  }

  @PostMapping("/NER")
  @ResponseBody
  public NLPResponse detectNER(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectNER(request.getText()));
    return nlpResponse;
  }

  @PostMapping("/chunks")
  @ResponseBody
  public NLPResponse detectSentenceChunks(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectSentenceChunks(request.getText()));
    return nlpResponse;
  }

  @PostMapping("/nounPhrases")
  @ResponseBody
  public NLPResponse detectNounPhrases(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectNounPhrases(request.getText()));
    return nlpResponse;
  }

}

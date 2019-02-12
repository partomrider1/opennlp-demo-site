package com.opennlp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.opennlp.domain.NLPRequest;
import com.opennlp.domain.NLPResponse;
import com.opennlp.services.OpenNlpService;

@RestController
public class OpenNlpController {

  @Autowired
  private OpenNlpService nlpService;

  @PostMapping("/api/sentences")
  @ResponseBody
  public NLPResponse detectSentences(@RequestBody NLPRequest request) {
    NLPResponse nlpResponse = new NLPResponse();
    nlpResponse.setResponse(nlpService.detectSentences(request.getText()));
    return nlpResponse;
  }

}

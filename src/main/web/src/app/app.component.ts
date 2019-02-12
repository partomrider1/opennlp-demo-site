import { Component } from '@angular/core';
import { NlpAggregatorService } from './services/nlp-aggregator-service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent{
  title = 'open-nlp-ui';

  constructor(private nlpAggregatorService: NlpAggregatorService) {
  }
}

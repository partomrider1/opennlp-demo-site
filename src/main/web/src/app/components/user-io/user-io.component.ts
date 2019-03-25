import { Component, OnInit, ViewChild, Renderer2 } from '@angular/core';
import { GlobalEventService } from 'src/app/services/global-event-service';
import { environment } from '../../../environments/environment';
import { NlpAggregatorService } from 'src/app/services/nlp-aggregator-service';

@Component({
  selector: 'app-user-io',
  templateUrl: './user-io.component.html',
  styleUrls: ['./user-io.component.css']
})
export class UserIoComponent implements OnInit {

  constructor(private globalEventService: GlobalEventService, private renderer: Renderer2, private nlpAggregatorService: NlpAggregatorService) { }
  private subscriptions = [];
  @ViewChild ('userInput') userInput;
  @ViewChild ('result') result;

  ngOnInit() {
    this.subscriptions.push(this.globalEventService.refreshInput.subscribe((event) => {
      this.renderer.setProperty(this.userInput.nativeElement, 'value', '');
      this.renderer.setProperty(this.result.nativeElement, 'innerHTML', '');
      this.renderer.setProperty(this.userInput.nativeElement, 'placeholder', environment.placeholders[event.prop]);
    }));
  }

  ngOnDestroy() {
    this.subscriptions.forEach( subscription => subscription.unsubscribe());
  }

  triggerProcess() {
    let text = {text: this.userInput.nativeElement.value};
    let prop = this.globalEventService.getSelectedFeature();
    this.subscriptions.push(this.nlpAggregatorService.getParsedSentences(prop, text)
    .then(response => {
      this.renderer.setProperty(this.result.nativeElement, 'innerHTML', this.renderResults(response['response']));
    })
    .catch(error => console.log(JSON.stringify(error)))
    );
  }

  renderResults(resultArr) {
    let responseHTML = '';
    for(let i = 0; i < resultArr.length; i++) {
      responseHTML += i + ' : ' + resultArr[i];
      if(i !== resultArr.length - 1) {
        responseHTML += '<br /><br />';
      }
    }
    return responseHTML;
  }

}

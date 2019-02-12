import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { GlobalEventService } from 'src/app/services/global-event-service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit, AfterViewInit {

  @ViewChild ('sideNav') sideNav;
  @ViewChild('optionsList') optionsList;
  OptionElems = ['Sentence Detection', 'Tokenization', 'Name Entity Recognition', 'Finding Parts Of Speech', 'Parsing The Sentences', 'Chunking Sentences'];

  constructor(private globalEventService: GlobalEventService) { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.optionsList.nativeElement.children[0].click();
  }

  resetActiveState() {
    for(var elem = 0; elem < this.optionsList.nativeElement.children.length; elem ++) {
      this.optionsList.nativeElement.children[elem].classList.remove('active');
    };
  }

  toggleSideNav() {
    this.sideNav.toggle();
  }

  handleListClick(event) {
    event.preventDefault();
    this.resetActiveState();
    event.target.classList.toggle('active');
    this.globalEventService.refreshInputState(event.target.innerText);
  }

}

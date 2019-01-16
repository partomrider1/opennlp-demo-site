import { Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  @ViewChild ('sideNav') sideNav;

  constructor() { }

  ngOnInit() {
  }

  toggleSideNav() {
    this.sideNav.toggle();
  }

}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { CommonMaterialModule } from './components/material/material-module';
import { UserIoComponent } from './components/user-io/user-io.component';
import { NlpAggregatorService } from './services/nlp-aggregator-service';
import { GlobalEventService } from './services/global-event-service';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    UserIoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    CommonMaterialModule
  ],
  providers: [NlpAggregatorService, GlobalEventService],
  bootstrap: [AppComponent]
})
export class AppModule { }

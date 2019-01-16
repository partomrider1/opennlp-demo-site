import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { CommonMaterialModule } from './components/material/material-module';
import { UserIoComponent } from './components/user-io/user-io.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    UserIoComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    CommonMaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

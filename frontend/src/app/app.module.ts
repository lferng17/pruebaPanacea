import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnalizadorSeoComponent } from './components/analizador-seo/analizador-seo.component';
import { FormsModule } from '@angular/forms';
import { AnalizadorSeoService } from './services/analizador-seo.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AnalizadorSeoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AnalizadorSeoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

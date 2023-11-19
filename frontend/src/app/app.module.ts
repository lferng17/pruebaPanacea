import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuscadorSeoComponent } from './components/buscador-seo/buscador-seo.component';
import { FormsModule } from '@angular/forms';
import { AnalizadorSeoService } from './services/analizador-seo.service';
import { HttpClientModule } from '@angular/common/http';
import { InformacionEnlaceComponent } from './components/informacion-enlace/informacion-enlace.component';
import { RouterModule, Routes } from '@angular/router';
import { HistorialComponent } from './components/historial/historial.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { ResultsPageComponent } from './pages/results-page/results-page.component';

const appRoutes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'analisis/:id', component: ResultsPageComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    BuscadorSeoComponent,
    InformacionEnlaceComponent,
    HistorialComponent,
    LandingPageComponent,
    ResultsPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    AnalizadorSeoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

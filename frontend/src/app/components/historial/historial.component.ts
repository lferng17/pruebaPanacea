import { Component, OnInit } from '@angular/core';
import { AnalizadorSeoService } from 'src/app/services/analizador-seo.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit{

  url: string = '';
  urls: any;


  constructor(private analizadorSeoService: AnalizadorSeoService) {}

  ngOnInit() {
    //this.obtenerAnalisis();
    this.analizadorSeoService.obtenerTodasLasURLs().subscribe(data=>{
      this.urls = data;
      console.log(data);
    }, error=>{
      console.log(error);
    })
  }

}

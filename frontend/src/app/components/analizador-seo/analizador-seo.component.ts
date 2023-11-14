import { Component, OnInit } from '@angular/core';
import { AnalizadorSeoService } from 'src/app/services/analizador-seo.service';

@Component({
  selector: 'app-analizador-seo',
  templateUrl: './analizador-seo.component.html',
  styleUrls: ['./analizador-seo.component.css'],
})
export class AnalizadorSeoComponent implements OnInit{
  url: string = '';
  result: any;
  analisisList: any[] = [];

  constructor(private analizadorSeoService: AnalizadorSeoService) {}

  ngOnInit() {
    //this.obtenerAnalisis();
  }

  guardarURL() {
    if (this.url.trim() !== '') {
      this.analizadorSeoService.guardarURL(this.url).subscribe(
        data => {
          console.log('URL guardada correctamente:', data);

        },
        error => {
          console.error('Error al guardar la URL:', error);
        }
      );
    } else {
      console.warn('La URL está vacía');
    }
  }
}

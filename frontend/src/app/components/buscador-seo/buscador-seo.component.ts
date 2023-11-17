import { Component, OnInit } from '@angular/core';
import { AnalizadorSeoService } from 'src/app/services/analizador-seo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buscador-seo',
  templateUrl: './buscador-seo.component.html',
  styleUrls: ['./buscador-seo.component.css'],
})
export class BuscadorSeoComponent implements OnInit{
  url: string = '';
  urls: any;
  result: any;
  analisisList: any[] = [];

  constructor(private analizadorSeoService: AnalizadorSeoService, private router: Router) {}

  ngOnInit() {
    this.analizadorSeoService.obtenerTodasLasURLs().subscribe(data=>{
      this.urls = data;
    }, error=>{
      console.log(error);
    })
  }

  guardarURL() {
    if (this.url.trim() !== '') {
      this.analizadorSeoService.guardarURL(this.url).subscribe(
        data => {
          // EL IF NO FUNCIONA PORQUE DATA NO ALMACENA EL STATUS CODE
          if (data.status === 201) {
            console.log('URL guardada correctamente:', data);
          } else if (data.status === 200) {
            console.warn('La URL ya existe en la base de datos. Puedes ver el informe existente.', data);
          }
          this.router.navigate(['/analisis', data.id]);
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

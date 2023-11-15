import { Component, OnInit } from '@angular/core';
import { AnalizadorSeoService } from 'src/app/services/analizador-seo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-analizador-seo',
  templateUrl: './analizador-seo.component.html',
  styleUrls: ['./analizador-seo.component.css'],
})
export class AnalizadorSeoComponent implements OnInit{
  url: string = '';
  urls: any;
  result: any;
  analisisList: any[] = [];

  constructor(private analizadorSeoService: AnalizadorSeoService, private router: Router) {}

  ngOnInit() {
    //this.obtenerAnalisis();
    this.analizadorSeoService.obtenerTodasLasURLs().subscribe(data=>{
      this.urls = data;
      console.log(data);
    }, error=>{
      console.log(error);
    })
  }

  guardarURL() {
    if (this.url.trim() !== '') {
      this.analizadorSeoService.guardarURL(this.url).subscribe(
        data => {
          console.log('URL guardada correctamente:', data);
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

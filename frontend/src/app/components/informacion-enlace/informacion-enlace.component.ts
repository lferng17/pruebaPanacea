import { Component, OnInit } from '@angular/core';
 import { ActivatedRoute } from '@angular/router';
 import { AnalizadorSeoService } from 'src/app/services/analizador-seo.service';

 @Component({
   selector: 'app-informacion-enlace',
   templateUrl: './informacion-enlace.component.html',
   styleUrls: ['./informacion-enlace.component.css']
 })
 export class InformacionEnlaceComponent implements OnInit{
   urlId: number = 0;
   url: any = {}; 

   constructor(private route: ActivatedRoute, private analizadorSeoService: AnalizadorSeoService) { }

   ngOnInit() {
     // Obtener el ID de la URL desde la ruta
     this.route.params.subscribe(params => {
       this.urlId = +params['id'];
       console.log('ID de la URL:', this.urlId);
       // Obtener detalles de la URL por ID
       this.analizadorSeoService.obtenerURL(this.urlId).subscribe(
         data => {
           this.url = data;
         },
         error => {
           console.error('Error al obtener detalles de la URL:', error);
         }
       );
     });
   }
 }
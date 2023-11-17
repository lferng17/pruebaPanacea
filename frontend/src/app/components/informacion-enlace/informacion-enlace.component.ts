import { Component, OnInit } from '@angular/core';
 import { ActivatedRoute, Router } from '@angular/router';
 import { AnalizadorSeoService } from 'src/app/services/analizador-seo.service';

 @Component({
   selector: 'app-informacion-enlace',
   templateUrl: './informacion-enlace.component.html',
   styleUrls: ['./informacion-enlace.component.css']
 })
 export class InformacionEnlaceComponent implements OnInit{
   urlId: number = 0;
   url: any = {}; 

   constructor(private route: ActivatedRoute, private router: Router, private analizadorSeoService: AnalizadorSeoService) { }

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

   eliminarURL() {
    if (confirm('¿Estás seguro de que quieres eliminar esta URL?')) {
      this.analizadorSeoService.deleteURL(this.urlId).subscribe(
        () => {
          console.log('URL eliminada correctamente');
          // Redirigir a la página principal
          this.router.navigate(['/']);
        },
        error => {
          this.router.navigate(['/']);
          console.error('Error al eliminar URL:', error);
        }
      );
    }
  }

 }
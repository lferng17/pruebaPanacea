import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnalizadorSeoService {

  private apiServer = 'http://localhost:8080/api/analisis/';

  constructor(private httpClient: HttpClient) { }

  guardarURL(url: string): Observable<any> {
    const body = { url: url };
    return this.httpClient.post(this.apiServer, body);
  }

  obtenerId(url: string): Observable<any> {
    return this.httpClient.get(`${this.apiServer}id?url=${encodeURIComponent(url)}`);
  }

  obtenerURL(id: number): Observable<any> {
    return this.httpClient.get(`${this.apiServer}${id}`);

  }

  obtenerTodasLasURLs(): Observable<any> {
    return this.httpClient.get(this.apiServer);
  }

  deleteURL(id: number): Observable<any> {
    return this.httpClient.delete(`${this.apiServer}${id}`);
  }
  
}

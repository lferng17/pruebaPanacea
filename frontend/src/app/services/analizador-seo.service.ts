import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnalizadorSeoService {

  private baseUrl = 'http://localhost:8080/api/analisis/';

  constructor(private http: HttpClient) { }

  guardarURL(url: string): Observable<any> {
    const body = { url: url };
    return this.http.post(this.baseUrl, body);
  }

  
}

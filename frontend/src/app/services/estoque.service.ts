import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

export interface Produto {
  id: string;
  name: string;
  barcode: string;
  quantity: number;
}

export interface BaixaRequest {
  productId: string;
  quantity: number;
  reason: string;
}

const API_URL = 'http://localhost:8080/api/v1';

@Injectable({ providedIn: 'root' })
export class EstoqueService {
  private http = inject(HttpClient);

  // Se o termo for só número, busca pelo código de barras; senão, pelo nome
  buscarProdutos(termo: string): Observable<Produto[]> {
    if (/^\d+$/.test(termo)) {
      return this.http
        .get<Produto>(`${API_URL}/products/ean/${termo}`)
        .pipe(map((produto) => [produto]));
    }
    return this.http.get<Produto[]>(`${API_URL}/products`, { params: { search: termo } });
  }

  registrarBaixa(baixa: BaixaRequest): Observable<void> {
    return this.http.post<void>(`${API_URL}/stock/write-off`, baixa);
  }
}

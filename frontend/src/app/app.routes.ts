import { Routes } from '@angular/router';
import { BaixaEstoque } from './baixa-estoque/baixa-estoque';

export const routes: Routes = [
  { path: 'estoque/baixa', component: BaixaEstoque },
  { path: '', redirectTo: 'estoque/baixa', pathMatch: 'full' },
];

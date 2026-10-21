import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { EstoqueService, Produto } from '../services/estoque.service';

@Component({
  selector: 'app-baixa-estoque',
  imports: [ReactiveFormsModule],
  templateUrl: './baixa-estoque.html',
  styleUrl: './baixa-estoque.css',
})
export class BaixaEstoque {
  private fb = inject(FormBuilder);
  private estoqueService = inject(EstoqueService);

  motivos = [
    { valor: 'QUEBRA', texto: 'Quebra' },
    { valor: 'DEFEITO', texto: 'Defeito' },
    { valor: 'PERDA', texto: 'Perda' },
  ];

  form = this.fb.group({
    produtoId: ['', Validators.required],
    quantidade: [1, [Validators.required, Validators.min(1)]],
    motivo: ['', Validators.required],
  });

  termoBusca = '';
  resultados = signal<Produto[]>([]);
  produtoSelecionado = signal<Produto | null>(null);
  mensagemBusca = signal('');
  erro = signal('');
  toast = signal('');
  enviando = signal(false);

  buscar() {
    const termo = this.termoBusca.trim();
    if (!termo) return;

    this.mensagemBusca.set('Buscando...');
    this.resultados.set([]);

    this.estoqueService.buscarProdutos(termo).subscribe({
      next: (produtos) => {
        this.resultados.set(produtos);
        this.mensagemBusca.set(produtos.length ? '' : 'Nenhum produto encontrado.');
      },
      error: () => this.mensagemBusca.set('Nenhum produto encontrado.'),
    });
  }

  selecionar(produto: Produto) {
    this.produtoSelecionado.set(produto);
    this.form.patchValue({ produtoId: produto.id });
    this.resultados.set([]);
    this.termoBusca = '';
  }

  trocarProduto() {
    this.produtoSelecionado.set(null);
    this.form.patchValue({ produtoId: '' });
  }

  enviar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const produto = this.produtoSelecionado()!;
    const { quantidade, motivo } = this.form.getRawValue();

    if (quantidade! > produto.quantity) {
      this.erro.set(`Só existem ${produto.quantity} unidades em estoque.`);
      return;
    }

    this.erro.set('');
    this.enviando.set(true);

    this.estoqueService
      .registrarBaixa({ productId: produto.id, quantity: quantidade!, reason: motivo! })
      .subscribe({
        next: () => {
          this.enviando.set(false);
          this.mostrarToast(`Baixa de ${quantidade} un. de "${produto.name}" registrada.`);
          this.form.reset({ produtoId: '', quantidade: 1, motivo: '' });
          this.produtoSelecionado.set(null);
        },
        error: () => {
          this.enviando.set(false);
          this.erro.set('Não foi possível registrar a baixa. Tente novamente.');
        },
      });
  }

  private mostrarToast(texto: string) {
    this.toast.set(texto);
    setTimeout(() => this.toast.set(''), 3000);
  }
}

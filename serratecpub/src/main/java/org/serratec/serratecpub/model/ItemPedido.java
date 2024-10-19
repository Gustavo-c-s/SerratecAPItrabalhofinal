package org.serratec.serratecpub.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantidade;
	private Double precoVenda;
	private int percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
	private Double valorDesconto;
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setPrecoVenda(Produto produto) {
		this.precoVenda = produto.getValorUnitario()*2;//multiplicar pela porcentagem de lucro da loja
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	public Long getId() {
		return id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public int getPercentualDesconto() {
		return percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setPercentualDesconto(int percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public void calcularValores() {
        this.valorBruto = this.precoVenda * this.quantidade;
        this.valorDesconto = this.valorBruto * (this.percentualDesconto / 100.0);
        this.valorLiquido = this.valorBruto - this.valorDesconto;
   }
}

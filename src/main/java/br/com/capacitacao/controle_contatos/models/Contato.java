package br.com.capacitacao.controle_contatos.models;

import br.com.capacitacao.controle_contatos.models.enums.TipoContatoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTATO")
public class Contato {
	
	//Atributos
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)//não pode ser nulo
	private TipoContatoEnum tipoContato;
	
	@Column(nullable = false)//não pode ser nulo
	private String contato;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoContatoEnum getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContatoEnum tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Contato(Long id, TipoContatoEnum tipoContato, String contato) {
		this.id = id;
		this.tipoContato = tipoContato;
		this.contato = contato;
	}
	
	public Contato() {}

	//sobescreve o objeto da classe;
	@Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", tipoContato=" + tipoContato +
                ", contato='" + contato + '\'' +
                ", pessoa=" + pessoa.getId() + // Evitar loop infinito no toString
                '}';
    }

}

package br.com.capacitacao.controle_contatos.record.post;

import br.com.capacitacao.controle_contatos.models.Pessoa;
import br.com.capacitacao.controle_contatos.models.enums.TipoContatoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class ContatoDTO {

    @Schema(example = "TELEFONE")
    private TipoContatoEnum tipoContato;

    @Schema(example = "(11)99999-9999")
    private String contato;

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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Schema(description = "Dados da pessoa associada ao contato")
    private Pessoa pessoa;

}

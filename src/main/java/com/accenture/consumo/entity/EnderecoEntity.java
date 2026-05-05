package com.accenture.consumo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "logradouro", length = 200)
    private String logradouro;

    @Column(name = "complemento", length = 200)
    private String complemento;

    @Column(name = "bairro", length = 150)
    private String bairro;

    @Column(name = "localidade", length = 150)
    private String localidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "ibge", length = 20)
    private String ibge;

    @Column(name = "ddd", length = 5)
    private String ddd;

    @Column(name = "siafi", length = 20)
    private String siafi;

    @Column(name = "consultado_em")
    private LocalDateTime consultadoEm;

    @PrePersist
    protected void onCreate() {
        this.consultadoEm = LocalDateTime.now();
    }

    public EnderecoEntity() {}

    public Long getId()                            { return id; }
    public void setId(Long id)                     { this.id = id; }

    public String getCep()                         { return cep; }
    public void setCep(String cep)                 { this.cep = cep; }

    public String getLogradouro()                  { return logradouro; }
    public void setLogradouro(String logradouro)   { this.logradouro = logradouro; }

    public String getComplemento()                 { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro()                      { return bairro; }
    public void setBairro(String bairro)           { this.bairro = bairro; }

    public String getLocalidade()                  { return localidade; }
    public void setLocalidade(String localidade)   { this.localidade = localidade; }

    public String getUf()                          { return uf; }
    public void setUf(String uf)                   { this.uf = uf; }

    public String getIbge()                        { return ibge; }
    public void setIbge(String ibge)               { this.ibge = ibge; }

    public String getDdd()                         { return ddd; }
    public void setDdd(String ddd)                 { this.ddd = ddd; }

    public String getSiafi()                       { return siafi; }
    public void setSiafi(String siafi)             { this.siafi = siafi; }

    public LocalDateTime getConsultadoEm()              { return consultadoEm; }
    public void setConsultadoEm(LocalDateTime v)        { this.consultadoEm = v; }
}

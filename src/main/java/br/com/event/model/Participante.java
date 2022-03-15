package br.com.event.model;

import java.util.Objects;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_PARTICIPANTE")
@SequenceGenerator(name = "participante", allocationSize = 1, sequenceName = "sq_participante")
public class Participante {

	@Id
	@GeneratedValue(generator = "participante", strategy = GenerationType.IDENTITY)
	@Column(name = "id_participante")
	private Long id;
	
	@Column(name = "nm_nome")
	private String nome;
	
	@Column(name = "ds_cpf")
	private String cpf;
	
	@Column(name = "dt_data")
	private String dataNascimento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;
	
	public Participante() {}
	
	public Participante(String nome, String cpf, String data) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, evento, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(evento, other.evento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}
}

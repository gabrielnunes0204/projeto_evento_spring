package br.com.event.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "TB_EVENTO")
@SequenceGenerator(name = "evento", allocationSize = 1, sequenceName = "sq_evento")
public class Evento {

	@Id
	@GeneratedValue(generator = "evento", strategy = GenerationType.IDENTITY)
	@Column(name = "id_evento")
	private Long id;
	
	@Column(name = "nm_nome")
	private String nome;
	
	@Column(name = "ds_local")
	private String local;
	
	@Column(name = "dt_data")
	private String data;
	
	@Column(name = "hr_horario")	
	private String horario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Participante> participantes;
	
	public Evento() {}
	
	public Evento(String nome, String local, String data, String horario) {
		this.nome = nome;
		this.local = local;
		this.data = data;
		this.horario = horario;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, horario, id, local, nome, participantes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(data, other.data) && Objects.equals(horario, other.horario)
				&& Objects.equals(id, other.id) && Objects.equals(local, other.local)
				&& Objects.equals(nome, other.nome) && Objects.equals(participantes, other.participantes);
	}
}

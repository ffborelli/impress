package br.ufabc.impress.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name = "fusion_log")
public class FusionLog implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "fusion_log_func", sequenceName = "fusion_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fusion_log_func")
	@Column(name = "id_fusion_log", nullable = false)
	private Integer id;
	
	@Column(name = "fusion_log_value", nullable = false)
	private String fusionLogValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_fusion_fk", referencedColumnName = "id_fusion")
	private Fusion fusion;
	
	@OneToMany(mappedBy = "fusionLog")
	private List<ResourceFusionLog> resourceFusionLog;
	
	@OneToMany(mappedBy = "fusionLog")
	private List<FusionRuleLog> fusionRuleLog;
	
	public FusionLog(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setFusionLogValue(String fusionLogValue){
		this.fusionLogValue = fusionLogValue;
	}
	
	public String getFusionLogValue(){
		return this.fusionLogValue;
	}
	
	public void setCreationDate(Date creationDate){
		this.creationDate = creationDate;
	}
	
	public Date getCreationDate(){
		return this.creationDate;
	}
	
	public void setFusion(Fusion fusion){
		this.fusion = fusion;
	}
	
	public Fusion getFusion(){
		return this.fusion;
	}
	
	public void setResourceFusionLog(List<ResourceFusionLog> resourceFusionLog){
		this.resourceFusionLog = resourceFusionLog;
	}
	
	public List<ResourceFusionLog> getResourceFusionLog(){
		return this.resourceFusionLog;
	}
	
	public void setFusionRuleLog(List<FusionRuleLog> fusionRuleLog){
		this.fusionRuleLog = fusionRuleLog;
	}
	
	public List<FusionRuleLog> getFusionRuleLog(){
		return this.fusionRuleLog;
	}
	
}

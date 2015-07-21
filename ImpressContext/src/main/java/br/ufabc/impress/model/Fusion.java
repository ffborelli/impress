package br.ufabc.impress.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@Table(name = "fusion")
public class Fusion implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "fusion_func", sequenceName = "fusion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fusion_func")
	@Column(name = "id_fusion", nullable = false)
	private Integer id;
	
	@Column(name = "fusion_name", nullable = false)
	private String fusionName;
	
	@Column(name = "fusion_text", nullable = false)
	private String fusionText;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@OneToMany(mappedBy = "fusion")
	private List<FusionLog> fusionLog;
	
	@OneToMany(mappedBy = "fusion")
	private List<ResourceFusion> resourceFusion;
	
	@OneToMany(mappedBy = "fusion")
	private List<FusionRule> fusionRule;
	
	@OneToMany(mappedBy = "fusion")
	private List<FusionContext> fusionContext;
	
	public Fusion(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setFusionName(String fusionName){
		this.fusionName = fusionName;
	}
	
	public String getFusionName(){
		return this.fusionName;
	}
	
	public void setFusionText(String fusionText){
		this.fusionText = fusionText;
	}
	
	public String getFusionText(){
		return this.fusionText;
	}
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}
	
	public void setFusionLog(List<FusionLog> fusionLog){
		this.fusionLog = fusionLog;
	}
	
	public List<FusionLog> getFusionLog(){
		return this.fusionLog;
	}
	
	public void setResourceFusion(List<ResourceFusion> resourceFusion){
		this.resourceFusion = resourceFusion;
	}
	
	public List<ResourceFusion> getResourceFusion(){
		return this.resourceFusion;
	}
	
	public void setFusionRule(List<FusionRule> fusionRule){
		this.fusionRule = fusionRule;
	}
	
	public List<FusionRule> getFusionRule(){
		return this.fusionRule;
	}
	
	public void setFusionContext(List<FusionContext> fusionContext){
		this.fusionContext = fusionContext;
	}
	
	public List<FusionContext> getFusionContext(){
		return this.fusionContext;
	}
	
}

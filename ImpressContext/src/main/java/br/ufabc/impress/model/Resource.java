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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name = "resource")
public class Resource implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "resource_func", sequenceName = "resource_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_func")
	@Column(name = "id_resource", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_type_fk", referencedColumnName = "id_resource_type")
	private ResourceType resourceType;
	
	@ManyToOne
	@JoinColumn(name = "id_place_fk", referencedColumnName = "id_place")
	private Place place;
	
	@OneToMany(mappedBy = "resource")
	private List<ResourceLog> resourceLog;
	
	@OneToMany(mappedBy = "resource")
	private List<ResourceFusion> resourceFusion;
	
	@OneToMany(mappedBy = "resource")
	private List<Action> action;
	
	@OneToMany(mappedBy = "resource")
	private List<RuleActionLog> ruleActionLog;
	
	@OneToMany(mappedBy = "resource")
	private List<ResourceContext> resourceContext;
	
	public Resource(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}
	
	public void setResourceType(ResourceType resourceType){
		this.resourceType = resourceType;
	}
	
	public ResourceType getResourceType(){
		return this.resourceType;
	}
	
	public void setPlace(Place place){
		this.place = place;
	}
	
	public Place getPlace(){
		return this.place;
	}
	
	public void setResourceLog(List<ResourceLog> resourceLog){
		this.resourceLog = resourceLog;
	}
	
	public List<ResourceLog> getResourceLog(){
		return this.resourceLog;
	}
	
	public void setResourceFusion(List<ResourceFusion> resourceFusion){
		this.resourceFusion = resourceFusion;
	}
	
	public List<ResourceFusion> getResourceFusion(){
		return this.resourceFusion;
	}
	
	public void setAction(List<Action> action){
		this.action = action;
	}
	
	public List<Action> getAction(){
		return this.action;
	}
	
	public void setRuleActionLog(List<RuleActionLog> ruleActionLog){
		this.ruleActionLog = ruleActionLog;
	}
	
	public List<RuleActionLog> getRuleActionLog(){
		return this.ruleActionLog;
	}
	
	public void setResourceContext(List<ResourceContext> resourceContext){
		this.resourceContext = resourceContext;
	}
	
	public List<ResourceContext> getResourceContext(){
		return this.resourceContext;
	}
	
}

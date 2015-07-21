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
@Table(name = "rsc_action_type")
public class ResourceActionType implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "rsc_action_type_func", sequenceName = "rsc_action_type_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rsc_action_type_func")
	@Column(name = "id_rsc_action_type", nullable = false)
	private Integer id;
	
	@Column(name = "rsc_action_type_type", nullable = false)
	private String resourceActionTypeText;
	
	@ManyToOne
	@JoinColumn(name = "id_resource_type_fk", referencedColumnName = "id_resource_type")
	private ResourceType resourceType;
	
	@OneToMany(mappedBy = "resourceActionType")
	private List<Action> action;
	
	@OneToMany(mappedBy = "resourceActionType")
	private List<RuleActionLog> ruleActionLog;
	
	public ResourceActionType(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setResourceActionTypeText(String resourceActionTypeText){
		this.resourceActionTypeText = resourceActionTypeText;
	}
	
	public String getResourceActionTypeText(){
		return this.resourceActionTypeText;
	}
	
	public void setResourceType(ResourceType resourceType){
		this.resourceType = resourceType;
	}
	
	public ResourceType getResourceType(){
		return this.resourceType;
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
	
}

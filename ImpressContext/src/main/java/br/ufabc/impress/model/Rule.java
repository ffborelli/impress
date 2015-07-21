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
@Table(name = "rule")
public class Rule implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public static final int DEPENDENT = 0;
	public static final int INDEPENDENT = 1;
	
	@Id
	@SequenceGenerator(name = "rule_func", sequenceName = "rule_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_func")
	@Column(name = "id_rule", nullable = false)
	private Integer id;
	
	@Column(name = "rule_name", nullable = false)
	private String ruleName;
	
	@Column(name = "rule_text", nullable = false)
	private String ruleText;
	
	@Column(name = "dependent_0_independent_1", nullable = false)
	private int dependentIndependent;
	
	@OneToMany(mappedBy = "rule")
	private List<Action> action;
	
	@OneToMany(mappedBy = "rule")
	private List<FusionRule> fusionRule;
	
	@OneToMany(mappedBy = "rule")
	private List<RuleActionLog> ruleActionLog;
	
	@OneToMany(mappedBy = "rule")
	private List<RuleContext> ruleContext;
	
	public Rule(){
		
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public void setRuleName(String ruleName){
		this.ruleName = ruleName;
	}
	
	public String getRuleName(){
		return this.ruleName;
	}
	
	public void setRuleText(String ruleText){
		this.ruleText = ruleText;
	}
	
	public String getRuleText(){
		return this.ruleText;
	}
	
	public void setDependentIndependent(int dependentIndependent){
		this.dependentIndependent = dependentIndependent;
	}
	
	public int getDependentIndependent(){
		return this.dependentIndependent;
	}
	
	public void setAction(List<Action> action){
		this.action = action;
	}
	
	public List<Action> getAction(){
		return this.action;
	}
	
	public void setFusionRule(List<FusionRule> fusionRule){
		this.fusionRule = fusionRule;
	}
	
	public List<FusionRule> getFusionRule(){
		return this.fusionRule;
	}
	
	public void setRuleActionLog(List<RuleActionLog> ruleActionLog){
		this.ruleActionLog = ruleActionLog;
	}
	
	public List<RuleActionLog> getRuleActionLog(){
		return this.ruleActionLog;
	}
	
	public void setRuleContext(List<RuleContext> ruleContext){
		this.ruleContext = ruleContext;
	}
	
	public List<RuleContext> getRuleContext(){
		return this.ruleContext;
	}
	
}

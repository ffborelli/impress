package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ContextCount;

public class ContextCountDAO extends GenericDAO<ContextCount> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContextCountDAO() {
		super(ContextCount.class);
	}

	public void delete(ContextCount obj) {
		super.delete(obj.getId(), ContextCount.class);
	}

	public List<ContextCount> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ContextCount> query = builder.createQuery(ContextCount.class);
			Root<ContextCount> variableRoot = query.from(ContextCount.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ContextCount> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}

package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Context;

public class ContextDAO extends GenericDAO<Context> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContextDAO() {
		super(Context.class);
	}

	public void delete(Context obj) {
		super.delete(obj.getId(), Context.class);
	}

	public List<Context> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Context> query = builder.createQuery(Context.class);
			Root<Context> variableRoot = query.from(Context.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Context> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}

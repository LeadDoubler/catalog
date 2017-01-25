/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blob.pas;

import com.asap.catalog.dao.util.PaginatedListImpl;
import org.hibernate.Query;
import util.HibernateUtil;

/**
 *
 * @author Morten
 * Strongly inspired by: http://icoloma.blogspot.com/2007/02/listing-with-displaytag-stripes-and.html
 */
public class BasicController {

         /**
   * Retrieves a paged query. The query can include a sorting order, which would then
   * be appended to the sort indicated by the page object, if any.
   *
   * @param page The page to retrieve, including sorting order
   * @param queryString The query.
   * @param params The query params
   */
  public void query(final PaginatedListImpl page, final String q, final Object... params) {

    // add the order by
    String queryString = page.addOrderBy(q);

    // executes the query
    Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(queryString);
    query.setFirstResult(page.getFirstRecordIndex());
    query.setMaxResults(page.getPageSize());
    setParameters(query, params);
    page.setList(query.list());

    // obtains the total query results
    Query count = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("select count(*) " + q);
    setParameters(count, params);
    page.setTotal(((Number)count.uniqueResult()).intValue());
  }

    /**
   * Sets all the parameters of a query
   */
  private void setParameters(Query query, Object... params) {
    for (int i = 0; i < params.length; i++)
      query.setParameter(i + 1, params[i]);
  }

}

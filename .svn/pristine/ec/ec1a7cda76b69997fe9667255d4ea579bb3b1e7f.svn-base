/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asap.catalog.dao.util;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * Default PaginatedList implementation. 
 * @author icoloma
 */
public class PaginatedListImpl implements PaginatedList {

	/** default page size */
	private static int DEFAULT_PAGE_SIZE = 20;

	/** current page index, starts at 0 */
	private int index;

	/** number of results per page */
	private int pageSize;

	/** total results (records, not pages) */
	private int fullListSize;

	/** list of results in the current page */
	private List list;

	/** default sorting order */
	private SortOrderEnum sortDirection = SortOrderEnum.ASCENDING;

	/** sort criteria (sorting property name) */
	private String sortCriterion;

	/**
	 * For tests: a page as big as possible, which is effectively the same as no paging
	 * @return a page of Integer.MAX_VALUE size
	 */
	public static PaginatedListImpl newHugeResponse(){
		PaginatedListImpl hugeResponse = new PaginatedListImpl();
		hugeResponse.setPageSize(Integer.MAX_VALUE);
		return hugeResponse;
	}

	public PaginatedListImpl() {
		// empty
	}

	/**
	 * Factory-style constructor. Initializes properties with a request attributes
	 */
	public PaginatedListImpl(HttpServletRequest request) {
		sortCriterion = request.getParameter("sort");
		sortDirection = "desc".equals(request.getParameter("dir"))? SortOrderEnum.DESCENDING : SortOrderEnum.ASCENDING;
		pageSize = DEFAULT_PAGE_SIZE;
		String page = request.getParameter("page");
                String pagerId = request.getParameter( "pagerid" );
                
                if ( pagerId != null )
                {
                    if ( page == null )
                    {
                        if ( request.getSession().getAttribute( pagerId ) != null )
                            page = (String) request.getSession().getAttribute( pagerId );
                    }
                    else
                        request.getSession().setAttribute( pagerId, page );
                }
//                boolean debug = true;
//                if ( debug )
//                {
//                    if ( pagerId == null )
//                    {
//                        System.out.println( "request URL : \"" + request.getRequestURL().toString() + "\"" );
//                        System.out.println( "request URI : \"" + request.getRequestURI() + "\"" );
//                        System.out.println( "page : \"" + page + "\"" );
//                        System.out.println( "querystring : \"" + request.getQueryString() + "\"" );
//                    }
//                    else
//                    {
//                        System.out.println( "pagerid : \"" + pagerId + "\" request URL : \"" + request.getRequestURL().toString() + "\"" );
//                        System.out.println( "pagerid : \"" + pagerId + "\" request URI : \"" + request.getRequestURI() + "\"" );
//                        System.out.println( "pagerid : \"" + pagerId + "\" page : \"" + page + "\"" );
//                        System.out.println( "pagerid : \"" + pagerId + "\" querystring : \"" + request.getQueryString() + "\"" );
//                    }
//                    System.out.println();
//                }
                
                if ( page == null )
                    index = 0;
                else
                    index = Integer.parseInt( page ) - 1;
	}

	/**
	 * @return A readable description of this instance state for logs
	 */
	public String toString() {
		return "PageResponse { index = " + index + ", pageSize = " + pageSize + ", total = " + fullListSize + " }";
	}

	public int getFirstRecordIndex() {
		return index * pageSize;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getList() {
		return list;
	}
	public void setList(List results) {
		this.list = results;
	}
	public int getFullListSize() {
		return fullListSize;
	}
	public void setTotal(int total)
        {
            int numberOfPages = ( total - 1 ) / pageSize;
            if ( numberOfPages < index && 0 < index  )
                index--;
            this.fullListSize = total;
	}

	public int getTotalPages() {
		return (int)Math.ceil(((double)fullListSize )/ pageSize);
	}

	public int getObjectsPerPage() {
		return pageSize;
	}

	public int getPageNumber() {
		return index + 1;
	}

	public String getSearchId() {
		// unimplemented
		return null;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public String getSqlSortDirection() {
		return SortOrderEnum.DESCENDING.equals(sortDirection)? "desc" : "asc";
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	/**
	 * Adds the order by parameter to a query. If there is already an
	 * order by, <b>prepends</b> the current order to the one included
	 * in the query
	 */
	public String addOrderBy(String query) {
		// no order by
		if (sortCriterion == null)
			return query;
		int pos = locateOrderBy(query);
		// order by one criteria
		if (pos == -1)
			return query + " order by " + sortCriterion + " " + getSqlSortDirection();
		// order by two criteria
		return query.substring(0, pos) +  sortCriterion + " " + getSqlSortDirection() + ", " + query.substring(pos);
	}

	/**
	 * Locates the "order by" clause, if there is one
	 * @param query the QL query to inspect
	 * @return The position of the first character after the "order by" clause if there is one, -1 if not.
	 */
	private int locateOrderBy(String query) {
		int pos = query.lastIndexOf(" order ");
		if (pos != -1) {
			pos = query.lastIndexOf("by ", pos + 7);
			if (pos != -1)
				return pos + 3;
		}
		return pos;

	}

}

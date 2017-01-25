package com.asap.catalog.dao;

import com.asap.catalog.explorer.Explorable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends Component implements Explorable{
	/*@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
*/
	private String title; 
        
        private String englishtitle;

	private String intro;

	private String description;

	private String stringCategory;
        
        private Boolean deleted = false;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent")
	private Category parent;
 
        public Category(){
        }
        
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
        
	public String getStringCategory() {
		return stringCategory;
	}

	public void setStringCategory(String stringCategory) {
		this.stringCategory = stringCategory;
	}
        
        public String getXml(){
            if ( ! this.getDeleted() ){
                String s = "<category><id>" + getId() + "</id><title>"+title+"</title>";
                s+="<children>";
                if (children != null){
                for (Iterator<Category> iterator = children.iterator(); iterator.hasNext();) {
                    Category category = iterator.next();
                    s+=category.getXml();
                    }
                }
                s+="</children></category>";
                return s; 
            }
            else{
                return "";
            }
        }

    public void initializeChildren() {
        if (getChildren()!=null){
            for (Iterator<Category> iterator = children.iterator(); iterator.hasNext();) {
                Category cat = iterator.next();
                cat.initializeChildren();
            }
        }
        else{
            getId();
        }
    }

    @OneToMany(mappedBy = "parent", fetch=FetchType.EAGER)
    List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
    
    /*@OneToMany(mappedBy = "category", fetch=FetchType.EAGER)
    List<Product> products;*/

   /* public List<Product> getProducts() {
        return HibernateUtil.getSessionFactory().getCurrentSession()
            .createCriteria(Product.class)
            .add(Restrictions.eq("category",this))
            .add(Restrictions.eq("deleted",false))
            .addOrder(Order.asc("title")).list();
    }
    
    public List<Product> getDeletedProducts() {
        if(this.deleted.booleanValue()){
            return HibernateUtil.getSessionFactory().getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.eq("category",this))
                .addOrder(Order.asc("title")).list();
        }else{
            return HibernateUtil.getSessionFactory().getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.eq("category",this))
                .add(Restrictions.eq("deleted",true))
                .addOrder(Order.asc("title")).list();
        }
    }*/

    public boolean getChildless(){
        if(getChildren() != null)
            if(getChildren().size() > 0)
                return false;
        
        return true;
    }

    public String getEnglishtitle() {
        return englishtitle;
    }

    public void setEnglishtitle(String englishtitle) {
        this.englishtitle = englishtitle;
    }
    
    public String toString(){
        if(getId() != null){
            return getId()+"";
        }
        return null;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


}

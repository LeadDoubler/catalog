package com.asap.catalog.extensions.seminar;

import com.asap.catalog.dao.*;
import com.asap.catalog.extensions.seminar.Event;
import com.asap.catalog.extensions.shop.course.dao.Course;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.criterion.Order; 
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import util.HibernateUtil;

@Entity
@Table(name = "term")
public class Term implements java.io.Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "term")
        private List<Event> events;
        
        @ManyToOne//(cascade = { CascadeType.ALL })
	@JoinColumn(name = "teacher")
	private Profile teacher;

	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "course")
	private Course course;

        private boolean deleted;
        
//	@Type(type = "com.asap.core.number.MutableIntegerType")
//	private MutableInteger count;

        public Term(){
            deleted = false;
        }
        
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public event getevent() {
		return event;
	}

	public void setevent(event event) {
		if (this.event != null) {
			int oldCount = this.event.getLocation().getCapacity();

			if (event.getLocation().getCapacity() > oldCount) {
				this.count = new MutableInteger(event.getLocation()
						.getCapacity());
				this.event = event;
			}
			if ((oldCount - count.getValue()) > event.getLocation()
					.getCapacity()) {
				// TODO: Make Exception mechanizm and add here exception
			}
			this.count = new MutableInteger(event.getLocation().getCapacity());
			this.event = event;
		} else {
			this.event = event;
			this.count = new MutableInteger(event.getLocation().getCapacity());
		}

	}*/

	public Profile getTeacher() {
		return teacher;
	}

	public void setTeacher(Profile teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean equals(Object object) {
		if (object instanceof Term) {
			return (((Term) object).getId().equals(getId()) ? true : false);
		}
		return false;
	}

	@Override
	public int hashCode() {
                if(id == null) return 0;
                return getId().intValue();
	}

/*	public MutableInteger getCount() {
		return count;
	}

	public void setCount(MutableInteger count) {
		this.count = count;
	}

	public boolean incrementCount() {
		int overallCount = 99999;//this.event.getLocation().getCapacity();
		if (count.getValue() >= overallCount) {
			return false;
		} else {
			count.increment();
			return true;
		}
	}

	public boolean decrementCount() {
		if (count.getValue() <= 0) {
			return false;
		} else {
			count.decrement();
			return true;
		}
	}

	public boolean incrementCount(int amount) {
		int overallCount = 99999;//this.event.getLocation().getCapacity();
		if (count.getValue() > (overallCount - amount)) {
			return false;
		} else {
			count.add(amount);
			return true;
		}
	}

	public boolean incrementCount(long amount) {
		int overallCount = 9999;//this.event.getLocation().getCapacity();
		if (count.getValue() > (overallCount - amount)) {
			return false;
		} else {
			count.add(amount);
			count.getValue();
			return true;
		}
	}

	public boolean decrementCount(int amount) {

		if (count.getValue() <= amount) {
			return false;
		} else {
			count.subtract(amount);
			return true;
		}
	}

	public boolean decrementCount(long amount) {

		if (count.getValue() <= amount) {
			return false;
		} else {
			count.subtract(amount);
			return true;
		}
	}*/
        
        public String getPeriod() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd"); 
            List<Event> events = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Event.class)
                                .add(Restrictions.eq("term",this))
                                .add(Restrictions.isNotNull("date")).list();
            if(events == null){
                return "";
            }
            if(events.size() == 0){
                return "";
            }
            if(events.size() == 1){
                return sdf.format(events.get(0).getDate())+". "+events.get(0).getLocation().getDepartment().getName();
            }
            else{
                return sdf.format(events.get(0).getDate())+".-"+sdf.format(events.get(events.size()-1).getDate())+". "+events.get(0).getLocation().getDepartment().getName();
            }
        }
        
        /*
        public List<Event> getEvents(){
            return HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Event.class)
                .add(Restrictions.eq("term",this))
                .createAlias("term","term").add(Restrictions.eq("term.deleted",false)).list();
        }
        */
        public boolean hasEvents() {
            return getEvents().size() > 0;
        }
       
        public String toString() {
		return id + "";
	}

        public void setEvents(List<Event> events) {
            this.events = events;
        }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Event> getEvents() {
        return events;
    }
}

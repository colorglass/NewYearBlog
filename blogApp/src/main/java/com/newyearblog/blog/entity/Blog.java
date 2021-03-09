package com.newyearblog.blog.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    private Date updateTime;
    private Boolean isPublished;

    @ManyToOne
    private Type type;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + "}";
    }

    public static Specification<Blog> filter(String title, Long typeId, Long tagId, Boolean isPublished) {
        return new Specification<Blog>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(title) && title != null) {
                    predicates.add(builder.like(root.get("title"), "%" + title + "%"));
                }
                if (typeId != null) {
                    predicates.add(builder.equal(root.get("type").get("id"), typeId));
                }
                if (tagId != null) {
                    Join<Blog, Tag> join = root.join("tags");
                    predicates.add(builder.equal(join.get("id"), tagId));
                }
                if (isPublished) {
                    predicates.add(builder.equal(root.get("isPublished"), isPublished));
                }
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        };
    }

}

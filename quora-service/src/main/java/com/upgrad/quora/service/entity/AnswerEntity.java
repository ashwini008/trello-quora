package com.upgrad.quora.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;

//POJO class for answer table
//@Entity annotation specifies that the class is an entity and is mapped to a database table.
@Entity
// @Table annotation specifies that the entity is mapped to the specified(name = "user_auth") table in the database
@Table(name = "answer")
//@NamedQueries JPA annotation Specifies a static, named query
@NamedQueries({
        @NamedQuery(
                name = "getAnswerByUuid",
                query = "select a from AnswerEntity a where a.uuid=:uuid"),
        @NamedQuery(name = "getAllAnswersToQuestion", query = "select a from AnswerEntity a where a.uuid = :uuid")
})
public class AnswerEntity implements Serializable {
    //@Id annotation is used to specify the identifier property of the entity
    @Id
    // @Column annotation is used to specify the details of the column to which a field or property will be mapped.
    @Column(name = "id")
    // The @GeneratedValue annotation is used to specify the primary key generation strategy to use and strategy defined here is "IDENTITY"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    // @Size annotation is used to to validate the size of a field
    @Size(max = 200)
    // The @NotNull Annotation is an explicit contract declaring that the variable cannot or should not hold null value
    @NotNull
    private String uuid;

    @Column(name = "ans")
    @Size(max = 255)
    @NotNull
    private String answer;

    @Column(name = "date")
    @NotNull
    private ZonedDateTime date;

    // @ManyToOne annotation is used here to specify that this entity has ManyToOne cardinality with userEntity
    @ManyToOne
    // @OnDelete annotation is used in JPA to specify the foreign key attribute in the Java class for DELETE CASCADE option.
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JoinColumn annotation marks a column for as a join column for an entity association or an element collection.
    // It contains the reference of another table
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    // @ManyToOne annotation is used here to specify that this entity has ManyToOne cardinality with questionEntity
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}

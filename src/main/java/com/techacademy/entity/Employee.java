package com.techacademy.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
@Where(clause = "delete_flag = 0")
public class Employee {


    /**　主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**　名前。２０桁。null不許可　*/
    @Column(length = 20, nullable = false)
    @NotEmpty
    @Length(max=20)
    private String name;

    /**　削除フラグ　*/
    private Integer delete_flag;

    /** 登録日時　*/
    private LocalDateTime createdAt;

    /** 更新日時　*/
    private LocalDateTime updatedAt;


    @OneToOne(mappedBy = "emp", cascade = CascadeType.ALL)
    private Authentication authentication;

    @Valid
    @OneToMany(mappedBy = "emp", cascade = CascadeType.ALL)
    private List<Report> reports;

}

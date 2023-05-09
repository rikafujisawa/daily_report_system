package com.techacademy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "report")
public class Report {

    /**　主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**　日報の日付　*/
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    /** タイトル　*/
    @Column(length = 255)
    private String title;

    /** 内容　*/
    @Column(nullable = false)
    @Type(type="text")
    private String content;

    /** 従業員テーブルのid　*/
    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee emp;


    /** 登録日時　*/
    private LocalDateTime createdAt;

    /** 更新日時　*/
    private LocalDateTime updatedAt;

}




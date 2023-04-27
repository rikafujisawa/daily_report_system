package  com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 性別用の列挙型 */
    public static enum Role {
        一般,管理者
    }

    /** 主キー。 */
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード。255桁。null不許可 */
    @Column(length = 255, nullable = false)
    @NotEmpty
    @Length(max=255)
    private String password;

    /** 権限。3桁。null不許可 */
    @Column(length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;


    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee emp;
}

package ems.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "leave_tbl")
@NoArgsConstructor
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @NotNull
    private Integer noOfDays;

    private String approvalStatus;
    private String autoDeducted;

    private String teamId;

    public Leave(long id) {
        this.id = id;
    }

    public Leave(LocalDate startDate, Integer noOfDays, Employee employee) {
        this(startDate, noOfDays, employee, "Y");
    }

    public Leave(LocalDate startDate, Integer noOfDays, Employee employee, String autoDeducted) {
        this.noOfDays = noOfDays;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(noOfDays);
        this.employee = employee;
        this.autoDeducted = autoDeducted;
        this.approvalStatus = "P";
        this.teamId = employee.getTeamId();
    }
}
package com.starbucks.back.agreement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.agreement.domain.converter.AgreementTypeConverter;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.common.entity.BaseEntity;
import com.starbucks.back.user.domain.converter.UserGenderConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "required", nullable = false)
    private boolean required;

    @Convert(converter = AgreementTypeConverter.class)
    @Column(name = "type", nullable = false)
    private AgreementType type;

    @Builder
    public Agreement(Long id, String name, String description, boolean required) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.required = required;
    }



}

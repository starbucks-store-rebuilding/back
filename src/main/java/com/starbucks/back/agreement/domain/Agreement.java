package com.starbucks.back.agreement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.agreement.domain.converter.AgreementTypeConverter;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.common.entity.BaseEntity;
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

    @Lob
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @Convert(converter = AgreementTypeConverter.class)
    @Column(name = "type", nullable = false)
    private AgreementType type;

    @Builder
    public Agreement(Long id, String name, String description, boolean isRequired) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
    }



}

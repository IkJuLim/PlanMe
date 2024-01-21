package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import com.planme.main.domain.mapping.MeContent;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emoticon;

    private String color;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean meStoryHidden;    //  0: 숨기지 않음, 1: 숨김

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<MeContent> meContentList = new ArrayList<>();
}

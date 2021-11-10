package com.jdrake.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //공통 매핑 정보 상속시킬때
@EntityListeners(AuditingEntityListener.class) //auditing 기능을 쓰겠다 하는 의미
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성되어 저장될 때 시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity 값을 변경할 때 시간이 자동 저장됨.
    private LocalDateTime modifiedDate;
}

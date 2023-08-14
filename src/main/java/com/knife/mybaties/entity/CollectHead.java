package com.knife.mybaties.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类。
 * @author 86151
 * @since 2023-07-31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "collect_head")
public class CollectHead implements Serializable {

    /**
     * 序号
     */
    @Id(keyType=KeyType.Auto)
    private Integer collectId;

    /**
     * 用户主号码
     */
    private String primaryPhone;

    /**
     * 一证多查总数
     */
    private Integer totalPhoneNumber;

    /**
     * 收集人
     */
    private String collectUserId;

    /**
     * 收集时间
     */
    private LocalDateTime collectTime;

}

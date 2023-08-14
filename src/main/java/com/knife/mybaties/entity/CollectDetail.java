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
 *  实体类。
 *
 * @author 86151
 * @since 2023-08-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "collect_detail")
public class CollectDetail implements Serializable {

    /**
     * 明细序号
     */
    @Id(keyType = KeyType.Auto)
    private Integer detailId;

    /**
     * 收集序号
     */
    private Integer collectId;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 运营商
     */
    private String isp;

    /**
     * 主号码标志
     */
    private Boolean isPrimary;

    /**
     * 合约到期时间
     */
    private LocalDateTime contractExpiration;

}
